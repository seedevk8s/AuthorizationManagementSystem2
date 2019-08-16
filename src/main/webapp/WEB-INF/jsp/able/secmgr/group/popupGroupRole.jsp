<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>권한 추가</title>
    
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
	    
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
	
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>" ></script>
    
    <c:url var="pageUrl" value="/secmgr/group/popupGroupRole.do">
    	<c:param name="groupId" value="${hMap.groupId}"/>
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
    //tr 클릭 -> checkbox 클릭
    $(document).ready(function(){
    	$('tr').filter(':has(:checkbox)').click(function(e){
    		if(e.target.type != "checkbox"){
    			$(':checkbox',this).trigger('click');
    		}
    	});
    });
    </script>
</head>
<body>
    <div class="container-fluid">
		<!-- 권한 추가 시작 -->
	    <h3>권한 추가</h3>
	    <!-- 검색 시작 -->
	    <div class="row col-sm-9 col-md-7">
			<form class="form-inline" method="GET" action='<c:url value="/secmgr/group/popupGroupRole.do"/>'>
				<select class="form-control"  name="qcol">
					<option value="id" ${hMap.qcol eq 'id' ? 'selected="selected"' : ''}>ID</option>
					<option value="name" ${hMap.qcol eq 'name' ? 'selected="selected"' : ''}>이름</option>
				</select>
				<input type="text" class="form-control" name="qval" value='<c:out value="${hMap.qval}"/>'/>
				<input type="hidden" name="groupId" value="${hMap.groupId }"/>
				
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					검색
				</button>
			</form>
		</div>
		<!-- 검색 끝 -->
	  	<form method="POST" action='<c:url value="/secmgr/group/addRoles.do"/>'>
	  		<!-- 추가/닫기 시작-->
	  		<div class="text-right">
		  		<button type="submit" class="btn btn-success">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					추가
				</button>
				<button type="button" class="btn btn-default" onclick="window.close();">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					닫기
				</button>
			</div>
			<!-- 추가/닫기 끝-->
	  		<table class="table table-condensed table-hover">
				<thead>
					<tr>
						<th>선택</th>
						<th>권한ID</th>
						<th>권한명</th>
						<th>등록자</th>
						<th>등록일자</th>
					</tr>
				</thead>
				<tbody>
					<input type="hidden" name="groupId" value="${hMap.groupId }"/>
					<c:forEach var="addableGroupRoleVO" items="${addableGroupRoleVOList}">
						<tr>
							<td><input type="checkbox" name="roleId" value="${addableGroupRoleVO.roleId}"/></td>
							<td><c:out value="${addableGroupRoleVO.roleId}" /></td>
							<td><c:out value="${addableGroupRoleVO.roleName}" /></td>
							<td><c:out value="${addableGroupRoleVO.regUser}" /></td>
							<td><fmt:formatDate	value="${addableGroupRoleVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	  	</form>
	  	<!-- 페이징 영역 시작 -->
		<div class="text-center">
			<able:pagination paginationInfo="${pagingInfo}" type="image" jsFunction="jsPage"/>
		</div>
		<!-- 페이징 영역 끝 -->
		<!-- 권한 추가 끝 -->
    </div>
</body>
</html>