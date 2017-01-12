package com.oneg.whsquared.dao.vendor;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.repository.RoleRepository;
import com.oneg.whsquared.repository.UserRepository;
import com.oneg.whsquared.repository.vendor.VendorRepository;

@Repository
public class VendorDaoImpl implements VendorDao {

	@Autowired
	VendorRepository vendorRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRpository;

	@Override
	public Vendor save(Vendor vendor) throws Exception {
		/*
		 * User user = vendor.getUserVendorList().get(0).getUser(); if
		 * (user.getId() == null || user.getId() == 0) { String password =
		 * user.getPasswordTemp(); password = DigestUtils.md5Hex(password);
		 * user.setPassword(password); List<Role> roles =
		 * roleRpository.findByName("ROLE_VENDOR"); if (!roles.isEmpty()) {
		 * user.setRole(roles.get(0)); } else { throw new Exception(
		 * "Role need to config in masterdata. Contact system admin"); } String
		 * email = user.getEmail(); if (email == null || email.isEmpty()) {
		 * throw new Exception("Email id Missing"); } User duplicate =
		 * userRepository.findByEmail(email); if (duplicate != null) { throw new
		 * Exception("Email id already exist"); } String userName =
		 * user.getUserName(); if (userName == null || userName.isEmpty()) {
		 * throw new Exception("username id Missing"); } duplicate =
		 * userRepository.findUser(userName); if (duplicate != null) { throw new
		 * Exception("Username  already exist"); } } else { String email =
		 * user.getEmail(); if (email == null || email.isEmpty()) { throw new
		 * Exception("Email id Missing"); } User duplicate =
		 * userRepository.findByEmail(email); if (duplicate != null &&
		 * !(duplicate.getId().equals(user.getId()))) { throw new Exception(
		 * "Email id already exist"); } String userName = user.getUserName(); if
		 * (userName == null || userName.isEmpty()) { throw new Exception(
		 * "username id Missing"); } duplicate =
		 * userRepository.findUser(userName); if (duplicate != null &&
		 * !(duplicate.getId().equals(user.getId()))) { throw new Exception(
		 * "Username  already exist"); } } userRepository.save(user);
		 */
		// vendor.setUser(user);
		vendorRepo.save(vendor);
		return vendor;
	}

	@Override
	public Vendor getById(Integer vendorId) {
		Vendor vendor = null;
		// if (CommonUtils.validateString(vendorId))
		vendor = vendorRepo.findOne(vendorId);
		return vendor;
	}

	@Override
	public List<Vendor> getAll() {
		List<Vendor> vendorList = Lists.newArrayList(vendorRepo.findAll());
		return vendorList;
	}

	@Override
	public boolean deleteById(Integer vendorId) {
		boolean result = false;
		// if (CommonUtils.validateString(vendorId)) {
		vendorRepo.delete(vendorId);
		result = true;
		// }
		return result;
	}

}
