package com.atmosware.busraciftlik.music.provider.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAlbumRequest {
    private String name;
    private List<Integer> musicIds;
    private Integer artistId;

}
