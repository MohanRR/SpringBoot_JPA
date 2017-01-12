/**
 * 
 */
package com.oneg.whsquared.repository.vendor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.Vendor;

/**
 * @author Anbukkani G
 * 
 */
@RepositoryRestResource
public interface VendorRepository extends PagingAndSortingRepository<Vendor, Integer> {

	@Query(value = "SELECT v FROM Vendor v WHERE v.name like CONCAT('%', :searchText, '%')or v.description like CONCAT('%', :searchText, '%')")
	Page<Vendor> searchByNameDesc(@Param("searchText") String searchText, Pageable pageable);

	@Query(value = "SELECT v FROM Vendor v WHERE " + "LOWER(v.name) LIKE LOWER(CONCAT('%',:searchText, '%')) " + "OR "
			+ "LOWER(v.description) LIKE LOWER(CONCAT('%',:searchText, '%')) " + "ORDER BY v.name ASC")
	List<Vendor> searchByName(@Param("searchText") String searchText);

	// @Query(value = "SELECT v FROM Vendor v WHERE v.user.id= :userId")
	@Query(value = "SELECT v FROM Vendor v")
	List<Vendor> findByUserId(@Param("userId") int userId);

	List<Vendor> findAll();

}
