package com.csrda.adts.service;

public interface DataTypeServiceImpl {

	public String addStruct(String typId,String typName,String typSize,String typDesc,String memList);
	
	public String updateStruct(String typId, String typName, String typSize, String typDesc, String memList);
}
