package org.example.team_kms.authority.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.common.domain.BaseEntity;

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

    @ManyToOne
    @JoinColumn
    private AuthorityGroup superGroup;

    @Setter
    private String delYn = "N";

    public AuthorityGroup() {}

    @OneToMany(mappedBy = "superGroup", cascade = CascadeType.PERSIST)
    private final List<AuthorityGroup> authorityGroups = new ArrayList<>();
    public void setAuthorityGroups(List<AuthorityGroup> authorityGroups) {
        this.authorityGroups.clear();
        if (authorityGroups != null) this.authorityGroups.addAll(authorityGroups);
    }

    @Builder
    public AuthorityGroup(String gropuName, GroupType groupType, AuthorityGroup superGroup) {
        this.groupName = gropuName;
        this.groupType = groupType;
        this.superGroup = superGroup;
    }
}
