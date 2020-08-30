package com.dnd.jachwirus.write.repository;

import com.dnd.jachwirus.write.domain.DocumentHashtag;
import com.dnd.jachwirus.write.domain.Hashtag;
import com.dnd.jachwirus.write.domain.QDocumentHashtag;
import com.dnd.jachwirus.write.domain.QHashtag;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DocumentHashtagSearchRepository extends QuerydslRepositorySupport {

    @PersistenceContext
    private EntityManager em;

    public DocumentHashtagSearchRepository(){
        super(DocumentHashtag.class);
    }

    public Page<DocumentHashtag> search(
            Hashtag hashtag,
            Pageable pageable
    ){

        QDocumentHashtag qDocumentHashtag = QDocumentHashtag.documentHashtag;
        QHashtag qHashtag = QHashtag.hashtag;
        JPAQuery<DocumentHashtag> jpaQuery = new JPAQuery<>(em);

        jpaQuery.from(qDocumentHashtag)
                .leftJoin(qDocumentHashtag.hashtag, qHashtag)
                .fetchJoin()
                .distinct();

        jpaQuery.where(qHashtag.id.eq(hashtag.getId()));

        List<DocumentHashtag> documentHashtagList = jpaQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchAll()
                .fetch();

        long count = jpaQuery.fetchCount();

        return new PageImpl<>(documentHashtagList, pageable, count);

    }
}
