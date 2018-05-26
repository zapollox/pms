/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmspurchasesinfo.entity;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmssupplierinfo.entity.PmsSupplierInfo;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 进货信息Entity
 * @author j
 * @version 2018-04-15
 */
public class PmsPurchasesInfo extends DataEntity<PmsPurchasesInfo> {
	
	private static final long serialVersionUID = 1L;
//	private String drugInfoId;		// 药品表id
	private PmsDrugInfo pmsDrugInfo;//药品实体
	private String purchasesMan;		// 进货人
	private Date purchasesDate;		// 进货时间
	private String purchasesNum;		// 进货数量
//	private String supplierId;		// 供应商id
	private PmsSupplierInfo pmsSupplierInfo;//供应商实体
	private String purchasesPric;		// 进货金额
	private Date beginPurchasesDate;		// 开始 进货时间
	private Date endPurchasesDate;		// 结束 进货时间
	private PmsWarehouseInfo pmsWarehouseInfo;//库房实体
	private Date outDate;//过期时间
	private String batchNum;//批次号
	
	public PmsPurchasesInfo() {
		super();
	}

	public PmsPurchasesInfo(String id){
		super(id);
	}

//	@Length(min=0, max=32, message="药品表id长度必须介于 0 和 32 之间")
//	public String getDrugInfoId() {
//		return drugInfoId;
//	}
//
//	public void setDrugInfoId(String drugInfoId) {
//		this.drugInfoId = drugInfoId;
//	}


	public PmsDrugInfo getPmsDrugInfo() {
		return pmsDrugInfo;
	}

	public void setPmsDrugInfo(PmsDrugInfo pmsDrugInfo) {
		this.pmsDrugInfo = pmsDrugInfo;
	}

	@Length(min=0, max=255, message="进货人长度必须介于 0 和 255 之间")
	public String getPurchasesMan() {
		return purchasesMan;
	}

	public void setPurchasesMan(String purchasesMan) {
		this.purchasesMan = purchasesMan;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPurchasesDate() {
		return purchasesDate;
	}

	public void setPurchasesDate(Date purchasesDate) {
		this.purchasesDate = purchasesDate;
	}
	
	@Length(min=0, max=11, message="进货数量长度必须介于 0 和 11 之间")
	public String getPurchasesNum() {
		return purchasesNum;
	}

	public void setPurchasesNum(String purchasesNum) {
		this.purchasesNum = purchasesNum;
	}
	
//	@Length(min=0, max=32, message="供应商id长度必须介于 0 和 32 之间")
//	public String getSupplierId() {
//		return supplierId;
//	}
//
//	public void setSupplierId(String supplierId) {
//		this.supplierId = supplierId;
//	}


	public PmsSupplierInfo getPmsSupplierInfo() {
		return pmsSupplierInfo;
	}

	public void setPmsSupplierInfo(PmsSupplierInfo pmsSupplierInfo) {
		this.pmsSupplierInfo = pmsSupplierInfo;
	}

	public String getPurchasesPric() {
		return purchasesPric;
	}

	public void setPurchasesPric(String purchasesPric) {
		this.purchasesPric = purchasesPric;
	}
	
	public Date getBeginPurchasesDate() {
		return beginPurchasesDate;
	}

	public void setBeginPurchasesDate(Date beginPurchasesDate) {
		this.beginPurchasesDate = beginPurchasesDate;
	}
	
	public Date getEndPurchasesDate() {
		return endPurchasesDate;
	}

	public void setEndPurchasesDate(Date endPurchasesDate) {
		this.endPurchasesDate = endPurchasesDate;
	}

	public PmsWarehouseInfo getPmsWarehouseInfo() {
		return pmsWarehouseInfo;
	}

	public void setPmsWarehouseInfo(PmsWarehouseInfo pmsWarehouseInfo) {
		this.pmsWarehouseInfo = pmsWarehouseInfo;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
}