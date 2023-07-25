package com.atmosware.busraciftlik.music.provider.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlists")
public class Playlist extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Music> musics;
}
