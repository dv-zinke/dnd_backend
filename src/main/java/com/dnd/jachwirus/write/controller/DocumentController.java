package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Api(value = "Document")
@RestController
@RequestMapping("/v1/api/document")
@Slf4j
@ResponseBody
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/{id}")
    @ApiOperation(value = "문서 조회", notes = "문서 아이디로 문서 조회")
    public Optional<Document> getDocumentById(
            @ApiParam(value = "문서 아이디", required = true, example = "1") @PathVariable Long id
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
