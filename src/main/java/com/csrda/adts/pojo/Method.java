package com.csrda.adts.pojo;

import java.util.List;
/**
 * 
 * 
 * @description:接口类
 */
public class Method {
	public String id;
	public int eq;
	public int getEq() {
		return eq;
	}
	public void setEq(int eq) {
		this.eq = eq;
	}
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
	/**接口原型*/
	public String interfaceCode;
	
	public List<Param> outputParams;
	
	public List<Param> inputParams;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Param> getOutputParams() {
		return outputParams;
	}
	public void setOutputParams(List<Param> outputParams) {
		this.outputParams = outputParams;
	}
	public List<Param> getInputParams() {
		return inputParams;
	}
	public void setInputParams(List<Param> inputParams) {
		this.inputParams = inputParams;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
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
