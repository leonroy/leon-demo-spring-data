package com.github.leonroy.datademo.user.repository;

import com.github.leonroy.datademo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByDeletedAtIsNullAndEmailEquals(@Param("email") String email);

    List<User> findByDeletedAtIsNull();

    User findByDeletedAtIsNullAndIdEquals(Long id);

    List<User> findByDeletedAtIsNotNull();

    List<User> findByDeletedAtIsNullAndBillingAccountIsNull();

    List<User> findByCreatedAtBeforeAndEnabledFalseAndDeletedAtIsNull(Instant date);

    @Query(value = "SELECT * FROM my_user u WHERE created_at > :dateFromParam AND created_at <= :dateToParam AND enabled=TRUE" +
            " AND deleted_at IS NULL" +
            " AND (u.properties ->> 'paramCount' IS NULL OR CAST (u.properties ->> 'paramCount' AS INTEGER) = :paramCountParam)", nativeQuery = true)
    List<User> findByCreatedAtBetweenAndEnabledTrueAndDeletedAtIsNull(@Param("dateFromParam") Instant dateFrom, @Param("dateToParam") Instant dateTo, @Param("paramCountParam") int paramCount);

    @Query(value = "SELECT * FROM my_user u WHERE created_at <= :dateParam AND enabled=TRUE" +
            " AND deleted_at IS NULL" +
            " AND (u.properties ->> 'paramCount' IS NULL OR CAST (u.properties ->> 'paramCount' AS INTEGER) < :paramCountParam)", nativeQuery = true)
    List<User> findByCreatedAtBeforeAndCallsCountLessThanAndEnabledTrueAndDeletedAtIsNull(
            @Param("dateParam") Instant date,
            @Param("paramCountParam") int paramCount);

}