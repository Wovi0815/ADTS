package com.csrda.adts.pojo;

import java.util.List;
/**
 * 
 * 
 * @description:接口类
 */
public class Method {
	/**接口标识*/
	public String interfaceId;
	/**接口名*/
	public String interfaceName;
	/**接口描述*/
	public String interfaceDesc;
	/**返回值*/
	public String interfaceReturn;
	/**返回值描述*/
	public String interfaceReturnDesc;
	/**参数列表*/
	public List<Param> params;
	public String getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getInterfaceDesc() {
		return interfaceDesc;
	}
	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}
	public String getInterfaceReturn() {
		return interfaceReturn;
	}
	public void setInterfaceReturn(String interfaceReturn) {
		this.interfaceReturn = interfaceReturn;
	}
	public String getInterfaceReturnDesc() {
		return interfaceReturnDesc;
	}
	public void setInterfaceReturnDesc(String interfaceReturnDesc) {
		this.interfaceReturnDesc = interfaceReturnDesc;
	}
	public List<Param> getParams() {
		return params;
	}
	public void setParams(List<Param> params) {
		this.params = params;
	}
	
}
