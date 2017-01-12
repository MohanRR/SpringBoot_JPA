package com.oneg.whsquared.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCalender;

public interface EventCalenderRepository extends PagingAndSortingRepository<EventCalender, Integer> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM EventCalender ec WHERE ec.eventId.id= :eventId")
	void deleteCalenderDetail(@Param("eventId") int eventId);

	EventCalender findByEventId(Event eventId);

}
