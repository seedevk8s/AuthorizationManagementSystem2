<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>권한 없음</title>
</head>
<body>
요청한 페이지에 대한 접근 권한이 없습니다.
<br/>
<a href="<c:url value='/index.do'/>">[/index로 가기]</a>
</body>
</html>