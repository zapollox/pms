/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmswarehouseinfo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 库房信息Entity
 * @author j
 * @version 2018-04-15
 */
public class PmsWarehouseInfo extends DataEntity<PmsWarehouseInfo> {
	
	private static final long serialVersionUID = 1L;
	private String warehouseNumber;		// 库房编号
	private String respPerson;		// 负责人
	private String drugType;		// 药品类型
	private String temp;		// 温度
	
	public PmsWarehouseInfo() {
		super();
	}

	public PmsWarehouseInfo(String id){
		super(id);
	}

	@Length(min=0, max=32, message="库房编号长度必须介于 0 和 32 之间")
	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}
	
	@Length(min=0, max=255, message="负责人长度必须介于 0 和 255 之间")
	public String getRespPerson() {
		return respPerson;
	}

	public void setRespPerson(String respPerson) {
		this.respPerson = respPerson;
	}
	
	@Length(min=0, max=255, message="药品类型长度必须介于 0 和 255 之间")
	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	
	@Length(min=0, max=255, message="温度长度必须介于 0 和 255 之间")
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
	
}