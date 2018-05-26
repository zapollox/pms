<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库房信息管理</title>
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
		<li class="active"><a href="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/">库房信息列表</a></li>
		<shiro:hasPermission name="pmswarehouseinfo:pmsWarehouseInfo:edit"><li><a href="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/form">库房信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsWarehouseInfo" action="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>库房编号：</label>
				<form:input path="warehouseNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>负责人：</label>
				<form:input path="respPerson" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>药品类型：</label>
				<form:select path="drugType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('drug_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>温度：</label>
				<form:input path="temp" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>库房编号</th>
				<th>负责人</th>
				<th>药品类型</th>
				<th>温度</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pmswarehouseinfo:pmsWarehouseInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsWarehouseInfo">
			<tr>
				<td><a href="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/form?id=${pmsWarehouseInfo.id}">
					${pmsWarehouseInfo.warehouseNumber}
				</a></td>
				<td>
					${pmsWarehouseInfo.respPerson}
				</td>
				<td>
					${fns:getDictLabel(pmsWarehouseInfo.drugType, 'drug_type', '')}
				</td>
				<td>
					${pmsWarehouseInfo.temp}
				</td>
				<td>
					<fmt:formatDate value="${pmsWarehouseInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pmsWarehouseInfo.remarks}
				</td>
				<shiro:hasPermission name="pmswarehouseinfo:pmsWarehouseInfo:edit"><td>
    				<a href="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/form?id=${pmsWarehouseInfo.id}">修改</a>
					<a href="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/delete?id=${pmsWarehouseInfo.id}" onclick="return confirmx('确认要删除该库房信息吗？', this.href)">删除</a>
					<a href="${ctx}/pmswarehouseinfo/pmsWarehouseInfo/Warehousedetailslist?pmsWarehouseInfo.id=${pmsWarehouseInfo.id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>