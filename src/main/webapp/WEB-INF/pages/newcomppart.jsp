<%--
  Created by IntelliJ IDEA.
  User: Borris
  Date: 25.12.2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Part Information</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
        .panel-title{
            color: #d1cbbc;
        }
    </style>
</head>

<body class=".container-fluid" style="background-color:whitesmoke">
<div class="container myrow-container">
    <div class="panel panel-success">

        <div class="panel-heading" style="background-color:#128181">
        </div>

        <div class="panel-body">
            <h2 class="panel-title" style="color: black">
                <strong>Характеристики детали</strong>
            </h2>

        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function submitPartForm() {
        var description = $('#description').val().trim();
        var amount = $('#amount').val();
        $('#required').val();
        if (description.length === 0) {
            alert('Название нельзя оставлять пустым');
            $('#description').focus();
            return false;
        }
        if (amount < 0) {
            alert('Неподходящее количество на складе');
            $('#amount').focus();
            return false;
        }
        return true;
    };
</script>
</body>
</html>
