package org.example.team_kms.document.dto.res;

import lombok.Getter;
import org.example.team_kms.document.domain.Document;
import org.example.team_kms.document.domain.KmsDocType;

@Getter
public class DocumentResDto {

    private final String title;
    private final String isPublish;
    private final KmsDocType kmsDocType;
    private Long upLinkId;
    private String upDocTitle;
    private Long downLinkId;
    private String downDocTitle;
    private final Long authorityGroupId;
    private final String authorityGroupName;

    public DocumentResDto(Document document) {
        this.title = document.getTitle();
        this.isPublish = document.getIsPublish() ? "Yes" : "No";
        this.kmsDocType = document.getKmsDocType();

        if (document.getUpLink() != null) {
            this.upLinkId = document.getUpLink().getId();
            this.upDocTitle = document.getUpLink().getTitle();
        }

        if (document.getDownLink() != null) {
            this.downLinkId = document.getDownLink().getId();
            this.downDocTitle = document.getDownLink().getTitle();
        }

        this.authorityGroupId = document.getAuthorityGroup().getId();
        this.authorityGroupName = document.getAuthorityGroup().getGroupName();
    }
}
