/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmssellinfo.service;

import java.util.List;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.entity.PmsPurchasesInfo;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.service.PmsPurchasesInfoService;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pmssellinfo.entity.PmsSellInfo;
import com.thinkgem.jeesite.modules.pmssellinfo.dao.PmsSellInfoDao;

/**
 * 销售管理Service
 * @author j
 * @version 2018-04-19
 */
@Service
@Transactional(readOnly = true)
public class PmsSellInfoService extends CrudService<PmsSellInfoDao, PmsSellInfo> {
	@Autowired
	private PmsPurchasesInfoService pmsPurchasesInfoService;

	public PmsSellInfo get(String id) {
		return super.get(id);
	}
	
	public List<PmsSellInfo> findList(PmsSellInfo pmsSellInfo) {
		return super.findList(pmsSellInfo);
	}
	
	public Page<PmsSellInfo> findPage(Page<PmsSellInfo> page, PmsSellInfo pmsSellInfo) {
		return super.findPage(page, pmsSellInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PmsSellInfo pmsSellInfo) {
		if (pmsSellInfo.getIsNewRecord()){
			pmsSellInfo.preInsert();
			dao.insert(pmsSellInfo);
			//根据药品id和库房id以及批次找出该药品的数量
			PmsPurchasesInfo pmsPurchasesInfo = new PmsPurchasesInfo();
			pmsPurchasesInfo.setPmsDrugInfo(new PmsDrugInfo(pmsSellInfo.getPmsDrugInfo().getId()));
			pmsPurchasesInfo.setPmsWarehouseInfo(new PmsWarehouseInfo(pmsSellInfo.getPmsWarehouseInfo().getId()));
			pmsPurchasesInfo.setBatchNum(pmsSellInfo.getBatchNum());
			List<PmsPurchasesInfo> list = pmsPurchasesInfoService.findList(pmsPurchasesInfo);
			PmsPurchasesInfo purchasesInfo = list.get(0);
			Integer i = Integer.parseInt(purchasesInfo.getPurchasesNum()) - Integer.parseInt(pmsSellInfo.getSellNum());
			purchasesInfo.setPurchasesNum(i.toString());
			pmsPurchasesInfoService.save(purchasesInfo);
		}else{
			pmsSellInfo.preUpdate();
			dao.update(pmsSellInfo);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PmsSellInfo pmsSellInfo) {
		super.delete(pmsSellInfo);
	}
	
}