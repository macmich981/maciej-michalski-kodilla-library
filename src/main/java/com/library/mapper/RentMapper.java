package com.library.mapper;

import com.library.domain.copies.Copy;
import com.library.domain.readers.Reader;
import com.library.domain.rents.Rent;
import com.library.domain.rents.RentDto;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class RentMapper {

    public Rent mapToRent(final Reader reader, final Copy copy) {
        Date dateOfRent = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfRent);
        calendar.add(Calendar.DATE, 14);

        Rent rent = new Rent(dateOfRent, calendar.getTime());
        rent.setReader(reader);
        rent.setCopy(copy);
        reader.getRents().add(rent);
        copy.getRents().add(rent);

        return rent;
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(rent.getId(), rent.getRentDate(), rent.getReturnDate());
    }
}
