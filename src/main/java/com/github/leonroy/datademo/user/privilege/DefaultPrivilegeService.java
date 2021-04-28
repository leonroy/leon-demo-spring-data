package com.github.leonroy.datademo.user.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DefaultPrivilegeService implements PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Transactional(readOnly = true)
    @Override
    public Collection<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Privilege findOne(Long id) {
        return privilegeRepository.findById(id).orElse(null);
    }

    @Override
    public Privilege createPrivilegeWithName(String name) throws PrivilegeAlreadyExistsException {
        if (doesPrivilegeAlreadyExist(name)) {
            throw new PrivilegeAlreadyExistsException("Privilege with name " + name + " already exists");
        }
        return privilegeRepository.save(new Privilege(name));
    }

    public boolean doesPrivilegeAlreadyExist(final String name) {
        final Privilege privilege = findByName(name);
        return privilege != null;
    }

    @Override
    public Privilege savePrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Transactional(readOnly = true)
    @Override
    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name);
    }

    @Override
    public void deletePrivilege(Long id) {
        privilegeRepository.deleteById(id);
    }

    @Override
    public void deletePrivilege(Privilege privilege) {
        privilegeRepository.delete(privilege);
    }

    @Override
    public void validatePrivilegeExists(Long id) throws PrivilegeNotFoundException {
        if (findOne(id) == null) {
            throw new PrivilegeNotFoundException("Privilege not found" + id);
        }
    }

}
