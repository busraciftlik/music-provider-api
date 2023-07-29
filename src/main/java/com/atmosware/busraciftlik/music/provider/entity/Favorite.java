package com.atmosware.busraciftlik.music.provider.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorites")
public class Favorite extends BaseEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Music music;
}
