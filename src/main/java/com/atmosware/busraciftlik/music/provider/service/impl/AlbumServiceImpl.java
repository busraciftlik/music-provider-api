package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import com.atmosware.busraciftlik.music.provider.repository.AlbumRepository;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapAlbumEntity2AlbumDto;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository repository;
    private final MusicService musicService;
    private final ArtistService artistService;


    @Override
    @Cacheable(value = "album_set")
    public Set<AlbumDto> findAll() {
        return mapAlbumEntity2AlbumDto(repository.findAll());
    }

    @Override
    public Album findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @CacheEvict(value = "album_set",allEntries = true)
    // TODO: 5.08.2023 -> veritabanındaki tüm ilişkiler kurulmasına rağmen artist ve müziğe albüm eklenmiyor 
    public AlbumDto add(CreateAlbumRequest request) {
        Set<Music> musics = musicService.findAllByIdsAndArtistId(request.getMusicIds(), request.getArtistId());
        if (musics.size() != request.getMusicIds().size()) {
            throw new BusinessException(Message.Album.INVALID_REQUEST);
        }
        Artist artist = artistService.getById(request.getArtistId());
        final Album album = Album.builder()
                .name(request.getName())
                .releaseDate(LocalDate.now())
                .build();
        musics.forEach(album::addToMusics);
        musics.forEach(music -> musicService.update(music.getId(),music));
        artist.addToAlbums(album);
        artistService.update(artist.getId(),artist);
        return mapAlbumEntity2AlbumDto(album);
    }

    @Override
    @CacheEvict(value = "album_set", keyGenerator = "customKeyGenerator")
    public AlbumDto update(Integer id, UpdateAlbumRequest request) {
        Album album = repository.findById(id).orElseThrow(()-> new BusinessException(Message.Album.NOT_EXISTS));
        album.setName(request.getName());
        return mapAlbumEntity2AlbumDto(repository.save(album));
    }

    @Override
    @CacheEvict(value = "album_set", keyGenerator = "customKeyGenerator")
    public AlbumDto delete(Integer id) {
        Album album = repository.findById(id).orElseThrow(()-> new BusinessException(Message.Album.NOT_EXISTS));
        repository.deleteById(id);
        return mapAlbumEntity2AlbumDto(album);
    }

}
