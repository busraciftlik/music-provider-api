package com.atmosware.busraciftlik.music.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMusicLikeDto {
    private Long id;
    private Long userId;
    private Long musicId;
}
