<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title></title>
    <!-- jQuery -->
    <script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
    
    <script type="text/javascript">
    $(document).ready(function(){
    	//refresh parent
    	opener.location.reload(true);
    	//popup close
    	window.close();
    });
    </script>
</head>
<body>
</body>
</html>