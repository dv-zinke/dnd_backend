package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.DocumentHashtag;
import com.dnd.jachwirus.write.domain.Hashtag;
import com.dnd.jachwirus.write.repository.DocumentHashtagRepository;
import com.dnd.jachwirus.write.repository.DocumentHashtagSearchRepository;
import com.dnd.jachwirus.write.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DocumentHashtagService {
    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    DocumentHashtagRepository documentHashtagRepository;

    @Autowired
    DocumentHashtagSearchRepository documentHashtagSearchRepository;

    public Page<DocumentHashtag> findDocumentHashtagByName(Pageable page, String name) {
        Hashtag foundHashtag = hashtagRepository.findByName(name);

        return documentHashtagSearchRepository.search(foundHashtag, page);
    }
}
