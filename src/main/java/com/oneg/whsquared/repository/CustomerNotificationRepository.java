/**
 * 
 */
package com.oneg.whsquared.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.CustomerNotification;

/**
 * @author Anbukkani G
 *
 */
@RepositoryRestResource
public interface CustomerNotificationRepository extends PagingAndSortingRepository<CustomerNotification, Integer> {

}
