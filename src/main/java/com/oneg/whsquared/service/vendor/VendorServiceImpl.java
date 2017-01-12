package com.oneg.whsquared.service.vendor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneg.whsquared.dao.vendor.VendorAddressDao;
import com.oneg.whsquared.dao.vendor.VendorDao;
import com.oneg.whsquared.dao.vendor.VendorDetailDao;
import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorCategory;
import com.oneg.whsquared.entity.VendorDetail;
import com.oneg.whsquared.repository.AddressRepository;
import com.oneg.whsquared.repository.VendorCategoryRepository;
import com.oneg.whsquared.request.vendor.VendorSaveRequest;
import com.oneg.whsquared.response.vendor.VendorSaveResponse;
import com.oneg.whsquared.util.Util;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorDao vendorDao;

	@Autowired
	private Util util;

	@Autowired
	VendorDetailDao vendorDetailDao;

	@Autowired
	VendorAddressDao vendorAddressDao;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	VendorCategoryRepository vendorCategoryRepository;

	@Override
	public VendorSaveResponse save(VendorSaveRequest request) throws Exception {
		VendorSaveResponse response = new VendorSaveResponse();
		Vendor vendor = request.getVendor();
		VendorDetail vendorDetail = request.getDetail();
		List<VendorCategory> vendorCategorys = vendor.getVendorCategoryList();
		VendorAddress vendorAddress = request.getAddress();
		vendor.setVendorCategoryList(null);
		Vendor savedVendor = null;
		if (vendor != null) {
			/*
			 * if (vendor.getUser() != null) { User user = vendor.getUser();
			 * user.setAvatarUrl(util.uploadFile(user.getAvatarUrl()));
			 * vendor.setUser(user); }
			 */
			savedVendor = vendorDao.save(vendor);
			response.setVendor(vendor);
		}
		if (vendorAddress != null && vendorAddress.getAddress() != null) {
			Address address = vendorAddress.getAddress();
			addressRepository.save(address);
			vendorAddress.setVendor(vendor);
			vendorAddress.setAddress(address);
			vendorAddressDao.save(vendorAddress);
		}
		if (vendorDetail != null) {
			vendorDetail.setLogoUrl(util.uploadFile(vendorDetail.getLogoUrl()));
			vendorDetail.setBackgroundUrl(util.uploadFile(vendorDetail.getBackgroundUrl()));
			vendorDetail.setVendor(vendor);
			vendorDetailDao.save(vendorDetail);
			response.setDetail(vendorDetail);
		} else {
			response.setDetail(new VendorDetail());

		}
		if (vendorCategorys != null && vendorCategorys.size() > 0) {
			List<VendorCategory> vendorCategories = new ArrayList<VendorCategory>();
			for (VendorCategory vendorCategory : vendorCategorys) {
				vendorCategory.setVendor(savedVendor);
				vendorCategoryRepository.save(vendorCategory);
				vendorCategories.add(vendorCategory);
			}
			vendor.setVendorCategoryList(vendorCategories);
			response.setVendor(vendor);
		}
		/*
		 * if (vendorCategorys != null && !vendorCategorys.isEmpty()) { for
		 * (VendorCategory vc : vendorCategorys) { vc.setVendor(vendor); }
		 * vendorCategoryRepository.save(vendorCategorys); }
		 */

		return response;
	}

	@Override
	public VendorSaveResponse update(VendorSaveRequest request) throws Exception {
		VendorSaveResponse response = new VendorSaveResponse();
		Vendor vendor = request.getVendor();
		Vendor vendorOld =vendorDao.getById(vendor.getId());
                List<VendorCategory> exisitingList = vendorOld.getVendorCategoryList();
		VendorDetail vendorDetail = request.getDetail();
		VendorAddress vendorAddress = request.getAddress();
		List<VendorCategory> vendorCategorys = vendor.getVendorCategoryList();
                vendor.setVendorCategoryList(null);
		if (vendor != null) {
			/*
			 * if (vendor.getUser() != null) { User user = vendor.getUser();
			 * user.setAvatarUrl(util.uploadFile(user.getAvatarUrl()));
			 * vendor.setUser(user); }
			 */
			vendorDao.save(vendor);
			response.setVendor(vendor);
		}
		if (vendorDetail != null) {
			vendorDetail.setLogoUrl(util.uploadFile(vendorDetail.getLogoUrl()));
			vendorDetail.setBackgroundUrl(util.uploadFile(vendorDetail.getBackgroundUrl()));
			vendorDetail.setVendor(vendor);
			vendorDetailDao.save(vendorDetail);
			response.setDetail(vendorDetail);
		}
		if (vendorAddress != null) {
			vendorAddressDao.save(vendorAddress);
			response.setAddress(vendorAddress);
		}

                Set<Integer> notRemoveIds = new TreeSet<>();
		if (vendorCategorys != null && !vendorCategorys.isEmpty()) {
			vendor = vendorDao.getById(vendor.getId());
			List<VendorCategory> removeList = new ArrayList<>();
                        for (VendorCategory vc : vendorCategorys) {
                        vc.setVendor(vendor);
                        if (vc.getSubcategory() != null) {
                             VendorCategory validate = vendorCategoryRepository.getVendorCategory(vc.getCategory().getId(), vendor.getId(), vc.getSubcategory().getId());
                            if ( validate == null) {
                                vc = vendorCategoryRepository.save(vc);
                            }else {
                                vc= validate;
                            }
                        } else{ 
                            VendorCategory validate = vendorCategoryRepository.getVendorCategoryWithoutSub(vc.getCategory().getId(), vendor.getId());
                            if (validate == null) {
                                vc = vendorCategoryRepository.save(vc);
                            }else {
                                vc= validate;
                            }
                        }
                        notRemoveIds.add(vc.getId());
                    }
                          
                        }
                if(!notRemoveIds.isEmpty()) {
                    vendorCategoryRepository.deleteVendorCategory(notRemoveIds, vendor.getId());
                }
		return response;
	}
        
        private boolean isRemovable(VendorCategory evc,  List<VendorCategory> exisitingList) {
                if(evc.getId() == null) {
                return false;
                }
                
            for (VendorCategory vc : exisitingList) {
                if(evc.getId() == vc.getId()) {
                return false;
                }
            }
            return true;
        }
	@Override
	public Vendor getById(Integer id) {
		return vendorDao.getById(id);
	}

	@Override
	public List<Vendor> getAll() {
		return vendorDao.getAll();
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean result = false;
		vendorDao.deleteById(id);
		result = true;
		return result;
	}

}
