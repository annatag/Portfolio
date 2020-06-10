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
            <h1>LOCATIONS</h1>
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
                    <li role="presentation" class="active">
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

            <div row>
                <div class="col-md-6">
                    <h2>Locations</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="20%">Name:</th>
                            <th width="20%">Description:</th>
                            <th width="20%">Address:</th>
                            <th width="20%">Coordinates:</th>
                            <th width="10%"></th>
                            <th width="10%"></th>

                        </tr>
                        <c:forEach var="current" items="${locationList}">
                            <tr>
                                <td>
                                    <c:out value="${current.nameLocation}"/>   
                                </td>
                                <td>
                                    <c:out value="${current.descriptionLocation}"/>
                                </td>

                                <td>
                                    <c:out value="${current.addressLocation}"/>
                                </td>
                                <td>
                                    <c:out value="${current.coordinates}"/>
                                </td>
                                <td>
                                    <a href="displayEditLocationForm?idLocation=${current.idLocation}">
                                        Edit
                                    </a>  
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                        <a href="deleteLocation?idLocation=${current.idLocation}">
                                            Delete
                                        </a>
                                    </sec:authorize>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 

                <div class="col-md-6">
                    <h2>Add New Location</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createLocation">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="nameLocation" placeholder="Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-descriptionLocation" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="descriptionLocation" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-addressLocation" class="col-md-4 control-label">Address</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="addressLocation" placeholder="Address"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-coordinates" class="col-md-4 control-label">Coordinates</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="coordinates" placeholder="Coordinates"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create New Location"/>
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


