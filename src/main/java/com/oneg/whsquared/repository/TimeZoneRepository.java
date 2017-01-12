/**
 * @author Anbukkani G
 */
package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.TimeZone;

@RepositoryRestResource
public interface TimeZoneRepository extends PagingAndSortingRepository<TimeZone, Integer> {

	List<TimeZone> findAll();

}
