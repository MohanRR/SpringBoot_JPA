/**
 * 
 */
package com.oneg.whsquared.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.oneg.whsquared.repository.AddressRepository;
import com.oneg.whsquared.repository.CategoryRepository;
import com.oneg.whsquared.repository.CustomerFavoriteCategoryRepository;
import com.oneg.whsquared.repository.CustomerFavoriteRepository;
import com.oneg.whsquared.repository.CustomerFavoriteVendorRepository;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.EventAddressRepository;
import com.oneg.whsquared.repository.EventAgendaRepository;
import com.oneg.whsquared.repository.EventCalenderRepository;
import com.oneg.whsquared.repository.EventCategoryModalRepository;
import com.oneg.whsquared.repository.EventCategoryRepository;
import com.oneg.whsquared.repository.EventDetailRepository;
import com.oneg.whsquared.repository.EventRepository;
import com.oneg.whsquared.repository.PreferenceRepository;
import com.oneg.whsquared.repository.RateCardRepository;
import com.oneg.whsquared.repository.ReminderRepository;
import com.oneg.whsquared.repository.StateRepository;
import com.oneg.whsquared.repository.VendorCategoryRepository;
import com.oneg.whsquared.repository.WHVersionDetailsRepository;
import com.oneg.whsquared.repository.vendor.VendorAddressRepository;
import com.oneg.whsquared.repository.vendor.VendorDetailRepository;
import com.oneg.whsquared.repository.vendor.VendorRepository;
import com.oneg.whsquared.security.CustomerAuthenticationProvider;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.util.ControllerUtil;
import com.oneg.whsquared.util.Helper;
import com.oneg.whsquared.util.Util;

/**
 * @author arivu
 * 
 */
public abstract class BaseDependecy {

	@Autowired
	protected JwtUtil jwtUtil;

	@Autowired
	protected CategoryRepository categoryRepository;

	@Autowired
	protected PreferenceRepository preferenceRepository;

	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected EventAddressRepository eventAddressRepository;

	@Autowired
	protected EventAgendaRepository eventAgendaRepository;

	@Autowired
	protected RateCardRepository rateCardRepository;

	@Autowired
	protected CustomerFavoriteRepository customerFavoriteRepository;

	@Autowired
	protected EventCategoryRepository eventCategoryRepository;

	@Autowired
	protected EventDetailRepository eventDetailRepository;

	@Autowired
	protected ReminderRepository reminderRepository;

	@Autowired
	protected EventRepository eventRepository;

	@Autowired
	protected VendorAddressRepository vendorAddressRepository;

	@Autowired
	protected CustomerFavoriteVendorRepository customerFavoriteVendorRepository;

	@Autowired
	protected VendorRepository vendorRepository;

	@SuppressWarnings("rawtypes")
	@Autowired
	protected Util util;

	@Autowired
	protected EventCalenderRepository eventCalenderRepository;

	@Autowired
	protected EventCategoryModalRepository eventCategoryModalRepository;

	@Autowired
	protected WHVersionDetailsRepository whVersionDetailsRepository;

	@Autowired
	protected VendorCategoryRepository vendorCategoryRepository;

	@Autowired
	protected VendorDetailRepository vendorDetailRepository;

	@Autowired
	protected Helper helper;

	@Autowired
	protected CustomerFavoriteCategoryRepository customerFavoriteCategoryRepository;

	@Autowired
	protected ControllerUtil controllerUtil;

	@Autowired
	protected AddressRepository addressRepository;

	@Autowired
	protected StateRepository stateRepository;

	@Autowired
	protected CustomerAuthenticationProvider customerAuthenticationProvider;

	/**
	 * @return the jwtUtil
	 */
	public JwtUtil getJwtUtil() {
		return jwtUtil;
	}

	/**
	 * @param jwtUtil
	 *            the jwtUtil to set
	 */
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	/**
	 * @return the categoryRepository
	 */
	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	/**
	 * @param categoryRepository
	 *            the categoryRepository to set
	 */
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * @return the preferenceRepository
	 */
	public PreferenceRepository getPreferenceRepository() {
		return preferenceRepository;
	}

	/**
	 * @param preferenceRepository
	 *            the preferenceRepository to set
	 */
	public void setPreferenceRepository(PreferenceRepository preferenceRepository) {
		this.preferenceRepository = preferenceRepository;
	}

	/**
	 * @return the customerRepository
	 */
	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	/**
	 * @param customerRepository
	 *            the customerRepository to set
	 */
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * @return the eventAddressRepository
	 */
	public EventAddressRepository getEventAddressRepository() {
		return eventAddressRepository;
	}

	/**
	 * @param eventAddressRepository
	 *            the eventAddressRepository to set
	 */
	public void setEventAddressRepository(EventAddressRepository eventAddressRepository) {
		this.eventAddressRepository = eventAddressRepository;
	}

	/**
	 * @return the eventAgendaRepository
	 */
	public EventAgendaRepository getEventAgendaRepository() {
		return eventAgendaRepository;
	}

	/**
	 * @param eventAgendaRepository
	 *            the eventAgendaRepository to set
	 */
	public void setEventAgendaRepository(EventAgendaRepository eventAgendaRepository) {
		this.eventAgendaRepository = eventAgendaRepository;
	}

	/**
	 * @return the rateCardRepository
	 */
	public RateCardRepository getRateCardRepository() {
		return rateCardRepository;
	}

	/**
	 * @param rateCardRepository
	 *            the rateCardRepository to set
	 */
	public void setRateCardRepository(RateCardRepository rateCardRepository) {
		this.rateCardRepository = rateCardRepository;
	}

	/**
	 * @return the customerFavoriteRepository
	 */
	public CustomerFavoriteRepository getCustomerFavoriteRepository() {
		return customerFavoriteRepository;
	}

	/**
	 * @param customerFavoriteRepository
	 *            the customerFavoriteRepository to set
	 */
	public void setCustomerFavoriteRepository(CustomerFavoriteRepository customerFavoriteRepository) {
		this.customerFavoriteRepository = customerFavoriteRepository;
	}

	/**
	 * @return the eventCategoryRepository
	 */
	public EventCategoryRepository getEventCategoryRepository() {
		return eventCategoryRepository;
	}

	/**
	 * @param eventCategoryRepository
	 *            the eventCategoryRepository to set
	 */
	public void setEventCategoryRepository(EventCategoryRepository eventCategoryRepository) {
		this.eventCategoryRepository = eventCategoryRepository;
	}

	/**
	 * @return the eventDetailRepository
	 */
	public EventDetailRepository getEventDetailRepository() {
		return eventDetailRepository;
	}

	/**
	 * @param eventDetailRepository
	 *            the eventDetailRepository to set
	 */
	public void setEventDetailRepository(EventDetailRepository eventDetailRepository) {
		this.eventDetailRepository = eventDetailRepository;
	}

	/**
	 * @return the reminderRepository
	 */
	public ReminderRepository getReminderRepository() {
		return reminderRepository;
	}

	/**
	 * @param reminderRepository
	 *            the reminderRepository to set
	 */
	public void setReminderRepository(ReminderRepository reminderRepository) {
		this.reminderRepository = reminderRepository;
	}

	/**
	 * @return the eventRepository
	 */
	public EventRepository getEventRepository() {
		return eventRepository;
	}

	/**
	 * @param eventRepository
	 *            the eventRepository to set
	 */
	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	/**
	 * @return the vendorAddressRepository
	 */
	public VendorAddressRepository getVendorAddressRepository() {
		return vendorAddressRepository;
	}

	/**
	 * @param vendorAddressRepository
	 *            the vendorAddressRepository to set
	 */
	public void setVendorAddressRepository(VendorAddressRepository vendorAddressRepository) {
		this.vendorAddressRepository = vendorAddressRepository;
	}

	/**
	 * @return the customerFavoriteVendorRepository
	 */
	public CustomerFavoriteVendorRepository getCustomerFavoriteVendorRepository() {
		return customerFavoriteVendorRepository;
	}

	/**
	 * @param customerFavoriteVendorRepository
	 *            the customerFavoriteVendorRepository to set
	 */
	public void setCustomerFavoriteVendorRepository(CustomerFavoriteVendorRepository customerFavoriteVendorRepository) {
		this.customerFavoriteVendorRepository = customerFavoriteVendorRepository;
	}

	/**
	 * @return the vendorRepository
	 */
	public VendorRepository getVendorRepository() {
		return vendorRepository;
	}

	/**
	 * @param vendorRepository
	 *            the vendorRepository to set
	 */
	public void setVendorRepository(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}

	/**
	 * @return the util
	 */
	@SuppressWarnings("rawtypes")
	public Util getUtil() {
		return util;
	}

	/**
	 * @param util
	 *            the util to set
	 */
	@SuppressWarnings("rawtypes")
	public void setUtil(Util util) {
		this.util = util;
	}

	/**
	 * @return the eventCalenderRepository
	 */
	public EventCalenderRepository getEventCalenderRepository() {
		return eventCalenderRepository;
	}

	/**
	 * @param eventCalenderRepository
	 *            the eventCalenderRepository to set
	 */
	public void setEventCalenderRepository(EventCalenderRepository eventCalenderRepository) {
		this.eventCalenderRepository = eventCalenderRepository;
	}

	/**
	 * @return the eventCategoryModalRepository
	 */
	public EventCategoryModalRepository getEventCategoryModalRepository() {
		return eventCategoryModalRepository;
	}

	/**
	 * @param eventCategoryModalRepository
	 *            the eventCategoryModalRepository to set
	 */
	public void setEventCategoryModalRepository(EventCategoryModalRepository eventCategoryModalRepository) {
		this.eventCategoryModalRepository = eventCategoryModalRepository;
	}

	/**
	 * @return the whVersionDetailsRepository
	 */
	public WHVersionDetailsRepository getWhVersionDetailsRepository() {
		return whVersionDetailsRepository;
	}

	/**
	 * @param whVersionDetailsRepository
	 *            the whVersionDetailsRepository to set
	 */
	public void setWhVersionDetailsRepository(WHVersionDetailsRepository whVersionDetailsRepository) {
		this.whVersionDetailsRepository = whVersionDetailsRepository;
	}

	/**
	 * @return the vendorCategoryRepository
	 */
	public VendorCategoryRepository getVendorCategoryRepository() {
		return vendorCategoryRepository;
	}

	/**
	 * @param vendorCategoryRepository
	 *            the vendorCategoryRepository to set
	 */
	public void setVendorCategoryRepository(VendorCategoryRepository vendorCategoryRepository) {
		this.vendorCategoryRepository = vendorCategoryRepository;
	}

	/**
	 * @return the vendorDetailRepository
	 */
	public VendorDetailRepository getVendorDetailRepository() {
		return vendorDetailRepository;
	}

	/**
	 * @param vendorDetailRepository
	 *            the vendorDetailRepository to set
	 */
	public void setVendorDetailRepository(VendorDetailRepository vendorDetailRepository) {
		this.vendorDetailRepository = vendorDetailRepository;
	}

	/**
	 * @return the helper
	 */
	public Helper getHelper() {
		return helper;
	}

	/**
	 * @param helper
	 *            the helper to set
	 */
	public void setHelper(Helper helper) {
		this.helper = helper;
	}

	/**
	 * @return the customerFavoriteCategoryRepository
	 */
	public CustomerFavoriteCategoryRepository getCustomerFavoriteCategoryRepository() {
		return customerFavoriteCategoryRepository;
	}

	/**
	 * @param customerFavoriteCategoryRepository
	 *            the customerFavoriteCategoryRepository to set
	 */
	public void setCustomerFavoriteCategoryRepository(CustomerFavoriteCategoryRepository customerFavoriteCategoryRepository) {
		this.customerFavoriteCategoryRepository = customerFavoriteCategoryRepository;
	}

	/**
	 * @return the controllerUtil
	 */
	public ControllerUtil getControllerUtil() {
		return controllerUtil;
	}

	/**
	 * @param controllerUtil
	 *            the controllerUtil to set
	 */
	public void setControllerUtil(ControllerUtil controllerUtil) {
		this.controllerUtil = controllerUtil;
	}

	/**
	 * @return the addressRepository
	 */
	public AddressRepository getAddressRepository() {
		return addressRepository;
	}

	/**
	 * @param addressRepository
	 *            the addressRepository to set
	 */
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	/**
	 * @return the stateRepository
	 */
	public StateRepository getStateRepository() {
		return stateRepository;
	}

	/**
	 * @param stateRepository
	 *            the stateRepository to set
	 */
	public void setStateRepository(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	/**
	 * @return the customerAuthenticationProvider
	 */
	public CustomerAuthenticationProvider getCustomerAuthenticationProvider() {
		return customerAuthenticationProvider;
	}

	/**
	 * @param customerAuthenticationProvider
	 *            the customerAuthenticationProvider to set
	 */
	public void setCustomerAuthenticationProvider(CustomerAuthenticationProvider customerAuthenticationProvider) {
		this.customerAuthenticationProvider = customerAuthenticationProvider;
	}

}
