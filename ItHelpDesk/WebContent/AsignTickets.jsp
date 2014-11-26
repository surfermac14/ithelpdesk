<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css" >
<title>Asign Tickets</title>
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
		<div id="emp">
			<table>
				<tr>
					<td>Ticket type</td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td>Priority</td>
					<td><select>
							<option value="Select">--------Select--------</option>
							<option value="High">High</option>
							<option value="Middle">Middle</option>
							<option value="Low">Low</option>
					</select></td>
				</tr>
				<tr>
					<td>Empid</td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td>Issue</td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td>Solution</td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td>Assigned to</td>
					<td><select name="select">
							<option value="Select">-------Select--------</option>
							<option value="  "></option>
							<option value="          "></option>
							<option value="          "></option>
					</select></td>
				</tr>
				<tr>
					<td>Start date</td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td>End date</td>
					<td><input type="text" /></td>
				</tr>
			</table>
			<br>
			<center>
				<input type="button" value="update Ticket" />
			</center>
		</div>
	</div>
</body>
</html>