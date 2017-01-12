package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.EventCategoryModal;

@RepositoryRestResource
public interface EventCategoryModalRepository extends PagingAndSortingRepository<EventCategoryModal, Integer> {
	List<EventCategoryModal> findByName(@Param("name") String name);

	EventCategoryModal findById(@Param("id") Integer id);

	List<EventCategoryModal> findAll();

	@Query(value = "SELECT DISTINCT c FROM EventCategoryModal c ORDER BY c.name ASC")
	Page<EventCategoryModal> findAll(Pageable pageable);

	@Query(value = "SELECT DISTINCT c FROM EventCategoryModal c WHERE c.name like CONCAT('%', :searchText, '%')")
	Page<EventCategoryModal> searchByName(@Param("searchText") String searchText, Pageable pageable);

	@Query(value = "SELECT DISTINCT c FROM EventCategoryModal c WHERE c.name like CONCAT('%', :searchText, '%')")
	List<EventCategoryModal> searchByNameAndDesc(@Param("searchText") String searchText);

	@Query(value = "SELECT DISTINCT p.category FROM Preference p WHERE p.customer =:customer")
	Page<EventCategoryModal> findByCustomerPreference(@Param("customer") Customer customer, Pageable pageable);

	@Query(value = "SELECT DISTINCT p.category FROM Preference p WHERE p.customer =:customer")
	List<EventCategoryModal> findCategoriesByCustomer(@Param("customer") Customer customer);
}
