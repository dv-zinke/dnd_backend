package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    public Optional<Document> getDocumentById(Long id){
        return documentRepository.findById(id);
    }
    public List<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    public Document createDocument(Document newDocument) {
        return documentRepository.save(newDocument);
    }
}
