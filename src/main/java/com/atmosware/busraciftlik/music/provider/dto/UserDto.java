package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

@Builder
public record UserDto (Integer id, String firstName, String lastName){
}
