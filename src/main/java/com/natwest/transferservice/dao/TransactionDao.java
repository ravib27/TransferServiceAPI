package com.natwest.transferservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natwest.transferservice.dto.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

}
