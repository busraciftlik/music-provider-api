package com.atmosware.busraciftlik.music.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Integer id;
    private Integer userId;
    private Integer musicId;
}
