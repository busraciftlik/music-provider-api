package com.atmosware.busraciftlik.music.provider.business.concretes;

import com.atmosware.busraciftlik.music.provider.business.abstracts.MusicService;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    private final MusicRepository repository;

    @Override
    public List<Music> findAll() {
        return repository.findAll();
    }

    @Override
    public Music add(Music music) {
        return repository.save(music);
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
    public Music delete(UUID id) {
        Music music = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return music;
    }
}
