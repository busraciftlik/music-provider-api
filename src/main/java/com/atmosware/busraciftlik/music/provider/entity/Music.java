package com.atmosware.busraciftlik.music.provider.entity;

import com.atmosware.busraciftlik.music.provider.enums.Genre;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musics")
public class Music extends BaseEntity {
    private String name;
    @ManyToOne
    private Artist artist;
    @ManyToOne
    private Album album;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
