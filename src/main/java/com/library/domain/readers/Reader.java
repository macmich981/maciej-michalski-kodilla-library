package com.library.domain.readers;

import com.library.domain.rents.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "READERS")
public class Reader {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date accountCreatedDate;

    @OneToMany(
        mappedBy = "reader",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        accountCreatedDate = new Date();
    }
}
