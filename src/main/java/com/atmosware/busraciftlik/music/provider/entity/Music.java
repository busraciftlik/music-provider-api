package com.atmosware.busraciftlik.music.provider.entity;

import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
    @ManyToOne
    private Artist artist;
    @JsonBackReference
    @ManyToOne ///(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Album album;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}