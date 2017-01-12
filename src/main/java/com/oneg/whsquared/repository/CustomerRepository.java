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

import com.oneg.whsquared.entity.Customer;

/**
 * @author Anbukkani G
 * 
 */
@Transactional
@RepositoryRestResource
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

	public List<Customer> findByUserName(@Param("userName") String userName);

	public Customer findByUserNameAndPassword(@Param("userName") String username, @Param("password") String password);

	public Customer findByEmail(@Param("email") String email);

	public Customer findByPassword(@Param("password") String password);

	public Customer findByRegisterationTypeAndPassword(@Param("registerationType") String registerationType, @Param("password") String password);

	public Customer findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	public Customer findByEmailAndRegisterationType(@Param("email") String email, @Param("registerationType") String registerationType);

	public Customer findByEmailAndPasswordAndRegisterationType(@Param("email") String email, @Param("password") String password,
			@Param("registerationType") String registerationType);

	public Customer findCustomerByUserName(@Param("userName") String userName);

	@Query(value = "SELECT c FROM Customer c WHERE c.firstName like CONCAT('%', :searchText, '%')or c.lastName like CONCAT('%', :searchText, '%') or  c.userName like CONCAT('%', :searchText, '%') or  c.email like CONCAT('%', :searchText, '%')")
	Page<Customer> search(@Param("searchText") String searchText, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CalendarEvent ce WHERE ce.customerId.id= :id")
	void deleteCalendarEventByCustomerId(@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CustomerFavoriteVendor cfv WHERE cfv.customerId.id= :id")
	void deleteCustomerFavoriteByCustomerId(@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CustomerFavoriteCategory cfc WHERE cfc.customerId.id= :id")
	void deleteCustomerFavoriteCategoryByCustomerId(@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM PasswordVerification pv WHERE pv.customerId.id= :id")
	void deletePasswordVerificationByCustomerId(@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CustomerNotification cn WHERE cn.customer.id= :id")
	void deleteCustomerNotificationByCustomerId(@Param("id") int id);

}
