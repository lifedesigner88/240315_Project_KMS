package org.example.team_kms.document.dto.res;

import lombok.Getter;
import org.example.team_kms.document.domain.Document;

@Getter
public class GetDocumentResDto {

    private final String title;

    public GetDocumentResDto(Document document, Boolean isAccssible){
        if(isAccssible)
            this.title = document.getTitle();
        else
            this.title = "접근할 수 없는 유저입니다";
    }
}
