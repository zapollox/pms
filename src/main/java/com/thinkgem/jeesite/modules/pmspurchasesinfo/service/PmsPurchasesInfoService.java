/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmspurchasesinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.entity.PmsPurchasesInfo;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.dao.PmsPurchasesInfoDao;

/**
 * 进货信息Service
 * @author j
 * @version 2018-04-15
 */
@Service
@Transactional(readOnly = true)
public class PmsPurchasesInfoService extends CrudService<PmsPurchasesInfoDao, PmsPurchasesInfo> {

	public PmsPurchasesInfo get(String id) {
		return super.get(id);
	}
	
	public List<PmsPurchasesInfo> findList(PmsPurchasesInfo pmsPurchasesInfo) {
		return super.findList(pmsPurchasesInfo);
	}
	
	public Page<PmsPurchasesInfo> findPage(Page<PmsPurchasesInfo> page, PmsPurchasesInfo pmsPurchasesInfo) {
		return super.findPage(page, pmsPurchasesInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PmsPurchasesInfo pmsPurchasesInfo) {
		super.save(pmsPurchasesInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmsPurchasesInfo pmsPurchasesInfo) {
		super.delete(pmsPurchasesInfo);
	}

	public Page<PmsPurchasesInfo> findAllStockPage(Page<PmsPurchasesInfo> pmsPurchasesInfoPage, PmsPurchasesInfo pmsPurchasesInfo) {
		pmsPurchasesInfo.setPage(pmsPurchasesInfoPage);
		pmsPurchasesInfoPage.setList(dao.findAllStockPage(pmsPurchasesInfo));
		return pmsPurchasesInfoPage;
	}
}