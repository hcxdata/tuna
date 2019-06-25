package com.hobin.crm.model.dto;

import java.io.Serializable;

public class DataResultDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String domain;
	private String effectiveQuery;
	private String link;
	private String linkType;
	private String noResults;
	private String numResults;
	private String numResultsForQuery;
	private String numReviews;
	private String pageNumber;
	private String query;
	private String rank;
	private String rating;
	private String requestedAt;
	private String requestedBy;
	private String scrapeMethod;
	private String searchEngineName;
	private String snippet;
	private String status;
	private String title;
	private String visibleLink;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEffectiveQuery() {
		return effectiveQuery;
	}

	public void setEffectiveQuery(String effectiveQuery) {
		this.effectiveQuery = effectiveQuery;
	}
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getNoResults() {
		return noResults;
	}

	public void setNoResults(String noResults) {
		this.noResults = noResults;
	}
	public String getNumResults() {
		return numResults;
	}

	public void setNumResults(String numResults) {
		this.numResults = numResults;
	}
	public String getNumResultsForQuery() {
		return numResultsForQuery;
	}

	public void setNumResultsForQuery(String numResultsForQuery) {
		this.numResultsForQuery = numResultsForQuery;
	}
	public String getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(String numReviews) {
		this.numReviews = numReviews;
	}
	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(String requestedAt) {
		this.requestedAt = requestedAt;
	}
	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getScrapeMethod() {
		return scrapeMethod;
	}

	public void setScrapeMethod(String scrapeMethod) {
		this.scrapeMethod = scrapeMethod;
	}
	public String getSearchEngineName() {
		return searchEngineName;
	}

	public void setSearchEngineName(String searchEngineName) {
		this.searchEngineName = searchEngineName;
	}
	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getVisibleLink() {
		return visibleLink;
	}

	public void setVisibleLink(String visibleLink) {
		this.visibleLink = visibleLink;
	}
}
