package com.csrda.adts.pojo;
/**
 * 
 * @author 
 * @description:报文数据类
 */
public class MessageData {
	/**字节序号*/
	public String mesDataRange;
	/**数据名称*/
	public String mesDataName;
	/**数据描述*/
	public String mesDataDesc;
	/**数据类型*/
	public String mesDataType;
	public String getMesDataRange() {
		return mesDataRange;
	}
	public void setMesDataRange(String mesDataRange) {
		this.mesDataRange = mesDataRange;
	}
	public String getMesDataName() {
		return mesDataName;
	}
	public void setMesDataName(String mesDataName) {
		this.mesDataName = mesDataName;
	}
	public String getMesDataDesc() {
		return mesDataDesc;
	}
	public void setMesDataDesc(String mesDataDesc) {
		this.mesDataDesc = mesDataDesc;
	}
	public String getMesDataType() {
		return mesDataType;
	}
	public void setMesDataType(String mesDataType) {
		this.mesDataType = mesDataType;
	}
	
}
