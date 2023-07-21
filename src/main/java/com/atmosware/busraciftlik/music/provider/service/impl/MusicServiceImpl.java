package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.repository.MusicRepository;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    private final MusicRepository repository;

    @Override
    public Set<MusicDto> findAll() {
        return mapMusicEntity2MusicDto(repository.findAll());
    }

    @Override
    public MusicDto add(CreateMusicRequest request) {
        return MusicDto.builder()
                .name(request.getName())
                .genre(request.getGenre()).build();

    }

    @Override
    public Music update(Music music) {
        Music exists = repository.findById(music.getId()).orElseThrow();
        exists.setName(music.getName());
        exists.setAlbum(music.getAlbum());
        exists.setArtist(music.getArtist());
        return repository.save(exists);
    }

    @Override
    public Music delete(Integer id) {
        Music music = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return music;
    }

    @Override
    public Set<MusicDto> addAlbum(Integer musicId, Album album) {

        return null;
    }

}
