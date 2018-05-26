/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsreturngoodinfo.entity;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 退货处理Entity
 * @author j
 * @version 2018-04-21
 */
public class PmsReturngoodsInfo extends DataEntity<PmsReturngoodsInfo> {
	
	private static final long serialVersionUID = 1L;
	private PmsDrugInfo pmsDrugInfo;		// 药品id
	private String returnNum;		// 退货数量
	private String returnPrice;		// 退货金额
	
	public PmsReturngoodsInfo() {
		super();
	}

	public PmsReturngoodsInfo(String id){
		super(id);
	}

	public PmsDrugInfo getPmsDrugInfo() {
		return pmsDrugInfo;
	}

	public void setPmsDrugInfo(PmsDrugInfo pmsDrugInfo) {
		this.pmsDrugInfo = pmsDrugInfo;
	}

	@Length(min=0, max=11, message="退货数量长度必须介于 0 和 11 之间")
	public String getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(String returnNum) {
		this.returnNum = returnNum;
	}
	
	public String getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(String returnPrice) {
		this.returnPrice = returnPrice;
	}
	
}