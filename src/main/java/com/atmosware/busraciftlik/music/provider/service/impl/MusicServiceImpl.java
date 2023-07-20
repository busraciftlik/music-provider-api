package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.repository.MusicRepository;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    private final MusicRepository repository;

    @Override
    public List<MusicDto> findAll() {
        List<Music> musics = repository.findAll();
        return musics.stream().map(music -> MusicDto.builder()
                .id(music.getId())
                .name(music.getName())
                .artistName(music.getArtist().getName())
                .albumName(music.getAlbum().getName())
                .genre(music.getGenre()).build()).collect(Collectors.toList());
    }

    @Override
    public Music add(Music request) {

        return repository.save(request);
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
}
