package com.oneg.whsquared.controller.vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.controller.EventController;
import com.oneg.whsquared.controller.WHCommon;
import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.CustomerFavoriteEvents;
import com.oneg.whsquared.entity.CustomerFavoriteVendor;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.entity.UserVendors;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorCategory;
import com.oneg.whsquared.entity.VendorDetail;
import com.oneg.whsquared.entity.VendorView;
import com.oneg.whsquared.repository.AddressRepository;
import com.oneg.whsquared.repository.CustomerFavoriteRepository;
import com.oneg.whsquared.repository.EventRepository;
import com.oneg.whsquared.repository.RoleRepository;
import com.oneg.whsquared.repository.StateRepository;
import com.oneg.whsquared.repository.UserRepository;
import com.oneg.whsquared.repository.UserVendorRepository;
import com.oneg.whsquared.repository.VendorCategoryRepository;
import com.oneg.whsquared.repository.VendorViewRepository;
import com.oneg.whsquared.repository.vendor.VendorAddressRepository;
import com.oneg.whsquared.repository.vendor.VendorRepository;
import com.oneg.whsquared.request.vendor.VendorSaveRequest;
import com.oneg.whsquared.response.vendor.VendorPaginationResponse;
import com.oneg.whsquared.response.vendor.VendorSaveResponse;
import com.oneg.whsquared.service.vendor.VendorService;
import com.oneg.whsquared.util.Helper;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	VendorRepository vendorRepo;

	@Autowired
	private WHCommon whCommon;

	@Autowired
	private VendorViewRepository vendorViewRepository;

	@Autowired
	private VendorAddressRepository vendorAddressRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private Util<Object> util;

	@Autowired
	private EventController eventController;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRpository;

	@Autowired
	private VendorCategoryRepository vendorCategoryRepository;

	@Autowired
	private UserVendorRepository userVendorRepository;

	@Autowired
	private CustomerFavoriteRepository customerFavoriteRepository;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseType<Object> create(@RequestBody VendorSaveRequest vendor) {
		VendorSaveResponse data = new VendorSaveResponse();
		try {
			VendorAddress address = vendor.getAddress();
			State state = stateRepository.findOne(address.getAddress().getState().getId());
			address.getAddress().getState().setName(state.getName());
			// Added by Aariv
			String latLongs[] = Helper.getLatLongPositions(vendor.getAddress().getFullAddress());
			address.getAddress().setLat(latLongs[0]);
			address.getAddress().setLong1(latLongs[1]);
			if (vendor != null && vendor.getVendor() != null) {
				List<UserVendors> userVendors = userVendorRepository.findByVendorId(vendor.getVendor().getId());
				vendor.getVendor().setUserVendorList(userVendors);
			}
			data = vendorService.save(vendor);
			address = vendorAddressRepository.save(address);
			data.setAddress(address);

			// vendor.setAddress(address);
			// vendor = vendorAddressRepository.save(vendorAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", data);
	}

	@RequestMapping(value = "/{vendorId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> getById(@PathVariable("vendorId") String vendorId) {
		Vendor vendor = null;
		List<VendorAddress> vendorAddress = null;
		VendorSaveResponse vendorResponse = new VendorSaveResponse();
		try {
			vendor = vendorRepo.findOne(Integer.parseInt(vendorId));
			vendorResponse.setVendor(vendor);
			vendorResponse.setDetail(vendor.getVendorDetail());
			// vendorResponse.setVendorCategoryList(vendor.getVendorCategoryList());
			int Id = Integer.parseInt(vendorId);
			vendorAddress = vendorAddressRepository.findByVendorId(Id);
			if (vendorAddress != null && vendorAddress.size() > 0)
				vendorResponse.setAddress(vendorAddress.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorResponse);
	}

	@RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> getByUserId(@PathVariable("userId") int userId, @RequestParam("page") int page, @RequestParam("size") int size) {
		VendorPaginationResponse vendorPaginationResponse = new VendorPaginationResponse();
		List<VendorSaveResponse> vendorResponseList = new ArrayList<VendorSaveResponse>();
		if (userId > 0) {
			Vendor vendor = null;
			List<VendorAddress> vendorAddress = null;
			try {
				PageRequest request = new PageRequest(page, size);
				Page<UserVendors> userVendors = userVendorRepository.findByUserIdWithPage(userId, request);
				if (userVendors != null) {
					for (UserVendors userVendor : userVendors) {
						VendorSaveResponse vendorResponse = new VendorSaveResponse();
						vendor = vendorRepo.findOne(userVendor.getVendor().getId());
						vendorResponse.setVendor(vendor);
						vendorResponse.setDetail(vendor.getVendorDetail());
						vendorAddress = vendorAddressRepository.findByVendorId(userVendor.getVendor().getId());
						if (vendorAddress != null && vendorAddress.size() > 0)
							vendorResponse.setAddress(vendorAddress.get(0));
						vendorResponseList.add(vendorResponse);

					}
				}
				vendorPaginationResponse.setVendorSaveResponseList(vendorResponseList);
				vendorPaginationResponse.setFirst(userVendors.isFirst());
				vendorPaginationResponse.setLast(userVendors.isLast());
				vendorPaginationResponse.setTotalElements(userVendors.getTotalElements());
				vendorPaginationResponse.setTotalPages(userVendors.getTotalPages());
				vendorPaginationResponse.setSize(userVendors.getSize());
				vendorPaginationResponse.setNumber(userVendors.getNumber());
				vendorPaginationResponse.setNumberOfElements(userVendors.getNumberOfElements());
				vendorPaginationResponse.setSort(userVendors.getSort());
			} catch (Exception e) {
				e.printStackTrace();
				return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
			}
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorPaginationResponse);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType<Object> update(@RequestBody VendorSaveRequest vendor) {
		try {
			List<VendorCategory> vendorCategorys = vendor.getVendor().getVendorCategoryList();
			List<UserVendors> userVendors = new ArrayList<UserVendors>();
			if (vendor != null && vendor.getVendor() != null && vendor.getVendor().getId() > 0) {
				userVendors = userVendorRepository.findByVendorId(vendor.getVendor().getId());
				vendor.getVendor().setUserVendorList(userVendors);
			}

			VendorSaveResponse data = vendorService.update(vendor);

			if (userVendors != null && userVendors.size() > 0) {
				for (UserVendors userVendor : userVendors) {
					userVendorRepository.save(userVendor);
				}
			}
			// VendorAddress vendorAddress = vendor.getAddress();
			Address address = vendor.getAddress().getAddress();
			address = addressRepository.save(address);
			vendor.getAddress().setAddress(address);
			// vendorAddress.setAddress(address);
			data.setAddress(vendor.getAddress());
			Set<Integer> removeIds = new TreeSet<>();
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	@RequestMapping(value = "/{vendorId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType<Object> delete(@PathVariable("vendorId") String vendorId) {
		try {
			Vendor vendor = vendorRepo.findOne(Integer.parseInt(vendorId));

			List<CustomerFavoriteVendor> customerFavoriteVendors = vendor.getCustomerFavoriteVendorList();

			List<VendorAddress> vendorAddressList = vendorAddressRepository.findByVendorId(vendor.getId());
			List<UserVendors> userVendors = vendor.getUserVendorList();
			for (UserVendors userVendor : userVendors) {
				userVendorRepository.delete(userVendor);
			}

			for (VendorCategory vendorCateory : vendor.getVendorCategoryList()) {
				vendorCategoryRepository.delete(vendorCateory);
			}

			for (VendorAddress vendorAddress : vendorAddressList) {
				vendorAddressRepository.delete(vendorAddress);
			}
			List<Event> events = eventRepository.findByVendorId(vendor.getId());
			for (Event event : events) {
				List<CustomerFavoriteEvents> favoriteEvents = customerFavoriteRepository.findByEvent(event);
				for (CustomerFavoriteEvents customerFavoriteEvents : favoriteEvents) {
					customerFavoriteRepository.delete(customerFavoriteEvents);
				}
				eventController.delete(event.getId());
			}
			if (!customerFavoriteVendors.isEmpty()) {
				whCommon.deleteCustomerFavoriteVendors(customerFavoriteVendors);
			}
			// VendorDetail vendorDetail=vendor.getVendorDetail();
			List<VendorView> vendorViews = vendor.getVendorViewList();
			for (VendorView vendorView : vendorViews) {
				vendorViewRepository.delete(vendorView);
			}
			vendorRepo.delete(Integer.parseInt(vendorId));
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		}
		return util.response(WHStatus.SUCCESS.value(), "", null);
	}

	@Transactional
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getCategories() throws Exception {
		List<Vendor> vendors = vendorRepo.findAll();
		return util.response(WHStatus.SUCCESS.value(), "", vendors);
	}

	@Transactional
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> search(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("searchText") String searchText) {
		Page<Vendor> vendorList = null;
		JSONObject jb = new JSONObject();
		List<VendorSaveRequest> list = new ArrayList<VendorSaveRequest>();
		try {
			PageRequest request = new PageRequest(page, size);
			if (searchText != null && !searchText.equalsIgnoreCase("\"\"")) {
				vendorList = vendorRepo.searchByNameDesc(searchText, request);
			} else {
				vendorList = vendorRepo.findAll(request);
			}
			for (Vendor vendor : vendorList) {
				VendorSaveRequest vendorSaveRequest = new VendorSaveRequest();
				vendorSaveRequest.setVendor(vendor);
				vendorSaveRequest.setDetail(vendor.getVendorDetail());
				list.add(vendorSaveRequest);
			}
			// vendorList.get
			jb.put("vendors", list);
			jb.put("size", vendorList.getSize());
			jb.put("totalElements", vendorList.getTotalElements());
			jb.put("totalPages", vendorList.getTotalPages());
			jb.put("numberOfElements", vendorList.getNumberOfElements());
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), jb);
		}
		return util.response(WHStatus.SUCCESS.value(), "", jb);
	}

	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> vendors(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<Vendor> vendorList = null;
		JSONObject jb = new JSONObject();
		List<VendorSaveRequest> list = new ArrayList<VendorSaveRequest>();
		try {
			PageRequest request = new PageRequest(page, size);
			vendorList = vendorRepo.findAll(request);
			for (Vendor vendor : vendorList) {
				VendorSaveRequest vendorSaveRequest = new VendorSaveRequest();
				vendorSaveRequest.setVendor(vendor);
				vendorSaveRequest.setDetail(vendor.getVendorDetail());
				list.add(vendorSaveRequest);
			}
			// vendorList.get
			jb.put("vendors", list);
			jb.put("size", vendorList.getSize());
			jb.put("totalElements", vendorList.getTotalElements());
			jb.put("totalPages", vendorList.getTotalPages());
			jb.put("numberOfElements", vendorList.getNumberOfElements());
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), jb);
		}
		return util.response(WHStatus.SUCCESS.value(), "", jb);
	}

	@Transactional
	@RequestMapping(value = "/address/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> vendorAddressList(@RequestParam("vendorId") int vendorId) {
		List<VendorAddress> vendorAddressList = null;
		try {

			vendorAddressList = vendorAddressRepository.findByVendorId(vendorId);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), vendorAddressList);
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorAddressList);
	}

	@Transactional
	@RequestMapping(value = "/address/get/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseType<Object> getVendorAddress(@PathVariable("id") int id) {
		VendorAddress vendorAddress = null;
		try {
			vendorAddress = vendorAddressRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), vendorAddress);
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorAddress);
	}

	@Transactional
	@RequestMapping(value = "/address/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseType<Object> deleteAddress(@PathVariable("id") int id) {
		VendorAddress vendorAddress = null;
		try {
			vendorAddressRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), vendorAddress);
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorAddress);
	}

	@Transactional
	@RequestMapping(value = "/address/save", method = RequestMethod.POST)
	public @ResponseBody
	ResponseType<Object> saveVendorAddress(@RequestBody VendorAddress vendorAddress) {
		try {
			Address address = vendorAddress.getAddress();
			addressRepository.save(address);
			vendorAddress.setAddress(address);
			vendorAddress = vendorAddressRepository.save(vendorAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), vendorAddress);
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorAddress);
	}

	@Transactional
	@RequestMapping(value = "/address/update", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseType<Object> updateVendorAddress(@RequestBody VendorAddress vendorAddress) {
		try {
			vendorAddress = vendorAddressRepository.save(vendorAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), vendorAddress);
		}
		return util.response(WHStatus.SUCCESS.value(), "", vendorAddress);
	}

	@RequestMapping(value = "/user-vendor/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseType getByVendorId(@PathVariable("userId") int userId) {
		try {
			return util.response(WHStatus.SUCCESS.value(), "", vendorRepo.findByUserId(userId));
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), "internal error", "");
		}
	}

	@RequestMapping(value = "/delete-image/{vendorId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseType deleteImage(@PathVariable("vendorId") int vendorId, @RequestParam("property") String property, @RequestParam("uri") String uri) {
		try {
			Vendor vendor = vendorRepo.findOne(vendorId);
			VendorDetail vendorDetail = vendor.getVendorDetail();
			if (property.equalsIgnoreCase("logo")) {
				if (!uri.equalsIgnoreCase(vendorDetail.getLogoUrl())) {
					return util.response(WHStatus.FAILURE.value(), "invalid resource", "");
				}
				vendorDetail.setLogoUrl(null);
			}
			if (property.equalsIgnoreCase("backgroundurl")) {
				if (!uri.equalsIgnoreCase(vendorDetail.getBackgroundUrl())) {
					return util.response(WHStatus.FAILURE.value(), "invalid resource", "");
				}
				vendorDetail.setBackgroundUrl(null);
			}
			boolean deleteFile = util.deleteFile(uri);
			vendor.setVendorDetail(vendorDetail);
			vendorRepo.save(vendor);
			return util.response(WHStatus.SUCCESS.value(), "", deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), "internal error", "");
		}
	}

}
