package com.atmosware.busraciftlik.music.provider.entity;

import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musics")
public class Music extends BaseEntity {
    private String name;
    @ManyToOne
    private Artist artist;
    @JsonBackReference
    @ManyToOne
    private Album album;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
