package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.DocumentHashtag;
import com.dnd.jachwirus.write.service.DocumentHashtagService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(value = "문서 해시태그 Api")
@RestController
@RequestMapping("/api/v1/doc-hashtag")
@Slf4j
@ResponseBody
@CrossOrigin(value = "*")
public class DocumentHashtagController {

    @Autowired
    DocumentHashtagService documentHashtagService;

    @GetMapping
    Page<DocumentHashtag> findAllByHashtagName(
            @RequestParam String HashtagName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ){
        return documentHashtagService.findDocumentHashtagByName(PageRequest.of(page, size), HashtagName);
    }

}
