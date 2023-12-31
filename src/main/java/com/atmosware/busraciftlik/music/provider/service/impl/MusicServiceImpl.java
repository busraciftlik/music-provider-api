package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.atmosware.busraciftlik.music.provider.enums.Status;
import com.atmosware.busraciftlik.music.provider.exception.BusinessException;
import com.atmosware.busraciftlik.music.provider.repository.MusicRepository;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.util.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapMusicEntity2MusicDto;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    private final MusicRepository repository;
    private final ArtistService artistService;

    @Override
    @Cacheable("music_set")
    public Set<MusicDto> findAll() {
        return mapMusicEntity2MusicDto(repository.findAll());
    }

    @Override
    public Set<Music> findAllByIdsAndArtistId(List<Integer> ids, Integer artistId) {
        return repository.findAllByIdInAndArtistIdAndStatus(ids, artistId, Status.ACTIVE);
    }

    @Override
    public Set<Music> findAllByIds(List<Integer> ids) {
        return repository.findAllByIdInAndStatus(ids, Status.ACTIVE);
    }

    @Override
    public Music findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(Message.Music.NOT_EXISTS));
    }

    @Override
    @CacheEvict(value = {"music_set","artist_set"}, allEntries = true)
    public MusicDto add(CreateMusicRequest request) {
        Artist artist = artistService.getById(request.getArtistId());
        final Music music = Music.builder()
                .name(request.getName())
                .genre(request.getGenre())
                .artist(artist)
                .build();
        final Music saved = repository.save(music);
        return mapMusicEntity2MusicDto(saved);

    }

    @Override
    @CacheEvict(value = "music_set", keyGenerator = "customKeyGenerator")
    public Music update(Integer id, Music music) {
        Music exists = repository.findById(id).orElseThrow(() -> new BusinessException(Message.Music.NOT_EXISTS));
        exists.setName(music.getName());
        exists.setAlbum(music.getAlbum());
        exists.setArtist(music.getArtist());
        return repository.save(exists);
    }

    @Override
    @CacheEvict(value = {"music_set","artist_set"} , keyGenerator = "customKeyGenerator")
    public Music delete(Integer id) {
        Music music = repository.findById(id).orElseThrow(() -> new BusinessException(Message.Music.NOT_EXISTS));
        repository.deleteById(id);
        return music;
    }

    /**
     * Searches for music by the name of the album it belongs to
     *
     * @param albumName The name of the album to search for music
     * @return A set of {@link MusicDto} objects representing the music found with the specified album name.
     * If no music matches the given album name, an empty set is returned.
     */

    @Override
    public Set<MusicDto> searchMusicsByAlbum(String albumName) {
        Set<Music> musics = repository.findMusicByAlbumName(albumName);
        return mapMusicEntity2MusicDto(musics);
    }

    /**
     * Searches for music by the name of artist it belongs to
     *
     * @param artisName The of the artist to search for music
     * @return A set of {@link MusicDto} objects representing the music found with the specified artist name.
     * If no music matches the given artist name, an empty set is returned.
     */
    @Override
    public Set<MusicDto> searchMusicsByArtist(String artisName) {
        Set<Music> musics = repository.findMusicByArtistName(artisName);
        return mapMusicEntity2MusicDto(musics);
    }

    /**
     * Searches for music by the specified genre
     *
     * @param genre The genre to search for music
     * @return A set of {@link MusicDto} objects representing the music found with the specified genre.
     * If no music matches the given genre, an empty set is returned
     */
    @Override
    public Set<MusicDto> searchMusicsByGenre(Genre genre) {
        Set<Music> musics = repository.findMusicByGenre(genre);
        return mapMusicEntity2MusicDto(musics);
    }

}
