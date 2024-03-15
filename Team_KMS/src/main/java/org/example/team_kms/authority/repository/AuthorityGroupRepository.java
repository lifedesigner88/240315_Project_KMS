package org.example.team_kms.authority.repository;

import org.example.team_kms.authority.domain.AuthorityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityGroupRepository extends JpaRepository<AuthorityGroup, Long> {
}
