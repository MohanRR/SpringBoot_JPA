/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.repository;

import com.oneg.whsquared.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Anbukkani G
 */
@RepositoryRestResource
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>{
  
  @Query(value="select r from Role r where r.name= :name" )
  List<Role> findByName(@Param("name") String name);
}
