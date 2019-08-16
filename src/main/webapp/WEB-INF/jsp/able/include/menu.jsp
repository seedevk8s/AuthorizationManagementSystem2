<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-sidebar">
  
      <c:choose>
        <c:when test="${menuId==7 }">
        <li class="active"><a href="<c:url value='/cmm/mybatis/selectMyBatisList.do'/>">마이바티스(MyBatis) <span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/cmm/mybatis/selectMyBatisList.do'/>">마이바티스(MyBatis)</a></li>   
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${menuId==6 }">
        <li class="active"><a href="<c:url value='/cmm/crypto/sampleSHA256.do'/>">Encryption & Decryption<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/cmm/crypto/sampleSHA256.do'/>">Encryption & Decryption</a></li>    
        </c:otherwise>
    </c:choose>  
    
    <c:choose>
        <c:when test="${menuId==1 }">
        <li class="active"><a href="<c:url value='/secmgr/user/selectUserList.do'/>">User<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/secmgr/user/selectUserList.do'/>">User</a></li>   
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${menuId==2 }">
        <li class="active"><a href="<c:url value='/secmgr/role/selectRoleList.do'/>">Role<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/secmgr/role/selectRoleList.do'/>">Role</a></li>   
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${menuId==3 }">
        <li class="active"><a href="<c:url value='/secmgr/group/selectGroupList.do'/>">Group<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/secmgr/group/selectGroupList.do'/>">Group</a></li>   
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${menuId==4 }">
        <li class="active"><a href="<c:url value='/secmgr/resource/selectResourceList.do'/>">Resource<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/secmgr/resource/selectResourceList.do'/>">Resource</a></li>   
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${menuId==5 }">
        <li class="active"><a href="<c:url value='/secmgr/menu/selectMenuList.do'/>">Menu<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
        <li><a href="<c:url value='/secmgr/menu/selectMenuList.do'/>">Menu</a></li>   
        </c:otherwise>
    </c:choose>
    
  </ul>
  
</div>