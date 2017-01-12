/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteCategory;
import com.oneg.whsquared.entity.EventCategoryModal;

/**
 * @author Anbukkani G
 * 
 */
public interface CustomerFavoriteCategoryRepository extends PagingAndSortingRepository<CustomerFavoriteCategory, Integer> {

	CustomerFavoriteCategory findByCategoryIdAndCustomerId(EventCategoryModal category, Customer customer);

	List<CustomerFavoriteCategory> findByCustomerId(Customer customerid);

	@Modifying
	@Query(value = "DELETE FROM CustomerFavoriteCategory cf WHERE cf.categoryId =:categoryId AND cf.customerId =:customerId")
	@Transactional
	void deleteByCategoryAndCustomerId(@Param("categoryId") EventCategoryModal category, @Param("customerId") Customer customerid);
}
