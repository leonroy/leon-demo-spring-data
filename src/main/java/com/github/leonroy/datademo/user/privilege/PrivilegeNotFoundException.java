package com.github.leonroy.datademo.user.privilege;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class PrivilegeNotFoundException extends RuntimeException {

    public PrivilegeNotFoundException(String message) {
        super(message);
    }

}
