package com.dnd.jachwirus.write.repository;

import com.dnd.jachwirus.write.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c")
    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findByDocumentId(Pageable pageable, Long documentId);



}
