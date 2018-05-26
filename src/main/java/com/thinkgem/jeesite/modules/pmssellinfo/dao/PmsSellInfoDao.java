/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmssellinfo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pmssellinfo.entity.PmsSellInfo;

/**
 * 销售管理DAO接口
 * @author j
 * @version 2018-04-19
 */
@MyBatisDao
public interface PmsSellInfoDao extends CrudDao<PmsSellInfo> {
	
}