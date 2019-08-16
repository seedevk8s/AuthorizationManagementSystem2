<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>유저 생성</title>
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
        <c:set var="menuId" value="1"/>
        <%@include file="/WEB-INF/jsp/able/include/menu.jsp" %>
        <!--  왼쪽 메뉴 끝 -->
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		
		<!-- 유저 생성 시작 -->
		<h3>유저 생성</h3>
		<c:url value="/secmgr/user/createUser.do" var="createUserUrl"/>
		<form:form method="POST" action="${createUserUrl}" commandName="userVO" >
			<!-- 생성 시작 -->
			<div class="text-right">
				<button type="submit" class="btn btn-success">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					생성
				</button>
			</div>
			<!-- 생성 끝 -->
			<table class="table table-condensed table-hover">
				<thead>
					<tr>
					<th class="col-md-3 col-sm-2">분류</th>
					<th></th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td><strong>유저ID</strong></td>
							<td>
								<spring:bind path="id">
									<div class="${status.error ? 'has-error':'' }">
										<form:input path="id" class="form-control" type="text" />
										<form:errors path="id" cssClass="help-block"/>
									</div>
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td><strong>유저명</strong></td>
							<td>
								<form:input path="name" class="form-control"/>
								<form:errors path="name"/>
							</td>
						</tr>
						<tr>
							<td><strong>패스워드</strong></td>
							<td>
								<spring:bind path="pwd">
									<div class="${status.error ? 'has-error':'' }">
										<form:password path="pwd" class="form-control"/>
										<form:errors path="pwd" cssClass="help-block"/>
									</div>
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td><strong>패스워드 확인</strong></td>
							<td>
								<spring:bind path="pwdVerify">
									<div class="${status.error ? 'has-error':'' }">
										<form:password path="pwdVerify" class="form-control"/>
										<form:errors path="pwdVerify" cssClass="help-block"/>
									</div>
								</spring:bind>
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
				</tbody>
			</table>
		</form:form>
		<!-- 유저 생성 끝 -->
		  
        </div>
      </div>
    </div>
</body>
</html>