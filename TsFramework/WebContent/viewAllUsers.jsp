

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ts.beans.UserBean"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@ page errorPage="error.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Talent Sprint</title>
		
	<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>

<body>

<!-- content starts here  -->


<!-- top header starts here  -->
<div class="main">

<div class="header-top">
  <div class="right login-top"><a href="#">Login</a> | <a href="#">Forgot Password</a></div>
  </div>  
    <div class="clearfix">&nbsp;</div>
<!-- top header ends here  -->



<!-- header starts here  --><div class="header menu">
    	
    <a href="#">Home</a> | <a href="#">Heading1</a>  |  <a href="#">Heading2</a>   |  <a href="#">Career</a>  
     
    </div><!-- header ends here  -->
    <!-- middle content starts here  -->
    
    <div class="inner-content">
        
   	 <!-- sidebar starts here  -->   
	<div class="sidebar left">
			<div class="glossymenu">
			<h3>News Room</h3>
	<ul>	<!--  Calling another jsp and sending the values as arguments in the url  -->
	<!--  Reading the user bean property  -->	
	<ul><li><a href="updateProfile.jsp"> Update Profile</a> </li>
	<!--  End calling update.jsp -->
	
	
	<!--  Calling servlet to get the user list 
	Check UserComand.java viewAllUser function -->
	
	<li><a href="javascript:document.myForm1.submit();">View All</a>
	<form name="myForm1" action="controller.do">
		<input type=hidden name="form_action" value="user">
		<input type=hidden name="action" value="viewAll">
	</form>
	</li>
	<!--  Ending servlet to get the user list -->
	
	<!-- Generating PDF file. Check PDFCommand.java -->
	<li> <a href="javascript:document.myForm2.submit();">Report</a>
	<form name="myForm2" action="controller.do">
		<input type=hidden name="form_action" value="pdfCreate">				
	</form></li>
	<!-- End Generating PDF file. Check PDFCommand.java -->
	
	<li><a href="logout.jsp"> Logout </a></li></ul>
	</div>   </div>
	  <!-- sidebar ends here  -->    
  
   
    
<!-- inner content starts here  -->    
<div class="inner"> 
	
	<!-- Start define a form here-->
	<!-- form_action is defined as user and action as delete. 
	Check the Controller.java and UserCommand.java class -->

    <form name="myFormA" method="post" action="controller.do">
      <input type="hidden" name="form_action" value="user" /> 
        <input type="hidden" name="action" value="delete" /> 
        
 	<!--  Display the list of users. Check UserCommand.java viewAllUser function -->
        <h1>View All Users!</h1>
        <table border="1">
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Email Id</th>
             <th>Age</th>
            <th>Delete</th>
            </tr>
          <%
                Collection col = (ArrayList)request.getAttribute("userBeans");
                Iterator i = col.iterator();
                while (i.hasNext()) {
                	UserBean item = (UserBean)i.next();
                    %>
                     <tr>
                     <td>
                         <%=item.getName()%>                       
                     </td>
                      <td>
                         <%=item.getAddress()%>                         
                     </td>
                      <td>
                         <%=item.getEmail()%>                        
                     </td>
                      <td>
                         <%=item.getAge()%>                        
                     </td>
                     <td> 
                     	<!-- Select the user to delete. Check UserCommand.java deleteUser function -->
                         <input type="radio" name="notify" value="<%=item.getEmail()%>"  />                            
                     </td>
                     
                     </tr>
				<%
				  }
				%>				
		</table>
		<input type="Submit" name="enter_button" value="Delete" />		
	</form>
	<!-- End define a form here-->
   	
 </div>
<!-- inner content ends here  -->        
    
    
    
    
  </div><!-- middle content ends here  -->
    <!-- footer starts here  --><div class="footer">footer here</div><!-- footer ends here  -->
</div>

<!-- content ends here  -->

</body>
</html>
