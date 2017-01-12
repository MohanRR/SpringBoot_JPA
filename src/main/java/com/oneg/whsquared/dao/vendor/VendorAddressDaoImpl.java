package com.oneg.whsquared.dao.vendor;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.repository.vendor.VendorAddressRepository;

@Repository
public class VendorAddressDaoImpl implements VendorAddressDao {

	@Autowired
	VendorAddressRepository vendorAddressRepo;

	@Override
	public VendorAddress save(VendorAddress vendorAddress) {
		vendorAddressRepo.save(vendorAddress);
		return vendorAddress;
	}

	@Override
	public VendorAddress getById(Integer vendorAddressId) {
		VendorAddress vendorAddress = null;
		// if (CommonUtils.validateString(vendorAddressId))
		vendorAddress = vendorAddressRepo.findOne(vendorAddressId);
		return vendorAddress;
	}

	@Override
	public List<VendorAddress> getAll() {
		List<VendorAddress> vendorAddressList = Lists.newArrayList(vendorAddressRepo.findAll());
		return vendorAddressList;
	}

	@Override
	public boolean deleteById(Integer vendorAddressId) {
		boolean result = false;
		// if (CommonUtils.validateString(vendorAddressId)) {
		vendorAddressRepo.delete(vendorAddressId);
		result = true;
		// }
		return result;
	}

}
