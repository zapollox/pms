<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进货信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/">进货信息列表</a></li>
		<li class="active"><a href="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/form?id=${pmsPurchasesInfo.id}">进货信息<shiro:hasPermission name="pmspurchasesinfo:pmsPurchasesInfo:edit">${not empty pmsPurchasesInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pmspurchasesinfo:pmsPurchasesInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmsPurchasesInfo" action="${ctx}/pmspurchasesinfo/pmsPurchasesInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">药品名：</label>
			<div class="controls">
				<form:select  path="pmsDrugInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="pmsDrugInfo" items="${drugInfoList}">
						<form:option value="${pmsDrugInfo.id}">${pmsDrugInfo.name}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货人：</label>
			<div class="controls">
				<form:input path="purchasesMan" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货时间：</label>
			<div class="controls">
				<input name="purchasesDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pmsPurchasesInfo.purchasesDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货数量：</label>
			<div class="controls">
				<form:input path="purchasesNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<%--<form:input path="supplierId" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<form:select  path="pmsSupplierInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="supplierInfo" items="${supplierInfoList}">
						<form:option value="${supplierInfo.id}">${supplierInfo.company}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库编号：</label>
			<div class="controls">
				<form:select  path="pmsWarehouseInfo.id" class="input-xlarge required">
					<option value="" label=""/>
					<c:forEach var="warehouse" items="${warehouseList}">
						<form:option value="${warehouse.id}">${warehouse.warehouseNumber}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批次号：</label>
			<div class="controls">
				<form:input path="batchNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">过期时间：</label>
			<div class="controls">
				<input name="outDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${pmsPurchasesInfo.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货金额：</label>
			<div class="controls">
				<form:input path="purchasesPric" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pmspurchasesinfo:pmsPurchasesInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>