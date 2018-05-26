/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmssellinfo.entity;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 销售管理Entity
 * @author j
 * @version 2018-04-19
 */
public class PmsSellInfo extends DataEntity<PmsSellInfo> {
	
	private static final long serialVersionUID = 1L;
	private PmsDrugInfo pmsDrugInfo;//药品实体
	private String sellNum;		// 销售数量
	private String sellPrice;		// 销售金额
	private PmsWarehouseInfo pmsWarehouseInfo;//库房实体
	private String batchNum;		// 批次号
	
	public PmsSellInfo() {
		super();
	}

	public PmsSellInfo(String id){
		super(id);
	}

	public PmsDrugInfo getPmsDrugInfo() {
		return pmsDrugInfo;
	}

	public void setPmsDrugInfo(PmsDrugInfo pmsDrugInfo) {
		this.pmsDrugInfo = pmsDrugInfo;
	}

	@Length(min=0, max=11, message="销售数量长度必须介于 0 和 11 之间")
	public String getSellNum() {
		return sellNum;
	}

	public void setSellNum(String sellNum) {
		this.sellNum = sellNum;
	}
	
	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public PmsWarehouseInfo getPmsWarehouseInfo() {
		return pmsWarehouseInfo;
	}

	public void setPmsWarehouseInfo(PmsWarehouseInfo pmsWarehouseInfo) {
		this.pmsWarehouseInfo = pmsWarehouseInfo;
	}

	@Length(min=0, max=255, message="批次号长度必须介于 0 和 255 之间")
	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	
}