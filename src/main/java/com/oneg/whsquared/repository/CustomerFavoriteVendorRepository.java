/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteVendor;
import com.oneg.whsquared.entity.Vendor;

/**
 * 
 * @author Anbukkani G
 */
@RepositoryRestResource
public interface CustomerFavoriteVendorRepository extends PagingAndSortingRepository<CustomerFavoriteVendor, Integer> {

	@Modifying
	@Query(value = "DELETE FROM CustomerFavoriteVendor cf WHERE cf.vendorId =:vendorId AND cf.customerId =:customerid")
	@Transactional
	void deleteByEventAndCustomerId(@Param("vendorId") Vendor vendor, @Param("customerid") Customer customerid);

	CustomerFavoriteVendor findByVendorIdAndCustomerId(Vendor vendor, Customer customerid);

	List<CustomerFavoriteVendor> findByVendorId(Vendor vendor);

	List<CustomerFavoriteVendor> findByCustomerId(Customer customer);
}
