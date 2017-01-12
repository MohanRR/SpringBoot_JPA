/**
 * 
 */
package com.oneg.whsquared.repository.vendor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.VendorAddress;

/**
 * @author Anbukkani G
 *
 */
@RepositoryRestResource
public interface VendorAddressRepository extends PagingAndSortingRepository<VendorAddress, Integer> {

	@Query(value = "SELECT va FROM VendorAddress va WHERE va.vendor.id =:vendorId")
	List<VendorAddress> findByVendorId(@Param("vendorId") int vendorId);

}
