<%@page import="ithelp.beans.EmployeeBean"%>
<jsp:useBean id="user" class="ithelp.beans.EmployeeBean" scope="session">

</jsp:useBean>
<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css">
<%
	user=(EmployeeBean)session.getAttribute("userBean");
%>
<title>Welcome <%= user.getName() %></title>
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
		<h1>
			<i><center>WELCOME <%= user.getName() %></center></i>
		</h1>
		
		 
		<center>
			<a href="AddEmployee.jsp">Add Employee</a><br> 
			
			<a href="javascript:document.myForm1.submit();"> View Employee Hierarchy</a><br>
			<form name="myForm1" action="controller.do">
				<input type=hidden name="form_action" value="user">
				<input type=hidden name="action" value="viewall">
			</form> 
			<a href="AssignManager.jsp">Assign Manager</a><br> 
			<a href="logout" >Logout</a>
		</center>
	</div>
</body>
</html>


