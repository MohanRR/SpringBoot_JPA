/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventDetail;

/**
 * @author arivu
 * 
 */
public interface EventDetailRepository extends PagingAndSortingRepository<EventDetail, Integer> {

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true")
	Page<EventDetail> getAllActiveEvents(Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true ORDER BY ed.eventId.eventCalender.startDate")
	Page<EventDetail> getAllActiveEventsAndDateOrder(Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId IN :events ORDER BY ed.eventId.eventCalender.startDate")
	Page<EventDetail> getAllActiveEventsAndDateOrderAndEvents(@Param("events") List<Event> events, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId.eventCalender.startDate =:date")
	Page<EventDetail> getAllActiveEventsByDate(@Param("date") Date date, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId.showToday = true")
	Page<EventDetail> getAllActiveTodaysEvents(Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId.eventCalender.startDate =:date AND ed.eventId IN :events")
	Page<EventDetail> getAllActiveEventsByEvents(@Param("date") Date date, @Param("events") List<Event> events, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId.showToday = true AND ed.eventId IN :events")
	Page<EventDetail> getAllActiveEventsByEventsToday(@Param("events") List<Event> events, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId.eventCalender.startDate =:date ORDER BY ed.eventId.eventCalender.startDate")
	Page<EventDetail> getAllActiveEventsByDateAndDateOrder(@Param("date") Date date, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId IN :events AND ed.eventId.eventCalender.startDate =:date ORDER BY ed.eventId.eventCalender.startDate")
	Page<EventDetail> getAllActiveEventsByDateAndDateOrderAndEvents(@Param("events") List<Event> events, @Param("date") Date date, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId IN (:events)")
	Page<EventDetail> findByEvents(@Param("events") List<Event> events, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId IN (:events)")
	List<EventDetail> findByEvents(@Param("events") List<Event> events);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE ed.eventId.active = true AND ed.eventId IN (:events) ORDER BY ed.eventId.eventCalender.startDate")
	Page<EventDetail> findByEventsAndDateOrder(@Param("events") List<Event> events, Pageable pageable);

	EventDetail findByEventId(Event eventid);

	//
	// @Query(value =
	// "SELECT v FROM EventDetail v WHERE v.name like CONCAT('%', :searchText, '%')or v.description like CONCAT('%', :searchText, '%')")
	// List<EventDetail> search(@Param("searchText") String searchText);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE " + "LOWER(ed.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(ed.eventId.shortDescription) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(ed.eventId.eventCalender.startDate) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(ed.eventId.eventCalender.endDate) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "ORDER BY ed.name ASC")
	Page<EventDetail> search(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query(value = "SELECT ed FROM EventDetail ed WHERE " + "LOWER(ed.eventId.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(ed.eventId.shortDescription) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(ed.eventId.eventCalender.startDate) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "OR "
			+ "LOWER(ed.eventId.eventCalender.endDate) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " + "ORDER BY ed.eventId.name ASC")
	List<EventDetail> searchFromEventDetails(@Param("searchTerm") String searchTerm);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM EventDetail rc WHERE rc.eventId.id= :eventId")
	void deleteEventDetail(@Param("eventId") int eventId);

}
