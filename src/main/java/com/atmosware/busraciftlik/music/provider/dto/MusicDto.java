package com.atmosware.busraciftlik.music.provider.dto;

import com.atmosware.busraciftlik.music.provider.enums.Genre;
import lombok.Builder;

import java.util.Set;

@Builder
public record MusicDto (Integer id, String name, Set<String> albums, Genre genre){

}
