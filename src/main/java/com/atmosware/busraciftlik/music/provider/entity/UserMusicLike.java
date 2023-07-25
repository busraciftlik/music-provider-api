package com.atmosware.busraciftlik.music.provider.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_music_likes")
public class UserMusicLike extends BaseEntity {
    private Integer user;
    @OneToOne
    private Music music;
}
