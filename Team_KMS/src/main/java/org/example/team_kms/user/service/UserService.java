package org.example.team_kms.user.service;

import org.example.team_kms.user.domain.User;
import org.example.team_kms.user.dto.req.UserReqDto;
import org.example.team_kms.user.dto.res.UserResDto;
import org.example.team_kms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserResDto createUser(UserReqDto dto) {
        User user = dto.makeUserReqDtoToUser();
        User savedUser = userRepo.save(user);
        return new UserResDto(savedUser);
    }
}
