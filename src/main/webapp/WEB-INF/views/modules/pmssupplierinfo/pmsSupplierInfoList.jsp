<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理</title>
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
		<li class="active"><a href="${ctx}/pmssupplierinfo/pmsSupplierInfo/">供应商信息列表</a></li>
		<shiro:hasPermission name="pmssupplierinfo:pmsSupplierInfo:edit"><li><a href="${ctx}/pmssupplierinfo/pmsSupplierInfo/form">供应商信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsSupplierInfo" action="${ctx}/pmssupplierinfo/pmsSupplierInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司名称：</label>
				<form:input path="company" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>公司地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="telphone" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公司名称</th>
				<th>公司地址</th>
				<th>经营范围</th>
				<th>联系电话</th>
				<th>签约时间</th>
				<th>签约时长</th>
				<%--<th>更新时间</th>--%>
				<th>备注信息</th>
				<shiro:hasPermission name="pmssupplierinfo:pmsSupplierInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsSupplierInfo">
			<tr>
				<td><a href="${ctx}/pmssupplierinfo/pmsSupplierInfo/form?id=${pmsSupplierInfo.id}">
					${pmsSupplierInfo.company}
				</a></td>
				<td>
					${pmsSupplierInfo.address}
				</td>
				<td>
					${pmsSupplierInfo.scope}
				</td>
				<td>
					${pmsSupplierInfo.telphone}
				</td>
				<td>
					<fmt:formatDate value="${pmsSupplierInfo.signTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pmsSupplierInfo.year}
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${pmsSupplierInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<td>
					${pmsSupplierInfo.remarks}
				</td>
				<shiro:hasPermission name="pmssupplierinfo:pmsSupplierInfo:edit"><td>
    				<a href="${ctx}/pmssupplierinfo/pmsSupplierInfo/form?id=${pmsSupplierInfo.id}">修改</a>
					<a href="${ctx}/pmssupplierinfo/pmsSupplierInfo/delete?id=${pmsSupplierInfo.id}" onclick="return confirmx('确认要删除该供应商信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>