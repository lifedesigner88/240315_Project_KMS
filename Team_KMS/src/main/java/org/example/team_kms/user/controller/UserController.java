package org.example.team_kms.user.controller;

import org.example.team_kms.user.dto.req.UserReqDto;
import org.example.team_kms.user.dto.res.UserResDto;
import org.example.team_kms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("create")
    public ResponseEntity<UserResDto> createUser(@RequestBody UserReqDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }
}
