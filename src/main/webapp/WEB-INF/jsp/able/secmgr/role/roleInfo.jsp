<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>권한 정보</title>
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
	    
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
	
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>" ></script>
</head>
<body>
    <!-- 상단 메뉴 시작 -->
    <%@include file="/WEB-INF/jsp/able/include/nav.jsp" %>
    <!-- 상단 메뉴 끝 -->
    
    <div class="container-fluid">
      <div class="row">
      
        <!--  왼쪽 메뉴 시작 -->
        <c:set var="menuId" value="2"/>
        <%@include file="/WEB-INF/jsp/able/include/menu.jsp" %>
        <!--  왼쪽 메뉴 끝 -->
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		
		<!-- 권한 정보 시작 -->
		<h3>권한 정보</h3>
		
		<form method="POST" action='<c:url value="/secmgr/role/updateRole.do"/>'>
			<!-- 추가/삭제/수정 시작 -->
			<div class="text-right">
				<button type="submit" class="btn btn-warning">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
					수정
				</button>
				<a class="btn btn-danger" href='<c:url value="/secmgr/role/deleteRole.do?id=${roleVO.id}"/>'>
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					삭제
				</a>
			</div>
			<!-- 추가/삭제/수정 끝 -->
			<table class="table table-condensed table-hover">
				<thead>
					<tr>
					<th class="col-md-3 col-sm-2">분류</th>
					<th></th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td><strong>권한ID</strong></td>
							<td>
								<c:out value="${roleVO.id}" />
								<input type="hidden" name="id" value="${roleVO.id}"/>
							</td>
						</tr>
						<tr>
							<td><strong>권한명</strong></td>
							<td><input class="form-control" type="text" name="name" value='<c:out value="${roleVO.name}"/>'/></td>
						</tr>
						<tr>
							<td><strong>사용여부</strong></td>
							<td>
								<select class="form-control" name="yn">
									<option value="Y" ${roleVO.yn eq 'Y' ? 'selected="selected"' : '' }>Y</option>
									<option value="N" ${roleVO.yn eq 'N' ? 'selected="selected"' : '' }>N</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><strong>설명</strong></td>
							<td>
								<textarea class="form-control" rows="5" name="desc"><c:out value="${roleVO.desc}" /></textarea>
							</td>
						</tr>
						<tr>
							<td><strong>등록자/등록일자</strong></td>
							<td><c:out value="${roleVO.regUser}" /> / <fmt:formatDate	value="${roleVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td><strong>수정자/수정일자</strong></td>
							<td><c:out value="${roleVO.modUser}" /> / <fmt:formatDate	value="${roleVO.modDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
				</tbody>
			</table>
		</form>
		<!-- 권한 정보 끝 -->
		  
        </div>
      </div>
    </div>
</body>
</html>