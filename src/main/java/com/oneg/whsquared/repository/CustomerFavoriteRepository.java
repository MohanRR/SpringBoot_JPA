/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerFavoriteEvents;
import com.oneg.whsquared.entity.Event;

/**
 * @author arivu
 * 
 */
public interface CustomerFavoriteRepository extends PagingAndSortingRepository<CustomerFavoriteEvents, Integer> {

	List<CustomerFavoriteEvents> findByCustomerId(Customer customerid);

	CustomerFavoriteEvents findByEventAndCustomerId(Event event, Customer customerid);

	List<CustomerFavoriteEvents> findByEvent(Event event);

	@Query(value = "SELECT x FROM CustomerFavoriteEvents x WHERE x.mailSent=false")
	public List<CustomerFavoriteEvents> findByMailSent();

	@Modifying
	@Query(value = "DELETE FROM CustomerFavoriteEvents cf WHERE cf.event =:event AND cf.customerId =:customerid")
	@Transactional
	void deleteByEventAndCustomerId(@Param("event") Event event, @Param("customerid") Customer customerid);
}
