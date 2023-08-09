package com.atmosware.busraciftlik.music.provider.entity;

import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
@Table(name = "musics")
@SQLDelete(sql = "UPDATE musics SET status = 'INACTIVE' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status <> 'INACTIVE'")
public class Music extends BaseEntity {
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @Where(clause = "status <> 'INACTIVE'")
    private Artist artist;
    @JsonBackReference
    @ManyToOne ///(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Where(clause = "status <> 'INACTIVE'")
    private Album album;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToMany
    private Set<Favorite> favorites = new HashSet<>();


}