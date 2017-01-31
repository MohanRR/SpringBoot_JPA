package com.learn.boot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learn.boot.modal.Bank;

@Repository
public interface BankRepository extends PagingAndSortingRepository<Bank, Integer> {

}
