package com.example.demo.event.model;

public class Search {
	
	String userId;
	
    String searchTerms;

    public Search(String userId, String searchTerms) {
        this.userId = userId;
        this.searchTerms = searchTerms;
    }

    public String getUserID() {
        return userId;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

}
