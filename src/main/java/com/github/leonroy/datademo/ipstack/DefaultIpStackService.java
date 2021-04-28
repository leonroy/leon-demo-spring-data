package com.github.leonroy.datademo.ipstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultIpStackService {

    @Autowired
    private IpStackRepository repository;

    public void test() {
        repository.findAll();
    }

}
