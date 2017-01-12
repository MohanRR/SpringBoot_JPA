/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.controller.CategoryController;
import com.oneg.whsquared.controller.EventController;
import com.oneg.whsquared.controller.vendor.VendorController;
import com.oneg.whsquared.entity.Address;
import com.oneg.whsquared.entity.Category;
import com.oneg.whsquared.entity.Event;
import com.oneg.whsquared.entity.EventCategory;
import com.oneg.whsquared.entity.EventCategoryModal;
import com.oneg.whsquared.entity.EventDetail;
import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.entity.SubCategory;
import com.oneg.whsquared.entity.Vendor;
import com.oneg.whsquared.entity.VendorAddress;
import com.oneg.whsquared.entity.VendorCategory;
import com.oneg.whsquared.entity.VendorDetail;
import com.oneg.whsquared.repository.CategoryRepository;
import com.oneg.whsquared.repository.EventCategoryModalRepository;
import com.oneg.whsquared.repository.StateRepository;
import com.oneg.whsquared.repository.SubCategoryRepository;
import com.oneg.whsquared.request.vendor.VendorSaveRequest;
import com.oneg.whsquared.service.UploadService;
import com.oneg.whsquared.util.ResponseType;

/**
 * 
 * @author Rajan-G
 * 
 * 
 */
@Component
public class UploadServiceImpl implements UploadService {

	@Autowired
	CategoryController categoryController;

	@Autowired
	EventController eventController;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	VendorController vendorController;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	EventCategoryModalRepository eventCategoryModalRepository;
	// common
	static String NAME = "NAME *";
	static String DESCRIPTION = "Description";
	// event
	static String SHORT_DSCRIPTION = "Short Description *";
	static String IS_PAID = "isPaid(yes/No)";
	static String START_DATE = "Start Date(MM/DD/YYYY) *";
	static String END_DATE = "End Date(MM/DD/YYYY) *";
	static String START_TIME = "Start Time(Example 10:20 AM) *";
	static String END_TIME = "End Time(Example 10:20 AM) *";
	static String IS_ACTIVE = "Active(yes/No)";
	static String CATEGORY_NAME = "Category Name(s) *";
	// static String URL_LINK = "Event Detail Link *";
	// static String EMAIL_ID = "Event Detail Email Id *";
	// static String PHONE_NUMBER = "Event Detail Phone Number";
	// static String HAPPY_HOUR_MENU = "Event Detail Happy Hour Menu";
	// static String DINNER_MENU = "Event Detail Dinner Menu";
	// static String PHOTO_URL = "Event Detail Photos Url";
	// vendor
	static String VENDOR_NAME = "Business (Place) Name";
	static String VENDOR_CATEGORY = "Category";
	static String VENDOR_SUBCATEGORY = "Sub category";
	static String ADDRESS_LINE_1 = "Address 1";
	static String ADDRESS_LINE_2 = "Address 2";
	static String CITY = "City";
	static String ZIP = "Zip";
	static String STATE = "State";
	static String PHONE_NUMBER = "Phone Number";
	static String VENDOR_BUSINESS_HOURS = "Business Hours";
	static String VENDOR_BUSINESS_Email = "Email";
	static String VENDOR_WEBSITE = "Website";
	static String FACEBOOK = "Facebook Page";
	static String TWITTER = "Twitter Page";
	static String INSTAGRAM = "Instagram Page";

	@Override
	public String vendor(InputStream inputStream, int userId, HttpServletRequest request) {
		StringBuilder resultBuilder = new StringBuilder();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			Iterator<Sheet> sheetIterator = workbook.iterator();
			// Iterate sheet by sheet
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();

				Iterator<Row> rowIterator = sheet.iterator();
				int count = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					String name, category, subCategory, description, addressLine1, addressLine2, city, state, zip, phoneNum, busHours, busEmail, website, faceBookUrl, twitterUrl, instagramUrl;
					List<String> fileds = new ArrayList<String>();
					// vlidate header
					if (row.getRowNum() == 0) {
						int countH = 0;
						String nameHeader = getColumnValue(row, countH++);
						String categoryHeader = getColumnValue(row, countH++);
						String subCategoryHeader = getColumnValue(row, countH++);
						String descrptionHeader = getColumnValue(row, countH++);
						String address1Header = getColumnValue(row, countH++);
						String address2Header = getColumnValue(row, countH++);
						String cityHeader = getColumnValue(row, countH++);
						String stateHeader = getColumnValue(row, countH++);
						String zipHeader = getColumnValue(row, countH++);
						String phoneHeader = getColumnValue(row, countH++);
						String businessHourHeader = getColumnValue(row, countH++);
						String businessEmailHeader = getColumnValue(row, countH++);
						String websiteHeader = getColumnValue(row, countH++);
						String faceBookHeader = getColumnValue(row, countH++);
						String twitterHeader = getColumnValue(row, countH++);
						String instagramHeader = getColumnValue(row, countH++);
						if (!nameHeader.equalsIgnoreCase(VENDOR_NAME) || !descrptionHeader.equalsIgnoreCase(DESCRIPTION) || !categoryHeader.equalsIgnoreCase(VENDOR_CATEGORY)
								|| !subCategoryHeader.equalsIgnoreCase(VENDOR_SUBCATEGORY) || !address1Header.equalsIgnoreCase(ADDRESS_LINE_1)
								|| !address2Header.equalsIgnoreCase(ADDRESS_LINE_2) || !cityHeader.equalsIgnoreCase(CITY) || !stateHeader.equalsIgnoreCase(STATE)
								|| !zipHeader.equalsIgnoreCase(ZIP) || !phoneHeader.equalsIgnoreCase(PHONE_NUMBER) || !businessHourHeader.equalsIgnoreCase(VENDOR_BUSINESS_HOURS)
								|| !businessEmailHeader.equalsIgnoreCase(VENDOR_BUSINESS_Email) || !websiteHeader.equalsIgnoreCase(VENDOR_WEBSITE)
								|| !faceBookHeader.equalsIgnoreCase(FACEBOOK) || !twitterHeader.equalsIgnoreCase(TWITTER) || !instagramHeader.equalsIgnoreCase(INSTAGRAM)) {
							return "Template headers miss match";
						}
					}
					if (row.getRowNum() > 0) {
						int colunmCount = 0;
						name = getColumnValue(row, colunmCount++);
						category = getColumnValue(row, colunmCount++);
						subCategory = getColumnValue(row, colunmCount++);
						description = getColumnValue(row, colunmCount++);
						addressLine1 = getColumnValue(row, colunmCount++);
						addressLine2 = getColumnValue(row, colunmCount++);
						city = getColumnValue(row, colunmCount++);
						state = getColumnValue(row, colunmCount++);
						zip = getColumnValue(row, colunmCount++);
						// colunmCount++; // to be removed
						// phoneNum = "8870098762"; // to be removed and enable
						// below one
						phoneNum = getColumnValue(row, colunmCount++);
						busHours = getColumnValue(row, colunmCount++);
						busEmail = getColumnValue(row, colunmCount++);
						website = getColumnValue(row, colunmCount++);
						faceBookUrl = getColumnValue(row, colunmCount++);
						twitterUrl = getColumnValue(row, colunmCount++);
						instagramUrl = getColumnValue(row, colunmCount++);
						String status = "";
						// validation block start
						if (name == null || name.isEmpty()) {
							fileds.add("Business (Place) Name");
							status = status + "Business (Place) Name";
						}
						if (category == null || category.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Category";
							} else {
								status = status + "Category ";
							}
							fileds.add("Category");
						}
						if (addressLine1 == null || addressLine1.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Address 1";
							} else {
								status = status + "Address 1 ";
							}
							fileds.add("Address 1");
						}
						if (city == null || city.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "City";
							} else {
								status = status + "City";
							}
							fileds.add("City");
						}
						if (state == null || state.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "State";
							} else {
								status = status + "State";
							}
							fileds.add("State");
						}
						if (zip == null || zip.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Zip";
							} else {
								status = status + "Zip";
							}
							fileds.add("Zip");
						}
						if (phoneNum == null || phoneNum.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Phone Number";
							} else {
								status = status + "Phone Number";
							}
							fileds.add("Phone Number");
						}
						if (busHours == null || busHours.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Business Hours";
							} else {
								status = status + "Business Hours";
							}
							fileds.add("Business Hours");
						}
						// validation block end
						// process for save function
						try {
							State states = stateRepository.findStateByAbbreviation(state);
							if (states == null) {
								states = stateRepository.findStateByName(state);
								if (states == null) {
									throw new Exception("Invalid state name");
								}
							}
							if (status.isEmpty()) {
								VendorSaveRequest vendorRequest = new VendorSaveRequest();
								Vendor vendor = new Vendor();
								VendorDetail vendorDetail = new VendorDetail();
								VendorAddress vandorAddress = new VendorAddress();
								vendor.setName(name);
								if (category != null && !category.trim().isEmpty() && subCategory != null && !subCategory.trim().isEmpty()) {
									Category isExistingCategory = categoryRepository.findByName(category);
									Category savedCategory = null;
									SubCategory savedSubCategory = null;
									if (isExistingCategory != null) {
										savedCategory = isExistingCategory;
										SubCategory isExistingSubcategory = subCategoryRepository.findByNameAndCategory(isExistingCategory.getId(), subCategory);
										if (isExistingSubcategory != null) {
											savedSubCategory = isExistingSubcategory;
										} else {
											SubCategory newSubCategory = new SubCategory();
											newSubCategory.setName(subCategory);
											newSubCategory.setCategory(savedCategory);
											savedSubCategory = subCategoryRepository.save(newSubCategory);
										}
									} else {
										Category newCategory = new Category();
										newCategory.setName(category);
										savedCategory = categoryRepository.save(newCategory);
										SubCategory newSubCategory = new SubCategory();
										newSubCategory.setName(subCategory);
										newSubCategory.setCategory(savedCategory);
										savedSubCategory = subCategoryRepository.save(newSubCategory);
									}
									List<VendorCategory> vendorCategories = new ArrayList<VendorCategory>();
									VendorCategory vendorCategory = new VendorCategory();
									vendorCategory.setCategory(savedCategory);
									vendorCategory.setSubcategory(savedSubCategory);
									vendorCategories.add(vendorCategory);
								} else if (category != null && !category.trim().isEmpty()) {
									Category existingCategory = categoryRepository.findByName(category);
									if (existingCategory != null) {
										List<VendorCategory> vendorCategories = new ArrayList<VendorCategory>();
										VendorCategory vendorCategory = new VendorCategory();
										vendorCategory.setCategory(existingCategory);
										if (existingCategory.getSubCategorys() != null && existingCategory.getSubCategorys().size() > 0) {
											for (SubCategory subCategory1 : existingCategory.getSubCategorys()) {
												vendorCategory.setSubcategory(subCategory1);
												vendorCategories.add(vendorCategory);
											}
										} else {
											vendorCategories.add(vendorCategory);
										}
										vendor.setVendorCategoryList(vendorCategories);
									} else {
										Category newCategory = new Category();
										newCategory.setName(category);
										Category saveCategory = categoryRepository.save(newCategory);
										List<VendorCategory> vendorCategories = new ArrayList<VendorCategory>();
										VendorCategory vendorCategory = new VendorCategory();
										vendorCategory.setCategory(saveCategory);
										vendorCategories.add(vendorCategory);
									}
								}

								vendorDetail.setDescription(description);
								vendorDetail.setPhoneNumber(phoneNum);
								vendorDetail.setBusinessHours(busHours);
								vendorDetail.setEmail(busEmail);
								vendorDetail.setVendorUrl(website);

								vendorDetail.setDescription(description);
								vendorDetail.setFacebookUrl(faceBookUrl);
								vendorDetail.setTwitterUrl(twitterUrl);
								vendorDetail.setInstagramUrl(instagramUrl);

								Address address = new Address();
								address.setAddress1(addressLine1);
								address.setAddress2(addressLine2);
								address.setCity(city);
								address.setZip(zip);
								address.setState(states);
								vandorAddress.setAddress(address);

								vendorRequest.setAddress(vandorAddress);
								vendorRequest.setDetail(vendorDetail);
								vendorRequest.setVendor(vendor);

								ResponseType responseType = vendorController.create(vendorRequest);
								if (responseType.getStatus().equals("Failure")) {
									status = responseType.getMessage();
									throw new Exception(status);
								}
							}

							if (!status.isEmpty()) {
								resultBuilder.append("Row :").append(count).append(" failer: ")
										.append(fileds.size() > 1 ? "required fileds missing Filed Names: " + status : "required filed missing Filed Name: " + status);
							}

						} catch (Exception e) {
							e.printStackTrace();
							resultBuilder.append("Row :").append(count).append(" failer: ").append(e.getMessage());
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			resultBuilder.append(e.getMessage());
		}
		return resultBuilder.toString();
	}

	@Override
	public String category(InputStream inputStream, int userId, HttpServletRequest request) {
		StringBuilder resultBuilder = new StringBuilder();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			Iterator<Sheet> sheetIterator = workbook.iterator();
			// Iterate sheet by sheet
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();

				Iterator<Row> rowIterator = sheet.iterator();
				int count = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					String name, description;
					List<String> fileds = new ArrayList<String>();
					// vlidate header
					if (row.getRowNum() == 0) {
						String nameHeader = getColumnValue(row, 0);
						String descrptionHeader = getColumnValue(row, 1);
						if (!nameHeader.equalsIgnoreCase(NAME) || !descrptionHeader.equalsIgnoreCase(DESCRIPTION)) {
							return "Template headers miss match";
						}
					}
					if (row.getRowNum() > 0) {
						int colunmCount = 0;
						name = getColumnValue(row, colunmCount++);
						description = getColumnValue(row, colunmCount++);
						String status = "";
						// validation block start
						if (name == null || name.isEmpty()) {
							fileds.add("Name");
							status = status + "Name";
						}
						if (description == null || description.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Description";
							} else {
								status = status + "Description ";
							}
							fileds.add("Description");
						}
						// validation block end
						// process for save function
						try {
							if (status.isEmpty()) {
								Category category = new Category();
								category.setName(name);
								ResponseType responseType = categoryController.save(category, request);
								if (responseType.getStatus().equals("Failure")) {
									status = responseType.getMessage();
									throw new Exception(status);
								}
							}

							if (!status.isEmpty()) {
								resultBuilder.append("Row :").append(count).append(" failer: ")
										.append(fileds.size() > 1 ? "required fileds missing Filed Names: " + status : "required filed missing Filed Name: " + status);
							}

						} catch (Exception e) {
							e.printStackTrace();
							resultBuilder.append("Row :").append(count).append(" failer: ").append(e.getMessage());
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			resultBuilder.append(e.getMessage());
		}
		return resultBuilder.toString();
	}

	@Override
	public String uploadEventCategory(InputStream inputStream, int userId, HttpServletRequest request) {
		StringBuilder resultBuilder = new StringBuilder();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			Iterator<Sheet> sheetIterator = workbook.iterator();
			// Iterate sheet by sheet
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();

				Iterator<Row> rowIterator = sheet.iterator();
				int count = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					String categoryName;
					List<String> fileds = new ArrayList<String>();
					// vlidate header
					if (row.getRowNum() == 0) {
						String categoryHeader = getColumnValue(row, 0);
						if (!categoryHeader.equalsIgnoreCase(VENDOR_CATEGORY)) {
							return "Template headers miss match";
						}
					}
					if (row.getRowNum() > 0) {
						int colunmCount = 0;
						categoryName = getColumnValue(row, colunmCount++);
						String status = "";
						// validation block start
						if (categoryName == null || categoryName.isEmpty()) {
							fileds.add("Category");
							status = status + "Category";
						}
						// validation block end
						// process for save function
						try {
							if (status.isEmpty()) {
								Category category = new Category();
								category.setName(categoryName);
								ResponseType responseType = categoryController.save(category, request);
								if (responseType.getStatus().equals("Failure")) {
									status = responseType.getMessage();
									throw new Exception(status);
								}
							}

							if (!status.isEmpty()) {
								resultBuilder.append("Row :").append(count).append(" failer: ")
										.append(fileds.size() > 1 ? "required fileds missing Filed Names: " + status : "required filed missing Filed Name: " + status);
							}

						} catch (Exception e) {
							e.printStackTrace();
							resultBuilder.append("Row :").append(count).append(" failer: ").append(e.getMessage());
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			resultBuilder.append(e.getMessage());
		}
		return resultBuilder.toString();
	}

	@Override
	public String uploadVendorCategory(InputStream inputStream, int userId, HttpServletRequest request) {
		StringBuilder resultBuilder = new StringBuilder();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			Iterator<Sheet> sheetIterator = workbook.iterator();
			// Iterate sheet by sheet
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();

				Iterator<Row> rowIterator = sheet.iterator();
				int count = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					String categoryName;
					List<String> fileds = new ArrayList<String>();
					// vlidate header
					if (row.getRowNum() == 0) {
						String categoryHeader = getColumnValue(row, 0);
						if (!categoryHeader.equalsIgnoreCase(VENDOR_CATEGORY)) {
							return "Template headers miss match";
						}
					}
					if (row.getRowNum() > 0) {
						int colunmCount = 0;
						categoryName = getColumnValue(row, colunmCount++);
						String status = "";
						// validation block start
						if (categoryName == null || categoryName.isEmpty()) {
							fileds.add("Category");
							status = status + "Category";
						}
						// validation block end
						// process for save function
						try {
							if (status.isEmpty()) {
								Category category = new Category();
								category.setName(categoryName);
								ResponseType responseType = categoryController.save(category, request);
								if (responseType.getStatus().equals("Failure")) {
									status = responseType.getMessage();
									throw new Exception(status);
								}
							}

							if (!status.isEmpty()) {
								resultBuilder.append("Row :").append(count).append(" failer: ")
										.append(fileds.size() > 1 ? "required fileds missing Filed Names: " + status : "required filed missing Filed Name: " + status);
							}

						} catch (Exception e) {
							e.printStackTrace();
							resultBuilder.append("Row :").append(count).append(" failer: ").append(e.getMessage());
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			resultBuilder.append(e.getMessage());
		}
		return resultBuilder.toString();
	}

	@Override
	public String event(InputStream inputStream, int userId, HttpServletRequest request) {
		StringBuilder resultBuilder = new StringBuilder();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			Iterator<Sheet> sheetIterator = workbook.iterator();
			// Iterate sheet by sheet
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();

				Iterator<Row> rowIterator = sheet.iterator();
				int count = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					String name, description, shortDescription, isPaid, startTime, endTime, active, categoryName, urlLink, emailId, phoneNumber, happyHourMenu, dinnerMenu, photosUrl;
					Date startDate, endDate;
					List<String> fileds = new ArrayList<String>();
					// vlidate header
					if (row.getRowNum() == 0) {
						int countH = 0;
						String nameHeader = getColumnValue(row, countH++);
						String descrptionHeader = getColumnValue(row, countH++);
						String shortDescriptionHeader = getColumnValue(row, countH++);
						String isPaidHeader = getColumnValue(row, countH++);
						String startDateHeader = getColumnValue(row, countH++);
						String endDateHeader = getColumnValue(row, countH++);
						String startTimeHeader = getColumnValue(row, countH++);
						String endTimeHeader = getColumnValue(row, countH++);
						String activeHeader = getColumnValue(row, countH++);
						String categoryNameHeader = getColumnValue(row, countH++);
						// String urlLinkHeader = getColumnValue(row, countH++);
						// String emailIdHeader = getColumnValue(row, countH++);
						// String phoneNumberHeader = getColumnValue(row,
						// countH++);
						// String happyHourMenuHeader = getColumnValue(row,
						// countH++);
						// String dinnerMenuHeader = getColumnValue(row,
						// countH++);
						// String photosUrlHeader = getColumnValue(row,
						// countH++);
						if (!nameHeader.equalsIgnoreCase(NAME) || !descrptionHeader.equalsIgnoreCase(DESCRIPTION) || !shortDescriptionHeader.equalsIgnoreCase(SHORT_DSCRIPTION)
								|| !isPaidHeader.equalsIgnoreCase(IS_PAID) || !startDateHeader.equalsIgnoreCase(START_DATE) || !endDateHeader.equalsIgnoreCase(END_DATE)
								|| !startTimeHeader.equalsIgnoreCase(START_TIME) || !endTimeHeader.equalsIgnoreCase(END_TIME) || !activeHeader.equalsIgnoreCase(IS_ACTIVE)
								|| !categoryNameHeader.equalsIgnoreCase(CATEGORY_NAME)) {
							// || !urlLinkHeader.equalsIgnoreCase(URL_LINK) ||
							// !emailIdHeader.equalsIgnoreCase(EMAIL_ID)
							// ||
							// !phoneNumberHeader.equalsIgnoreCase(PHONE_NUMBER)
							// ||
							// !happyHourMenuHeader.equalsIgnoreCase(HAPPY_HOUR_MENU)
							// ||
							// !dinnerMenuHeader.equalsIgnoreCase(DINNER_MENU)
							// || !photosUrlHeader.equalsIgnoreCase(PHOTO_URL))
							// {
							return "Template headers miss match";
						}
					}
					if (row.getRowNum() > 0) {
						int colunmCount = 0;
						name = getColumnValue(row, colunmCount++);
						description = getColumnValue(row, colunmCount++);
						shortDescription = getColumnValue(row, colunmCount++);
						isPaid = getColumnValue(row, colunmCount++);
						startDate = getColumnValueAsDate(row, colunmCount++);
						endDate = getColumnValueAsDate(row, colunmCount++);
						startTime = getColumnValue(row, colunmCount++);
						endTime = getColumnValue(row, colunmCount++);
						active = getColumnValue(row, colunmCount++);
						categoryName = getColumnValue(row, colunmCount++);
						urlLink = getColumnValue(row, colunmCount++);
						emailId = getColumnValue(row, colunmCount++);
						phoneNumber = getColumnValueAsNumber(row, colunmCount++);
						happyHourMenu = getColumnValue(row, colunmCount++);
						dinnerMenu = getColumnValue(row, colunmCount++);
						photosUrl = getColumnValue(row, colunmCount++);
						String status = "";
						// validation block start
						if (name == null || name.isEmpty()) {
							fileds.add("Name");
							status = status + "Name";
						}
						if (description == null || description.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Description";
							} else {
								status = status + "Description ";
							}
							fileds.add("Description");
						}
						if (startDate == null) {
							if (status.length() > 0) {
								status = status + "," + "Start Date";
							} else {
								status = status + "Start Date ";
							}
							fileds.add("Start Date");
						}
						if (endDate == null) {
							if (status.length() > 0) {
								status = status + "," + "End Date";
							} else {
								status = status + "End Date ";
							}
							fileds.add("End Date");
						}
						if (startTime == null || startTime.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Start Time";
							} else {
								status = status + "Start Time ";
							}
							fileds.add("Start Time");
						}
						if (endTime == null || endTime.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "End Time";
							} else {
								status = status + "End Time ";
							}
							fileds.add("End Time");
						}
						if (isPaid == null || isPaid.isEmpty() || (!isPaid.isEmpty() && !isPaid.equalsIgnoreCase("yes") && !isPaid.equalsIgnoreCase("no"))) {
							if (status.length() > 0) {
								status = status + "," + "Is Paid";
							} else {
								status = status + "Is Paid ";
							}
							fileds.add("Is Paid");
						}
						if (active == null || active.isEmpty() || (!active.isEmpty() && !active.equalsIgnoreCase("yes") && !active.equalsIgnoreCase("no"))) {
							if (status.length() > 0) {
								status = status + "," + "Is active";
							} else {
								status = status + "Is active ";
							}
							fileds.add("Is active");
						}

						if (emailId == null || emailId.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Email Id";
							} else {
								status = status + "Email Id ";
							}
							fileds.add("Email Id");
						}
						if (phoneNumber == null || phoneNumber.isEmpty()) {
							if (status.length() > 0) {
								status = status + "," + "Phone Number";
							} else {
								status = status + "Phone Number";
							}
							fileds.add("Phone Number");
						}
						// validation block end
						// process for save function
						try {
							if (status.isEmpty()) {
								Event event = new Event();
								event.setName(name);
								event.setDescription(description);
								event.setShortDescription(shortDescription);
								event.setIsPaid(isPaid.equalsIgnoreCase("yes"));
								event.setStartDate(startDate);
								event.setEndDate(endDate);
								event.setStartTime(startDate);
								event.setEndTime(endTime);
								event.setActive(active.equalsIgnoreCase("yes"));
								List<EventCategory> eventCategoryList = new ArrayList<EventCategory>();
								String[] categorys = categoryName.split(",");
								for (String catName : categorys) {
									EventCategory ec = new EventCategory();
									List<EventCategoryModal> catList = eventCategoryModalRepository.findByName(catName);
									if (catList.isEmpty()) {
										throw new Exception("Invalid Category name ocured");
									}
									ec.setEventCategory(catList.get(0));
									eventCategoryList.add(ec);
								}
								event.setEventCategoryList(eventCategoryList);
								EventDetail eventDetail = new EventDetail();
								// eventDetail.setName(name);
								// eventDetail.setEmailId(emailId);
								// eventDetail.setUrlLink(urlLink);
								// eventDetail.setPhoneNumber(phoneNumber);
								// eventDetail.setHappyHourMenu(happyHourMenu);
								// eventDetail.setDinnerMenu(dinnerMenu);
								// eventDetail.setPhotosUrl(photosUrl);
								event.setEventDetail(eventDetail);
								ResponseType responseType = eventController.create(event, request);
								if (responseType.getStatus().equals("Failure")) {
									status = responseType.getMessage();
									throw new Exception(status);
								}
							}

							if (!status.isEmpty()) {
								resultBuilder.append("Row :").append(count).append(" failer: ")
										.append(fileds.size() > 1 ? "required fileds missing Filed Names: " + status : "required filed missing Filed Name: " + status);
							}

						} catch (Exception e) {
							e.printStackTrace();
							resultBuilder.append("Row :").append(count).append(" failer: ").append(e.getMessage());
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			resultBuilder.append(e.getMessage());
		}
		return resultBuilder.toString();
	}

	@Override
	public String getColumnValue(Row row, int columnNUmber) {
		String result = "";
		Cell cell = row.getCell(columnNUmber);
		try {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				result = cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				result = String.valueOf((long) cell.getNumericCellValue());
			else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				result = null;
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	public String getColumnValueAsNumber(Row row, int columnNUmber) {
		String result;
		try {
			result = row.getCell(columnNUmber).getNumericCellValue() + "";
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	public Date getColumnValueAsDate(Row row, int columnNUmber) {
		Date result;
		try {
			result = row.getCell(columnNUmber).getDateCellValue();
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	public double getColumnValueAsTime(Row row, int columnNUmber) {
		double result = 0;
		try {
			int type = row.getCell(columnNUmber).getCellType();
			result = row.getCell(columnNUmber).getCellType();
		} catch (Exception e) {
			// result = null;
		}
		return result;
	}

	public boolean validateHeaderLength(Row row, int count) {
		int headerCount = 0;
		if (row.getRowNum() == 0) {
			Iterator<Cell> rowIterater = row.cellIterator();
			while (rowIterater.hasNext()) {
				rowIterater.next();
				headerCount++;
			}
		}
		if (headerCount != count) {
			return false;
		}
		return true;
	}

}
