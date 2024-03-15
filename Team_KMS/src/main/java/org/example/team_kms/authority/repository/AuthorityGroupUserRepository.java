package org.example.team_kms.authority.repository;

import org.example.team_kms.authority.domain.AuthorityGroup;
import org.example.team_kms.authority.domain.AuthorityGroupUser;
import org.example.team_kms.authority.domain.AuthorityGroupUserId;
import org.example.team_kms.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityGroupUserRepository extends JpaRepository<AuthorityGroupUser, AuthorityGroupUserId> {
    List<AuthorityGroupUser> findByAuthorityGroup(AuthorityGroup authorityGroup);
    List<AuthorityGroupUser> findByUser(User user);
    Optional<AuthorityGroupUser> findByAuthorityGroupAndUser(AuthorityGroup authorityGroup, User user);
}
