package org.example.team_kms.user.controller;

import org.example.team_kms.user.dto.req.CreateUserReqDto;
import org.example.team_kms.user.dto.res.CreateUserResDto;
import org.example.team_kms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("create")
    public ResponseEntity<CreateUserResDto> createUser(@RequestBody CreateUserReqDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping("create/users")
    public ResponseEntity<List<CreateUserResDto>> createUsers(@RequestBody List<CreateUserReqDto> dto){
        return ResponseEntity.ok(userService.createUsers(dto));
    }
}
