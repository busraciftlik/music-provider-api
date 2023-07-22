package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.repository.AlbumRepository;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository repository;
    private final MusicService musicService;


    @Override
    public Set<AlbumDto> findAll() {
        return mapAlbumEntity2AlbumDto(repository.findAll());
    }
    @Override
    public Album findById(Integer id){
       return repository.findById(id).orElseThrow();
    }
    @Override
    public AlbumDto add(CreateAlbumRequest request) {
        Set<Music> musics = musicService.findAllByIds(request.getMusicIds()); //atılan istek ile gelen sonuclar karşılaştırılmalı
        Album saved = repository.save(Album.builder().name(request.getName()).build());
        return mapAlbumEntity2AlbumDto(saved);
    }

    @Override
    public Album update(Album album) {

        return null;
    }

    @Override
    public Album delete(Integer id) {
        Album album = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return album;
    }
}
