package com.atmosware.busraciftlik.music.provider.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
@SQLDelete(sql = "UPDATE albums SET status = 'INACTIVE' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status <> 'INACTIVE'")
public class Album extends BaseEntity {
    private String name;
    private LocalDate releaseDate;
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @Builder.Default
    @Where(clause = "status <> 'INACTIVE'")
    private Set<Music> musics = new HashSet<>();
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @Where(clause = "status <> 'INACTIVE'")
    private Artist artist;

    public void addToMusics(Music music) {
        music.setAlbum(this);
        musics.add(music);
    }

}