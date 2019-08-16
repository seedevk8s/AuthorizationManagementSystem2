<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>메뉴 정보</title>
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
	    
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
	
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>" ></script>
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
        <c:set var="menuId" value="5"/>
        <%@include file="/WEB-INF/jsp/able/include/menu.jsp" %>
        <!--  왼쪽 메뉴 끝 -->
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		
		<!-- 메뉴 정보 시작 -->
		<h3>메뉴 정보</h3>
		<c:url value="/secmgr/menu/updateMenu.do" var="updateMenuUrl"/>
		<form:form method="POST" action='${updateMenuUrl}' commandName="menuVO">
			<!-- 수정/삭제 시작 -->
			<div class="text-right">
				<button type="submit" class="btn btn-warning">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
					수정
				</button>
				<a class="btn btn-danger" href='<c:url value="/secmgr/menu/deleteMenu.do?id=${menuVO.id}"/>'>
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
							<td><strong>메뉴ID</strong></td>
							<td>
								<c:out value="${menuVO.id}" />
								<input type="hidden" name="id" value="${menuVO.id}"/>
							</td>
						</tr>
						<tr>
							<td><strong>메뉴명</strong></td>
							<td>
								<form:input path="name" class="form-control"/>
								<form:errors path="name"/>
							</td>
						</tr>
						<tr>
							<td><strong>사용여부</strong></td>
							<td>
								<form:select path="yn" class="form-control">
									<form:option value="Y">Y</form:option>
									<form:option value="N">N</form:option>
								</form:select>
							</td>
						</tr>
						<tr>
							<td><strong>링크</strong></td>
							<td>
								<spring:bind path="link">
									<div class="${status.error ? 'has-error':'' }">
										<form:input path="link" class="form-control" type="text" />
										<form:errors path="link" cssClass="help-block"/>
									</div>
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td><strong>설명</strong></td>
							<td>
								<form:textarea path="desc" class="form-control" rows="5" />
								<form:errors path="desc"/>
							</td>
						</tr>
						<tr>
							<td><strong>등록자/등록일자</strong></td>
							<td>
								<c:out value="${menuVO.regUser}" /> / <fmt:formatDate	value="${menuVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td><strong>수정자/수정일자</strong></td>
							<td>
								<c:out value="${menuVO.modUser}" /> / <fmt:formatDate	value="${menuVO.modDateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
				</tbody>
			</table>
		</form:form>
		<!-- 메뉴 정보 끝 -->
		
		<!--  권한 설정 시작 -->
		<h3>권한 설정</h3>
		<form method="POST"  action='<c:url value="/secmgr/menu/deleteRoles.do"/>'>
			<!-- 추가/삭제 시작 -->
			<div class="text-right">
				<button type="button" class="btn btn-success" onclick="popup('<c:url value="/secmgr/menu/popupMenuRole.do?menuId=${menuVO.id}"/>')">
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
					<input type="hidden" name="menuId" value="${menuVO.id }"/>
					<c:forEach var="menuRoleVO" items="${menuRoleVOList}">
						<tr>
								<td>
								<input type="checkbox" name="roleId" value="${menuRoleVO.roleId }"/>
								</td>
								<td>
									<a href='<c:url value="/secmgr/role/selectRole.do?id=${menuRoleVO.roleId}"/>'>
									<c:out value="${menuRoleVO.roleId}" />
									</a>
								</td>
								<td><c:out value="${menuRoleVO.roleName}" /></td>
								<td><c:out value="${menuRoleVO.roleYn}" /></td>
								<td><c:out value="${menuRoleVO.regUser}" /></td>
								<td><fmt:formatDate	value="${menuRoleVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<!--  권한 설정 끝 -->
        </div>
      </div>
    </div>
</body>
</html>