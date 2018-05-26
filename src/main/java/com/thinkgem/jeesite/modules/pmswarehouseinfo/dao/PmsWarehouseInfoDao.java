/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmswarehouseinfo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;

/**
 * 库房信息DAO接口
 * @author j
 * @version 2018-04-15
 */
@MyBatisDao
public interface PmsWarehouseInfoDao extends CrudDao<PmsWarehouseInfo> {
	
}