<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售信息管理</title>
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
		<li class="active"><a href="${ctx}/pmssellinfo/pmsSellInfo/">销售信息列表</a></li>
		<shiro:hasPermission name="pmssellinfo:pmsSellInfo:edit"><li><a href="${ctx}/pmssellinfo/pmsSellInfo/form">销售信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsSellInfo" action="${ctx}/pmssellinfo/pmsSellInfo/" method="post" class="breadcrumb form-search">
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
			<%--<li><label>销售数量：</label>--%>
				<%--<form:input path="sellNum" htmlEscape="false" maxlength="11" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>销售金额：</label>--%>
				<%--<form:input path="sellPrice" htmlEscape="false" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>库房编号：</label>
				<form:select  path="pmsWarehouseInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="warehouse" items="${warehouseList}">
						<form:option value="${warehouse.id}">${warehouse.warehouseNumber}</form:option>
					</c:forEach>
				</form:select>
			</li>
			<li><label>批次号：</label>
				<form:input path="batchNum" htmlEscape="false" maxlength="11" class="input-medium"/>
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
				<th>销售数量</th>
				<th>销售金额</th>
				<th>库房编号</th>
				<th>批次号</th>
				<%--<th>更新时间</th>--%>
				<%--<th>备注信息</th>--%>
				<shiro:hasPermission name="pmssellinfo:pmsSellInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsSellInfo">
			<tr>
				<td>
					${pmsSellInfo.pmsDrugInfo.name}
				</td>
				<td>
					${pmsSellInfo.sellNum}
				</td>
				<td>
					${pmsSellInfo.sellPrice}
				</td>
				<td>
					${pmsSellInfo.pmsWarehouseInfo.warehouseNumber}
				</td>
				<td>
					${pmsSellInfo.batchNum}
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${pmsSellInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${pmsSellInfo.remarks}--%>
				<%--</td>--%>
				<shiro:hasPermission name="pmssellinfo:pmsSellInfo:edit"><td>
    				<a href="${ctx}/pmssellinfo/pmsSellInfo/form?id=${pmsSellInfo.id}">修改</a>
					<a href="${ctx}/pmssellinfo/pmsSellInfo/delete?id=${pmsSellInfo.id}" onclick="return confirmx('确认要删除该销售信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>