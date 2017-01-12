package com.oneg.whsquared.dao.vendor;

import java.util.List;

import com.oneg.whsquared.entity.VendorAddress;

public interface VendorAddressDao {

	VendorAddress save(VendorAddress vendorAddress);

	VendorAddress getById(Integer vendorAddressId);

	List<VendorAddress> getAll();

	boolean deleteById(Integer vendorAddressId);

}
