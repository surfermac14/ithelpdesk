<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page errorPage="error.jsp" %>
 <jsp:useBean id="userBean" class="com.ts.beans.UserBean" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<!--  Calling another jsp and sending the values as arguments in the url  -->
	<!--  Reading the user bean property  -->	
	<ul><li><a href="updateProfile.jsp"> Update Profile</a> </li>
	<!--  End calling update.jsp -->
	
	
	<!--  Calling servlet to get the user list 
	Check UserComand.java viewAllUser function -->
	
	<li><a href="javascript:document.myForm1.submit();">View All</a>
	<form name="myForm1" action="controller.do">
		<input type=hidden name="form_action" value="user">
		<input type=hidden name="action" value="viewAll"></form>	
	</li>
	<!--  Ending servlet to get the user list -->
	
	<!-- Generating PDF file. Check PDFCommand.java -->
	<li> <a href="javascript:document.myForm2.submit();">Report</a>
	<form name="myForm2" action="controller.do">
		<input type=hidden name="form_action" value="pdfCreate">				
	</form></li>
	<!-- End Generating PDF file. Check PDFCommand.java -->
	
	<li><a href="logout.jsp"> Logout </a></li>
	
	
	</ul>
	</div>   </div>
	  <!-- sidebar ends here  -->    
  
 
 <SCRIPT LANGUAGE = "JavaScript">

<!-- Start call to servlet -->
function getForm(){

		document.myForm.action = "controller.do";		
		document.myForm.submit(); 	

}
//Call to Servlet ends here

function loginForm(){
   	window.location.href="login.jsp";

}
</SCRIPT>
    
<!-- inner content starts here  -->    
<div class="inner">    


	<!--  Reading the userbean's 'name' property. Check LoginCommand.java -->
	<h1> Welcome <jsp:getProperty name="userBean" property="name" /></h1>
		
<body>
<h1> Update Profile</h1>

<!-- Start define a form here-->
<!-- form_action is defined as user and action as update. 
Check the Controller.java and UserCommand.java updateUser function -->
	
  <form name="myFormA" method="post" action="controller.do">
      <input type="hidden" name="form_action" value="user" /> 
      <input type="hidden" name="action" value="update" /> 
      <input type="hidden" name="email"	value="<jsp:getProperty name="userBean" property="email" />" />
   
		<table>
   			<tr>   
   				<td> Name  </td>
				<td><input type=text name="name" value = "<jsp:getProperty name="userBean" property="name" />" /></td>
   			</tr>
      		<tr>
   		   		<td> Address:</td>
				<td><input type=text name="address" value = "<jsp:getProperty name="userBean" property="address" />"></td>
  			</tr>
      		<tr>
   		   		<td> Age</td>
				<td><input type=text name="age" value = "<jsp:getProperty name="userBean" property="age" />"> </td>
  			</tr>
      		<tr>
   		   		<td> Password </td>
				<td><input type="password" name="password" value ="<jsp:getProperty name="userBean" property="password" />" /></td>
   			</tr>
   			<tr>
   				<td><input type="submit" name="enter_button" value="Update" /></td>
   				<td><input type="button" name="enter_button" value="Cancel" onClick="loginForm()" /></td>
   			</tr>
   		</table>
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