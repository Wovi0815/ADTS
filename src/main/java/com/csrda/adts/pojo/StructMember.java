package com.csrda.adts.pojo;

public class StructMember {
	/**成员ID*/
	String memId;
	/**成员名*/
	String memName;
	/**成员类型*/
	String memType;
	/**成员描述*/
	String memDesc;
	String memPhyDim;
	/**成员最大值*/
	String memMax;
	/**成员最小值*/
	String memMin;
	/**成员默认值*/
	String memDefault;
	/**成员顺序*/
	String memNo;
	public String getMemPhyDim() {
		return memPhyDim;
	}
	public void setMemPhyDim(String memPhyDim) {
		this.memPhyDim = memPhyDim;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	public String getMemDesc() {
		return memDesc;
	}
	public void setMemDesc(String memDesc) {
		this.memDesc = memDesc;
	}
	public String getMemMax() {
		return memMax;
	}
	public void setMemMax(String memMax) {
		this.memMax = memMax;
	}
	public String getMemMin() {
		return memMin;
	}
	public void setMemMin(String memMin) {
		this.memMin = memMin;
	}
	public String getMemDefault() {
		return memDefault;
	}
	public void setMemDefault(String memDefault) {
		this.memDefault = memDefault;
	}
}
