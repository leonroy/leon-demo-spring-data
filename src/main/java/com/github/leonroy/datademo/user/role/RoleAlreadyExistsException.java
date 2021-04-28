package com.github.leonroy.datademo.user.role;

public final class RoleAlreadyExistsException extends RuntimeException {

    public RoleAlreadyExistsException(String message) {
        super(message);
    }

}
