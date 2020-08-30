package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.Comment;
import com.dnd.jachwirus.write.service.CommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(value = "댓글 Api")
@RestController
@RequestMapping("/api/v1/comment")
@Slf4j
@ResponseBody
@CrossOrigin(value = "*")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    Page<Comment> getCommentByPageable(Pageable pageable) {
        return commentService.getCommentByPageable(pageable);
    }
}

