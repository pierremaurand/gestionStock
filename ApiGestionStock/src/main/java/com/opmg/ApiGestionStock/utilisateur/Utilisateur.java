package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.common.Sexe;
import com.opmg.ApiGestionStock.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur extends BaseEntity {
    private String nom;
    @Column(unique = true)
    private String login;
    private String motDePasse;
    private Sexe sexe;
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateurs_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    @Transient
    public List<Long> getRolesName(){
        return roles.stream().map(Role::getId).toList();
    }
}
