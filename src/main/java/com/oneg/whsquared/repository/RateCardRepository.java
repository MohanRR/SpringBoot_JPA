/**
 * 
 */
package com.oneg.whsquared.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.RateCard;

/**
 * @author arivu
 * 
 */

public interface RateCardRepository extends JpaRepository<RateCard, Integer> {

	@Query(value = "SELECT DISTINCT rc.eventId FROM RateCard rc WHERE rc.amount BETWEEN :startRange AND :endRange")
	public List<Event> getEventDataByAmountRange(@Param("startRange") BigDecimal startRange, @Param("endRange") BigDecimal endRange);

	@Query(value = "SELECT DISTINCT rc.eventId FROM RateCard rc WHERE rc.amount BETWEEN :startRange AND :endRange AND rc.eventId IN :events")
	public List<Event> getEventDataByAmountRangeAndEvents(@Param("startRange") BigDecimal startRange, @Param("endRange") BigDecimal endRange, @Param("events") List<Event> events);

	@Query(value = "SELECT DISTINCT rc.eventId FROM RateCard rc WHERE rc.amount BETWEEN :startRange AND :endRange AND rc.eventId.startDate =:date")
	public List<Event> getEventDataByAmountRangeAndDate(@Param("startRange") BigDecimal startRange, @Param("endRange") BigDecimal endRange, @Param("date") Date date);

	@Query(value = "SELECT DISTINCT rc.eventId FROM RateCard rc WHERE rc.amount BETWEEN :startRange AND :endRange AND rc.eventId.showToday = true")
	public List<Event> getEventDataByAmountRangeAndToday(@Param("startRange") BigDecimal startRange, @Param("endRange") BigDecimal endRange);

	@Query(value = "SELECT DISTINCT rc.eventId FROM RateCard rc WHERE rc.amount BETWEEN :startRange AND :endRange AND rc.eventId.startDate =:date AND rc.eventId IN :events")
	public List<Event> getEventDataByAmountRangeAndDateAndEvents(@Param("startRange") BigDecimal startRange, @Param("endRange") BigDecimal endRange, @Param("date") Date date,
			@Param("events") List<Event> events);

	@Query(value = "SELECT DISTINCT rc.eventId FROM RateCard rc WHERE rc.amount BETWEEN :startRange AND :endRange AND rc.eventId.showToday = true AND rc.eventId IN :events")
	public List<Event> getEventDataByAmountRangeAndDateAndEventsToday(@Param("startRange") BigDecimal startRange, @Param("endRange") BigDecimal endRange,
			@Param("events") List<Event> events);

	List<RateCard> findByEventId(Event eventid);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM RateCard rc WHERE rc.eventId.id= :eventId")
	void deleteEventRateCard(@Param("eventId") int eventId);

}
