package com.csrda.adts.pojo;

import java.util.List;

public class Title {
	public String TitleName;
	public String content;
	//public List<FirstLevelTitle> titles;
	public String getTitleName() {
		return TitleName;
	}
	public void setTitleName(String titleName) {
		TitleName = titleName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
