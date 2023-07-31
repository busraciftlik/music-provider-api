package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.AlbumRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateArtistRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateArtistNameRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import com.atmosware.busraciftlik.music.provider.repository.ArtistRepository;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;

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
    public Set<ArtistDto> findAll() {
        return mapArtistEntity2ArtistDto(repository.findAll());
    }

    @Override
    public Artist getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }


    @Override
    public ArtistDto add(CreateArtistRequest request) {
        Artist saved = repository.save(Artist.builder().name(request.getName()).build());
        return mapArtistEntity2ArtistDto(saved);
    }

    @Override
    public ArtistDto update(Integer id,Artist artist) {
        Artist exists = repository.findById(artist.getId()).orElseThrow();
        exists.setName(artist.getName());
        exists.setAlbums(artist.getAlbums());
        exists.setMusics(artist.getMusics());
        Artist saved = repository.save(exists);
        return mapArtistEntity2ArtistDto(saved);
    }

    @Override
    public ArtistDto delete(Integer id) {
        Artist artist = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return mapArtistEntity2ArtistDto(artist);
    }

    @Override
    public Set<MusicDto> addMusic(Integer artistId, Music music) {
        Artist artist = repository.findById(artistId).orElseThrow();
        Set<Music> musics = artist.getMusics();
        musics.add(music);
        Artist saved = repository.save(artist);
        return mapMusicEntity2MusicDto(saved.getMusics());
    }

    @Override
    // TODO: 1.08.2023  
    public Set<AlbumDto> addAlbum(Integer artistId, AlbumRequest request) {
        Artist artist = repository.findById(artistId).orElseThrow(()->new BusinessException(Message.Artist.NOT_EXISTS));
        Album album = Album.builder().name(request.getName()).build();
        Set<Album> albums = artist.getAlbums();
        albums.add(album);
        Artist saved = repository.save(artist);
        return mapAlbumEntity2AlbumDto(saved.getAlbums());
    }

    @Override
    public ArtistDto updateArtistName(Integer id, UpdateArtistNameRequest request) {
        Artist artist = repository.findById(id).orElseThrow();
        artist.setName(request.getName());
        Artist saved = repository.save(artist);
        return mapArtistEntity2ArtistDto(saved);
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
