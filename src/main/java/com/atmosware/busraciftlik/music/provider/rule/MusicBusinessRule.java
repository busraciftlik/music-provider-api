package com.atmosware.busraciftlik.music.provider.rule;

import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.repository.MusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class MusicBusinessRule {
    private final MusicRepository repository;

    public void checkIfMusicExists(Integer id){
        if(!repository.existsById(id)){
            throw new BusinessException("");
        }
    }
}
