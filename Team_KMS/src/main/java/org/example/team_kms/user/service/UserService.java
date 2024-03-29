package org.example.team_kms.user.service;

import org.example.team_kms.user.domain.User;
import org.example.team_kms.user.dto.req.CreateUserReqDto;
import org.example.team_kms.user.dto.res.UserResDto;
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

//    Create
    public UserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeUserReqDtoToUser();
        User savedUser = userRepo.save(user);
        return new UserResDto(savedUser);
    }

    public List<UserResDto> createUsers(List<CreateUserReqDto> dtos) {
        return dtos.stream()
                .map(CreateUserReqDto::makeUserReqDtoToUser)
                .map(userRepo::save)
                .map(UserResDto::new)
                .collect(Collectors.toList());
    }

//    Read
    public List<UserResDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(UserResDto::new)
                .collect(Collectors.toList());
    }
//    함수공통화
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}