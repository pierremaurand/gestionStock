package com.opmg.ApiGestionStock.role;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.privilege.Privilege;
import com.opmg.ApiGestionStock.utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<Utilisateur> utilisateurs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Collection<Privilege> privileges;

    @Transient
    public List<String> getPrivilegeNames() {
        if (privileges == null || privileges.isEmpty()) {
            return null;
        }
        return privileges.stream().map(Privilege::getName).toList();
    }
}


