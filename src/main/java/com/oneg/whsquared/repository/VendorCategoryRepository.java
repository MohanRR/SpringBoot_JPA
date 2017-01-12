/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorCategory;

/**
 * 
 * @author Rajan
 */
@RepositoryRestResource
public interface VendorCategoryRepository extends PagingAndSortingRepository<VendorCategory, Integer> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM VendorCategory ea WHERE ea.id= :id")
	void deleteVendorCategory(@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM VendorCategory ea WHERE ea.id NOT IN :ids AND ea.vendor.id = :vendorId")
	void deleteVendorCategory(@Param("ids") Set<Integer> id, @Param("vendorId") int vendorId);

	@Query(value = "SELECT DISTINCT ec FROM VendorCategory ec WHERE ec.category.id =:categoryId")
	public List<VendorCategory> getVendorCategoryByCategory(@Param("categoryId") Integer categoryId);

	@Query(value = "SELECT DISTINCT ec.vendor FROM VendorCategory ec WHERE ec.category.id =:categoryId GROUP BY ec.vendor ")
	public List<Vendor> getVendorsByCategory(@Param("categoryId") Integer categoryId);

	@Query(value = "SELECT DISTINCT ec FROM VendorCategory ec WHERE ec.vendor.id =:vendorId")
	public List<VendorCategory> getVendorCategoryByVendor(@Param("vendorId") Integer vendorId);

	public List<VendorCategory> findByVendor(Vendor vendor);

	@Query(value = "FROM VendorCategory ec WHERE ec.subcategory.id =:subcategorId AND ec.category.id =:categoryId AND ec.vendor.id = :vendorId")
	VendorCategory getVendorCategory(@Param("categoryId") Integer categoryId, @Param("vendorId") Integer vendorId, @Param("subcategorId") Integer subcategorId);

	@Query(value = "FROM VendorCategory ec WHERE ec.subcategory.id = null AND ec.category.id =:categoryId AND ec.vendor.id = :vendorId")
	VendorCategory getVendorCategoryWithoutSub(@Param("categoryId") Integer categoryId, @Param("vendorId") Integer vendorId);

}
