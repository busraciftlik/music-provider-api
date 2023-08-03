package com.atmosware.busraciftlik.music.provider.dto;

import lombok.*;

import java.io.Serializable;


@Builder
public record FavoriteDto(Integer id, Integer userId, Integer musicId) implements Serializable {

}
