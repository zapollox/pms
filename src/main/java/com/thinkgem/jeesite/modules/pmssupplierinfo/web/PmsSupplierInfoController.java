/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmssupplierinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.pmssupplierinfo.entity.PmsSupplierInfo;
import com.thinkgem.jeesite.modules.pmssupplierinfo.service.PmsSupplierInfoService;

/**
 * 供应商信息Controller
 * @author j
 * @version 2018-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pmssupplierinfo/pmsSupplierInfo")
public class PmsSupplierInfoController extends BaseController {

	@Autowired
	private PmsSupplierInfoService pmsSupplierInfoService;
	
	@ModelAttribute
	public PmsSupplierInfo get(@RequestParam(required=false) String id) {
		PmsSupplierInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmsSupplierInfoService.get(id);
		}
		if (entity == null){
			entity = new PmsSupplierInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pmssupplierinfo:pmsSupplierInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmsSupplierInfo pmsSupplierInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsSupplierInfo> page = pmsSupplierInfoService.findPage(new Page<PmsSupplierInfo>(request, response), pmsSupplierInfo); 
		model.addAttribute("page", page);
		return "modules/pmssupplierinfo/pmsSupplierInfoList";
	}

	@RequiresPermissions("pmssupplierinfo:pmsSupplierInfo:view")
	@RequestMapping(value = "form")
	public String form(PmsSupplierInfo pmsSupplierInfo, Model model) {
		model.addAttribute("pmsSupplierInfo", pmsSupplierInfo);
		return "modules/pmssupplierinfo/pmsSupplierInfoForm";
	}

	@RequiresPermissions("pmssupplierinfo:pmsSupplierInfo:edit")
	@RequestMapping(value = "save")
	public String save(PmsSupplierInfo pmsSupplierInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmsSupplierInfo)){
			return form(pmsSupplierInfo, model);
		}
		pmsSupplierInfoService.save(pmsSupplierInfo);
		addMessage(redirectAttributes, "保存供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmssupplierinfo/pmsSupplierInfo/?repage";
	}
	
	@RequiresPermissions("pmssupplierinfo:pmsSupplierInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PmsSupplierInfo pmsSupplierInfo, RedirectAttributes redirectAttributes) {
		pmsSupplierInfoService.delete(pmsSupplierInfo);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/pmssupplierinfo/pmsSupplierInfo/?repage";
	}

}