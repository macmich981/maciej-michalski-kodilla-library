package com.library.domain.copies;

import com.library.domain.rents.Rent;
import com.library.domain.titles.Title;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Copy.retrieveTitlesWithStatus",
        query = "select count(*) from copies c, titles t where t.id = c.title_id and c.state=:copy_state and t.id=:title_id")

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Copies")
public class Copy {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    private String state;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @OneToMany(
        mappedBy = "copy",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public Copy(State state) {
        this.state = state.name();
    }
}
