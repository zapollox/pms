/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmssupplierinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pmssupplierinfo.entity.PmsSupplierInfo;
import com.thinkgem.jeesite.modules.pmssupplierinfo.dao.PmsSupplierInfoDao;

/**
 * 供应商信息Service
 * @author j
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class PmsSupplierInfoService extends CrudService<PmsSupplierInfoDao, PmsSupplierInfo> {

	public PmsSupplierInfo get(String id) {
		return super.get(id);
	}
	
	public List<PmsSupplierInfo> findList(PmsSupplierInfo pmsSupplierInfo) {
		return super.findList(pmsSupplierInfo);
	}
	
	public Page<PmsSupplierInfo> findPage(Page<PmsSupplierInfo> page, PmsSupplierInfo pmsSupplierInfo) {
		return super.findPage(page, pmsSupplierInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PmsSupplierInfo pmsSupplierInfo) {
		super.save(pmsSupplierInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmsSupplierInfo pmsSupplierInfo) {
		super.delete(pmsSupplierInfo);
	}
	
}