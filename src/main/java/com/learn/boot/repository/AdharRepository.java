package com.learn.boot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learn.boot.modal.Adhar;

@Repository
public interface AdharRepository extends PagingAndSortingRepository<Adhar, Integer>{

}
