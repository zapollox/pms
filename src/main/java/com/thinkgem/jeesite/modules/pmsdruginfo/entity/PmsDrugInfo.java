/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsdruginfo.entity;

import com.thinkgem.jeesite.modules.pmssupplierinfo.entity.PmsSupplierInfo;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品信息Entity
 * @author j
 * @version 2018-04-14
 */
public class PmsDrugInfo extends DataEntity<PmsDrugInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 药品名
	private String dosageForms;		// 剂型
	private String drugType;		// 药品类型
	private String attribute;		// 属性
	private String size;		// 规格
	private String component;		// 成分
	private String effect;		// 功效
	private String licenseNo;		// 批准文号
	private Date licenseDate;		// 批准日期
	private String produceCompany;		// 生产商
	private String produceAddress;		// 生产地
	private String standardCode;		// 药品本位码
//	private String supplier;		// 供应商
	private PmsSupplierInfo pmsSupplierInfo;//供应商实体
	
	public PmsDrugInfo() {
		super();
	}

	public PmsDrugInfo(String id){
		super(id);
	}

	@Length(min=0, max=255, message="药品名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="剂型长度必须介于 0 和 255 之间")
	public String getDosageForms() {
		return dosageForms;
	}

	public void setDosageForms(String dosageForms) {
		this.dosageForms = dosageForms;
	}
	
	@Length(min=0, max=255, message="药品类型长度必须介于 0 和 255 之间")
	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	
	@Length(min=0, max=255, message="属性长度必须介于 0 和 255 之间")
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@Length(min=0, max=255, message="规格长度必须介于 0 和 255 之间")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	@Length(min=0, max=255, message="成分长度必须介于 0 和 255 之间")
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}
	
	@Length(min=0, max=255, message="功效长度必须介于 0 和 255 之间")
	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	@Length(min=0, max=255, message="批准文号长度必须介于 0 和 255 之间")
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}
	
	@Length(min=0, max=255, message="生产商长度必须介于 0 和 255 之间")
	public String getProduceCompany() {
		return produceCompany;
	}

	public void setProduceCompany(String produceCompany) {
		this.produceCompany = produceCompany;
	}
	
	@Length(min=0, max=255, message="生产地长度必须介于 0 和 255 之间")
	public String getProduceAddress() {
		return produceAddress;
	}

	public void setProduceAddress(String produceAddress) {
		this.produceAddress = produceAddress;
	}
	
	@Length(min=0, max=255, message="药品本位码长度必须介于 0 和 255 之间")
	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	
//	@Length(min=0, max=255, message="供应商长度必须介于 0 和 255 之间")
//	public String getSupplier() {
//		return supplier;
//	}
//
//	public void setSupplier(String supplier) {
//		this.supplier = supplier;
//	}


	public PmsSupplierInfo getPmsSupplierInfo() {
		return pmsSupplierInfo;
	}

	public void setPmsSupplierInfo(PmsSupplierInfo pmsSupplierInfo) {
		this.pmsSupplierInfo = pmsSupplierInfo;
	}
}