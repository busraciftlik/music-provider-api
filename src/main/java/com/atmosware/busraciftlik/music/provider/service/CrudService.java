package com.atmosware.busraciftlik.music.provider.service;

import java.util.List;
import java.util.UUID;

public interface CrudService<T> {
    List<T> findAll();
    T add(T t);
    T update(T t);
    T delete(UUID id);
}
