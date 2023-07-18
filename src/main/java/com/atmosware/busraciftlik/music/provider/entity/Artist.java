package com.atmosware.busraciftlik.music.provider.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artists")
public class Artist extends BaseEntity  {
    private String name;
    @OneToMany
    @Schema(nullable = true)
    private Set<Music> musics;
    @OneToMany
    private Set<Album> albums;

}
