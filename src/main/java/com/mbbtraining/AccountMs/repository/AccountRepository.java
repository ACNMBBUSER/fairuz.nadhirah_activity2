package com.mbbtraining.AccountMs.repository;

import com.mbbtraining.AccountMs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByName(String name);
}
