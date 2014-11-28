<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>Add Employee</title>
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
			/*String  one=(String)session.getAttribute("stats");
			if(one==(null)){
				one="";
			}
			
			out.println(one);
		*/
		%>
		<div id="emp">
			
			<form method="post" action="controller.do">
				<input type="hidden" name="form_action" value="user">
				<input type="hidden" name="action" value="insert"> 
				<table>
					<tr>
						<td>Emp name :</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>Emp email :</td>
						<td><input type="text" name="mail"></td>
					</tr>
					<tr>
						<td>Emp phno :</td>
						<td><input type="text" name="phno"></td>
					</tr>
					<tr>
						<td>Emp pass :</td>
						<td><input type="password" name="pass"></td>
					</tr>
					<tr>
						<td>Department :</td>
						<td><select name="dept">

								<option value="admin">Admin</option>
								<option value="IT">IT</option>
								<option value="Project">Development</option>
						</select></td>
					</tr>
					<tr>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>