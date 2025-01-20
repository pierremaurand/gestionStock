package com.opmg.ApiGestionStock.role;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

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
}


