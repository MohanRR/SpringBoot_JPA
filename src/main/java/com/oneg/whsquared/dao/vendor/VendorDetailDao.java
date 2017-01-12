package com.oneg.whsquared.dao.vendor;

import java.util.List;

import com.oneg.whsquared.entity.VendorDetail;

public interface VendorDetailDao {

	VendorDetail save(VendorDetail vendorDetail);

	VendorDetail getById(Integer vendorDetailId);

	List<VendorDetail> getAll();

	boolean deleteById(Integer vendorDetailId);

}
