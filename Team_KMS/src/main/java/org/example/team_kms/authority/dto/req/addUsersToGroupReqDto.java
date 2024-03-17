package org.example.team_kms.authority.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class addUsersToGroupReqDto {

    private Long groupId;
    private List<UserEmailListAndGroupRole> groupUsers;

    @Setter
    @Getter
    public static class UserEmailListAndGroupRole {
        private Long userId;
        private String groupRole;
    }
}
