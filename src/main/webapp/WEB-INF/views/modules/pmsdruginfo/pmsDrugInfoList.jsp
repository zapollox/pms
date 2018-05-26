<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pmsdruginfo/pmsDrugInfo/">药品信息列表</a></li>
		<shiro:hasPermission name="pmsdruginfo:pmsDrugInfo:edit"><li><a href="${ctx}/pmsdruginfo/pmsDrugInfo/form">药品信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsDrugInfo" action="${ctx}/pmsdruginfo/pmsDrugInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>药品名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>剂型：</label>
				<form:select path="dosageForms" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dosage_forms')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>药品类型：</label>
				<form:select path="drugType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('drug_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>生产商：</label>
				<form:input path="produceCompany" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>供应商：</label>
				<%--<form:input path="supplier" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
				<form:select  path="pmsSupplierInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="supplierInfo" items="${supplierInfoList}">
						<form:option value="${supplierInfo.id}">${supplierInfo.company}</form:option>
					</c:forEach>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>药品名</th>
				<th>剂型</th>
				<th>药品类型</th>
				<th>属性</th>
				<th>规格</th>
				<th>成分</th>
				<th>功效</th>
				<th>批准文号</th>
				<th>批准日期</th>
				<th>生产商</th>
				<th>生产地</th>
				<th>药品本位码</th>
				<th>供应商</th>
				<%--<th>更新时间</th>--%>
				<th>备注信息</th>
				<shiro:hasPermission name="pmsdruginfo:pmsDrugInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsDrugInfo">
			<tr>
				<td><a href="${ctx}/pmsdruginfo/pmsDrugInfo/form?id=${pmsDrugInfo.id}">
					${pmsDrugInfo.name}
				</a></td>
				<td>
					${fns:getDictLabel(pmsDrugInfo.dosageForms, 'dosage_forms', '')}
				</td>
				<td>
					${fns:getDictLabel(pmsDrugInfo.drugType, 'drug_type', '')}
				</td>
				<td>
					<%--${pmsDrugInfo.attribute}--%>
					${fns:getDictLabel(pmsDrugInfo.attribute, 'drug_property', '')}
				</td>
				<td>
					${pmsDrugInfo.size}
				</td>
				<td>
					${pmsDrugInfo.component}
				</td>
				<td>
					${pmsDrugInfo.effect}
				</td>
				<td>
					${pmsDrugInfo.licenseNo}
				</td>
				<td>
					<fmt:formatDate value="${pmsDrugInfo.licenseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pmsDrugInfo.produceCompany}
				</td>
				<td>
					${pmsDrugInfo.produceAddress}
				</td>
				<td>
					${pmsDrugInfo.standardCode}
				</td>
				<td>
					${pmsDrugInfo.pmsSupplierInfo.company}
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${pmsDrugInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<td>
					${pmsDrugInfo.remarks}
				</td>
				<shiro:hasPermission name="pmsdruginfo:pmsDrugInfo:edit"><td>
    				<a href="${ctx}/pmsdruginfo/pmsDrugInfo/form?id=${pmsDrugInfo.id}">修改</a>
					<a href="${ctx}/pmsdruginfo/pmsDrugInfo/delete?id=${pmsDrugInfo.id}" onclick="return confirmx('确认要删除该药品信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>