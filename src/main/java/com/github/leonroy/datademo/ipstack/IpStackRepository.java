package com.github.leonroy.datademo.ipstack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpStackRepository extends JpaRepository<IpStackRecord, Long> {

    @Query(value = "SELECT * FROM ip_stack_record r WHERE r.record ->> 'ip' = :ipParam", nativeQuery = true)
    List<IpStackRecord> findByRecordIpIsEqualTo(@Param("ipParam") String ip);

}