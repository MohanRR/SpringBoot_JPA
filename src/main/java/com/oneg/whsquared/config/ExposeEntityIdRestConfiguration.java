/**
 * 
 */
package com.oneg.whsquared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.CustomerDevice;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.Preference;
import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.entity.User;

/**
 * Used for expose the primary key which is annotated with @Id in entity in web
 * service(spring data rest)
 * 
 * @author Anbukkani G
 * 
 */
@Configuration
public class ExposeEntityIdRestConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Category.class);
		config.exposeIdsFor(User.class);
		config.exposeIdsFor(Event.class);
		config.exposeIdsFor(EventCategoryModal.class);
		config.exposeIdsFor(State.class);
		config.exposeIdsFor(Preference.class);
		config.exposeIdsFor(Address.class);
		config.exposeIdsFor(Customer.class);
		config.exposeIdsFor(CustomerDevice.class);
	}
}