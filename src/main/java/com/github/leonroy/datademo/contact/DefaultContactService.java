package com.github.leonroy.datademo.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void findAll() {
    }

}
