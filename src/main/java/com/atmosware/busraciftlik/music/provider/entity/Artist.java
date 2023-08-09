package com.atmosware.busraciftlik.music.provider.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;
@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artists")
@SQLDelete(sql = "UPDATE artists SET status = 'INACTIVE' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status <> 'INACTIVE'")
public class Artist extends BaseEntity {
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    @Builder.Default
    @Where(clause = "status <> 'INACTIVE'")
    private Set<Music> musics = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    @Builder.Default
    @Where(clause = "status <> 'INACTIVE'")
    private Set<Album> albums = new HashSet<>();

    public void addToMusics(Music music){
        music.setArtist(this);
        musics.add(music);
    }

    public void addToAlbums(Album album){
        album.setArtist(this);
        albums.add(album);
    }

    /*@PreRemove
    public void deleteArtist(){
        musics.forEach(music -> music.setStatus(Status.INACTIVE));
        albums.forEach(album -> album.setStatus(Status.INACTIVE));
    }*/

}