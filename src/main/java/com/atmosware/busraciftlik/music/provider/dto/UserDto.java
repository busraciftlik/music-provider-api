package com.atmosware.busraciftlik.music.provider.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserDto (Integer id, String firstName, String lastName) implements Serializable {
}
