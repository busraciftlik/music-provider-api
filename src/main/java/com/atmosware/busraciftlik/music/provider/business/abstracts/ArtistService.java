package com.atmosware.busraciftlik.music.provider.business.abstracts;

import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ArtistService extends CrudService<Artist> {
    Set<Music> addMusic(UUID artistId, Music music);
}
