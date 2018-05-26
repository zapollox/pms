/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsreturngoodinfo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pmsreturngoodinfo.entity.PmsReturngoodsInfo;

/**
 * 退货处理DAO接口
 * @author j
 * @version 2018-04-21
 */
@MyBatisDao
public interface PmsReturngoodsInfoDao extends CrudDao<PmsReturngoodsInfo> {
	
}