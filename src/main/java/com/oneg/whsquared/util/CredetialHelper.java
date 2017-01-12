/**
 * 
 */
package com.oneg.whsquared.util;

import java.util.Arrays;
import java.util.HashSet;

import javax.mail.MessagingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.BaseDependecy;
import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.Role;
import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.entity.WHAppVersion;

/**
 * @author arivu
 * 
 */
@Component
public class CredetialHelper extends BaseDependecy {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public CustomerResponseData onLoginProcess(Customer customer, Device device) {
		Customer customerFromDb = new Customer();
		if (RestValidation.onEmptyvalidation(customer.getRegisterationType())) {
			if (RestValidation.onEmptyvalidation(getRegistrationType(customer))) {
				if (isSocialLogin(getRegistrationType(customer))) {
					if (RestValidation.onEmptyvalidation(customer.getPassword())) {
						customerFromDb = customerRepository.findByRegisterationTypeAndPassword(customer.getRegisterationType(), DigestUtils.md5Hex(customer.getPassword()));
						if (customerFromDb != null) {
							onSecurityAuthendication(customerFromDb);
							controllerUtil.updateRequiredSession(customerFromDb);
							customerFromDb.setDeviceId(customer.getDeviceId());
							customerRepository.save(customerFromDb);
							if (logger.isDebugEnabled()) {
								logger.error("Social login");
							}
							return helper.updateCustomerProfile(customerFromDb, device);
						} else {
							return RestMessage.onErrorMessage("Invalid username or password");
						}
					}
				} else if (!isSocialLogin(getRegistrationType(customer))) {
					if (RestValidation.onEmptyvalidation(customer.getUserName()) && RestValidation.onEmptyvalidation(customer.getPassword())) {
						customerFromDb = customerRepository.findByUserNameAndPassword(customer.getUserName(), DigestUtils.md5Hex(customer.getPassword()));
						if (customerFromDb != null) {
							onSecurityAuthendication(customerFromDb);
							controllerUtil.updateRequiredSession(customerFromDb);
							customerFromDb.setDeviceId(customer.getDeviceId());
							customerRepository.save(customerFromDb);
							return helper.updateCustomerProfile(customerFromDb, device);
						} else {
							return RestMessage.onErrorMessage("Invalid username or password");
						}
					} else {
						return RestMessage.onErrorMessage("Username or password is empty.");
					}
				}
			} else {
				return RestMessage.onErrorMessage("Registeration type is incorrect");
			}
		} else {
			return RestMessage.onErrorMessage("Registeration type is empty");
		}
		return null;
	}

	/**
	 * 
	 * @param customerFromDb
	 * 
	 *            This method is to authenticate a customer into spring security
	 */
	private void onSecurityAuthendication(Customer customerFromDb) {
		Authentication responseAuthentication;
		try {
			UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(customerFromDb.getUserName(), customerFromDb.getPassword());
			responseAuthentication = customerAuthenticationProvider.authenticate(requestAuthentication);

			if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
				throw new Exception("Unable to authenticate Domain User for provided credentials");
			}
			SecurityContextHolder.getContext().setAuthentication(responseAuthentication);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CustomerResponseData onRegistration(Customer customer, Device device) {
		logger.debug("Register Using " + customer.getUserName());
		if (RestValidation.onEmptyvalidation(customer.getRegisterationType())) {
			Customer customerExistInDb = new Customer();
			customerExistInDb = customerRepository.findByEmailAndRegisterationType(customer.getEmail(), getRegistrationType(customer));
			if (customerExistInDb == null) {
				customerExistInDb = new Customer();
				/**
				 * Check for the username which needs to be unique
				 */
				customerExistInDb = customerRepository.findCustomerByUserName(customer.getUserName());
				if (customerExistInDb == null) {
					Address address = customer.getAddress();
					if (address != null) {
						State state = address.getState();
						if (state != null) {
							state = stateRepository.findOne(state.getId());
							if (state != null) {
								address.setState(state);
							} else {
								return RestMessage.onErrorMessage("State not found");
							}
						}
						address = addressRepository.save(address);
						customer.setAddress(address);
					}
					if (customer.getRegisterationType().equalsIgnoreCase(RegistrationType.FACEBOOK.getName())
							|| customer.getRegisterationType().equalsIgnoreCase(RegistrationType.GOOGLE.getName())) {
						customer.setProfileImageUrl(customer.getProfileImageUrl());
					} else {
						customer.setProfileImageUrl(helper.uploadProfileImage(customer.getAvatar()));
					}
					// Verify the app version
					return registerApp(customer, device);
				} else {
					return RestMessage.onErrorMessage("Username is already exist");
				}
			} else {
				return RestMessage.onErrorMessage("User has been registered with this email");
			}
		}
		return RestMessage.onErrorMessage("Registeration type is empty");
	}

	private CustomerResponseData registerApp(Customer customer, Device device) {
		if (customer.getPassword() != null && !customer.getPassword().isEmpty()) {
			customer.setPassword(DigestUtils.md5Hex(customer.getPassword()));
		}

		customer.setRoles(new HashSet<Role>(Arrays.asList(new Role("ROLE_CUSTOMER", "ROLE_CUSTOMER"))));
		Customer customerFromDb = customerRepository.save(customer);
		if (customerFromDb != null) {
			return helper.updateCustomerProfile(customerFromDb, device);
		} else {
			return RestMessage.onErrorMessage("Failed to register customer");
		}
	}

	public ResponseData onAppVersion(String appVersionFromClient) {
		WHAppVersion appVersion = helper.getAppVersions();
		if (appVersion != null) {
			if (appVersion.getAndroidVersion().equals(appVersionFromClient)) {
				return new ResponseData("Success", true, appVersion.getAndroidVersion());
			} else if (appVersion.getiOSVersion().equals(appVersionFromClient)) {
				return new ResponseData("Success", true, appVersion.getiOSVersion());
			} else {
				return new ResponseData("Failure", false, "You are using an old version of this application. Please install new version.");
			}
		} else {
			return new ResponseData("Failure", false, "App version is not updated. Please try later");
		}
	}

	public CustomerResponseData onCustomerExistence(Customer customer, Device device) {
		logger.debug("Register Using " + customer.getUserName());
		if (RestValidation.onEmptyvalidation(customer.getRegisterationType())) {
			if (customer.getRegisterationType().equals(RegistrationType.MANUAL.getName())) {
				if (RestValidation.onEmptyvalidation(customer.getEmail())) {
					return RestMessage.onErrorMessage("Email is empty");
				}
				Customer customerExistInDb = new Customer();
				customerExistInDb = customerRepository.findByEmailAndRegisterationType(RestValidation.validate(customer.getEmail()),
						RestValidation.validate(getRegistrationType(customer)));
				if (customerExistInDb != null) {
					return helper.updateCustomerProfile(customerExistInDb, device);
				} else {
					return RestMessage.onErrorMessage("Customer is not registered");
				}
			} else {
				Customer customerExistInDb = new Customer();
				customerExistInDb = customerRepository.findByRegisterationTypeAndPassword(RestValidation.validate(getRegistrationType(customer)),
						DigestUtils.md5Hex(customer.getPassword()));
				if (customerExistInDb != null) {
					return helper.updateCustomerProfile(customerExistInDb, device);
				} else {
					return RestMessage.onErrorMessage("Customer is not registered");
				}
			}

		}
		return RestMessage.onErrorMessage("Registeration type is empty");
	}

	public boolean isSocialLogin(String registrationType) {
		boolean isSocialLogin = false;
		if (registrationType.equalsIgnoreCase(RegistrationType.FACEBOOK.getName()) || registrationType.equalsIgnoreCase(RegistrationType.GOOGLE.getName())) {
			isSocialLogin = true;
		} else if (registrationType.equalsIgnoreCase(RegistrationType.MANUAL.getName())) {
			isSocialLogin = false;
		}
		return isSocialLogin;
	}

	public String getRegistrationType(Customer customer) {
		String registerType = "";
		if (customer.getRegisterationType().equalsIgnoreCase(RegistrationType.MANUAL.getName())) {
			registerType = RegistrationType.MANUAL.getName();
		} else if (customer.getRegisterationType().equalsIgnoreCase(RegistrationType.FACEBOOK.getName())) {
			registerType = RegistrationType.FACEBOOK.getName();
		} else if (customer.getRegisterationType().equalsIgnoreCase(RegistrationType.GOOGLE.getName())) {
			registerType = RegistrationType.GOOGLE.getName();
		}
		return registerType;
	}

	public void constructRegistrationMailAndSend(Customer customer, String password) throws MessagingException {

		String contentForSocial = "Dear <b>" + customer.getFirstName() + " " + customer.getLastName()
				+ "</b>,<br><br>Your registration with WhatsHappenins is completed successfully with&nbsp;" + customer.getRegisterationType() + "! <br><br>"

				+ "If you have any query, please contact us." + "<br><br>" + "WhatsHappenins" + "<br>" + "whatshapenins@onegservice.com.";

		String content = "Dear <b>" + customer.getFirstName() + " " + customer.getLastName() + "</b>,<br><br>Your registration with WhatsHappenins is completed successfully!"
				+ "<br><br>" + "Below is your login information:" + "<br><br>" + "Username: " + customer.getUserName() + "<br>" + "Password: " + password + "<br><br>"

				+ "If you have any query, please contact us." + "<br><br>" + "WhatsHappenins" + "<br>" + "whatshapenins@onegservice.com.";

		if (getRegistrationType(customer).equalsIgnoreCase(RegistrationType.GOOGLE.getName())
				|| getRegistrationType(customer).equalsIgnoreCase(RegistrationType.FACEBOOK.getName())) {
			EmailFromLocal.sendEmail(contentForSocial, customer.getEmail());
		} else {
			EmailFromLocal.sendEmail(content, customer.getEmail());
		}

	}
}
