<%@ page import="ithelp.dao.TicketDao , ithelp.beans.TicketBean,java.util.Collection"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css" >
<title>View Tickets</title>
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
			<center>View Tickets</center>
		</h1>
		<form action="controller.do">
		<input type="hidden" name="form_action" value="tickets">
		<input type="hidden" name="action" value="select">
		<table border="1" width="100%">

			<%
				TicketDao ed = new TicketDao();
				Collection<TicketBean> beans = ed.selectTickets();
				  for (TicketBean e:beans) {
			%>
			<tr>
				<th>Ticket id</th>
				<th>Ticket type</th>
				<th>Priority</th>
				<th>Empid</th>
				<th>Issue</th>
				<th>Solution</th>
				<th>Assigned to</th>
				<th>Start date</th>
				<th>End date</th>
			</tr>
			<tr>
                     <td>
                         <%=e.getTicketId()%>                       
                     </td>
                      <td>
                         <%=e.getTicketType()%>                         
                     </td>
                      <td>
                         <%=e.getPriority()%>                        
                     </td>
                      <td>
                         <%=e.getEmpid()%>                        
                     </td>
                     <td>
                         <%=e.getIssue()%>                        
                     </td>
                     <td>
                         <%=e.getSolution()%>                        
                     </td>
                     <td>
                         <%=e.getAssignTo()%>                        
                     </td>
                     <td>
                         <%=e.getStartDate()%>                        
                     </td>
                     <td>
                         <%=e.getEndDate()%>                        
                     </td>
                     
                     
                     <td> 
                     	<!-- Select the user to delete. Check UserCommand.java deleteUser function -->
                         <input type="radio" name="ticketid" value="<%=e.getTicketId()%>"  />                            
                     </td>
			
			<%
				  }
			 %>
			
		</table>
		</form>
	</div>
</body>
</html>