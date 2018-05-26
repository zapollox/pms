/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmspurchasesinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmsdruginfo.service.PmsDrugInfoService;
import com.thinkgem.jeesite.modules.pmssupplierinfo.entity.PmsSupplierInfo;
import com.thinkgem.jeesite.modules.pmssupplierinfo.service.PmsSupplierInfoService;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.entity.PmsWarehouseInfo;
import com.thinkgem.jeesite.modules.pmswarehouseinfo.service.PmsWarehouseInfoService;
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
import com.thinkgem.jeesite.modules.pmspurchasesinfo.entity.PmsPurchasesInfo;
import com.thinkgem.jeesite.modules.pmspurchasesinfo.service.PmsPurchasesInfoService;

import java.util.List;

/**
 * 进货信息Controller
 * @author j
 * @version 2018-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/pmspurchasesinfo/pmsPurchasesInfo")
public class PmsPurchasesInfoController extends BaseController {

	@Autowired
	private PmsPurchasesInfoService pmsPurchasesInfoService;
	@Autowired
	private PmsDrugInfoService drugInfoService;
	@Autowired
	private PmsSupplierInfoService supplierInfoService;
	@Autowired
	private PmsWarehouseInfoService pmsWarehouseInfoService;

	@ModelAttribute
	public PmsPurchasesInfo get(@RequestParam(required=false) String id) {
		PmsPurchasesInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmsPurchasesInfoService.get(id);
		}
		if (entity == null){
			entity = new PmsPurchasesInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pmspurchasesinfo:pmsPurchasesInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmsPurchasesInfo pmsPurchasesInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsPurchasesInfo> page = pmsPurchasesInfoService.findPage(new Page<PmsPurchasesInfo>(request, response), pmsPurchasesInfo);
		model.addAttribute("page", page);
		List<PmsSupplierInfo> supplierInfoList = supplierInfoService.findList(new PmsSupplierInfo());
		model.addAttribute("supplierInfoList", supplierInfoList);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);

		return "modules/pmspurchasesinfo/pmsPurchasesInfoList";
	}

	@RequiresPermissions("pmspurchasesinfo:pmsPurchasesInfo:view")
	@RequestMapping(value = "form")
	public String form(PmsPurchasesInfo pmsPurchasesInfo, Model model) {
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		List<PmsSupplierInfo> supplierInfoList = supplierInfoService.findList(new PmsSupplierInfo());
		model.addAttribute("supplierInfoList", supplierInfoList);
		List<PmsWarehouseInfo> warehouseList = pmsWarehouseInfoService.findList(new PmsWarehouseInfo());
		model.addAttribute("warehouseList",warehouseList);
		model.addAttribute("pmsPurchasesInfo", pmsPurchasesInfo);
		return "modules/pmspurchasesinfo/pmsPurchasesInfoForm";
	}

	@RequiresPermissions("pmspurchasesinfo:pmsPurchasesInfo:edit")
	@RequestMapping(value = "save")
	public String save(PmsPurchasesInfo pmsPurchasesInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmsPurchasesInfo)){
			return form(pmsPurchasesInfo, model);
		}
		pmsPurchasesInfoService.save(pmsPurchasesInfo);
		addMessage(redirectAttributes, "保存进货信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmspurchasesinfo/pmsPurchasesInfo/?repage";
	}
	
	@RequiresPermissions("pmspurchasesinfo:pmsPurchasesInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PmsPurchasesInfo pmsPurchasesInfo, RedirectAttributes redirectAttributes) {
		pmsPurchasesInfoService.delete(pmsPurchasesInfo);
		addMessage(redirectAttributes, "删除进货信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmspurchasesinfo/pmsPurchasesInfo/?repage";
	}

}