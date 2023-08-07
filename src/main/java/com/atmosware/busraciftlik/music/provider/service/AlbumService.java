package com.atmosware.busraciftlik.music.provider.service;


import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;

import java.util.Set;

public interface AlbumService {
    Set<AlbumDto> findAll();

    Album findById(Integer id);

    AlbumDto add(CreateAlbumRequest request);

    AlbumDto update(Integer id, UpdateAlbumRequest request);

    AlbumDto delete(Integer id);

}
