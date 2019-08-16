<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>리소스 관리</title>
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
	    
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
	
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>" ></script>
    <c:url var="pageUrl" value="/secmgr/resource/selectResourceList.do">
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
    <!-- 상단 리소스 시작 -->
    <%@include file="/WEB-INF/jsp/able/include/nav.jsp" %>
    <!-- 상단 리소스 끝 -->
    
    <div class="container-fluid">
      <div class="row">
      
        <!--  왼쪽 리소스 시작 -->
        <c:set var="menuId" value="4"/>
        <%@include file="/WEB-INF/jsp/able/include/menu.jsp" %>
        <!--  왼쪽 리소스 끝 -->
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		
		<!-- 리소스 관리 시작 -->
		<h3>리소스 관리</h3>
		
		<!-- 검색 시작 -->
		<div class="row col-sm-9 col-md-7">
			<form class="form-inline" method="GET" action='<c:url value="/secmgr/resource/selectResourceList.do"/>'>
				<select class="form-control"  name="qcol">
					<option value="id" ${hMap.qcol eq 'id' ? 'selected="selected"' : ''}>ID</option>
					<option value="name" ${hMap.qcol eq 'name' ? 'selected="selected"' : ''}>이름</option>
					<option value="type" ${hMap.qcol eq 'type' ? 'selected="selected"' : ''}>타입</option>
					<option value="sort" ${hMap.qcol eq 'sort' ? 'selected="selected"' : ''}>순서</option>
					<option value="pattern" ${hMap.qcol eq 'pattern' ? 'selected="selected"' : ''}>패턴</option>
				</select>
				<input type="text" class="form-control" name="qval" value='<c:out value="${hMap.qval}"/>'/>
				
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					검색
				</button>
			</form>
		</div>
		<!-- 검색 끝 -->
		
		<!-- 갱신/신규등록 시작 -->
		<div class="text-right">
			<a class="btn btn-danger" href='<c:url value="/secmgr/resource/reload.do"/>'>
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
				URL자원 갱신
			</a>
			<a class="btn btn-success" href='<c:url value="/secmgr/resource/showResourceForm.do"/>'>
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				신규등록
			</a>
		</div>
		<!-- 갱신/신규등록 끝 -->
		<table class="table table-condensed table-hover">
			<thead>
				<tr>
					<th>리소스ID</th>
					<th>리소스명</th>
					<th>사용여부</th>
					<th>타입</th>
					<th>순서</th>
					<th>패턴</th>
					<!-- <th>설명</th> -->
					<th>등록자</th>
					<th>등록일자</th>
					<th>수정자</th>
					<th>수정일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="resourceVO" items="${resourceVOList}">
					<tr>
							<td>
								<a href='<c:url value="/secmgr/resource/selectResource.do?id=${resourceVO.id}"/>'>
								<c:out value="${resourceVO.id}" />
								</a>
							</td>
							<td><c:out value="${resourceVO.name}" /></td>
							<td><c:out value="${resourceVO.yn}" /></td>
							<td><c:out value="${resourceVO.type}" /></td>
							<td><c:out value="${resourceVO.sort}" /></td>
							<td><c:out value="${resourceVO.pattern}" /></td>
							<%-- <td><c:out value="${resourceVO.desc}" /></td> --%>
							<td><c:out value="${resourceVO.regUser}" /></td>
							<td><fmt:formatDate	value="${resourceVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><c:out value="${resourceVO.modUser}" /></td>
							<td><fmt:formatDate	value="${resourceVO.modDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 영역 시작 -->
		<div class="text-center">
			<able:pagination paginationInfo="${pagingInfo}" type="image" jsFunction="jsPage"/>
		</div>
		<!-- 페이징 영역 끝 -->
		<!-- 리소스 관리 끝 -->
		  
        </div>
      </div>
    </div>
</body>
</html>