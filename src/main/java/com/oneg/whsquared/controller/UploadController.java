/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Decoder;

import com.oneg.whsquared.entity.User;
import com.oneg.whsquared.repository.UserRepository;
import com.oneg.whsquared.security.JwtUtil;
import com.oneg.whsquared.service.UploadService;
import com.oneg.whsquared.util.ResponseType;
import com.oneg.whsquared.util.Util;
import com.oneg.whsquared.util.WHStatus;

/**
 * 
 * @author Rajan-G
 */
@RestController
@RequestMapping(value = "api/bulkupload")
public class UploadController {

	@Autowired
	Util util;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UploadService uploadService;

	@Transactional
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	@ResponseBody
	public ResponseType categories(@RequestBody FileBean fileBean, HttpServletRequest httpServletRequest) throws Exception {
		LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
		Integer userId = (Integer) linkedHashMap.get("id");
		User user = userRepository.findOne(userId);
		String message = uploadService.category(fileBean.getASInputStream(), user.getId(), httpServletRequest);
		return util.response(WHStatus.SUCCESS.value(), message, "");
	}

	@Transactional
	@RequestMapping(value = "event/categories", method = RequestMethod.POST)
	@ResponseBody
	public ResponseType eventCategories(@RequestBody FileBean fileBean, HttpServletRequest httpServletRequest) throws Exception {
		LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
		Integer userId = (Integer) linkedHashMap.get("id");
		User user = userRepository.findOne(userId);
		String message = uploadService.uploadEventCategory(fileBean.getASInputStream(), user.getId(), httpServletRequest);
		return util.response(WHStatus.SUCCESS.value(), message, "");
	}

	@Transactional
	@RequestMapping(value = "vendor/categories", method = RequestMethod.POST)
	@ResponseBody
	public ResponseType vendorCategories(@RequestBody FileBean fileBean, HttpServletRequest httpServletRequest) throws Exception {
		LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
		Integer userId = (Integer) linkedHashMap.get("id");
		User user = userRepository.findOne(userId);
		String message = uploadService.uploadVendorCategory(fileBean.getASInputStream(), user.getId(), httpServletRequest);
		return util.response(WHStatus.SUCCESS.value(), message, "");
	}

	@Transactional
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	@ResponseBody
	public ResponseType events(@RequestBody FileBean fileBean, HttpServletRequest httpServletRequest) throws Exception {
		LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
		Integer userId = (Integer) linkedHashMap.get("id");
		User user = userRepository.findOne(userId);
		String message = uploadService.event(fileBean.getASInputStream(), user.getId(), httpServletRequest);
		return util.response(WHStatus.SUCCESS.value(), message, "");
	}

	@Transactional
	@RequestMapping(value = "/vendors", method = RequestMethod.POST)
	@ResponseBody
	public ResponseType vendors(@RequestBody FileBean fileBean, HttpServletRequest httpServletRequest) throws Exception {
		LinkedHashMap linkedHashMap = jwtUtil.getUser(httpServletRequest);
		Integer userId = (Integer) linkedHashMap.get("id");
		String message = uploadService.vendor(fileBean.getASInputStream(), 0, httpServletRequest);
		return util.response(WHStatus.SUCCESS.value(), message, "");
	}

	@RequestMapping(value = "/download/{templateName}", method = RequestMethod.GET)
	public ResponseType download(@PathVariable("templateName") String templateName, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		OutputStream out = null;
		InputStream inputStream = null;
		try {

			out = response.getOutputStream();
			// ServletContext servletContextEvent=session.getServletContext();
			// String realpath=servletContextEvent.getRealPath("/template");
			// System.out.println("Path === "+ realpath);
			String path = "com/oneg/whsquared/template";
			String fileName = null;
			XSSFWorkbook workbook = new XSSFWorkbook();
			if ("Category".equalsIgnoreCase(templateName)) {
				fileName = "category-template.xls";
			} else if ("Event Category".equalsIgnoreCase(templateName)) {
				fileName = "Event Categories.xlsx";
			} else if ("Vendor Category".equalsIgnoreCase(templateName)) {
				fileName = "Vendor Categories .xlsx";
			} else if ("Event".equalsIgnoreCase(templateName)) {
				fileName = "event-template.xls";
			} else if ("Vendor".equalsIgnoreCase(templateName)) {
				generateVendorTemplate(workbook);
				fileName = "Vendor_Template.xlsx";
			} else {
				return util.response(WHStatus.FAILURE.value(), "Invalid template name", null);
			}
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return util.response(WHStatus.FAILURE.value(), e.getMessage(), null);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return util.response(WHStatus.FAILURE.value(), null, obj);
	}

	public void generateVendorTemplate(XSSFWorkbook workbook) {
		XSSFSheet sheet = workbook.createSheet("Vendor_Template");
		for (int i = 0; i < 16; i++) {
			sheet.setColumnWidth(i, 10000);
		}
		XSSFCellStyle cellStyle = digitalAMSHeaderStyle(workbook);
		XSSFRow row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Business (Place) Name");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue("Category");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue("Sub category");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue("Description");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue("Address 1");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue("Address 2");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue("City");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue("State");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue("Zip");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue("Phone Number");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue("Business Hours");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(11);
		cell.setCellValue("Email");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(12);
		cell.setCellValue("Website");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(13);
		cell.setCellValue("Facebook Page");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(14);
		cell.setCellValue("Twitter Page");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(15);
		cell.setCellValue("Instagram Page");
		cell.setCellStyle(cellStyle);
	}

	public XSSFCellStyle digitalAMSHeaderStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
		cellStyle.setFont(font);
		return cellStyle;
	}
}

class FileBean {

	private String fileName;
	private String fileBody;
	private int companyId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileBody() {
		return fileBody;
	}

	public void setFileBody(String fileBody) {
		this.fileBody = fileBody;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	InputStream getASInputStream() throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] imageByte = decoder.decodeBuffer(this.getFileBody());
		InputStream inputStream = new ByteArrayInputStream(imageByte);
		return inputStream;
	}

}
