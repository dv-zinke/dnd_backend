package com.dnd.jachwirus.write.repository;

import com.dnd.jachwirus.write.domain.DocumentHashtag;
import com.dnd.jachwirus.write.domain.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentHashtagRepository extends JpaRepository<DocumentHashtag, Long> {
    @Query("select dh from DocumentHashtag dh where dh.hashtag = :hashtagId")
    Page<DocumentHashtag> findAllByHashtagId(Pageable page, Long hashtagId);
}
