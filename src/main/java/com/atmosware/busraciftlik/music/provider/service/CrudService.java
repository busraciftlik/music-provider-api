package com.atmosware.busraciftlik.music.provider.service;

import java.util.List;

public interface CrudService<T> {
    List<T> findAll();
    T add(T t);
    T update(T t);
    T delete(Integer id);
}
