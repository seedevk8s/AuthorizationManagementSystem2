<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>리소스 정보</title>
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
		
		<!-- 리소스 정보 시작 -->
		<h3>리소스 정보</h3>
		<c:url value="/secmgr/resource/updateResource.do" var="updateResourceUrl"/>
		<form:form method="POST" action="${updateResourceUrl}" commandName="resourceVO">
			<!-- 수정/삭제 시작 -->
			<div class="text-right">
				<button type="submit" class="btn btn-warning">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
					수정
				</button>
				<a class="btn btn-danger" href='<c:url value="/secmgr/resource/deleteResource.do?id=${resourceVO.id}"/>'>
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
							<td><strong>리소스ID</strong></td>
							<td>
								<c:out value="${resourceVO.id}" />
								<input type="hidden" name="id" value="${resourceVO.id}"/>
							</td>
						</tr>
						<tr>
							<td><strong>리소스명</strong></td>
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
							<td><strong>타입</strong></td>
							<td>
								<form:select path="type" class="form-control">
									<form:option value="url">url</form:option>
									<form:option value="pointcut">pointcut</form:option>
									<form:option value="method">method</form:option>
								</form:select>
							</td>
						</tr>
						<tr>
							<td><strong>순서</strong></td>
							<td>
								<spring:bind path="sort">
									<div class="${status.error ? 'has-error':'' }">
										<form:input path="sort" class="form-control" type="text" />
										<form:errors path="sort" cssClass="help-block"/>
									</div>
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td><strong>패턴</strong></td>
							<td>
								<spring:bind path="pattern">
									<div class="${status.error ? 'has-error':'' }">
										<form:input path="pattern" class="form-control" type="text" />
										<form:errors path="pattern" cssClass="help-block"/>
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
								<c:out value="${resourceVO.regUser}" /> / <fmt:formatDate	value="${resourceVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td><strong>수정자/수정일자</strong></td>
							<td>
								<c:out value="${resourceVO.modUser}" /> / <fmt:formatDate	value="${resourceVO.modDateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
				</tbody>
			</table>
		</form:form>
		<!-- 리소스 정보 끝 -->
		
		<!--  권한 설정 시작 -->
		<h3>권한 설정</h3>
		<form method="POST"  action='<c:url value="/secmgr/resource/deleteRoles.do"/>'>
			<!-- 추가/삭제 시작 -->
			<div class="text-right">
				<button type="button" class="btn btn-success" onclick="popup('<c:url value="/secmgr/resource/popupResourceRole.do?resId=${resourceVO.id}"/>')">
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
					<input type="hidden" name="resId" value="${resourceVO.id }"/>
					<c:forEach var="resourceRoleVO" items="${resourceRoleVOList}">
						<tr>
								<td>
								<input type="checkbox" name="roleId" value="${resourceRoleVO.roleId }"/>
								</td>
								<td>
									<a href='<c:url value="/secmgr/role/selectRole.do?id=${resourceRoleVO.roleId}"/>'>
									<c:out value="${resourceRoleVO.roleId}" />
									</a>
								</td>
								<td><c:out value="${resourceRoleVO.roleName}" /></td>
								<td><c:out value="${resourceRoleVO.roleYn}" /></td>
								<td><c:out value="${resourceRoleVO.regUser}" /></td>
								<td><fmt:formatDate	value="${resourceRoleVO.regDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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