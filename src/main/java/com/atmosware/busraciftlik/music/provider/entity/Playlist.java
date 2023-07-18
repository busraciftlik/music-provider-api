package com.atmosware.busraciftlik.music.provider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlists")
public class Playlist extends BaseEntity {
    @OneToMany
    private Set<Music> musics;
}
