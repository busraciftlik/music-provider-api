package com.atmosware.busraciftlik.music.provider.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistNameRequest {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
}
