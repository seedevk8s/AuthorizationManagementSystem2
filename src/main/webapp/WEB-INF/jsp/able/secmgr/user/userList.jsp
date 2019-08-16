<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>유저 관리</title>
    <!-- Bootstrap core CSS -->
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
	    
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
	
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>" ></script>
    <c:url var="pageUrl" value="/secmgr/user/selectUserList.do">
    	<c:if test="${hMap.qcol ne null}">
			<c:param name="qcol" value="${hMap.qcol}"/>
			<c:param name="qval" value="${hMap.qval}"/>
		</c:if>
    </c:url>
    
    <script type="text/javascript">
    function jsPage(pageNo){
    	var url = "${pageUrl}";
    	location.href = url + (url.split('?')[1] ? '&':'?') + "pageNo=" + pageNo; 
    }
    </script>
</head>
<body>
    <!-- 상단 메뉴 시작 -->
    <%@include file="/WEB-INF/jsp/able/include/nav.jsp" %>
    <!-- 상단 메뉴 끝 -->
    
    <div class="container-fluid">
      <div class="row">
      
        <!--  왼쪽 메뉴 시작 -->
        <c:set var="menuId" value="1"/>
        <%@include file="/WEB-INF/jsp/able/include/menu.jsp" %>
        <!--  왼쪽 메뉴 끝 -->
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		
		<!-- 유저 관리 시작 -->
		<h3>유저 관리</h3>
		
		<!-- 검색 시작 -->
		<div class="row col-sm-9 col-md-7">
			<form class="form-inline" method="GET" action='<c:url value="/secmgr/user/selectUserList.do"/>'>
				<select class="form-control"  name="qcol">
					<option value="id" ${hMap.qcol eq 'id' ? 'selected="selected"' : ''}>ID</option>
					<option value="name" ${hMap.qcol eq 'name' ? 'selected="selected"' : ''}>이름</option>
				</select>
				<input type="text" class="form-control" name="qval" value='<c:out value="${hMap.qval}"/>'/>
				
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					검색
				</button>
			</form>
		</div>
		<!-- 검색 끝 -->
		
		<!-- 신규등록 시작 -->
		<div class="text-right">
			<a class="btn btn-success" href='<c:url value="/secmgr/user/showUserForm.do"/>'>
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				신규등록
			</a>
		</div>
		<!-- 신규등록 끝 -->
		<table class="table table-condensed table-hover">
			<thead>
				<tr>
					<th>유저ID</th>
					<th>유저명</th>
					<th>사용여부</th>
					<th>등록자</th>
					<th>등록일자</th>
					<th>수정자</th>
					<th>수정일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="userVO" items="${userVOList}">
					<tr>
							<td>
								<a href='<c:url value="/secmgr/user/selectUser.do?id=${userVO.id}"/>'>
								<c:out value="${userVO.id}" />
								</a>
							</td>
							<td><c:out value="${userVO.name}" /></td>
							<td><c:out value="${userVO.yn}" /></td>
							<td><c:out value="${userVO.regUser}" /></td>
							<td><fmt:formatDate	value="${userVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><c:out value="${userVO.modUser}" /></td>
							<td><fmt:formatDate	value="${userVO.modDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 영역 시작 -->
		<div class="text-center">
			<able:pagination paginationInfo="${pagingInfo}" type="image" jsFunction="jsPage"/>
		</div>
		<!-- 페이징 영역 끝 -->
		<!-- 유저 관리 끝 -->
		  
        </div>
      </div>
    </div>
</body>
</html>