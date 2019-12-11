package com.library.mapper;

import com.library.domain.readers.Reader;
import com.library.domain.readers.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(readerDto.getFirstName(), readerDto.getLastName());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(reader.getId(), reader.getFirstName(), reader.getLastName(), reader.getAccountCreatedDate());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> titleList) {
        return titleList.stream()
                .map(t -> new ReaderDto(t.getId(), t.getFirstName(), t.getLastName(), t.getAccountCreatedDate()))
                .collect(Collectors.toList());
    }
}
