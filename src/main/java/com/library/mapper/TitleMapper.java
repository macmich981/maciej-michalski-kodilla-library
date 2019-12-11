package com.library.mapper;

import com.library.domain.titles.Title;
import com.library.domain.titles.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {
    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationYear());
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationYear());
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(t -> new TitleDto(t.getId(), t.getTitle(), t.getAuthor(), t.getPublicationYear()))
                .collect(Collectors.toList());
    }
}
