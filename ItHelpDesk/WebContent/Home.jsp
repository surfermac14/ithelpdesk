<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>Welcome to IT Service HelpDesk</title>
</head>
<body bgcolor="#E9967A">
	<div class="mainbody">
		<div class="header">
			<div id="leftimg"></div>

			<div id="txt">
				<h1>IT HelpDesk</h1>
			</div>
			<div id="rightimg"></div>

		</div>





		<div class="center">
			<h1>Login Account</h1>


			<form name="myForm" method="get" action="controller.do">
				<input type="hidden" name="form_action" value="user" />
				<input type="hidden" name="action" value="check" />
				<table>
					<tr>
						<td>UserName</td>
						<td><input type="text" name="mail" id="in"></td>

					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="pass" id="in"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center"><input type="Submit"
							value="Login"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
