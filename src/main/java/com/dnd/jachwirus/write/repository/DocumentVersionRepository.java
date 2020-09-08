package com.dnd.jachwirus.write.repository;

import com.dnd.jachwirus.write.domain.DocumentVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {

    Page<DocumentVersion> findByDocumentIdOrderByCreatedAtDesc(Pageable pageable, Long documentId);
}
