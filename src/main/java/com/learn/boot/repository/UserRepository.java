package com.learn.boot.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learn.boot.modal.User;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

}
