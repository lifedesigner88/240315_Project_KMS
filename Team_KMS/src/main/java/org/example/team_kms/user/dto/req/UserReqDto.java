package org.example.team_kms.user.dto.req;

import lombok.Setter;
import org.example.team_kms.user.domain.User;

import java.time.LocalDateTime;

@Setter
public class UserReqDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String profileUrl;
    private LocalDateTime employedDay;

    public User makeUserReqDtoToUser(UserReqDto this) {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .password(this.password)
                .profileUrl(this.profileUrl)
                .employedDay(this.employedDay)
                .build();
    }
}
