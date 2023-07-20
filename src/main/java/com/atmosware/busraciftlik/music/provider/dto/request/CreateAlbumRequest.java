package com.atmosware.busraciftlik.music.provider.dto.request;


import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlbumRequest {
    private String name;
    private Set<MusicDto> musics;
}
