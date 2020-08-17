package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.DocumentVersion;
import com.dnd.jachwirus.write.repository.DocumentVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentVersionService {
    @Autowired
    DocumentVersionRepository documentVersionRepository;

    public DocumentVersion createDocumentVersion(DocumentVersion createDocumentVersion){
        return documentVersionRepository.save(createDocumentVersion);
    }
}
