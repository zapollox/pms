/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsdruginfo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;

/**
 * 药品信息DAO接口
 * @author j
 * @version 2018-04-14
 */
@MyBatisDao
public interface PmsDrugInfoDao extends CrudDao<PmsDrugInfo> {
	
}