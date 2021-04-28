package com.github.leonroy.datademo.user.privilege;

import java.util.Collection;

public interface PrivilegeService {

    Collection<Privilege> findAll();

    Privilege findOne(Long id);

    Privilege createPrivilegeWithName(String name) throws PrivilegeAlreadyExistsException;

    Privilege savePrivilege(Privilege privilege);

    void deletePrivilege(Long id);

    void deletePrivilege(Privilege privilege);

    void validatePrivilegeExists(Long id) throws PrivilegeNotFoundException;

    Privilege findByName(String name);

}
