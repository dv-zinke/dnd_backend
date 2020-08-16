package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("document")
@Slf4j
@ResponseBody
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/{id}")
    public Optional<Document> getDocumentById(
            @PathVariable Long id
    ){
        return documentService.getDocumentById(id);
    }

    @PostMapping("/test")
    public Document test(
            @RequestBody Document document
    ){
        return document;
    }

    @PostMapping("/create")
    public Document createDocument(
            @RequestBody Document document
    ){
        return documentService.createDocument(document);
    }

}
