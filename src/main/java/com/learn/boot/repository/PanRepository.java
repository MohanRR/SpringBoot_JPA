package com.learn.boot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learn.boot.modal.PanDetail;

@Repository
public interface PanRepository extends PagingAndSortingRepository<PanDetail, Integer>{

}
