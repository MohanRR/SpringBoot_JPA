/**
 *
 */
package com.oneg.whsquared.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author Anbukkani G
 * 
 */
public interface UploadService {

	public String category(InputStream inputStream, int userId, HttpServletRequest request);

	public String uploadEventCategory(InputStream inputStream, int userId, HttpServletRequest request);

	public String uploadVendorCategory(InputStream inputStream, int userId, HttpServletRequest request);

	public String vendor(InputStream inputStream, int userId, HttpServletRequest request);

	public String event(InputStream inputStream, int userId, HttpServletRequest request);

	public String getColumnValue(Row row, int columnNUmber);
}
