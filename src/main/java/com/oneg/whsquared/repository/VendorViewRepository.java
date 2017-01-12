/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.repository;

import com.oneg.whsquared.entity.VendorView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Anbukkani G
 */
@RepositoryRestResource
public interface VendorViewRepository extends PagingAndSortingRepository<VendorView, Integer>{
    
}
