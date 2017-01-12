/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.Preference;

/**
 * @author Anbukkani G
 * 
 */
@RepositoryRestResource
public interface PreferenceRepository extends PagingAndSortingRepository<Preference, Integer> {

	public List<Preference> findByCustomerId(@Param(value = "customerId") Integer customerId);

	@Query(value = "SELECT x FROM Preference x WHERE x.customer =:customer AND x.category =:category")
	public Preference findByCustomerAndCategory(@Param(value = "customer") Customer customer, @Param(value = "category") EventCategoryModal category);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Preference p WHERE p.customer.id= :id")
	void deletePreferenceByCustomerId(@Param("id") int id);
}
