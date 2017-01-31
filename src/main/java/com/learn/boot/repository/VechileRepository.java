package com.learn.boot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learn.boot.modal.Vechile;

@Repository
public interface VechileRepository extends PagingAndSortingRepository<Vechile, Integer> {

}
