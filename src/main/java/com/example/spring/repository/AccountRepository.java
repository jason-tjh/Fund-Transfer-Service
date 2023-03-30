package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
