package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.UserVendors;

public interface UserVendorRepository extends PagingAndSortingRepository<UserVendors, Integer> {

	@Query(value = "SELECT va FROM UserVendors va WHERE va.vendor.id =:vendorId")
	List<UserVendors> findByVendorId(@Param("vendorId") int vendorId);

	@Query(value = "SELECT va FROM UserVendors va WHERE va.user.id =:userId")
	List<UserVendors> findByUserId(@Param("userId") int userId);

	@Query(value = "SELECT va FROM UserVendors va WHERE va.user.id =:userId and va.vendor.id =:vendorId")
	UserVendors findByUserIdAndVendorId(@Param("userId") int userId, @Param("vendorId") int vendorId);

	@Query(value = "SELECT va FROM UserVendors va WHERE va.user.id =:userId")
	Page<UserVendors> findByUserIdWithPage(@Param("userId") int userId, Pageable pageable);
}
