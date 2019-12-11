package com.library.mapper;

import com.library.domain.copies.Copy;
import com.library.domain.copies.CopyDto;
import com.library.domain.copies.State;
import com.library.domain.titles.Title;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    public Copy mapToCopy(final Title title) {
        Copy copy = new Copy(State.AVAILABLE);
        copy.setTitle(title);
        title.getCopies().add(copy);
        return copy;
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(copy.getId(), copy.getTitle().getId(), copy.getState());
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(t -> new CopyDto(t.getId(), t.getTitle().getId(), t.getState()))
                .collect(Collectors.toList());
    }
}
