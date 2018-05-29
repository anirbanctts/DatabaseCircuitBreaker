package com.poc.sample.messages;


public class SearchMessage {

	private int searchId;
	
	private String[] searchParams;
	
	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public String[] getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(String[] searchParams) {
		this.searchParams = searchParams;
	}

}
