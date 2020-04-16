package com.csrda.adts.pojo;

import java.util.List;

/**
 * 
 * @author 
 * @description:类对象
 */
public class ObjectData {
	public int eq;
	public int getEq() {
		return eq;
	}
	public void setEq(int eq) {
		this.eq = eq;
	}
	public String ClassId;
	public String ClassName;
	public String ClassDesc;
	public String classFather;
	public List<Method> methods;
	public List<Method> interfaces;
	public String getClassFather() {
		return classFather;
	}
	public void setClassFather(String classFather) {
		this.classFather = classFather;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public List<Method> getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(List<Method> interfaces) {
		this.interfaces = interfaces;
	}
	public String getClassId() {
		return ClassId;
	}
	public void setClassId(String classId) {
		ClassId = classId;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getClassDesc() {
		return ClassDesc;
	}
	public void setClassDesc(String classDesc) {
		ClassDesc = classDesc;
	}
	
}
