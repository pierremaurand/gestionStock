package com.opmg.ApiGestionStock.service;

import com.opmg.ApiGestionStock.privilege.Privilege;
import com.opmg.ApiGestionStock.role.Role;
import com.opmg.ApiGestionStock.utilisateur.Utilisateur;
import com.opmg.ApiGestionStock.privilege.PrivilegeRepository;
import com.opmg.ApiGestionStock.role.RoleRepository;
import com.opmg.ApiGestionStock.utilisateur.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitDbService {

//    private final PrivilegeRepository privilegeRepository;
//    private final RoleRepository roleRepository;
//    private final UtilisateurRepository utilisateurRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public void init() {
//        Privilege readPrivilege = getPrivilege("READ_PRIVILEGE");
//        Privilege writePrivilege = getPrivilege("WRITE_PRIVILEGE");
//        Role adminRole = getRole("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
//        Role userRole = getRole("ROLE_USER", List.of(readPrivilege));
//        Utilisateur admin = getUser("admin", "admin123", List.of(adminRole));
//        Utilisateur user = getUser("user", "user123", List.of(userRole));
//        System.out.println(admin);
//        System.out.println(user);
//    }
//
//    private Privilege getPrivilege(String name) {
//        if (privilegeRepository.existsByName(name)) {
//            return privilegeRepository.findByName(name);
//        } else {
//            Privilege privilege = Privilege.builder()
//                    .name(name)
//                    .build();
//            return privilegeRepository.save(privilege);
//        }
//    }
//
//    private Role getRole(String name, List<Privilege> privileges) {
//        if (roleRepository.existsByName(name)) {
//            return roleRepository.findByName(name);
//        } else {
//            Role role = Role.builder()
//                    .name(name)
//                    .privileges(privileges)
//                    .build();
//            return roleRepository.save(role);
//        }
//    }
//
//    private Utilisateur getUser(String username, String password, List<Role> roles) {
//        if (utilisateurRepository.existsByUsername(username)) {
//            return utilisateurRepository.findByUsername(username);
//        } else {
//            Utilisateur user = Utilisateur.builder()
//                    .username(username)
//                    .password(passwordEncoder.encode(password))
//                    .roles(roles)
//                    .build();
//            return utilisateurRepository.save(user);
//        }
//    }

}
