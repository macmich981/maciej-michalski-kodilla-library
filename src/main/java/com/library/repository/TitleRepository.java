package com.library.repository;

import com.library.domain.titles.Title;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends CrudRepository<Title, Long> {
    @Override
    List<Title> findAll();

    @Override
    Optional<Title> findById(Long id);

    @Override
    Title save(Title title);
}
