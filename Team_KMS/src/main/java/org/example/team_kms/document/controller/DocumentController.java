package org.example.team_kms.document.controller;

import org.example.team_kms.document.dto.req.CreateDocumentReqDto;
import org.example.team_kms.document.dto.req.GetIsAccessibleDocReqDto;
import org.example.team_kms.document.dto.res.DocumentResDto;
import org.example.team_kms.document.dto.res.GetDocumentResDto;
import org.example.team_kms.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    Read
    @GetMapping("isAccessible")
    public ResponseEntity<GetDocumentResDto> getDocument(@RequestBody GetIsAccessibleDocReqDto dto) {
        return ResponseEntity.ok(documentService.GetIsAccessibleDoc(dto));
    }
}
