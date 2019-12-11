package com.library.service;

import com.library.domain.copies.Copy;
import com.library.domain.copies.State;
import com.library.domain.readers.Reader;
import com.library.domain.rents.Rent;
import com.library.domain.titles.Title;
import com.library.repository.CopyRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.RentRepository;
import com.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private RentRepository rentRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReader(final Long id) {
        return readerRepository.findById(id);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Optional<Title> getTitle(final Long id) {
        return titleRepository.findById(id);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Optional<Copy> getCopy(final Long id) {
        return copyRepository.findById(id);
    }

    public Copy saveCopy(final Copy copy) {
        return copyRepository.save(copy);
    }

    public int getAllCopiesWithStateAvailable(final Long id) {
        return copyRepository.retrieveTitlesWithStatus(State.AVAILABLE.name(), id);
    }

    public Optional<List<Copy>> findAvailableTitleCopy(final Long titleId) {
        return copyRepository.findByStateAndTitleId(State.AVAILABLE.name(), titleId);
    }

    public Rent saveRent(final Rent rent) {
        return rentRepository.save(rent);
    }

    public Optional<Rent> getRent(final Long rentId) {
        return rentRepository.getRentById(rentId);
    }
}
