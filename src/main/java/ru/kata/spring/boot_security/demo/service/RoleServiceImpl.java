package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getSetOfRoles(List<String> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(getRoleByName(roleName));
        }
        return roleSet;
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

}
