/**
 * 
 */
package com.oneg.whsquared.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneg.whsquared.entity.WHVersionDetails;

/**
 * @author arivu
 * 
 */
public interface WHVersionDetailsRepository extends JpaRepository<WHVersionDetails, Integer> {

	WHVersionDetails findByVersionName(String versionname);
}
