package org.example.team_kms.authority.dto.req;

import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.authority.domain.UserIdAndGroupRole;

import java.util.List;

@Setter
@Getter
public class addUsersToGroupReqDto {

    private Long groupId;
    private List<UserIdAndGroupRole> groupUsers;


}
