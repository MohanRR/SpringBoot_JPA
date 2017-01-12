package com.oneg.whsquared.dao.vendor;

import java.util.List;

import com.oneg.whsquared.entity.Vendor;

public interface VendorDao {

	Vendor save(Vendor vendor) throws Exception;

	Vendor getById(Integer vendorId);

	List<Vendor> getAll();

	boolean deleteById(Integer vendorId);

}
