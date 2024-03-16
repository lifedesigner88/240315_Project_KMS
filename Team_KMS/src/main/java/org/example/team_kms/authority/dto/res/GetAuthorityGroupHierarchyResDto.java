package org.example.team_kms.authority.dto.res;

import lombok.Getter;
import org.example.team_kms.authority.domain.AuthorityGroup;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetAuthorityGroupHierarchyResDto {
    private final Long groupId;
    private final String groupName;
    private final String groupType;
    private final List<GetAuthorityGroupHierarchyResDto> childGroups;

    public GetAuthorityGroupHierarchyResDto(AuthorityGroup authorityGroup){
        this.groupId = authorityGroup.getId();
        this.groupName = authorityGroup.getGroupName();
        this.groupType = authorityGroup.getGroupType().name();
        this.childGroups = authorityGroup.getChildGroups().stream()
                .map(GetAuthorityGroupHierarchyResDto::new)
                .collect(Collectors.toList());
    }
}
