package com.atmosware.busraciftlik.music.provider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Integer id;
    private Integer userId;
    private Integer musicId;
}
