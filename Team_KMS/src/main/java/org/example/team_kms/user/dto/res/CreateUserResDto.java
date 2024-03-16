package org.example.team_kms.user.dto.res;

import lombok.Getter;
import org.example.team_kms.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class CreateUserResDto {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final String profileUrl;
    private final LocalDateTime employedDay;

    public CreateUserResDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.profileUrl = user.getProfileUrl();
        this.employedDay = user.getEmployedDay();
    }
}
