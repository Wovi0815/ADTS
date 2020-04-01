package com.csrda.adts.pojo;

import java.util.List;

public class SecondLevelTitle extends Title{
	public List<ThirdLevelTitle> titles;

	public List<ThirdLevelTitle> getTitles() {
		return titles;
	}

	public void setTitles(List<ThirdLevelTitle> titles) {
		this.titles = titles;
	}
}
