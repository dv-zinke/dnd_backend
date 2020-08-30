package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.Comment;
import com.dnd.jachwirus.write.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Page<Comment> getCommentByPageable(Pageable page) {
        return commentRepository.findAll(page);
    }
}
