/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmswarehouseinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.dao.PmsWarehouseInfoDao;

/**
 * 库房信息Service
 * @author j
 * @version 2018-04-15
 */
@Service
@Transactional(readOnly = true)
public class PmsWarehouseInfoService extends CrudService<PmsWarehouseInfoDao, PmsWarehouseInfo> {

	public PmsWarehouseInfo get(String id) {
		return super.get(id);
	}
	
	public List<PmsWarehouseInfo> findList(PmsWarehouseInfo pmsWarehouseInfo) {
		return super.findList(pmsWarehouseInfo);
	}
	
	public Page<PmsWarehouseInfo> findPage(Page<PmsWarehouseInfo> page, PmsWarehouseInfo pmsWarehouseInfo) {
		return super.findPage(page, pmsWarehouseInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PmsWarehouseInfo pmsWarehouseInfo) {
		super.save(pmsWarehouseInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmsWarehouseInfo pmsWarehouseInfo) {
		super.delete(pmsWarehouseInfo);
	}
	
}