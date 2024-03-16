package org.example.team_kms.user.repository;

import org.example.team_kms.document.domain.Document;
import org.example.team_kms.user.domain.User;
import org.example.team_kms.user.domain.UserDocAuthority;
import org.example.team_kms.user.domain.UserDocAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDocAuthorityRepository extends JpaRepository<UserDocAuthority, UserDocAuthorityId> {
    List<UserDocAuthority> findByUser(User user);
    List<UserDocAuthority> findByDocument(Document document);

    Optional<UserDocAuthority> findByUserAndDocument(User user, Document document);
}
