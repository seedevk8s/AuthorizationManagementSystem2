<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>그룹 정보</title>
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
	    
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>    
	
    <script type="text/javascript">
    //tr 클릭 -> checkbox 클릭
    $(document).ready(function(){
    	$('tr').filter(':has(:checkbox)').click(function(e){
    		if(e.target.type != "checkbox"){
    			$(':checkbox',this).trigger('click');
    		}
    	});
    });
    
    function popup(url){
    	window.open(url, '_blank', 'width=800')
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
        <c:set var="menuId" value="3"/>
        <%@include file="/WEB-INF/jsp/able/include/menu.jsp" %>
        <!--  왼쪽 메뉴 끝 -->
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		
		<!-- 그룹 정보 시작 -->
		<h3>그룹 정보</h3>
		
		<form method="POST" action='<c:url value="/secmgr/group/updateGroup.do"/>'>
			<!-- 수정/삭제 시작 -->
			<div class="text-right">
				<button type="submit" class="btn btn-warning">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
					수정
				</button>
				<a class="btn btn-danger" href='<c:url value="/secmgr/group/deleteGroup.do?id=${groupVO.id}"/>'>
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					삭제
				</a>
			</div>
			<!-- 수정/삭제 끝 -->
			<table class="table table-condensed table-hover">
				<thead>
					<tr>
					<th class="col-md-3 col-sm-2">분류</th>
					<th></th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td><strong>그룹ID</strong></td>
							<td>
								<c:out value="${groupVO.id}" />
								<input type="hidden" name="id" value="${groupVO.id}"/>
							</td>
						</tr>
						<tr>
							<td><strong>그룹명</strong></td>
							<td><input class="form-control" type="text" name="name" value='<c:out value="${groupVO.name}"/>'/></td>
						</tr>
						<tr>
							<td><strong>사용여부</strong></td>
							<td>
								<select class="form-control" name="yn">
									<option value="Y" ${groupVO.yn eq 'Y' ? 'selected="selected"' : '' }>Y</option>
									<option value="N" ${groupVO.yn eq 'N' ? 'selected="selected"' : '' }>N</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><strong>등록자/등록일자</strong></td>
							<td><c:out value="${groupVO.regUser}" /> / <fmt:formatDate	value="${groupVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td><strong>수정자/수정일자</strong></td>
							<td><c:out value="${groupVO.modUser}" /> / <fmt:formatDate	value="${groupVO.modDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
				</tbody>
			</table>
		</form>
		<!-- 그룹 정보 끝 -->
		
		<!--  권한 설정 시작 -->
		<h3>권한 설정</h3>
		<form method="POST"  action='<c:url value="/secmgr/group/deleteRoles.do"/>'>
			<!-- 추가/삭제 시작 -->
			<div class="text-right">
				<button type="button" class="btn btn-success" onclick="popup('<c:url value="/secmgr/group/popupGroupRole.do?groupId=${groupVO.id}"/>')">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					권한추가
				</button>
				<button type="submit" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					선택삭제
				</button>
			</div>
			<!-- 추가/삭제 끝 -->
			<table class="table table-condensed table-hover">
				<thead>
					<tr>
						<th>선택</th>
						<th>권한ID</th>
						<th>권한명</th>
						<th>사용여부</th>
						<th>등록자</th>
						<th>등록일자</th>
					</tr>
				</thead>
				<tbody>
					<input type="hidden" name="groupId" value="${groupVO.id }"/>
					<c:forEach var="groupRoleVO" items="${groupRoleVOList}">
						<tr>
								<td>
								<input type="checkbox" name="roleId" value="${groupRoleVO.roleId }"/>
								</td>
								<td>
									<a href='<c:url value="/secmgr/role/selectRole.do?id=${groupRoleVO.roleId}"/>'>
									<c:out value="${groupRoleVO.roleId}" />
									</a>
								</td>
								<td><c:out value="${groupRoleVO.roleName}" /></td>
								<td><c:out value="${groupRoleVO.roleYn}" /></td>
								<td><c:out value="${groupRoleVO.regUser}" /></td>
								<td><fmt:formatDate	value="${groupRoleVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<!--  권한 설정 끝 -->
		
		<!--  유저 설정 시작 -->
		<h3>유저 설정</h3>
		<form method="POST"  action='<c:url value="/secmgr/group/deleteUsers.do"/>'>
			<!-- 추가/삭제 시작 -->
			<div class="text-right">
				<button type="button" class="btn btn-success" onclick="popup('<c:url value="/secmgr/group/popupGroupUser.do?groupId=${groupVO.id}"/>')">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					유저추가
				</button>
				<button type="submit" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					선택삭제
				</button>
			</div>
			<!-- 추가/삭제 끝 -->
			<table class="table table-condensed table-hover">
				<thead>
					<tr>
						<th>선택</th>
						<th>유저ID</th>
						<th>유저명</th>
						<th>사용여부</th>
						<th>등록자</th>
						<th>등록일자</th>
					</tr>
				</thead>
				<tbody>
					<input type="hidden" name="groupId" value="${groupVO.id }"/>
					<c:forEach var="groupUserVO" items="${groupUserVOList}">
						<tr>
								<td>
								<input type="checkbox" name="userId" value="${groupUserVO.userId }"/>
								</td>
								<td>
									<a href='<c:url value="/secmgr/user/selectUser.do?id=${groupUserVO.userId}"/>'>
									<c:out value="${groupUserVO.userId}" />
									</a>
								</td>
								<td><c:out value="${groupUserVO.userName}" /></td>
								<td><c:out value="${groupUserVO.userYn}" /></td>
								<td><c:out value="${groupUserVO.regUser}" /></td>
								<td><fmt:formatDate	value="${groupUserVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<!--  유저 설정 끝 -->
        </div>
      </div>
    </div>
    
</body>
</html>