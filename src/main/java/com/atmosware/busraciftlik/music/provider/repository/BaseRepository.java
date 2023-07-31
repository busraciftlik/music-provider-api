package com.atmosware.busraciftlik.music.provider.repository;

import com.atmosware.busraciftlik.music.provider.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID> extends JpaRepository<T, ID> {
    @Query("update #{#entityName} e set e.status='INACTIVE' where e.id = ?1") //SoftDelete // what if entity not exists ?
    @Transactional
    @Modifying
        //TODO
    void deleteById(@Param("id") ID id);

    /*default void deleteById(Integer id){
        Optional<T> byId = findById(id);
        T t = byId.orElseThrow();
        t.setStatus(Status.INACTIVE);
        save(t);
    }*/
}
