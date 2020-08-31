package com.dnd.jachwirus.write.repository;

import com.dnd.jachwirus.write.domain.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d ORDER BY d.like DESC")
    List<Document> findTop5ByOrOrderByLikeDisLikeDesc(Pageable pageable);

    @Query("select d from Document d order by d.createdAt DESC")
    List<Document> findTop5ByOrOrderByCreatedAtDesc(Pageable pageable);
}

