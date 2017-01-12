package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.SubCategory;

@RepositoryRestResource
public interface SubCategoryRepository extends PagingAndSortingRepository<SubCategory, Integer> {

	List<SubCategory> findByName(@Param("name") String name);

	SubCategory findById(@Param("id") Integer id);

	List<SubCategory> findAll();

	Page<SubCategory> findAll(Pageable pageable);

	@Query(value = "SELECT c FROM SubCategory c WHERE c.name like CONCAT('%', :searchText, '%')")
	Page<SubCategory> searchByName(@Param("searchText") String searchText, Pageable pageable);

	@Query(value = "SELECT c FROM SubCategory c WHERE c.name like CONCAT('%', :searchText, '%')")
	List<SubCategory> searchByNameAndDesc(@Param("searchText") String searchText);

	@Query(value = "SELECT c FROM SubCategory c WHERE c.name = :name and c.category.id = :categoryId")
	SubCategory findByNameAndCategory(@Param("categoryId") Integer categoryId, @Param("name") String name);

}
