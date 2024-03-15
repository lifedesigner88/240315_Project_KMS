package org.example.team_kms.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.common.domain.BaseEntity;

import java.time.LocalDateTime;


@Entity
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    private String profileUrl;

    private LocalDateTime employedDay;

    @Setter
    private String delYn = "N";

    User(){}

    @Builder
    public User(String name, String email, String password,
                String phoneNumber, String profileUrl,
                LocalDateTime employedDay) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileUrl = profileUrl;
        this.employedDay = employedDay;
    }

}
