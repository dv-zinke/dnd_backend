package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.DocumentVersion;
import com.dnd.jachwirus.write.service.DocumentVersionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.List;

@Api(value = "문서 버전 Api")
@RestController
@RequestMapping("/api/v1/document-version")
@Slf4j
@CrossOrigin
public class DocumentVersionController implements WebFluxConfigurer {

    @Autowired
    DocumentVersionService documentVersionService;

    @GetMapping("/search")
    @CrossOrigin
    public Page<DocumentVersion> findAllByDocumentId(
            @RequestParam(name = "document_id") Long documentId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        return documentVersionService.findAllByDocumentId(PageRequest.of(page, size), documentId);
    }
}
