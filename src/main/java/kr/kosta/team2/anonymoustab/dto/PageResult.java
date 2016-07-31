package kr.kosta.team2.anonymoustab.dto;

import java.util.List;

public class PageResult<T> {

	private SearchCond search;

	private List<T> results;

	public SearchCond getSearch() {
		return search;
	}

	public void setSearch(SearchCond search) {
		this.search = search;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	

}
