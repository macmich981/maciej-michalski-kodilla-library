package com.library.domain.rents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {
    private Long id;
    private Date rentDate;
    private Date returnDate;
}
