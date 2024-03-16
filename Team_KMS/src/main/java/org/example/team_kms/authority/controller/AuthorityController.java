package org.example.team_kms.authority.controller;

import org.example.team_kms.authority.dto.req.CreateAuthorityGroupReqDto;
import org.example.team_kms.authority.dto.res.AuthorityGroupResDto;
import org.example.team_kms.authority.dto.res.GetAuthorityGroupHierarchyResDto;
import org.example.team_kms.authority.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }


    //    Create
    @PostMapping("create")
    public ResponseEntity<AuthorityGroupResDto> createAuthorityGroup(
            @RequestBody CreateAuthorityGroupReqDto dto) {
        return ResponseEntity.ok(
                authorityService.createAuthorityGroup(dto)
        );
    }

    @PostMapping("create/groups")
    public ResponseEntity<List<AuthorityGroupResDto>> createAuthorityGroups(
            @RequestBody List<CreateAuthorityGroupReqDto> dtos) {
        return ResponseEntity.ok(
                authorityService.createAuthorityGroups(dtos));
    }

    // Read
    @GetMapping("childgroups/{authoritiyGroupId}")
    public ResponseEntity<List<AuthorityGroupResDto>> getAuthorityGroupChilds(
            @PathVariable Long authoritiyGroupId) {
        return ResponseEntity.ok(
                authorityService.getAuthorityGroupsById(authoritiyGroupId)
        );

    }

    @GetMapping("hierarchy/{authoritiyGroupId}")
    public ResponseEntity<GetAuthorityGroupHierarchyResDto> getAuthorityGroupHierarchy(
            @PathVariable Long authoritiyGroupId) {
        return ResponseEntity.ok(
                authorityService.getAuthorityGroupHierarchy(authoritiyGroupId)
        );
    }

}

