package org.example.team_kms.document.controller;

import org.example.team_kms.document.dto.req.CreateDocumentReqDto;
import org.example.team_kms.document.dto.res.DocumentResDto;
import org.example.team_kms.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

//    Create
    @PostMapping("create")
    public ResponseEntity<DocumentResDto> createDocument(@RequestBody CreateDocumentReqDto dto) {
        return ResponseEntity.ok(documentService.createDocument(dto));
    }
}
