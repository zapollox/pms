<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售信息管理</title>
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
		function getBatchNum() {
			var warehouseId = $("#warehouseId").val();
			//获得批次下拉框对象
            var batchSelect = document.getElementById("batchNum");
            $.ajax({
                type: 'POST',
                url: "${ctx}/pmssellinfo/pmsSellInfo/findBatchsByWareHouseId",
                data: {'warehouseId': warehouseId},
                success: function (data) {
                    //清空下拉框，仅留提示选项
                    batchSelect.length = 0;
                    //将值填充到下拉框中
                    for (var i = 0; i < data.length; i++) {
                        batchSelect[i] = new Option(data[i], data[i]);
                    }
                }
			});
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pmssellinfo/pmsSellInfo/">销售信息列表</a></li>
		<li class="active"><a href="${ctx}/pmssellinfo/pmsSellInfo/form?id=${pmsSellInfo.id}">销售信息<shiro:hasPermission name="pmssellinfo:pmsSellInfo:edit">${not empty pmsSellInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pmssellinfo:pmsSellInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmsSellInfo" action="${ctx}/pmssellinfo/pmsSellInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">药品id：</label>
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
			<label class="control-label">销售数量：</label>
			<div class="controls">
				<form:input path="sellNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售金额：</label>
			<div class="controls">
				<form:input path="sellPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库房编号：</label>
			<div class="controls">
				<form:select id="warehouseId" path="pmsWarehouseInfo.id" class="input-xlarge required" onchange="getBatchNum();">
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
				<form:select id="batchNum" path="batchNum" class="input-medium">
					<%--<form:option value=""></form:option>--%>
					<%--<c:forEach var="collCourseInfo" items="${collCourseInfoList}">--%>
						<%--<form:option value="${collCourseInfo.courseName}">${collCourseInfo.courseName}</form:option>--%>
					<%--</c:forEach>--%>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pmssellinfo:pmsSellInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>