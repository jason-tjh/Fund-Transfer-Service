package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.model.Debit;

public interface DebitRepository extends JpaRepository<Debit, Integer> {

}
