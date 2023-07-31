package com.atmosware.busraciftlik.music.provider.dto.request;

import com.atmosware.busraciftlik.music.provider.util.constant.Regex;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Pattern(regexp = Regex.EMAIL)
    private String email;
    @Pattern(regexp = Regex.PASSWORD)
    private String password;

}