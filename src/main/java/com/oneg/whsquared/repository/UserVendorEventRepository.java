package com.oneg.whsquared.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oneg.whsquared.entity.UserVendorEvents;

@RepositoryRestResource
public interface UserVendorEventRepository extends PagingAndSortingRepository<UserVendorEvents, Integer> {

}
