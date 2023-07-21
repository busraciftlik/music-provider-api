package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateArtistRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateArtistNameRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.repository.ArtistRepository;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository repository;

    @Override
    public Set<ArtistDto> findAll() {
        return mapArtistEntity2ArtistDto(repository.findAll());
    }

    @Override
    public ArtistDto add(CreateArtistRequest request) {
        Artist saved = repository.save(Artist.builder().name(request.getName()).build());
        return mapArtistEntity2ArtistDto(saved);
    }

    @Override
    public Artist update(Artist artist) {

        return null;
    }

    @Override
    public ArtistDto delete(Integer id) {
        Artist artist = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return mapArtistEntity2ArtistDto(artist);
    }

    @Override
    public Set<Music> addMusic(Integer artistId, Music music) {
        return null;
    }

    @Override
    public Set<Album> addAlbum(Integer artistId, Album album) {
        return null;
    }

    @Override
    public ArtistDto updateArtistName(Integer id, UpdateArtistNameRequest request) {
        Artist artist = repository.findById(id).orElseThrow();
        artist.setName(request.getName());
        Artist saved = repository.save(artist);
        return mapArtistEntity2ArtistDto(saved);
    }
}
