package com.hobin.crm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_result")
public class DataResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable=false )
	private Integer id;
	@Column(name = "domain", nullable=true )
	private String domain;
	@Column(name = "effective_query", nullable=true )
	private String effectiveQuery;
	@Column(name = "link", nullable=true )
	private String link;
	@Column(name = "link_type", nullable=true )
	private String linkType;
	@Column(name = "no_results", nullable=true )
	private String noResults;
	@Column(name = "num_results", nullable=true )
	private String numResults;
	@Column(name = "num_results_for_query", nullable=true )
	private String numResultsForQuery;
	@Column(name = "num_reviews", nullable=true )
	private String numReviews;
	@Column(name = "page_number", nullable=true )
	private String pageNumber;
	@Column(name = "query", nullable=true )
	private String query;
	@Column(name = "rank", nullable=true )
	private String rank;
	@Column(name = "rating", nullable=true )
	private String rating;
	@Column(name = "requested_at", nullable=true )
	private String requestedAt;
	@Column(name = "requested_by", nullable=true )
	private String requestedBy;
	@Column(name = "scrape_method", nullable=true )
	private String scrapeMethod;
	@Column(name = "search_engine_name", nullable=true )
	private String searchEngineName;
	@Column(name = "snippet", nullable=true )
	private String snippet;
	@Column(name = "status", nullable=true )
	private String status;
	@Column(name = "title", nullable=true )
	private String title;
	@Column(name = "visible_link", nullable=true )
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
