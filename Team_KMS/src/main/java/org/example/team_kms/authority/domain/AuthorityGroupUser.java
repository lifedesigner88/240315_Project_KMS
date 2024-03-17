package org.example.team_kms.authority.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.common.domain.BaseEntity;
import org.example.team_kms.user.domain.User;

@Getter
@Entity
@IdClass(AuthorityGroupUserId.class)
public class AuthorityGroupUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    @Id private AuthorityGroup authorityGroup;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Id private User user;


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private GroupRole groupRole;

    @Setter
    private String delYn = "N";

    public AuthorityGroupUser(){}

    @Builder
    public AuthorityGroupUser(AuthorityGroup authorityGroup,
                              User user, String groupRole) {
        this.authorityGroup = authorityGroup;
        this.user = user;
        this.groupRole = groupRole == null ? null : GroupRole.valueOf(groupRole);
    }
}
