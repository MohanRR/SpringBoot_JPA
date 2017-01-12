/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.Reminder;

/**
 * @author arivu
 * 
 */
public interface ReminderRepository extends PagingAndSortingRepository<Reminder, Integer> {

	public Reminder findByCustomerAndEvent(Customer customer, Event event);

	@Query(value = "SELECT x FROM Reminder x WHERE x.reminderSent=false")
	public List<Reminder> findByReminderSent();

	@Query(value = "SELECT x FROM Reminder x WHERE x.reminderSent=false AND x.reminderDateTime =:today")
	public List<Reminder> findByReminderSentAndTodaysReminders(@Param("today") Date date);

	List<Reminder> findByCustomer(Customer customer);

	List<Reminder> findByEvent(Event event);
}
