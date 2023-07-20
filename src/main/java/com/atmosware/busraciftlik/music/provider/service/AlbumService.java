package com.atmosware.busraciftlik.music.provider.service;


import com.atmosware.busraciftlik.music.provider.entity.Album;

import java.util.List;

public interface AlbumService  {
    List<Album> findAll();

    Album add(Album album);

    Album update(Album album);

    Album delete(Integer id);
}
