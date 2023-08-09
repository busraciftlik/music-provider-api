package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateArtistRequest;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.repository.ArtistRepository;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapArtistEntity2ArtistDto;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository repository;
    private AlbumService albumService;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Cacheable(value = "artist_set")
    public Set<ArtistDto> findAll() {
        return mapArtistEntity2ArtistDto(repository.findAll());
    }

    @Override
    public Artist getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(Message.Artist.NOT_EXISTS));
    }

    @Override
    @CacheEvict(value = "artist_set", allEntries = true)
    public ArtistDto add(CreateArtistRequest request) {
        Artist saved = repository.save(Artist.builder().name(request.getName()).build());
        return mapArtistEntity2ArtistDto(saved);
    }

    @Override
    @CacheEvict(value = "artist_set", keyGenerator = "customKeyGenerator")
    public ArtistDto update(Integer id, Artist artist) {
        Artist exists = repository.findById(artist.getId()).orElseThrow();
        exists.setName(artist.getName());
        exists.setAlbums(artist.getAlbums());
        exists.setMusics(artist.getMusics());
        Artist saved = repository.save(exists);
        return mapArtistEntity2ArtistDto(saved);
    }

    @Override
    @CacheEvict(value = {"artist_set","album_set","music_set"}, keyGenerator = "customKeyGenerator")
    public ArtistDto delete(Integer id) {
        Artist artist = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return mapArtistEntity2ArtistDto(artist);
    }

    @Autowired
    public void setAlbumService(@Lazy AlbumService albumService) {
        this.albumService = albumService;
    }

    @Transactional
    public void saveAllForJpaRepository(List<Artist> artists) {
        repository.saveAll(artists);
    }
}
