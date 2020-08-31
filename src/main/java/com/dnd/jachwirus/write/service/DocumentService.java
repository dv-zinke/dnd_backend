package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.domain.DocumentHashtag;
import com.dnd.jachwirus.write.domain.DocumentVersion;
import com.dnd.jachwirus.write.domain.Hashtag;
import com.dnd.jachwirus.write.domain.data.CreateDocumentParam;
import com.dnd.jachwirus.write.domain.data.UpdateDocumentParam;
import com.dnd.jachwirus.write.repository.DocumentHashtagRepository;
import com.dnd.jachwirus.write.repository.DocumentRepository;
import com.dnd.jachwirus.write.repository.HashtagRepository;
import com.dnd.jachwirus.write.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentVersionService documentVersionService;

    @Autowired
    S3Service s3Service;

    @Autowired
    DocumentHashtagRepository documentHashtagRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    public Optional<Document> getDocumentById(Long id){
        return documentRepository.findById(id);
    }
    public List<Document> getAllDocument() {
        return documentRepository.findAll();
    }

    public List<Document> getBestDocumentTop5(Pageable pageable) {
        return documentRepository.findTop5ByOrOrderByLikeDisLikeDesc(pageable);
    }

    public List<Document> getNewDocumentTop5(Pageable pageable) {
        return documentRepository.findTop5ByOrOrderByCreatedAtDesc(pageable);
    }

    @Transactional
    public Mono<Document> createDocument(CreateDocumentParam newCreateDocumentParam) {
        return Mono.just(newCreateDocumentParam)
                .map(createDocumentParam ->{

                    String filePath = getAwsS3UrlByString(createDocumentParam.getContent());
                    // Document 생성
                    Document document = newCreateDocumentParam.getDocument();
                    document.setTitle(document.getTitle());
                    document.setCreatedAt(LocalDateTime.now());
                    Document newDocument = documentRepository.save(document);


                    DocumentVersion newDocumentVersion = new DocumentVersion();
                    newDocumentVersion.setCreatedAt(LocalDateTime.now());
                    newDocumentVersion.setDataUrl(filePath);
                    newDocumentVersion.setDocumentId(newDocument.getId());


                    for(String hashtag : createDocumentParam.getHashtags()) {

                        Hashtag foundHashtag;
                        if(hashtagRepository.existsByName(hashtag)) {
                            foundHashtag = hashtagRepository.findByName(hashtag);
                        }else {
                            Hashtag newHashtag = new Hashtag();
                            newHashtag.setName(hashtag);
                            foundHashtag = hashtagRepository.save(newHashtag);
                        }

                        DocumentHashtag newDocumentHashtag = new DocumentHashtag();
                        newDocumentHashtag.setDocument(newDocument);
                        newDocumentHashtag.setHashtag(foundHashtag);
                        documentHashtagRepository.save(newDocumentHashtag);
                    }
                    DocumentVersion savedDocumentVersion = documentVersionService.createDocumentVersion(newDocumentVersion);

                    newDocument.setLastVersion(savedDocumentVersion);
                    return documentRepository.save(newDocument);
                });
    }

    public Mono<Document> updateDocument(UpdateDocumentParam updateDocumentParam){
        return Mono.just(updateDocumentParam)
                .map(updateDocument-> getAwsS3UrlByString(updateDocument.getContent()))
                .map(pathUrl ->{
                    DocumentVersion newDocumentVersion = new DocumentVersion();
                    newDocumentVersion.setCreatedAt(LocalDateTime.now());
                    newDocumentVersion.setDataUrl(pathUrl);
                    newDocumentVersion.setDocumentId(updateDocumentParam.getDocumentId());
                    DocumentVersion savedDocumentVersion = documentVersionService.createDocumentVersion(newDocumentVersion);

                    Optional<Document> originalDocument = documentRepository.findById(updateDocumentParam.getDocumentId());
                    if(originalDocument.isPresent()){
                        originalDocument.get().setLastVersion(savedDocumentVersion);
                        return documentRepository.save(originalDocument.get());
                    }else {
                        return null;
                    }
                });

    }

    String getAwsS3UrlByString(String content){
        InputStream is = new ByteArrayInputStream(content.getBytes());
        String PathUrl = null;
        try {
            PathUrl = s3Service.uploadContent(new UuidUtil().getUuid(),is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PathUrl;
    }

    String getAwsS3UrlByFile(MultipartFile file) {
        String PathUrl = null;
        try {
            PathUrl = s3Service.uploadFile(new UuidUtil().getUuid(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PathUrl;
    }


}
