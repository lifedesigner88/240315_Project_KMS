package org.example.team_kms.authority.dto.req;

import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.domain.GroupType;

@Setter
public class CreateAuthorityGroupReqDto {

    private String groupName;
    private String groupType;

    @Getter
    private Long supperGroupId;

    public AuthorityGroup makeAuthorityReqDtoToAuthorityGroup(CreateAuthorityGroupReqDto this){
        return AuthorityGroup.builder()
                .groupName(this.groupName)
                .groupType(GroupType.valueOf(this.groupType))
                .build();
    }

}
