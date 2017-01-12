/**
 * 
 */
package com.oneg.whsquared.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.oneg.whsquared.entity.State;

/**
 * @author Anbukkani G
 * 
 */
public interface StateRepository extends JpaRepository<State, Integer>, PagingAndSortingRepository<State, Integer> {

	public List<State> findByName(@Param(value = "name") String name);

	public State findStateByName(@Param(value = "name") String name);

	public State findStateByAbbreviation(@Param(value = "abbreviation") String abbreviation);

}
