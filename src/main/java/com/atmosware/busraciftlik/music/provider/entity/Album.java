package com.atmosware.busraciftlik.music.provider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
public class Album extends BaseEntity {
    private String name;
    private LocalDate releaseDate;
    @OneToMany
    private Set<Music> musics;
    @ManyToOne
    private Artist artist;

}
