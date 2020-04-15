package com.csrda.adts.pojo;

/**
 * 
 * @author
 * @description:参数类
 * 
 */
public class Param {
	/**参数标识*/
	public String paraId;
	/**参数名称*/
	public String paraName;
	/**参数类型*/
	public String paraType;
	/**参数属性*/
	public String paraAttr;
	/**参数描述*/
	public String paraDesc;
	/**物理量杠*/
	public String paraPhyDim;
	/**最大值*/
	public String paraMax;
	/**最小值*/
	public String paraMin;
	public String getParaMax() {
		return paraMax;
	}
	public void setParaMax(String paraMax) {
		this.paraMax = paraMax;
	}
	public String getParaMin() {
		return paraMin;
	}
	public void setParaMin(String paraMin) {
		this.paraMin = paraMin;
	}
	public String getParaDefault() {
		return paraDefault;
	}
	public void setParaDefault(String paraDefault) {
		this.paraDefault = paraDefault;
	}
	/**默认值*/
	public String paraDefault;
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getParaAttr() {
		return paraAttr;
	}
	public void setParaAttr(String paraAttr) {
		this.paraAttr = paraAttr;
	}
	public String getParaDesc() {
		return paraDesc;
	}
	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}
	public String getParaPhyDim() {
		return paraPhyDim;
	}
	public void setParaPhyDim(String paraPhyDim) {
		this.paraPhyDim = paraPhyDim;
	}

	
}
