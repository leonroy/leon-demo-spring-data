package com.github.leonroy.datademo.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {

    Page<Contact> findByUserIdOrderByLastNameAsc(String userId, Pageable pageable);

    Page<Contact> findByUserId(String userId, Pageable pageable);

    Contact findByUserIdAndId(String userId, String id);

    @Query("{$text: {$search: ?0}}, {score: {$meta: \"textScore\"}}).sort({score:{$meta:\"textScore\"}}")
    List<Contact> findAllByOrderByScoreDesc(String query);

    Page<Contact> findByUserIdOrderByScoreDesc(String userId, TextCriteria criteria, Pageable pageable);

    @Modifying
    void deleteAllByUserIdIs(String userId);

}

