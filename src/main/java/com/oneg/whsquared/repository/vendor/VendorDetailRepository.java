package com.oneg.whsquared.repository.vendor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.VendorDetail;

@RepositoryRestResource
public interface VendorDetailRepository extends PagingAndSortingRepository<VendorDetail, Integer> {

	@Query(value = "SELECT vd FROM VendorDetail vd WHERE " + "LOWER(vd.vendor.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(vd.vendor.description) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "ORDER BY vd.vendor.name ASC")
	List<VendorDetail> searchFromVendorDetails(@Param("searchTerm") String searchTerm);
}
