package com.csrda.adts.pojo;

/**
 * @description:中间件类
 * */
public class MidWare {
	/**中间件ID*/
	public String midID ;
	/**中间件名称*/
	public String midName;
	/**中间件描述*/
	public String midDesc;
	public String getMidID() {
		return midID;
	}
	public void setMidID(String midID) {
		this.midID = midID;
	}
	public String getMidName() {
		return midName;
	}
	public void setMidName(String midName) {
		this.midName = midName;
	}
	public String getMidDesc() {
		return midDesc;
	}
	public void setMidDesc(String midDesc) {
		this.midDesc = midDesc;
	}
}
