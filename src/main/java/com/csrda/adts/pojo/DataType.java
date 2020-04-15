package com.csrda.adts.pojo;

import java.util.List;

public class DataType {
	/**类型ID*/
	String typeId;
	/**类型名*/
	String typeName;
	/**类型长度*/
	String typeSize;
	/**类型属性*/
	String typeAttr;
	/**类型描述*/
	String typeDesc;
	/**成员数量*/
	String memNum;
	String definition;
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	List<StructMember> structMembers;
	public List<StructMember> getStructMembers() {
		return structMembers;
	}
	public void setStructMembers(List<StructMember> structMembers) {
		this.structMembers = structMembers;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeSize() {
		return typeSize;
	}
	public void setTypeSize(String typeSize) {
		this.typeSize = typeSize;
	}
	public String getTypeAttr() {
		return typeAttr;
	}
	public void setTypeAttr(String typeAttr) {
		this.typeAttr = typeAttr;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
}
