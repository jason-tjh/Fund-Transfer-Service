package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.model.Credit;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

}
