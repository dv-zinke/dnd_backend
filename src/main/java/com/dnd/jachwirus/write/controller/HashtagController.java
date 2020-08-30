package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.Hashtag;
import com.dnd.jachwirus.write.service.HashtagService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "해쉬태그 Api")
@RestController
@RequestMapping("/api/v1/hashtag")
@Slf4j
@ResponseBody
@CrossOrigin(value = "*")
public class HashtagController {
    @Autowired
    HashtagService hashtagService;

    @GetMapping("/search")
    List<Hashtag> findAllByNameContain(
            @RequestParam String name
    ){
        return hashtagService.findAllByNameContain(name);
    }

    @GetMapping
    List<Hashtag> findAll(){
        return hashtagService.findAll();
    }
}
