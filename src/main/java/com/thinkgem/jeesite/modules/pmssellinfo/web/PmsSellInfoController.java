/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmssellinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmsdruginfo.service.PmsDrugInfoService;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.entity.PmsPurchasesInfo;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.service.PmsPurchasesInfoService;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.service.PmsWarehouseInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pmssellinfo.entity.PmsSellInfo;
import com.thinkgem.jeesite.modules.pmssellinfo.service.PmsSellInfoService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 销售管理Controller
 * @author j
 * @version 2018-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pmssellinfo/pmsSellInfo")
public class PmsSellInfoController extends BaseController {

	@Autowired
	private PmsSellInfoService pmsSellInfoService;
	@Autowired
	private PmsDrugInfoService drugInfoService;
	@Autowired
	private PmsWarehouseInfoService pmsWarehouseInfoService;
	@Autowired
	private PmsPurchasesInfoService pmsPurchasesInfoService;

	@ModelAttribute
	public PmsSellInfo get(@RequestParam(required=false) String id) {
		PmsSellInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmsSellInfoService.get(id);
		}
		if (entity == null){
			entity = new PmsSellInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pmssellinfo:pmsSellInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmsSellInfo pmsSellInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsSellInfo> page = pmsSellInfoService.findPage(new Page<PmsSellInfo>(request, response), pmsSellInfo); 
		model.addAttribute("page", page);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		List<PmsWarehouseInfo> warehouseList = pmsWarehouseInfoService.findList(new PmsWarehouseInfo());
		model.addAttribute("warehouseList",warehouseList);
		return "modules/pmssellinfo/pmsSellInfoList";
	}

	@RequiresPermissions("pmssellinfo:pmsSellInfo:view")
	@RequestMapping(value = "form")
	public String form(PmsSellInfo pmsSellInfo, Model model) {
		model.addAttribute("pmsSellInfo", pmsSellInfo);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		List<PmsWarehouseInfo> warehouseList = pmsWarehouseInfoService.findList(new PmsWarehouseInfo());
		model.addAttribute("warehouseList",warehouseList);
		return "modules/pmssellinfo/pmsSellInfoForm";
	}

	@RequiresPermissions("pmssellinfo:pmsSellInfo:edit")
	@RequestMapping(value = "save")
	public String save(PmsSellInfo pmsSellInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmsSellInfo)){
			return form(pmsSellInfo, model);
		}

		pmsSellInfoService.save(pmsSellInfo);
		addMessage(redirectAttributes, "保存销售信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmssellinfo/pmsSellInfo/?repage";
	}
	
	@RequiresPermissions("pmssellinfo:pmsSellInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PmsSellInfo pmsSellInfo, RedirectAttributes redirectAttributes) {
		pmsSellInfoService.delete(pmsSellInfo);
		addMessage(redirectAttributes, "删除销售信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmssellinfo/pmsSellInfo/?repage";
	}

	//根据库房编号查询该库房下的所有批次
	@RequiresPermissions("pmssellinfo:pmsSellInfo:view")
	@RequestMapping(value = "findBatchsByWareHouseId")
	@ResponseBody
	public List<String> findBatchsByWareHouseId(String warehouseId){
		PmsPurchasesInfo pmsPurchasesInfo=new PmsPurchasesInfo();
		pmsPurchasesInfo.setPmsWarehouseInfo(new PmsWarehouseInfo(warehouseId));
		List<PmsPurchasesInfo> purchasesInfoList = pmsPurchasesInfoService.findList(pmsPurchasesInfo);
		List<String> batchAllNumsList = new ArrayList<String>();
 		if(purchasesInfoList!=null && purchasesInfoList.size()>0){
			for(PmsPurchasesInfo p : purchasesInfoList){
				if(!p.getBatchNum().equals("")) {
					batchAllNumsList.add(p.getBatchNum());
				}
			}
		}
		List<String> batchNums = new ArrayList<String>(new HashSet<String>(batchAllNumsList));
		return batchNums;
	}

}