package com.oneg.whsquared.response.vendor;

import java.util.List;

import org.springframework.data.domain.Sort;

public class VendorPaginationResponse {

	private List<VendorSaveResponse> vendorSaveResponseList;

	private long totalElements;

	private int totalPages;

	private boolean last;

	private int size;

	private int number;

	private Sort sort;

	private int numberOfElements;

	private boolean first;

	public List<VendorSaveResponse> getVendorSaveResponseList() {
		return vendorSaveResponseList;
	}

	public void setVendorSaveResponseList(List<VendorSaveResponse> vendorSaveResponseList) {
		this.vendorSaveResponseList = vendorSaveResponseList;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

}
