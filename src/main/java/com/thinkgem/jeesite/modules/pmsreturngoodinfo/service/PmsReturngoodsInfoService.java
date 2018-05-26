/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsreturngoodinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pmsreturngoodinfo.entity.PmsReturngoodsInfo;
import com.thinkgem.jeesite.modules.pmsreturngoodinfo.dao.PmsReturngoodsInfoDao;

/**
 * 退货处理Service
 * @author j
 * @version 2018-04-21
 */
@Service
@Transactional(readOnly = true)
public class PmsReturngoodsInfoService extends CrudService<PmsReturngoodsInfoDao, PmsReturngoodsInfo> {

	public PmsReturngoodsInfo get(String id) {
		return super.get(id);
	}
	
	public List<PmsReturngoodsInfo> findList(PmsReturngoodsInfo pmsReturngoodsInfo) {
		return super.findList(pmsReturngoodsInfo);
	}
	
	public Page<PmsReturngoodsInfo> findPage(Page<PmsReturngoodsInfo> page, PmsReturngoodsInfo pmsReturngoodsInfo) {
		return super.findPage(page, pmsReturngoodsInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PmsReturngoodsInfo pmsReturngoodsInfo) {
		super.save(pmsReturngoodsInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmsReturngoodsInfo pmsReturngoodsInfo) {
		super.delete(pmsReturngoodsInfo);
	}
	
}