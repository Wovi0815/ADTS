package com.csrda.adts.pojo;

import java.util.List;

import javafx.scene.chart.PieChart.Data;

public class Title {
	public String titleName;
	public String content;
	//public List<FirstLevelTitle> titles;
	public List<DataType> structs;
	public DataType dataType;
	public List<DataType> dataTypes;
	public List<MidWare> midWares;
	public List<Message> messages;
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<MidWare> getMidWares() {
		return midWares;
	}
	public void setMidWares(List<MidWare> midWares) {
		this.midWares = midWares;
	}
	public List<DataType> getDataTypes() {
		return dataTypes;
	}
	public void setDataTypes(List<DataType> dataTypes) {
		this.dataTypes = dataTypes;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public List<DataType> getStructs() {
		return structs;
	}
	public void setStructs(List<DataType> structs) {
		this.structs = structs;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		titleName = titleName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
