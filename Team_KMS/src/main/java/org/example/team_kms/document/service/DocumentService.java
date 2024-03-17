package org.example.team_kms.document.service;

import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.service.AuthorityService;
import org.example.team_kms.document.domain.Document;
import org.example.team_kms.document.dto.req.CreateDocumentReqDto;
import org.example.team_kms.document.dto.res.DocumentResDto;
import org.example.team_kms.document.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepo;
    private final AuthorityService authorityService;


    @Autowired
    public DocumentService(DocumentRepository documentRepo,
                           AuthorityService authorityService) {
        this.documentRepo = documentRepo;
        this.authorityService = authorityService;
    }

//    Create
    public DocumentResDto createDocument(CreateDocumentReqDto dto) {
        Document newDocument = dto.makeDocDtoToDocument();
        if(dto.getUpLinkId() != null) {
            Document upLinkDoc = getDocumentById(dto.getUpLinkId());
            newDocument.setUpLink(upLinkDoc);
        }
        if(dto.getDownLinkId() != null) {
            Document downLinkDoc = getDocumentById(dto.getDownLinkId());
            newDocument.setDownLink(downLinkDoc);
        }
        AuthorityGroup authorityGroup = authorityService.getAuthorityGroupById(
                dto.getAuthorityGroupId()
        );
        newDocument.setAuthorityGroup(authorityGroup);
        return new DocumentResDto(documentRepo.save(newDocument));
    }

//    Read

//    중복함수
    public Document getDocumentById(Long DocumentId){
        return documentRepo.findById(DocumentId).orElse(null);
    }
}
