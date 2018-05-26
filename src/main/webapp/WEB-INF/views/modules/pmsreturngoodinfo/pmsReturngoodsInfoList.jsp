<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退货处理管理</title>
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
		<li class="active"><a href="${ctx}/pmsreturngoodinfo/pmsReturngoodsInfo/">退货处理列表</a></li>
		<shiro:hasPermission name="pmsreturngoodinfo:pmsReturngoodsInfo:edit"><li><a href="${ctx}/pmsreturngoodinfo/pmsReturngoodsInfo/form">退货处理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsReturngoodsInfo" action="${ctx}/pmsreturngoodinfo/pmsReturngoodsInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>药品名：</label>
				<form:select  path="pmsDrugInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="pmsDrugInfo" items="${drugInfoList}">
						<form:option value="${pmsDrugInfo.id}">${pmsDrugInfo.name}</form:option>
					</c:forEach>
				</form:select>
			</li>
			<li><label>退货原因：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>退货数量</th>
				<th>退货金额</th>
				<th>更新时间</th>
				<th>退货原因</th>
				<shiro:hasPermission name="pmsreturngoodinfo:pmsReturngoodsInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsReturngoodsInfo">
			<tr>
				<td><a href="${ctx}/pmsreturngoodinfo/pmsReturngoodsInfo/form?id=${pmsReturngoodsInfo.id}">
					${pmsReturngoodsInfo.pmsDrugInfo.name}
				</a></td>
				<td>
					${pmsReturngoodsInfo.returnNum}
				</td>
				<td>
					${pmsReturngoodsInfo.returnPrice}
				</td>
				<td>
					<fmt:formatDate value="${pmsReturngoodsInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pmsReturngoodsInfo.remarks}
				</td>
				<shiro:hasPermission name="pmsreturngoodinfo:pmsReturngoodsInfo:edit"><td>
    				<a href="${ctx}/pmsreturngoodinfo/pmsReturngoodsInfo/form?id=${pmsReturngoodsInfo.id}">修改</a>
					<a href="${ctx}/pmsreturngoodinfo/pmsReturngoodsInfo/delete?id=${pmsReturngoodsInfo.id}" onclick="return confirmx('确认要删除该退货处理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>