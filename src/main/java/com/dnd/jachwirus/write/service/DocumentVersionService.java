package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.DocumentVersion;
import com.dnd.jachwirus.write.repository.DocumentVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DocumentVersionService {
    @Autowired
    DocumentVersionRepository documentVersionRepository;

    DocumentVersion createDocumentVersion(DocumentVersion createDocumentVersion){
        return documentVersionRepository.save(createDocumentVersion);
    }

    public Page<DocumentVersion> findAllByDocumentId(Pageable page, Long documentId) {
        return documentVersionRepository.findByDocumentIdOrderByCreatedAtDesc(page, documentId);
    }
}
