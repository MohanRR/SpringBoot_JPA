package com.oneg.whsquared.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCategory;
import com.oneg.whsquared.entity.EventCategoryModal;

/**
 * 
 * @author arivu
 * 
 */
public interface EventCategoryRepository extends PagingAndSortingRepository<EventCategory, Integer> {

	@Query(value = "SELECT DISTINCT ec.event FROM EventCategory ec WHERE ec.eventCategory.id =:categoryId AND ec.event.id NOT IN :eventId")
	List<Event> findByCategoryIdAndEventNotIn(@Param("categoryId") Integer categoryId, @Param("eventId") Integer eventId);

	@Query(value = "SELECT DISTINCT ec.event FROM EventCategory ec WHERE ec.eventCategory.id =:categoryId")
	public List<Event> getEventByCategory(@Param("categoryId") Integer categoryId);

	@Query(value = "SELECT DISTINCT ec FROM EventCategory ec WHERE ec.eventCategory.id =:categoryId")
	public List<EventCategory> getEventCategoryByCategory(@Param("categoryId") Integer categoryId);

	@Query(value = "SELECT DISTINCT ec.eventCategory FROM EventCategory ec WHERE ec.event =:event")
	public List<EventCategoryModal> getCategoryByEvent(@Param("event") Event event);

	@Query(value = "SELECT DISTINCT ec.event FROM EventCategory ec WHERE ec.eventCategory.id =:categoryId AND ec.event.active = true AND ec.event.startDate =:date")
	public List<Event> getEventByCategoryAndDate(@Param("categoryId") Integer categoryId, @Param("date") Date date);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM EventCategory ec WHERE ec.event.id= :eventId")
	void deleteEventCategory(@Param("eventId") int eventId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM EventCategory ec WHERE ec.id= :id")
	void deleteEventCategoryById(@Param("id") int id);

	List<EventCategory> findByEventId(@Param("id") Integer id);

}
