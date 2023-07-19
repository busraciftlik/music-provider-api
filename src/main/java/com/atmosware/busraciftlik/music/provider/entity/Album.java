package com.atmosware.busraciftlik.music.provider.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
public class Album extends BaseEntity {
    private String name;
    private LocalDate releaseDate;
    @JsonManagedReference
    @OneToMany
    private Set<Music> musics;
    @ManyToOne
    private Artist artist;

}
