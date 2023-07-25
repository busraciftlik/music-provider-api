package com.atmosware.busraciftlik.music.provider.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistNameRequest {
    @NotBlank
    private String name;
}
