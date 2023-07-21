package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.repository.AlbumRepository;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository repository;


    @Override
    public Set<AlbumDto> findAll() {
        return mapAlbumEntity2AlbumDto(repository.findAll());
    }

    @Override
    public AlbumDto add(CreateAlbumRequest request) {
        Album album = Album.builder().name(request.getName()).build();
        repository.save(album);
        return mapAlbumEntity2AlbumDto(album);
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
