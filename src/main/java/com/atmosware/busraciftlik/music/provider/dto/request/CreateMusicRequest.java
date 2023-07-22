package com.atmosware.busraciftlik.music.provider.dto.request;

import com.atmosware.busraciftlik.music.provider.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicRequest {
    private String name;
    private Genre genre;
    private Integer artistId;
}
