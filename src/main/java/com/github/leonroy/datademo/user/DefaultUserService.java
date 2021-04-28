package com.github.leonroy.datademo.user;

import com.github.leonroy.datademo.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
public class DefaultUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${email.whitelist}")
    public String emailWhitelist;

    @Transactional(readOnly = true)
    public Collection<User> findAll() {
        return userRepository.findByDeletedAtIsNull();
    }

    @Transactional(readOnly = true)
    public List<User> findAllInactiveUsersBefore(Instant date) {
        return userRepository.findByCreatedAtBeforeAndEnabledFalseAndDeletedAtIsNull(date);
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsersCreatedBetweenAndWithoutCalls(Instant dateFrom, Instant dateTo) {
        return userRepository.findByCreatedAtBetweenAndEnabledTrueAndDeletedAtIsNull(dateFrom, dateTo, 0);
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsersCreatedBeforeAndWithCallsCountLessThan(Instant date, int paramCount) {
        return userRepository.findByCreatedAtBeforeAndCallsCountLessThanAndEnabledTrueAndDeletedAtIsNull(date, paramCount);
    }

    @Transactional(readOnly = true)
    public User findOne(Long id) {
        return userRepository.findByDeletedAtIsNullAndIdEquals(id);
    }

    @Transactional(readOnly = true)

    public User findByEmail(String email) {
        if (email != null) {
            email = email.toLowerCase();
        }
        return userRepository.findByDeletedAtIsNullAndEmailEquals(email);
    }

    public User saveRegisteredUser(final User user) {
        return userRepository.save(user);
    }

}
