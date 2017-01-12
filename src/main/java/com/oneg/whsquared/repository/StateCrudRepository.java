/**
 * 
 */
package com.oneg.whsquared.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneg.whsquared.entity.State;

/**
 * @author Anbukkani G
 * 
 */
public interface StateCrudRepository extends JpaRepository<State, Integer> {

}
