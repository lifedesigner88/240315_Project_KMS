package org.example.team_kms.authority.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.dto.res.CreateAuthorityGroupResDto;
import org.example.team_kms.authority.repository.AuthorityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final AuthorityGroupRepository authorityGroupRepo;

    @Autowired
    public AuthorityService(AuthorityGroupRepository authorityGroupRepo) {
        this.authorityGroupRepo = authorityGroupRepo;
    }

    public CreateAuthorityGroupResDto createAuthorityGroup(AuthorityGroup authorityGroup, Long supperGroupId) {
        AuthorityGroup supperGroup = null;
        if (supperGroupId != null)
            supperGroup = getAuthorityGroupById(supperGroupId);
        authorityGroup.setSuperGroup(supperGroup);
        return new CreateAuthorityGroupResDto(authorityGroupRepo.save(authorityGroup));
    }

    public AuthorityGroup getAuthorityGroupById(Long supperGroupId) {
        return authorityGroupRepo.findById(supperGroupId).orElseThrow(EntityNotFoundException::new);
    }
}
