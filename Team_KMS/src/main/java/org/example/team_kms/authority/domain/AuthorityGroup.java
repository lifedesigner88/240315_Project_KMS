package org.example.team_kms.authority.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.common.domain.BaseEntity;
import org.example.team_kms.document.domain.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class AuthorityGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private GroupType groupType;

    @Setter
    @ManyToOne
    @JoinColumn
    private AuthorityGroup superGroup;

    @Setter
    private String delYn = "N";
    public AuthorityGroup() {}

    @OneToMany(mappedBy = "superGroup", cascade = CascadeType.PERSIST)
    private final List<AuthorityGroup> childGroups = new ArrayList<>();
    public void setAuthorityGroups(List<AuthorityGroup> authorityGroups) {
        this.childGroups.clear();
        if (authorityGroups != null) this.childGroups.addAll(authorityGroups);
    }

    @OneToMany(mappedBy = "authorityGroup", cascade = CascadeType.PERSIST)
    private final List<Document> documents = new ArrayList<>();

    @Builder
    public AuthorityGroup(String groupName, GroupType groupType) {
        this.groupName = groupName;
        this.groupType = groupType;
    }
}
