package org.example.team_kms.authority.controller;

import org.example.team_kms.authority.dto.req.CreateAuthorityGroupReqDto;
import org.example.team_kms.authority.dto.res.CreateAuthorityGroupResDto;
import org.example.team_kms.authority.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }


    @PostMapping("create")
    public ResponseEntity<CreateAuthorityGroupResDto> createAuthorityGroup(
            @RequestBody CreateAuthorityGroupReqDto dto) {
        return ResponseEntity.ok(
                authorityService.createAuthorityGroup(dto)
        );
    }

    @PostMapping("create/groups")
    public ResponseEntity<List<CreateAuthorityGroupResDto>> createAuthorityGroups(
            @RequestBody List<CreateAuthorityGroupReqDto> dtos){
        return ResponseEntity.ok(
                authorityService.createAuthorityGroups(dtos));
    }
}

