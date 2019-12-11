package com.library.domain.titles;

import com.library.domain.copies.Copy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Titles")
public class Title {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @NotNull
    @Column
    private int publicationYear;

    @OneToMany(
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Copy> copies = new ArrayList<>();

    public Title (String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
