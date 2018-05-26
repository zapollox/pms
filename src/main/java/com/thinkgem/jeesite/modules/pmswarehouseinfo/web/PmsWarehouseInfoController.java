/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmswarehouseinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmsdruginfo.service.PmsDrugInfoService;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.entity.PmsPurchasesInfo;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.service.PmsPurchasesInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.service.PmsWarehouseInfoService;

import java.util.List;

/**
 * 库房信息Controller
 * @author j
 * @version 2018-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/pmswarehouseinfo/pmsWarehouseInfo")
public class PmsWarehouseInfoController extends BaseController {

	@Autowired
	private PmsWarehouseInfoService pmsWarehouseInfoService;
	@Autowired
	private PmsPurchasesInfoService pmsPurchasesInfoService;
	@Autowired
	private PmsDrugInfoService drugInfoService;

	@ModelAttribute
	public PmsWarehouseInfo get(@RequestParam(required=false) String id) {
		PmsWarehouseInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmsWarehouseInfoService.get(id);
		}
		if (entity == null){
			entity = new PmsWarehouseInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pmswarehouseinfo:pmsWarehouseInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmsWarehouseInfo pmsWarehouseInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsWarehouseInfo> page = pmsWarehouseInfoService.findPage(new Page<PmsWarehouseInfo>(request, response), pmsWarehouseInfo); 
		model.addAttribute("page", page);
		return "modules/pmswarehouseinfo/pmsWarehouseInfoList";
	}

	@RequiresPermissions("pmswarehouseinfo:pmsWarehouseInfo:view")
	@RequestMapping(value = "form")
	public String form(PmsWarehouseInfo pmsWarehouseInfo, Model model) {
		model.addAttribute("pmsWarehouseInfo", pmsWarehouseInfo);
		return "modules/pmswarehouseinfo/pmsWarehouseInfoForm";
	}

	@RequiresPermissions("pmswarehouseinfo:pmsWarehouseInfo:edit")
	@RequestMapping(value = "save")
	public String save(PmsWarehouseInfo pmsWarehouseInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmsWarehouseInfo)){
			return form(pmsWarehouseInfo, model);
		}
		pmsWarehouseInfoService.save(pmsWarehouseInfo);
		addMessage(redirectAttributes, "保存库房信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmswarehouseinfo/pmsWarehouseInfo/?repage";
	}
	
	@RequiresPermissions("pmswarehouseinfo:pmsWarehouseInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PmsWarehouseInfo pmsWarehouseInfo, RedirectAttributes redirectAttributes) {
		pmsWarehouseInfoService.delete(pmsWarehouseInfo);
		addMessage(redirectAttributes, "删除库房信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmswarehouseinfo/pmsWarehouseInfo/?repage";
	}

	//库房详情
	@RequiresPermissions("pmswarehouseinfo:pmsWarehouseInfo:view")
	@RequestMapping(value = {"Warehousedetailslist"})
	public String Warehousedetailslist(PmsPurchasesInfo pmsPurchasesInfo,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsPurchasesInfo> page = pmsPurchasesInfoService.findPage(new Page<PmsPurchasesInfo>(request, response), pmsPurchasesInfo);
		model.addAttribute("page", page);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		return "modules/pmswarehouseinfo/pmsWarehousedetailsInfoList";
	}

	//库存信息
	@RequiresPermissions("pmswarehouseinfo:pmsWarehouseInfo:view")
	@RequestMapping(value = {"stockList"})
	public String stockList(PmsPurchasesInfo pmsPurchasesInfo,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsPurchasesInfo> page = pmsPurchasesInfoService.findAllStockPage(new Page<PmsPurchasesInfo>(request, response), pmsPurchasesInfo);
		model.addAttribute("page", page);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		return "modules/pmswarehouseinfo/stockList";
	}

}