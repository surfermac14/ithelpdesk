<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        
   	 <!-- side bar starts here  -->   
	<div class="sidebar left">
			<div class="glossymenu">
			<h3>News Room</h3>
	<ul><li><a class="menuitem aboutuss" href="aboutus.php">Heading1</a></li>
	<li><a class="menuitem teams" href="leadership.php">Heading2</a></li>
	<li><a class="menuitem frugals" href="frugal-engineering.php">Heading3</a></li></ul>
	</div>   </div>
	  <!-- side bar ends here  -->    
  
       
<!-- inner content starts here  -->    

<div class="inner">    

<SCRIPT LANGUAGE = "JavaScript">

<!-- Start call to servlet -->
function getForm(){

		// Calling Controller.java
		document.myForm.action = "controller.do";		
		document.myForm.submit(); 	

	}
//Call to Servlet ends here

function loginForm(){
   	window.location.href="login.jsp";

}

</SCRIPT>

<!-- Start define a form here-->
<!-- form_action is defined as login. Check Controller.java and LoginCommand.java class -->

  <form name="myForm" method="post" action="controller.do">
      <input type="hidden" name="form_action" value="login" />
 
        <h1>login Page</h1>
	 	<table> 	
	 		<tr>
				<td>User Name:</td>
				<td> <input type="text" name="emailId"/></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
	   		<tr>
	    		<td><input type="submit" name="enter_button1" value="Login"/></td>	
	   	 		<td><input type="submit" name="enter_button2" value="Cancel" /></td>
	   
	   		</tr>
	   		<tr> </tr>
	   		<tr>
				<td>Sign Up</td>
				<td> <a href="newUser.jsp"> New User </a></td>
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