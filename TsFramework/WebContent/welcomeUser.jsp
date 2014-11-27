<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page errorPage="error.jsp" %>
 
<jsp:useBean id="userBean" class="com.ts.beans.UserBean" scope="session" />

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
        
   
	 <!-- side bar starts here  -->   
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
	  <!-- side bar ends here  -->      
  
   
    
<!-- inner content starts here  -->    
<div class="inner"> 

	<!--  Reading the userbean's 'name' property. Check LoginCommand.java -->
	<h1> Welcome <jsp:getProperty name="userBean" property="name" /></h1>
		
	
 </div>
<!-- inner content ends here  -->        
    </div><!-- middle content ends here  -->
    <!-- footer starts here  --><div class="footer">footer here</div><!-- footer ends here  -->
</div>

<!-- content ends here  -->

 </div>

</body>
</html>