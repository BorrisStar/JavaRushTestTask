<%--
  Created by IntelliJ IDEA.
  User: Borris
  Date: 25.12.2018
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form id="PartRegisterForm" cssClass="form-horizontal" modelAttribute="parts" method="post" action="saveCompPart">

    <div class="form-group">
        <div class="control-label col-xs-3"> <form:label path="description" >Название</form:label> </div>
        <div class="col-xs-6">
            <form:hidden path="id" value="${partObject.id}"/>
            <form:input cssClass="form-control" path="description" value="${partObject.description}"/>
        </div>
    </div>

    <div class="form-group">
        <div class="control-label col-xs-3"><form:label path="required">Необходимость</form:label></div>
        <div class="col-md-3" >
            <input type="radio" name="required" value="true"/> Да
            <input type="radio" name="required" value="false"/> Нет
        </div>
    </div>

    <div class="form-group">
        <form:label path="amount" cssClass="control-label col-xs-3">Количество</form:label>
        <div class="col-xs-6">
            <form:input cssClass="form-control" path="amount" value="${partObject.amount}"/>
        </div>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col-xs-4">
            </div>
            <div class="col-xs-4">
                <input type="submit" id="savePart" class="btn btn-primary" value="Save" onclick="return submitPartForm();"/>
            </div>
            <div class="col-xs-4">
            </div>
        </div>
    </div>

</form:form>
</body>
</html>
