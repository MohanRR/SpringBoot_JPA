/**
 * 
 */
package com.oneg.whsquared.repository;

import com.oneg.whsquared.entity.Category;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oneg.whsquared.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anbukkani G
 *
 */
// @RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	// @RestResource
	public List<User> findByUserName(/* @Param("userName") */ String userName);
        
	public User findOneByUserName(String userName);
        
        Page<User>findAll(Pageable pageable);
        
        @Query(value = "SELECT c FROM User c WHERE c.firstName like CONCAT('%', :searchText, '%')or c.lastName like CONCAT('%', :searchText, '%') or  c.userName like CONCAT('%', :searchText, '%') or  c.email like CONCAT('%', :searchText, '%')")
	Page<User> search(@Param("searchText") String searchText, Pageable pageable);
        
        @Query(value = "SELECT c FROM User c WHERE c.userName = :userName ")
	User findUser(@Param("userName") String userName);
        
        public User findByEmail(String email);

        @Modifying
	@Transactional
	@Query(value = "DELETE FROM User rc WHERE rc.id= :id")
	void deleteUser(@Param("id") int id);        

}
