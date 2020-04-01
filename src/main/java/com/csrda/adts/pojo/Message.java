package com.csrda.adts.pojo;

import java.util.List;

/**
 * 
 * @author
 * @description:报文类
 */
public class Message {
	/**报文标识*/
	public String mesId;
	/**报文名称*/
	public String mesName;
	/**报文描述*/
	public String mesDesc;
	/**数据源*/
	public String mesSource;
	/**目的地*/
	public String mesDestination;
	/**报文ID*/
	public String mesIdNum;
	/**功能码*/
	public String mesFunId;
	/**数据列表*/
	public List<MessageData> mesDatas;
	public String getMesId() {
		return mesId;
	}
	public void setMesId(String mesId) {
		this.mesId = mesId;
	}
	public String getMesName() {
		return mesName;
	}
	public void setMesName(String mesName) {
		this.mesName = mesName;
	}
	public String getMesDesc() {
		return mesDesc;
	}
	public void setMesDesc(String mesDesc) {
		this.mesDesc = mesDesc;
	}
	public String getMesSource() {
		return mesSource;
	}
	public void setMesSource(String mesSource) {
		this.mesSource = mesSource;
	}
	public String getMesDestination() {
		return mesDestination;
	}
	public void setMesDestination(String mesDestination) {
		this.mesDestination = mesDestination;
	}
	public String getMesIdNum() {
		return mesIdNum;
	}
	public void setMesIdNum(String mesIdNum) {
		this.mesIdNum = mesIdNum;
	}
	public String getMesFunId() {
		return mesFunId;
	}
	public void setMesFunId(String mesFunId) {
		this.mesFunId = mesFunId;
	}
	public List<MessageData> getMesDatas() {
		return mesDatas;
	}
	public void setMesDatas(List<MessageData> mesDatas) {
		this.mesDatas = mesDatas;
	}
}
