package com.cybertek.entity;

import com.cybertek.enums.MovieState;
import com.cybertek.enums.MovieType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie extends BaseEntity {
    private String name;

    @Column(columnDefinition = "DATE") //Jave 8
    private LocalDate releaseDate;

    private Integer duration;

    @Column(columnDefinition = "text") //sets no limitation in character limit
    private String summary;

    @Enumerated(EnumType.STRING) //if no annotation it will be ORDINAL
    private MovieType type;
    @Enumerated(EnumType.STRING)//if no annotation it will be ORDINAL
    private MovieState state;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "movie_genre_rel",
            joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList = new ArrayList<>(); //Set will be better, but for this lab using List

    public Movie(String name, LocalDate releaseDate, Integer duration,
                 MovieType type, MovieState state, BigDecimal price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.summary = summary;
        this.type = type;
        this.state = state;
        this.price = price;
    }
}
