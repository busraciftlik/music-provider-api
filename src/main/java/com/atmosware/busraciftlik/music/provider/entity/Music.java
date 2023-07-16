package com.atmosware.busraciftlik.music.provider.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musıcs")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Hidden
    private UUID id;
    private String name;
    @ManyToOne
    private Artist artist;
    @ManyToOne
    private Album album;
}
