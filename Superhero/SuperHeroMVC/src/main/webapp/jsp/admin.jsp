<%-- 
    Document   : displayUserList
    Created on : Dec 12, 2019, 2:20:42 PM
    Author     : Anna
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Heroes</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Super Heroes</h1>
            <hr/>
            <div class="navbar">

                <ul class="nav nav-tabs" >
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/hello">
                            Home
                        </a>
                    </li>


                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation" class="active">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>

                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayHeroesPage">
                            Heroes
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organizations
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Locations
                        </a>
                    </li>

                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayGalleryPage">
                            Gallery
                        </a>
                    </li>

                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>You are logged in as : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
               <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                <h2>Users</h2>
                <a href="displayUserForm">Add a User</a><br/>
                <hr/>
                
                <a href="displayUserList">Display User List</a><br/>
                
                <c:forEach var="user" items="${users}">
                    
                    <c:out value="${user.username} ${user.authorities}"/>  
                    
                    <a href="deleteUser?username=${user.username}">Delete</a>
                    
                    <br/><br/>
                    
                </c:forEach>
</sec:authorize>
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
