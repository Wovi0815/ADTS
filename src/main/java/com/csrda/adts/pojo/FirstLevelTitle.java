package com.csrda.adts.pojo;

import java.util.List;

public class FirstLevelTitle extends Title {
	public List<SecondLevelTitle> titles;

	public List<SecondLevelTitle> getTitles() {
		return titles;
	}

	public void setTitles(List<SecondLevelTitle> titles) {
		this.titles = titles;
	}
}
