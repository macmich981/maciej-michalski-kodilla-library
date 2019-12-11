package com.library.domain.copies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CopyDto {
    private Long id;
    private Long titleId;
    private String state;
}
