package com.library.repository;

import com.library.domain.copies.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CopyRepository extends CrudRepository<Copy, Long> {
    @Override
    List<Copy> findAll();

    @Override
    Optional<Copy> findById(Long id);

    @Override
    Copy save(Copy title);

    @Query(nativeQuery = true)
    int retrieveTitlesWithStatus(@Param("copy_state") String state, @Param("title_id") Long titleId);

    Optional<List<Copy>> findByStateAndTitleId(@Param("copy_state") String state, @Param("title_id") Long titleId);
}
