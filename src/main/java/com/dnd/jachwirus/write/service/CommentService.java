package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.Comment;
import com.dnd.jachwirus.write.domain.User;
import com.dnd.jachwirus.write.domain.data.CreateCommentParam;
import com.dnd.jachwirus.write.endpoint.UserEndpoint;
import com.dnd.jachwirus.write.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserEndpoint userEndpoint;

    public Page<Comment> getCommentByPageable(Pageable page) {
        return commentRepository.findAll(page);
    }
    public Page<Comment> getCommentByDocumentId(Pageable page, Long documentId) {
        return commentRepository.findByDocumentId(page, documentId);
    }

    public Mono<Comment> createComment(CreateCommentParam commentParam){
        Mono<User> userMono = Mono.just(commentParam)
                .flatMap(param -> userEndpoint.getUser(param.getUserId()));
        return Mono.zip(userMono, Mono.just(commentParam))
                .map(it -> {
                    User user = it.getT1();
                    CreateCommentParam newCommentParam = it.getT2();

                    Comment newComment = new Comment();
                    newComment.setContent(newCommentParam.getContent());
                    newComment.setUserId(user.getId());
                    newComment.setDocumentId(newCommentParam.getDocumentId());
                    newComment.setCreatedAt(LocalDateTime.now());
                    return commentRepository.save(newComment);
                });
    }
}
