package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.service.DocumentService;
import com.dnd.jachwirus.write.service.S3Service;
import com.dnd.jachwirus.write.utils.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Api(value = "Document")
@RestController
@RequestMapping("/api/v1/document")
@Slf4j
@ResponseBody
@CrossOrigin(value = "*")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    S3Service s3Service;

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
    ) throws IOException {
        InputStream is = new ByteArrayInputStream(document.content.getBytes());

        s3Service.upload(new UuidUtil().getUuid(),is);
        return documentService.createDocument(document);
    }

}
