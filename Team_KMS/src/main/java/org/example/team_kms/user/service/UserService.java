package org.example.team_kms.user.service;

import org.example.team_kms.user.domain.User;
import org.example.team_kms.user.dto.req.CreateUserReqDto;
import org.example.team_kms.user.dto.res.CreateUserResDto;
import org.example.team_kms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public CreateUserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeUserReqDtoToUser();
        User savedUser = userRepo.save(user);
        return new CreateUserResDto(savedUser);
    }

    public List<CreateUserResDto> createUsers(List<CreateUserReqDto> dto) {
        return dto.stream()
                .map(CreateUserReqDto::makeUserReqDtoToUser)
                .map(userRepo::save)
                .map(CreateUserResDto::new)
                .collect(Collectors.toList());
    }
}