/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsdruginfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.pmssupplierinfo.entity.PmsSupplierInfo;
import com.thinkgem.jeesite.modules.pmssupplierinfo.service.PmsSupplierInfoService;
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
import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmsdruginfo.service.PmsDrugInfoService;

import java.util.List;

/**
 * 药品信息Controller
 * @author j
 * @version 2018-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pmsdruginfo/pmsDrugInfo")
public class PmsDrugInfoController extends BaseController {

	@Autowired
	private PmsDrugInfoService pmsDrugInfoService;
	@Autowired
	private PmsSupplierInfoService supplierInfoService;
	
	@ModelAttribute
	public PmsDrugInfo get(@RequestParam(required=false) String id) {
		PmsDrugInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmsDrugInfoService.get(id);
		}
		if (entity == null){
			entity = new PmsDrugInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pmsdruginfo:pmsDrugInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmsDrugInfo pmsDrugInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsDrugInfo> page = pmsDrugInfoService.findPage(new Page<PmsDrugInfo>(request, response), pmsDrugInfo); 
		model.addAttribute("page", page);
		List<PmsSupplierInfo> supplierInfoList = supplierInfoService.findList(new PmsSupplierInfo());
		model.addAttribute("supplierInfoList", supplierInfoList);
		return "modules/pmsdruginfo/pmsDrugInfoList";
	}

	@RequiresPermissions("pmsdruginfo:pmsDrugInfo:view")
	@RequestMapping(value = "form")
	public String form(PmsDrugInfo pmsDrugInfo, Model model) {
		model.addAttribute("pmsDrugInfo", pmsDrugInfo);
		List<PmsSupplierInfo> supplierInfoList = supplierInfoService.findList(new PmsSupplierInfo());
		model.addAttribute("supplierInfoList", supplierInfoList);
		return "modules/pmsdruginfo/pmsDrugInfoForm";
	}

	@RequiresPermissions("pmsdruginfo:pmsDrugInfo:edit")
	@RequestMapping(value = "save")
	public String save(PmsDrugInfo pmsDrugInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmsDrugInfo)){
			return form(pmsDrugInfo, model);
		}
		pmsDrugInfoService.save(pmsDrugInfo);
		addMessage(redirectAttributes, "保存药品信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmsdruginfo/pmsDrugInfo/?repage";
	}
	
	@RequiresPermissions("pmsdruginfo:pmsDrugInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PmsDrugInfo pmsDrugInfo, RedirectAttributes redirectAttributes) {
		pmsDrugInfoService.delete(pmsDrugInfo);
		addMessage(redirectAttributes, "删除药品信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmsdruginfo/pmsDrugInfo/?repage";
	}

}