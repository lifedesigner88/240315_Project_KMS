package org.example.team_kms.document.service;

import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.domain.UserIdAndGroupRole;
import org.example.team_kms.authority.service.AuthorityService;
import org.example.team_kms.document.domain.Document;
import org.example.team_kms.document.dto.req.CreateDocumentReqDto;
import org.example.team_kms.document.dto.req.GetIsAccessibleDocReqDto;
import org.example.team_kms.document.dto.res.DocumentResDto;
import org.example.team_kms.document.dto.res.GetDocumentResDto;
import org.example.team_kms.document.repository.DocumentRepository;
import org.example.team_kms.user.domain.User;
import org.example.team_kms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepo;
    private final AuthorityService authorityService;
    private final UserService userService;


    @Autowired
    public DocumentService(DocumentRepository documentRepo,
                           AuthorityService authorityService,
                           UserService userService) {
        this.documentRepo = documentRepo;
        this.authorityService = authorityService;
        this.userService = userService;
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


    public GetDocumentResDto GetIsAccessibleDoc(GetIsAccessibleDocReqDto dto){
        boolean isAccessible = false;
        Document tryToOpenDocument = getDocumentById(dto.getDocumnetId());
        User tryUser = userService.getUserById(dto.getUserId());

        Long AuthorityGroupId = tryToOpenDocument.getAuthorityGroup().getId();

        List<UserIdAndGroupRole> accessibleUsers = authorityService.getAccessibleUsers(AuthorityGroupId);
        for(UserIdAndGroupRole obj : accessibleUsers) {
            System.out.println(
                    "맴버 아이디: " + obj.getUserId() + " " +
                    userService.getUserById(obj.getUserId()).getName() +
                    " / 권한정보: " + obj.getGroupRole());
        }
        isAccessible = accessibleUsers.stream()
                .anyMatch(userId -> userId.getUserId().equals(tryUser.getId()));

        return new GetDocumentResDto(tryToOpenDocument, isAccessible);
    }

//    중복함수
    public Document getDocumentById (Long documentId){
        return documentRepo.findById(documentId).orElse(null);
    }
}
