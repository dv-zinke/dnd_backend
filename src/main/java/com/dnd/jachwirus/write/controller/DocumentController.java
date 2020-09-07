package com.dnd.jachwirus.write.controller;

import com.dnd.jachwirus.write.domain.Document;
import com.dnd.jachwirus.write.domain.data.CreateDocumentParam;
import com.dnd.jachwirus.write.domain.data.UpdateDocumentParam;
import com.dnd.jachwirus.write.exception.RestException;
import com.dnd.jachwirus.write.service.DocumentService;
import com.dnd.jachwirus.write.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "문서 Api")
@RestController
@RequestMapping("/api/v1/document")
@Slf4j
@ResponseBody
@CrossOrigin
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    S3Service s3Service;

    @GetMapping
    @ApiOperation(value = "문서 전체 조회", notes = "문서 전체 조회")
    public List<Document> getAllDocument(
    ) {
        return documentService.getAllDocument();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "문서 조회", notes = "문서 아이디로 문서 조회")
    public Optional<Document> getDocumentById(
            @ApiParam(value = "문서 아이디", required = true, example = "1") @PathVariable Long id
    ) {
        return documentService.getDocumentById(id);
    }

    @GetMapping("/new")
    public List<Document> getNewDocument() {

        return documentService.getNewDocumentTop5(PageRequest.of(0,5));
    }

    @GetMapping("/best")
    public List<Document> getBestDocument() {
        return documentService.getBestDocumentTop5(PageRequest.of(0,5));
    }

    @PostMapping("/create")
    @ApiOperation(value = "문서 작성", notes = "문서작성")
    public Mono<Document> createDocument(
            @ApiParam(value = "문서 작성 양식", required = true) @Valid @RequestBody CreateDocumentParam createDocumentParam
    ) {
        return documentService.createDocument(createDocumentParam);
    }

    @PostMapping("/update")
    @ApiOperation(value = "문서 수정", notes = "문서 수정")
    public Mono<Document> updateDocument(
            @ApiParam(value = "문서 수정 양식", required = true) @Valid @RequestBody UpdateDocumentParam updateDocumentParam
    ) {
        return documentService.updateDocument(updateDocumentParam);
    }

    @PostMapping("/like/{documentId}")
    public Document likeDocument(
            @PathVariable Long documentId
    ) throws RestException {
        return documentService.likePlusDocument(documentId);
    }

    @PostMapping("/dislike/{documentId}")
    public Document disLikeDocument(
            @PathVariable Long documentId
    ) throws RestException {
        return documentService.likeMinusDocument(documentId);
    }

}
