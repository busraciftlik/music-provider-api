package com.atmosware.busraciftlik.music.provider.service;


import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;

import java.util.Set;

public interface AlbumService  {
    Set<AlbumDto> findAll();
    Album findById(Integer id);

    AlbumDto add(CreateAlbumRequest request);

    Album update(Album album);

    Album delete(Integer id);
}
