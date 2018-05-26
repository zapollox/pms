/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pmsreturngoodinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.pmsdruginfo.entity.PmsDrugInfo;
import com.thinkgem.jeesite.modules.pmsdruginfo.service.PmsDrugInfoService;
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
import com.thinkgem.jeesite.modules.pmsreturngoodinfo.entity.PmsReturngoodsInfo;
import com.thinkgem.jeesite.modules.pmsreturngoodinfo.service.PmsReturngoodsInfoService;

import java.util.List;

/**
 * 退货处理Controller
 * @author j
 * @version 2018-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/pmsreturngoodinfo/pmsReturngoodsInfo")
public class PmsReturngoodsInfoController extends BaseController {

	@Autowired
	private PmsReturngoodsInfoService pmsReturngoodsInfoService;
	@Autowired
	private PmsDrugInfoService drugInfoService;

	@ModelAttribute
	public PmsReturngoodsInfo get(@RequestParam(required=false) String id) {
		PmsReturngoodsInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmsReturngoodsInfoService.get(id);
		}
		if (entity == null){
			entity = new PmsReturngoodsInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pmsreturngoodinfo:pmsReturngoodsInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmsReturngoodsInfo pmsReturngoodsInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmsReturngoodsInfo> page = pmsReturngoodsInfoService.findPage(new Page<PmsReturngoodsInfo>(request, response), pmsReturngoodsInfo); 
		model.addAttribute("page", page);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		return "modules/pmsreturngoodinfo/pmsReturngoodsInfoList";
	}

	@RequiresPermissions("pmsreturngoodinfo:pmsReturngoodsInfo:view")
	@RequestMapping(value = "form")
	public String form(PmsReturngoodsInfo pmsReturngoodsInfo, Model model) {
		model.addAttribute("pmsReturngoodsInfo", pmsReturngoodsInfo);
		List<PmsDrugInfo> drugInfoList = drugInfoService.findList(new PmsDrugInfo());
		model.addAttribute("drugInfoList", drugInfoList);
		return "modules/pmsreturngoodinfo/pmsReturngoodsInfoForm";
	}

	@RequiresPermissions("pmsreturngoodinfo:pmsReturngoodsInfo:edit")
	@RequestMapping(value = "save")
	public String save(PmsReturngoodsInfo pmsReturngoodsInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmsReturngoodsInfo)){
			return form(pmsReturngoodsInfo, model);
		}
		pmsReturngoodsInfoService.save(pmsReturngoodsInfo);
		addMessage(redirectAttributes, "保存退货处理成功");
		return "redirect:"+Global.getAdminPath()+"/pmsreturngoodinfo/pmsReturngoodsInfo/?repage";
	}
	
	@RequiresPermissions("pmsreturngoodinfo:pmsReturngoodsInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PmsReturngoodsInfo pmsReturngoodsInfo, RedirectAttributes redirectAttributes) {
		pmsReturngoodsInfoService.delete(pmsReturngoodsInfo);
		addMessage(redirectAttributes, "删除退货处理成功");
		return "redirect:"+Global.getAdminPath()+"/pmsreturngoodinfo/pmsReturngoodsInfo/?repage";
	}

}