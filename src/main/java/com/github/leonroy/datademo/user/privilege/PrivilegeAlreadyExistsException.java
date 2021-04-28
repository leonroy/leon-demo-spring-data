package com.github.leonroy.datademo.user.privilege;

public final class PrivilegeAlreadyExistsException extends RuntimeException {

    public PrivilegeAlreadyExistsException(String message) {
        super(message);
    }

}
