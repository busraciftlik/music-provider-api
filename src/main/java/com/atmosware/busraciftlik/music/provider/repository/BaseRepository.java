//package com.atmosware.busraciftlik.music.provider.repository;
//
//import com.atmosware.busraciftlik.music.provider.entity.BaseEntity;
//import com.atmosware.busraciftlik.music.provider.enums.Status;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//
//import java.util.List;
//import java.util.Optional;
//public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {
////    @Query("UPDATE BaseEntity b SET b.status='INACTIVE' WHERE b.id=:id")
////    void deleteById(@Param("id") Integer id);
//    Optional<T> findByIdAndStatus(Integer id, Status status);
//    List<T> findAllByStatus(Status status);
//}
