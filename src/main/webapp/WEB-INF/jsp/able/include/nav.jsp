<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value='/loginSuccess.do'/>">현대글로비스 AutoBell 중고차매매</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">          	
            
            <sec:authorize access="isAuthenticated()">
            <li><a href="<c:url value='/loginSuccess.do'/>"><sec:authentication property="principal.username"/>님 안녕하세요!</a></li>
            <li><a href="<c:url value='/logout.do'/>">logout</a></li>
            </sec:authorize>
            
          </ul>
        </div>
      </div>
    </nav>