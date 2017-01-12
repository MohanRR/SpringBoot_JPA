package com.oneg.whsquared.dao.vendor;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oneg.whsquared.entity.VendorDetail;
import com.oneg.whsquared.repository.vendor.VendorDetailRepository;

@Repository
public class VendorDetailDaoImpl implements VendorDetailDao {

	@Autowired
	VendorDetailRepository vendorDetailRepo;

	@Override
	public VendorDetail save(VendorDetail vendorDetail) {
		vendorDetailRepo.save(vendorDetail);
		return vendorDetail;
	}

	@Override
	public VendorDetail getById(Integer vendorDetailId) {
		VendorDetail vendorDetail = null;
		// if (CommonUtils.validateString(vendorDetailId))
		vendorDetail = vendorDetailRepo.findOne(vendorDetailId);
		return vendorDetail;
	}

	@Override
	public List<VendorDetail> getAll() {
		List<VendorDetail> vendorDetailList = Lists.newArrayList(vendorDetailRepo.findAll());
		return vendorDetailList;
	}

	@Override
	public boolean deleteById(Integer vendorDetailId) {
		boolean result = false;
		// if (CommonUtils.validateString(vendorDetailId)) {
		vendorDetailRepo.delete(vendorDetailId);
		result = true;
		// }
		return result;
	}

}
