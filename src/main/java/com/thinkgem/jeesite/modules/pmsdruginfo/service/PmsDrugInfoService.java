/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsdruginfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmsdruginfo.dao.PmsDrugInfoDao;

/**
 * 药品信息Service
 * @author j
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class PmsDrugInfoService extends CrudService<PmsDrugInfoDao, PmsDrugInfo> {

	public PmsDrugInfo get(String id) {
		return super.get(id);
	}
	
	public List<PmsDrugInfo> findList(PmsDrugInfo pmsDrugInfo) {
		return super.findList(pmsDrugInfo);
	}
	
	public Page<PmsDrugInfo> findPage(Page<PmsDrugInfo> page, PmsDrugInfo pmsDrugInfo) {
		return super.findPage(page, pmsDrugInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PmsDrugInfo pmsDrugInfo) {
		super.save(pmsDrugInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmsDrugInfo pmsDrugInfo) {
		super.delete(pmsDrugInfo);
	}
	
}