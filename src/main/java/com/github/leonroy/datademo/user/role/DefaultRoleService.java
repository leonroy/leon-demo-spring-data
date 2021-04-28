package com.github.leonroy.datademo.user.role;

import com.github.leonroy.datademo.user.privilege.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

@Service
@Transactional
public class DefaultRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findOne(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role createRoleWithPrivileges(String name, Collection<Privilege> privileges) throws RoleAlreadyExistsException {
        if (doesRoleAlreadyExist(name)) {
            throw new RoleAlreadyExistsException("Role with name " + name + " already exists");
        }
        return roleRepository.save(new Role(name).setPrivileges(new HashSet<>(privileges)));
    }

    public boolean doesRoleAlreadyExist(final String name) {
        final Role role = findByName(name);
        return role != null;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public void validateRoleExists(Long id) throws RoleNotFoundException {
        if (findOne(id) == null) {
            throw new RoleNotFoundException("Role with id " + id + " not found");
        }
    }

}
