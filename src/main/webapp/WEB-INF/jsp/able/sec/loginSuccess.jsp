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

<title>Login Success (Test page)</title>

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
				<div class="table-responsive">
					<table>
						<thead>
						</thead>
						<tbody>
							<tr>
								<td>Username</td>
								<td><sec:authorize access="isAuthenticated()">
										<sec:authentication property="principal.username" />
									</sec:authorize> <sec:authorize access="isAnonymous()">
									Anonymous
								</sec:authorize></td>
							</tr>
							<tr>
								<td>Authorities</td>
								<td><sec:authorize access="isAuthenticated()">
										<sec:authentication property="principal.authorities" />
									</sec:authorize> <sec:authorize access="isAnonymous()">
									[ROLE_ANONYMOUS]
								</sec:authorize></td>
							</tr>
						</tbody>
					</table>
				</div>
				<h2>Session Attributes</h2>
				<div class="table-responsive">
					<table>
						<thead>
							<tr>
								<th>Attribute Key</th>
								<th>Attribute Value</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="session" items="${sessionScope}">
								<tr>
									<td><c:out value="${session.key}" /></td>
									<td><c:out value="${session.value}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<h2>URL-based Access-Control (Filter)</h2>
				<p>-> LoginPage / Access Denied (HTTP 403 or Access-denied page)</p>
				<div class="table-responsive">
					<table>
						<thead>
							<tr>
								<th>Type</th>
								<th>ROLE_ADMIN</th>
								<th>ROLE_USER</th>
								<th>ROLE_ADMIN, ROLE_USER</th>
								<th>Anonymous</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>URL-based access-control</td>
								<td><a href="<c:url value='/admin/access.do'/>">link</a></td>
								<td><a href="<c:url value='/user/access.do'/>">link</a></td>
								<td><a href="<c:url value='/both/access.do'/>">link</a></td>
								<td><a href="<c:url value='/anonymous/access.do'/>">link</a></td>
							</tr>
						</tbody>
					</table>
				</div>

				<h2>Method Security (Service Layer)</h2>
				<p>-> AccessDeniedException</p>
				<div class="table-responsive">
					<table>
						<thead>
							<tr>
								<th>Type</th>
								<th>ROLE_ADMIN</th>
								<th>ROLE_USER</th>
								<th>ROLE_ADMIN, ROLE_USER</th>
								<th>Anonymous</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Method</td>
								<td><a href="<c:url value='/method/admin/access.do'/>">link</a></td>
								<td><a href="<c:url value='/method/user/access.do'/>">link</a></td>
								<td><a href="<c:url value='/method/both/access.do'/>">link</a></td>
								<td><a href="<c:url value='/method/anonymous/access.do'/>">link</a></td>
							</tr>
							<tr>
								<td>Pointcut (XML)</td>
								<td><a href="<c:url value='/pointcut/admin/access.do'/>">link</a></td>
								<td><a href="<c:url value='/pointcut/user/access.do'/>">link</a></td>
								<td><a href="<c:url value='/pointcut/both/access.do'/>">link</a></td>
								<td><a
									href="<c:url value='/pointcut/anonymous/access.do'/>">link</a></td>
							</tr>
						</tbody>
					</table>
				</div>

				<h2>JSP Tag Example</h2>
				<h4>&lt;sec:authorize url=""&gt;</h4>
				<div class="table-responsive">
					<table>
						<thead>
							<tr>
								<th>/admin/access.do</th>
								<th>/user/access.do</th>
								<th>/both/access.do</th>
								<th>/anonymous/access.do</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><sec:authorize url="/admin/access.do" method="POST">
										<button>ADMIN</button>
									</sec:authorize></td>
								<td><sec:authorize url="/user/access.do">
										<button>USER</button>
									</sec:authorize></td>
								<td><sec:authorize url="/both/access.do">
										<button>ADMIN & USER</button>
									</sec:authorize></td>
								<td><sec:authorize url="/anonymous/access.do">
										<button>Anonymous</button>
									</sec:authorize></td>
							</tr>
						</tbody>
					</table>
				</div>

				<h4>&lt;sec:authorize access=""&gt;</h4>
				<div class="table-responsive">
					<table>
						<thead>
							<tr>
								<th>hasRole('ROLE_ADMIN')</th>
								<th>hasRole('ROLE_USER')</th>
								<th>hasAnyRole('ROLE_ADMIN, ROLE_USER')</th>
								<th>isAuthenticated()</th>
								<th>isAnonymous()</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><sec:authorize access="hasRole('ROLE_ADMIN')">
										<button>ADMIN</button>
									</sec:authorize></td>
								<td><sec:authorize access="hasRole('ROLE_USER')">
										<button>USER</button>
									</sec:authorize></td>
								<td><sec:authorize
										access="hasAnyRole('ROLE_ADMIN, ROLE_USER')">
										<button>ADMIN & USER</button>
									</sec:authorize></td>
								<td><sec:authorize access="isAuthenticated()">
										<button>Authenticated</button>
									</sec:authorize></td>
								<td><sec:authorize access="isAnonymous()">
										<button>Anonymous</button>
									</sec:authorize></td>
							</tr>
						</tbody>
					</table>
				</div>

				<h4>&lt;sec:authenticated property=""&gt;</h4>
				<div class="table-responsive">
					<table>
						<thead>
							<tr>
								<th>principal.username</th>
								<th>principal.authorities</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><sec:authorize access="isAuthenticated()">
										<sec:authentication property="principal.username" />
									</sec:authorize></td>
								<td><sec:authorize access="isAuthenticated()">
										<sec:authentication property="principal.authorities" />
									</sec:authorize></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>