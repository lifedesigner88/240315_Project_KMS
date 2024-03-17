package org.example.team_kms.authority.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.domain.AuthorityGroupUser;
import org.example.team_kms.authority.dto.req.CreateAuthorityGroupReqDto;
import org.example.team_kms.authority.dto.req.addUsersToGroupReqDto;
import org.example.team_kms.authority.dto.res.AuthorityGroupResDto;
import org.example.team_kms.authority.dto.res.GetAuthorityGroupHierarchyResDto;
import org.example.team_kms.authority.dto.res.GroupUsersRoleResDto;
import org.example.team_kms.authority.repository.AuthorityGroupRepository;
import org.example.team_kms.authority.repository.AuthorityGroupUserRepository;
import org.example.team_kms.user.domain.User;
import org.example.team_kms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private final UserService userService;
    private final AuthorityGroupRepository authorityGroupRepo;
    private final AuthorityGroupUserRepository authorityGroupUserRepo;

    @Autowired
    public AuthorityService(AuthorityGroupRepository authorityGroupRepo, UserService userService, AuthorityGroupUserRepository authorityGroupUserRepo) {
        this.userService = userService;
        this.authorityGroupRepo = authorityGroupRepo;
        this.authorityGroupUserRepo = authorityGroupUserRepo;
    }


    //    Create
    public AuthorityGroupResDto createAuthorityGroup(CreateAuthorityGroupReqDto dto) {
        AuthorityGroup newAuthorityGroup = createNewAuthorityGroup(dto);
        return new AuthorityGroupResDto(authorityGroupRepo.save(newAuthorityGroup));
    }
    public List<AuthorityGroupResDto> createAuthorityGroups(List<CreateAuthorityGroupReqDto> dtos) {
        return dtos.stream()
                .map(this::createAuthorityGroup)
                .collect(Collectors.toList());
    }
    public List<GroupUsersRoleResDto> addUsersToGroup(addUsersToGroupReqDto dto) {
        List<AuthorityGroupUser> addedUsers = getAuthorityGroupUsers(dto);
        return addedUsers.stream()
                .map(GroupUsersRoleResDto::new)
                .collect(Collectors.toList());
    }
    public List<List<GroupUsersRoleResDto>> addUsersToGroupList(List<addUsersToGroupReqDto> dtos) {
        List<List<GroupUsersRoleResDto>> addResult = new ArrayList<>();
        for (addUsersToGroupReqDto dto : dtos)
            addResult.add(addUsersToGroup(dto));
        return addResult;
    }

    //    Read
    public GetAuthorityGroupHierarchyResDto getAuthorityGroupHierarchy(Long authorityGroupId) {
        return new GetAuthorityGroupHierarchyResDto(getAuthorityGroupById(authorityGroupId));
    }
    public List<AuthorityGroupResDto> getAuthorityGroupsById(Long authorityGroupId) {
        return getAuthorityGroupById(authorityGroupId).getChildGroups().stream()
                .map(AuthorityGroupResDto::new)
                .collect(Collectors.toList());
    }
    public List<GroupUsersRoleResDto> getAuthorityGroupUsers(Long authorityGroupId) {
        AuthorityGroup authorityGroup = getAuthorityGroupById(authorityGroupId);
        return authorityGroupUserRepo.findByAuthorityGroup(authorityGroup).stream()
                .map(GroupUsersRoleResDto::new)
                .collect(Collectors.toList());
    }

    //    함수 공통화
    public AuthorityGroup getAuthorityGroupById(Long supperGroupId) {
        return authorityGroupRepo.findById(supperGroupId).orElseThrow(EntityNotFoundException::new);
    }
    private AuthorityGroup createNewAuthorityGroup(CreateAuthorityGroupReqDto dto) {
        AuthorityGroup newAuthorityGroup = dto.makeAuthorityReqDtoToAuthorityGroup();

        Optional.ofNullable(dto.getSupperGroupId())
                .map(this::getAuthorityGroupById)
                .ifPresent(newAuthorityGroup::setSuperGroup);

        return newAuthorityGroup;
    }
    private List<AuthorityGroupUser> getAuthorityGroupUsers(addUsersToGroupReqDto dto) {
        List<AuthorityGroupUser> addedUsers = new ArrayList<>();
        AuthorityGroup authorityGroup = getAuthorityGroupById(dto.getGroupId());
        for (addUsersToGroupReqDto.UserEmailListAndGroupRole user : dto.getGroupUsers()) {
            User tempUser = userService.getUserById(user.getUserId());
            AuthorityGroupUser newAuthorityGroupUser = AuthorityGroupUser.builder()
                    .authorityGroup(authorityGroup)
                    .user(tempUser)
                    .groupRole(user.getGroupRole())
                    .build();
            addedUsers.add(authorityGroupUserRepo.save(newAuthorityGroupUser));
        }
        return addedUsers;
    }

}
