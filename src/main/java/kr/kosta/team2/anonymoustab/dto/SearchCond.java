package kr.kosta.team2.anonymoustab.dto;

import java.io.Serializable;

public abstract class SearchCond implements Serializable {

	protected int page = 1;
	protected int limit = 10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return (page - 1) * limit; 
	}
}
