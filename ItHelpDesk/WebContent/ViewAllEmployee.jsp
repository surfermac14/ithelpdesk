<%@ page import="ithelp.dao.EmployeeDao,ithelp.beans.EmployeeBean,java.util.ArrayList,java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>View All Employees</title>
</head>



<%
	EmployeeDao ed = new EmployeeDao();
	Collection<EmployeeBean> beans = ed.selectUsers();
	
%>
<body>
	<div class="mainbody">
		<div class="header">
			<div id="leftimg"></div>

			<div id="txt">
				<h1>IT Help Desk</h1>
			</div>
			<div id="rightimg"></div>

		</div>
		<div id="emp">
			<table border="1" style="width: 75%">
				<tr>
					<th>Employee ID</th>
					<th>Employee Name</th>
					<th>Employee Email</th>
					<th>Employee Phone</th>
					<th>Employee Department</th>
				</tr>
				 <%
               
                
                for (EmployeeBean e:beans) {
                	
                    %>
                     <tr>
                     <td>
                         <%=e.getEmpid()%>                       
                     </td>
                      <td>
                         <%=e.getName()%>                         
                     </td>
                      <td>
                         <%=e.getEmail()%>                        
                     </td>
                      <td>
                         <%=e.getPhone()%>                        
                     </td>
                     <td>
                         <%=e.getDept()%>                        
                     </td>
                     <td> 
                     	<!-- Select the user to delete. Check UserCommand.java deleteUser function -->
                         <input type="radio" name="notify" value="<%=e.getEmail()%>"  />                            
                     </td>
                     
                     </tr>
				<%
				  }
				%>	
			</table>
		</div>
	</div>
</body>
</html>