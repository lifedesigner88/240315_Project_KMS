package org.example.team_kms.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.document.domain.Document;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UserDocAuthorityId implements Serializable {

    private User user;
    private Document document;

}
