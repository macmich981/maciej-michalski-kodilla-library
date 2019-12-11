package com.library.domain.rents;

import com.library.domain.copies.Copy;
import com.library.domain.readers.Reader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENTS")
public class Rent {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "COPY_ID")
    private Copy copy;

    @NotNull
    @Column(name = "RENT_DATE")
    private Date rentDate;

    @NotNull
    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public Rent(Date rentDate, Date returnDate) {
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
