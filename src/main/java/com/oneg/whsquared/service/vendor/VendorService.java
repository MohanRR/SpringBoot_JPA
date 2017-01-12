package com.oneg.whsquared.service.vendor;

import java.util.List;

import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.request.vendor.VendorSaveRequest;
import com.oneg.whsquared.response.vendor.VendorSaveResponse;

public interface VendorService {

	VendorSaveResponse save(VendorSaveRequest request)  throws Exception;

	VendorSaveResponse update(VendorSaveRequest requestss) throws Exception;

	Vendor getById(Integer id);

	List<Vendor> getAll();

	boolean deleteById(Integer id);

}
