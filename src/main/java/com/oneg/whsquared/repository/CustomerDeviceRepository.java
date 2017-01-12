/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.CustomerDevice;

/**
 * @author Anbukkani G
 * 
 */
public interface CustomerDeviceRepository extends PagingAndSortingRepository<CustomerDevice, Integer> {

	List<CustomerDevice> findByDeviceIdAndCustomer(@Param(value = "deviceId") String deviceId, @Param(value = "customer") Integer customer);

}