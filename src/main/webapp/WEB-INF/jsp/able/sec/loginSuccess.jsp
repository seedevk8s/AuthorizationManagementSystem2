<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap core CSS -->
<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
    
<!-- jQuery -->
<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>

<title>로그인 성공 (Test page)</title>

<style type="text/css">
table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
	padding: 2px;
}
</style>
</head>
<body>

	<!-- 상단 메뉴 시작 -->
	<%@include file="/WEB-INF/jsp/able/include/nav.jsp"%>
	<!-- 상단 메뉴 끝 -->

	<div class="container-fluid">
		<div class="row">

			<!--  왼쪽 메뉴 시작 -->
			<c:set var="menuId" value="1" />
			<%@include file="/WEB-INF/jsp/able/include/menu.jsp"%>
			<!--  왼쪽 메뉴 끝 -->

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2>User Info</h2>

				<h2>Session Attributes</h2>

				<h2>URL-based Access-Control (Filter)</h2>

				<h2>Method Security (Service Layer)</h2>

				<h2>JSP Tag Example</h2>

			</div>
		</div>
	</div>
</body>
</html>