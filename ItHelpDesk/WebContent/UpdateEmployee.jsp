<%@ page import="ithelp.dao.EmployeeDao,ithelp.beans.EmployeeBean,java.util.ArrayList,java.util.Collection" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css" >
<title>Update Employee</title>
</head>
<body>
	<div class="mainbody">
		<div class="header">
			<div id="leftimg"></div>

			<div id="txt">
				<h1>IT Help Desk</h1>
			</div>
			<div id="rightimg"></div>

		</div>
		<%
		EmployeeBean e=(EmployeeBean) request.getSession().getAttribute("userBean");
	EmployeeDao ed = new EmployeeDao();
	EmployeeBean emp = ed.selectUserByMail(e);
	
	
	
	
%>
		<div id="emp">
		<form action="controller.do">
			<input type="hidden" name="form_action" value="user" /> 
	        <input type="hidden" name="action" value="update" />
	        <input type="hidden" name="id" value="<%= emp.getEmpid() %>">
			<table>
				
				<tr>
					<td>Emp Name:</td>
					<td><input type="text" name="name" value="<%= emp.getName() %>"></td>
				</tr>
				<tr>
					<td>Email Id:</td>
					<td><input type="text" name="mail" value="<%= emp.getEmail() %>"></td>
				</tr>
				<tr>
					<td>Email Id:</td>
					<td><input type="password" name="pass" value="<%= emp.getPassword() %>"></td>
				</tr>
				<tr>
					<td>Email Id:</td>
					<td><input type="text" name="phno" value="<%= emp.getPhone() %>"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Update" ></td>
				</tr>
			</table>
		</form>	
		</div>
	</div>		
</body>
</html>