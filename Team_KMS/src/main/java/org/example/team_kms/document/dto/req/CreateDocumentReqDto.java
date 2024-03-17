package org.example.team_kms.document.dto.req;

import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.document.domain.Document;
import org.example.team_kms.document.domain.KmsDocType;

@Setter
@Getter
public class CreateDocumentReqDto {

    private String title;
    private Boolean isPublish;
    private String kmsDocType;
    private Long upLinkId;
    private Long downLinkId;
    private Long authorityGroupId;

    public Document makeDocDtoToDocument (CreateDocumentReqDto this){
        return Document.builder()
                .title(this.title)
                .kmsDocType(KmsDocType.valueOf(this.kmsDocType))
                .isPublish(this.isPublish)
                .build();
    }
}
