<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进货信息管理</title>
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
		<li class="active"><a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/">进货信息列表</a></li>
		<shiro:hasPermission name="pmspurchasesinfo:pmsPurchasesInfo:edit"><li><a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/form">进货信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsPurchasesInfo" action="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>药品名：</label>
				<%--<form:input path="pmsDrugInfo.name" htmlEscape="false" maxlength="32" class="input-medium"/>--%>
				<form:select  path="pmsDrugInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="pmsDrugInfo" items="${drugInfoList}">
						<form:option value="${pmsDrugInfo.id}">${pmsDrugInfo.name}</form:option>
					</c:forEach>
				</form:select>
			</li>
			<li><label>进货人：</label>
				<form:input path="purchasesMan" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>进货时间：</label>
				<input name="beginPurchasesDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pmsPurchasesInfo.beginPurchasesDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endPurchasesDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pmsPurchasesInfo.endPurchasesDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>供应商id：</label>
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
				<th>进货人</th>
				<th>进货时间</th>
				<th>过期时间</th>
				<th>批次号</th>
				<th>进货数量</th>
				<th>供应商</th>
				<th>库房编号</th>
				<th>进货金额</th>
				<shiro:hasPermission name="pmspurchasesinfo:pmsPurchasesInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsPurchasesInfo">
			<tr>
				<td><a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/form?id=${pmsPurchasesInfo.id}">
					${pmsPurchasesInfo.pmsDrugInfo.name}
				</a></td>
				<td>
					${pmsPurchasesInfo.purchasesMan}
				</td>
				<td>
					<fmt:formatDate value="${pmsPurchasesInfo.purchasesDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${pmsPurchasesInfo.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${pmsPurchasesInfo.batchNum}
				</td>
				<td>
					${pmsPurchasesInfo.purchasesNum}
				</td>
				<td>
					${pmsPurchasesInfo.pmsSupplierInfo.company}
				</td>
				<td>
					${pmsPurchasesInfo.pmsWarehouseInfo.warehouseNumber}
				</td>
				<td>
					${pmsPurchasesInfo.purchasesPric}
				</td>
				<shiro:hasPermission name="pmspurchasesinfo:pmsPurchasesInfo:edit"><td>
    				<a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/form?id=${pmsPurchasesInfo.id}">修改</a>
					<a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/delete?id=${pmsPurchasesInfo.id}" onclick="return confirmx('确认要删除该进货信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>