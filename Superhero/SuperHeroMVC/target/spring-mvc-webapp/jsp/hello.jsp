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
            <h1>SUPER HEROES</h1>
            <div class="navbar">

                <ul class="nav nav-tabs" >
                    <li role="presentation" 
                        class="active">
                        <a href="${pageContext.request.contextPath}/hello.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/admin">
                            Login 
                        </a>
                    </li>

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
                    
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/displayGalleryPage">
                            Gallery
                        </a>
                    </li>
                </ul>    
            </div>
                            
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h4>You are logged in as : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </h4>
            </c:if>


            <h3 class="text-center" class = "text-info">
                These are the last seen heroes!!!
            </h3>

            <div class="col-md-6">

                <table id="sightingTable" class="table table-hover">
                    <tr>
                        <th width="33%">Date</th>
                        <th width="33%">Location </th>
                        <th width="33%">Hero Sighted </th>

                    </tr>

                    <c:forEach var="current" items="${listSightingViews}">
                        <tr>
                            <td>
                                <c:out value="${current.sighting.dateSighting}"/>   
                            </td>
                            <td>
                                <c:out value="${current.location.nameLocation}"/>
                            </td>

                            <td> 
                                <c:forEach var="currentHero" items="${current.listHeroes}">

                                    <c:out value="${currentHero.nameHero}"/>

                                </c:forEach>
                            </td>


                        </tr>
                    </c:forEach>
                </table>          

            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

