<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>ABLE_Frame Security Login</title>
	<!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/able/dashboard.css'/>" rel="stylesheet">
    
    <!-- jQuery -->
    <script src="<c:url value='/js/jquery/jquery-2.1.4.min.js'/>" ></script>
    

<style>

body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

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

<c:if test="${param.error == 'true'}">
	<strong>아이디와 암호가 일치하지 않습니다.<br> 
            다시 확인해 주세요.
    </strong>
</c:if>

    <!-- 상단 메뉴 시작 -->
    <%@include file="/WEB-INF/jsp/able/include/nav2.jsp"%>
    <!-- 상단 메뉴 끝 -->    

    <div class="container">
        <div class="row">       
              <form class="form-signin" action="<c:url value='/loginProcess.do'/>" method="post">
                <h2 class="form-signin-heading">로그인 서비스</h2>
                <label for="userId" class="sr-only">로그인 ID</label>
                <input type="text" name="userId" id="userId" class="form-control" placeholder="로그인 ID" required autofocus>
                <label for="password" class="sr-only">패스워드</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="패스워드" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
              </form>
              
                <!--  왼쪽 메뉴 시작 -->
                <c:set var="menuId" value="8" />
                <%@include file="/WEB-INF/jsp/able/include/menu.jsp"%>
                <!--  왼쪽 메뉴 끝 -->
                          
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <!-- <h2>중고차 매매플랫폼</h2> -->
                    <img src="https://www.hyundai.com/content/dam/hyundai/kr/ko/images/vehicles/palisade/launching/design/pip-palisade-design-side-design.jpg">
        
                </div>                               
        </div>

    </div> 
</body>
</html>































