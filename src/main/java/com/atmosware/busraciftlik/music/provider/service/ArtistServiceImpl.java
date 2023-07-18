package com.atmosware.busraciftlik.music.provider.service;

import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository repository;

    @Override
    public List<Artist> findAll() {
        return repository.findAll();
    }

    @Override
    public Artist add(Artist artist) {
        return repository.save(artist);
    }

    @Override
    public Artist update(Artist artist) {
        Artist exists = repository.findById(artist.getId()).orElseThrow();
        exists.setName(artist.getName());
        if (Objects.nonNull(artist.getAlbums())) {
            exists.setAlbums(artist.getAlbums());
        }
        if (Objects.nonNull(artist.getMusics())) {
            exists.setMusics(artist.getMusics());
        }
        return repository.save(exists);
    }

    @Override
    public Artist delete(Integer id) {
//        public void checkIfExistsById(id) {
//            if (!repository.existsById(id)) {
//                throw new RuntimeException();
//            }
//        }
        Artist artist = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return artist;
    }

    @Override
    public Set<Music> addMusic(Integer artistId, Music music) {
        Artist artist = repository.findById(artistId).orElseThrow();
        Set<Music> musics = artist.getMusics();
        musics.add(music);
        repository.save(artist);
        return musics;
    }

    @Override
    public Set<Album> addAlbum(Integer artistId, Album album) {
        Artist artist = repository.findById(artistId).orElseThrow();
        Set<Album> albums = artist.getAlbums();
        albums.add(album);
        repository.save(artist);
        return albums;
    }
}
