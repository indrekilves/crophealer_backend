<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<style>
	body {font-family: "Trebuchet MS";}
	h1 {font-size: 1.5em;}
</style>

<script 
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script>
$(document).ready(function() {

	$('#addFile').click(function() {
		var fileIndex = $('#fileTable tr').children().length - 1;
		$('#fileTable').append(
				'<tr><td>'+
				'	<input type="file" name="files['+ fileIndex +']" />'+
				'</td></tr>');
	});
	
});
</script>
</head>
<body>
<h1>Spring Multiple File Upload example</h1>

<form:form method="post"  action="save"
		modelAttribute="uploadForm" enctype="multipart/form-data">

    <table>
      	<tr>
    		<td>Plant Part Phase Problem ID:</td> 
    		<td><input name = "pppProblemId" type="text"></td>
		</tr>
      	<tr>
    		<td>Location:</td> 
    		<td><input name = "location" type="text" width="100"></td>
		</tr>
	</table> 
	<p>Select files to upload. Press Add button to add more file inputs.</p>
	
	<input id="addFile" type="button" value="Add File" />
	<table id="fileTable">
		<tr>
			<td><input name="files[0]" type="file" /></td>
		</tr>
		<tr>
			<td><input name="files[1]" type="file" /></td>
		</tr>
	</table>
	<br/><input type="submit" value="Upload" />
</form:form>
</body>
</html>
