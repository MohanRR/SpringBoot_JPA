/**
 * 
 */
package com.oneg.whsquared.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oneg.whsquared.entity.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anbukkani G
 *
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, Integer>{
        @Modifying
	@Transactional
	@Query(value = "DELETE FROM Address rc WHERE rc.id= :id")
	void deleteAddress(@Param("id") int id);
}
