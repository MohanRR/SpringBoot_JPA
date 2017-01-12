/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventAgenda;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author arivu
 * 
 */
public interface EventAgendaRepository extends JpaRepository<EventAgenda, Integer> {

	List<EventAgenda> findByEventId(Event eventId);

	@Query(value = "SELECT ea FROM EventAgenda ea WHERE ea.eventId.id = :eventId")
	List<EventAgenda> findByEventId(@Param("eventId") int eventId);

	EventAgenda findByEventIdAndTitle(Event eventId, String title);
        @Modifying
	@Transactional
        @Query(value = "DELETE FROM EventAgenda ec WHERE ec.eventId.id= :eventId")
	void deleteEventAgenda(@Param("eventId") int eventId);
}
