<%-- 
    Document   : organizations
    Created on : Nov 5, 2019, 7:40:56 PM
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
            <h1>Sightings</h1>
            <div class="navbar">

                <ul class="nav nav-tabs" >
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/hello">
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
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organizations
                        </a>
                    </li>
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Locations
                        </a>
                    </li>

                    <li role="presentation" class="active" >
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

            <div row>
                <div class="col-md-6">
                    <h2>List of All Sightings</h2>
                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="20%">Date of the sighting</th>
                            <th width="20%">Location of the sighting</th>
                            <th width="20%">Hero sighted</th>
                            <th width="20%"></th>
                            <th width="20%"></th>

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

                                <td>
                                    <a href="displayEditSightingForm?idSighting=${current.sighting.idSighting}">
                                        Edit
                                    </a>  
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteSighting?idSighting=${current.sighting.idSighting}">
                                            Delete
                                        </a>  
                                    </sec:authorize>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 

                <div class="col-md-6">
                    <h2>Add New Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="add-dateSighting" class="col-md-4 control-label">Date</label>

                            <div class="col-md-8">
                                <input type="text" class="form-control" name="dateSighting" placeholder="YYYY-MM-DD"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-nameLocation" class="col-md-4 control-label">Location of the Sighting</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="nameLocation" placeholder="Please Input Existing Location"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-nameHero" class="col-md-4 control-label">Name of the Sighted Hero</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="nameHero" placeholder="Name of an Existing Hero"/>
                            </div>
                        </div>



                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Register New Sighting"/>
                            </div>
                        </div>
                    </form>

                </div> 




            </div>         

        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


