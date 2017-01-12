/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Event;

/**
 * @author Anbukkani G
 * 
 */
@RepositoryRestResource
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

	List<Event> findAll();

	Page<Event> findAll(Pageable pageable);

	@Query(value = "SELECT DISTINCT e FROM Event e WHERE e.vendorId.id =:vendorId AND e.id NOT IN :id")
	List<Event> findByVendorIdAndIdNotIn(@Param("vendorId") Integer vendorId, @Param("id") Integer id);

	@Query(value = "SELECT DISTINCT e FROM Event e WHERE e.name like CONCAT('%', :searchText, '%') or e.description like CONCAT('%', :searchText, '%')"
			+ " or e.shortDescription like CONCAT('%', :searchText, '%')")
	Page<Event> searchByNameDesc(Pageable pageable, @Param("searchText") String searchText);

	@Query(value = "SELECT DISTINCT e FROM Event e WHERE e.vendorId.id= :vendorId and e.name like CONCAT('%', :searchText, '%') or e.description like CONCAT('%', :searchText, '%')"
			+ " or e.shortDescription like CONCAT('%', :searchText, '%')")
	Page<Event> searchByNameDescVendor(Pageable pageable, @Param("searchText") String searchText, @Param("vendorId") int vendorId);

	Event findById(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Event ec WHERE ec.id= :eventId")
	void deleteEvent(@Param("eventId") int eventId);

	@Query(value = "select e from Event e where e.vendorId.id = :vendorId")
	Page<Event> findByVendorId(@Param("vendorId") int vendorId, Pageable pageable);

	@Query(value = "select e from Event e where e.vendorId.id = :vendorId")
	List<Event> findByVendorId(@Param("vendorId") int vendorId);

	@Query(value = "UPDATE Event e SET e.eventCalender=:calenderId WHERE e.id =:eventId")
	Event updateEventCalender(@Param("eventId") Integer eventId, @Param("calenderId") Integer calenderId);

	@Query(value = "select e from Event e where e.vendorId.id in ( :vendorId )")
	Page<Event> findAllVendorId(@Param("vendorId") List<Integer> vendorId, Pageable pageable);
}
