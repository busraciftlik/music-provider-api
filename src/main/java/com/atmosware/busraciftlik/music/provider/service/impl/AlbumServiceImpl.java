package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.repository.AlbumRepository;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private  final AlbumRepository repository;
    @Override
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public Album add(Album album) {
        return repository.save(album);
    }

    @Override
    public Album update(Album album) {
        Album exists = repository.findById(album.getId()).orElseThrow();
        exists.setName(album.getName());
        if(Objects.nonNull(album.getMusics())){
            exists.setMusics(album.getMusics());
        }
        if(Objects.nonNull(album.getArtist())){
            exists.setArtist(album.getArtist());
        }
        exists.setReleaseDate(album.getReleaseDate());
        return null;
    }

    @Override
    public Album delete(Integer id) {
        Album album = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return album;
    }
}
