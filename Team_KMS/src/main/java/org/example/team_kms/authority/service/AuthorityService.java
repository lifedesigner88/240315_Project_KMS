package org.example.team_kms.authority.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.dto.req.CreateAuthorityGroupReqDto;
import org.example.team_kms.authority.dto.res.AuthorityGroupResDto;
import org.example.team_kms.authority.dto.res.GetAuthorityGroupHierarchyResDto;
import org.example.team_kms.authority.repository.AuthorityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private final AuthorityGroupRepository authorityGroupRepo;

    @Autowired
    public AuthorityService(AuthorityGroupRepository authorityGroupRepo) {
        this.authorityGroupRepo = authorityGroupRepo;
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

//    Read
    public GetAuthorityGroupHierarchyResDto getAuthorityGroupHierarchy(Long authoritiyGroupId) {
        return new GetAuthorityGroupHierarchyResDto(getAuthorityGroupById(authoritiyGroupId));
    }

    public List<AuthorityGroupResDto> getAuthorityGroupsById(Long authoritiyGroupId) {
       return getAuthorityGroupById(authoritiyGroupId).getChildGroups().stream()
                .map(AuthorityGroupResDto::new)
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


}
