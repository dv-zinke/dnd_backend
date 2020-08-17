package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.domain.data.CreateDocumentParam;
import com.dnd.jachwirus.write.repository.DocumentRepository;
import com.dnd.jachwirus.write.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    S3Service s3Service;

    public Optional<Document> getDocumentById(Long id){
        return documentRepository.findById(id);
    }
    public List<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    public Mono<Document> createDocument(CreateDocumentParam newCreateDocumentParam) {
        return Mono.just(newCreateDocumentParam)
                .map(createDocumentParam->{
                    InputStream is = new ByteArrayInputStream(createDocumentParam.getContent().getBytes());
                    String PathUrl = null;
                    try {
                        PathUrl = s3Service.uploadContent(new UuidUtil().getUuid(),is);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return PathUrl;
                })
                .map(pathUrl ->{
                    Document newDocument = newCreateDocumentParam.getDocument();
                    newDocument.setDataUrl(pathUrl);
                    return documentRepository.save(newDocument);
                });
    }
}
