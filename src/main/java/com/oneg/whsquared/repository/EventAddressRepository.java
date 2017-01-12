/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventAddress;

/**
 * @author arivu
 * 
 */
public interface EventAddressRepository extends JpaRepository<EventAddress, Integer> {

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE ea.address.state.id = :state AND ea.address.city = :city ")
	public List<Event> getEventDataByAddress(@Param("state") Integer state, @Param("city") String city);

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE ea.address.state.id = :state AND ea.address.city = :city AND ea.event.startDate =:date")
	public List<Event> getEventDataByAddressAndDate(@Param("state") Integer state, @Param("city") String city, @Param("date") Date date);

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE ea.address.state.id = :state AND ea.address.city = :city AND ea.event.showToday = true")
	public List<Event> getEventDataByAddressAndTodayDate(@Param("state") Integer state, @Param("city") String city);

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE ea.address.state.id = :state AND ea.address.city = :city AND ea.event.startDate =:date AND ea.event IN :events")
	public List<Event> getEventDataByAddressAndDateAndEvents(@Param("state") Integer state, @Param("city") String city, @Param("date") Date date,
			@Param("events") List<Event> events);

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE ea.address.state.id = :state AND ea.address.city = :city AND ea.event.showToday= true AND ea.event IN :events")
	public List<Event> getEventDataByAddressAndDateAndEventsToday(@Param("state") Integer state, @Param("city") String city, @Param("events") List<Event> events);

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE ea.address.state.id = :state AND ea.address.city = :city AND ea.event IN :events")
	public List<Event> getEventDataByAddressAndEvents(@Param("state") Integer state, @Param("city") String city, @Param("events") List<Event> events);

	List<EventAddress> findByEvent(Event event);

	@Query(value = "SELECT ea.event FROM EventAddress ea WHERE LOWER(ea.address.city) LIKE LOWER(CONCAT('%',:searchTerm, '%')) ")
	public List<Event> getEventByAddress(@Param("searchTerm") String city);

	@Query(value = "SELECT ea FROM EventAddress ea WHERE ea.event.id = :eventId")
	List<EventAddress> findByEventId(@Param("eventId") int eventId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM EventAddress ea WHERE ea.event.id= :eventId")
	void deleteEventAddress(@Param("eventId") int eventId);
}
