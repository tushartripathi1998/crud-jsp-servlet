<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>User Management Application</title>
	</head>
	<body>
		<center>
			<h1>User Management</h1>
			<h2>
				<a href="new">Add new Users</a>
				&nbsp;&nbsp;&nbsp;
				<a href="list">List all Users</a>
			</h2>
		</center>
		
		<div align="center">
			<c:if test="${user!=null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${user==null}">
				<form action="insert" method="post">
			</c:if>
				<c:if test="${user!=null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}'/>">
				</c:if>
				<label>Enter Name</label>
				<input type="text" name="name" value="<c:out value='${user.name}'/>">
				<label>Enter Email</label>
				<input type="email" name="email" value="<c:out value='${user.email}'/>">
				<label>Enter Country</label>
				<input type="text" name="country" value="<c:out value='${user.country}'/>">
				
				<input type="submit" value="Save" />
				
				</form>
		</div>
		
	</body>
</html>