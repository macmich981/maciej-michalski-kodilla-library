package com.library.controller;

import com.library.domain.copies.Copy;
import com.library.domain.copies.CopyDto;
import com.library.domain.copies.State;
import com.library.domain.readers.Reader;
import com.library.domain.readers.ReaderDto;
import com.library.domain.rents.Rent;
import com.library.domain.rents.RentDto;
import com.library.domain.titles.Title;
import com.library.domain.titles.TitleDto;
import com.library.mapper.CopyMapper;
import com.library.mapper.ReaderMapper;
import com.library.mapper.RentMapper;
import com.library.mapper.TitleMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {
    @Autowired
    private DbService service;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private CopyMapper copyMapper;

    @Autowired
    private RentMapper rentMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/readers/addReader", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        service.saveReader(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readers/getAllReaders")
    public List<ReaderDto> getAllReaders() {
        return readerMapper.mapToReaderDtoList(service.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readers/getReader")
    public ReaderDto getReader(@RequestParam Long readerId) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(service.getReader(readerId).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles/getAllTitles")
    public List<TitleDto> getAllTitles() {
        return titleMapper.mapToTitleDtoList(service.getAllTitles());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles/getTitle")
    public TitleDto getTitle(@RequestParam Long titleId) throws TitleNotFoundException {
        return titleMapper.mapToTitleDto(service.getTitle(titleId).orElseThrow(TitleNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/titles/addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        service.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/getAllCopies")
    public List<CopyDto> getAllCopies() {
        return copyMapper.mapToCopyDtoList(service.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/getCopy")
    public CopyDto getCopy(@RequestParam Long copyId) throws CopyNotFoundException {
        return copyMapper.mapToCopyDto(service.getCopy(copyId).orElseThrow(CopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copies/{titleId}")
    public void addCopy(@PathVariable Long titleId) throws TitleNotFoundException {
        Title title = service.getTitle(titleId).orElseThrow(TitleNotFoundException::new);
        service.saveCopy(copyMapper.mapToCopy(title));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/copies/updateCopyState")
    public void updateCopyState(@RequestBody CopyDto copyDto) throws CopyNotFoundException {
        Copy copy = service.getCopy(copyDto.getId()).orElseThrow(CopyNotFoundException::new);
        copy.setState(copyDto.getState());
        service.saveCopy(copy);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/{titleId}")
    public int getQuantityOfCopiesAvailableToRent(@PathVariable Long titleId) {
        return service.getAllCopiesWithStateAvailable(titleId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rents/copies/{titleId}/readers/{readerId}")
    public RentDto rentCopy(@PathVariable Long titleId, @PathVariable Long readerId) throws ReaderNotFoundException, CopyNotFoundException {
        Reader reader = service.getReader(readerId).orElseThrow(ReaderNotFoundException::new);
        List<Copy> copies = service.findAvailableTitleCopy(titleId).orElseThrow(CopyNotFoundException::new);
        copies.get(0).setState(State.RENTED.name());
        service.saveCopy(copies.get(0));

        return rentMapper.mapToRentDto(service.saveRent(rentMapper.mapToRent(reader, copies.get(0))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rents/{rentId}")
    public RentDto getRentById(@PathVariable Long rentId) throws RentNotFoundException{
        return rentMapper.mapToRentDto(service.getRent(rentId).orElseThrow(RentNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rents/{rentId}")
    public RentDto returnCopy(@PathVariable Long rentId) throws RentNotFoundException{
        Rent rent = service.getRent(rentId).orElseThrow(RentNotFoundException::new);
        rent.setReturnDate(new Date());
        rent.getCopy().setState(State.AVAILABLE.name());
        return rentMapper.mapToRentDto(service.saveRent(rent));
    }
}