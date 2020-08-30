package com.dnd.jachwirus.write.repository;

import com.dnd.jachwirus.write.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findByName(String name);
    List<Hashtag> findAllByNameContains(String name);
}
