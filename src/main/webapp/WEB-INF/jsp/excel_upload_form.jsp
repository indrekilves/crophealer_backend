<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<style>
	body {font-family: "Trebuchet MS";}
	h1 {font-size: 1.5em;}
</style>

</head>
<body>
<h1>Excel data loading / deleting</h1>

<form:form method="post"  action="truncate"
		modelAttribute="truncateForm" enctype="multipart/form-data">	
	<br/><input type="submit" name="button_2" value="Clear DB" />
</form:form>

<form:form method="post"  action="save"
		modelAttribute="uploadForm" enctype="multipart/form-data">

    <table>
      	<tr>
    		<td>Reload reason (optional)</td> 
    		<td><input name = "uploadComment" type="text"></td>
		</tr>     	
	</table> 
	<table id="fileTable">
		<tr>
			<td><input name="excelFile" type="file" /></td>
		</tr>
	</table>
	<br/><input type="submit" name="button_1" value="Upload" />
</form:form>
</body>
</html>
