package org.example.team_kms.authority.dto.res;

import lombok.Getter;
import org.example.team_kms.authority.domain.AuthorityGroup;

@Getter
public class CreateAuthorityGroupResDto {

    private final Long groupId;
    private final String groupName;
    private final String groupType;
    private final Long superGroupId;

    public CreateAuthorityGroupResDto(AuthorityGroup authorityGroup){
        this.groupId = authorityGroup.getId();
        this.groupName = authorityGroup.getGroupName();
        this.groupType = authorityGroup.getGroupType().name();
        this.superGroupId = authorityGroup.getSuperGroup() != null ? authorityGroup.getSuperGroup().getId() : null;
    }

}
