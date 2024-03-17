package org.example.team_kms.authority.dto.res;


import lombok.Getter;
import org.example.team_kms.authority.domain.AuthorityGroupUser;
import org.example.team_kms.authority.domain.GroupRole;

@Getter
public class GroupUsersRoleResDto {

    private final String authorityGroupName;
    private final Long authorityGroupId;
    private final String userName;
    private final Long userId;
    private final GroupRole groupRole;

    public GroupUsersRoleResDto(AuthorityGroupUser authorityGroup) {
        this.authorityGroupName = authorityGroup.getAuthorityGroup().getGroupName();
        this.authorityGroupId = authorityGroup.getAuthorityGroup().getId();
        this.userName = authorityGroup.getUser().getName();
        this.userId = authorityGroup.getUser().getId();
        this.groupRole = authorityGroup.getGroupRole();
    }

}
