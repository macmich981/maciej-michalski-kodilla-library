package com.library.domain.titles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
}
