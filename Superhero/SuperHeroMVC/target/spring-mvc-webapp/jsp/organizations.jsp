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
            <h1>Organizations for Villains and Heros</h1>
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organizations
                        </a>
                    </li>
                    <li role="presentation" >
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
                    <h2>Organizations</h2>
                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th width="20%">Organization Name</th>
                            <th width="20%">Organization Description:</th>
                            <th width="20%">Contact Info:</th>
                            <th width="10%"></th>
                            <th width="10%"></th>

                        </tr>
                        <c:forEach var="current" items="${orgList}">
                            <tr>
                                <td>
                                    <c:out value="${current.nameOrg}"/>   
                                </td>
                                <td>
                                    <c:out value="${current.descriptionOrg}"/>
                                </td>

                                <td>
                                    <c:out value="${current.contactInfoOrg}"/>
                                </td>

                                <td>
                                    <a href="displayEditOrganizationForm?idOrganization=${current.idOrganization}">
                                        Edit
                                    </a>  
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteOrganization?idOrganization=${current.idOrganization}">
                                            Delete
                                        </a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-md-6">
                        <h2>Add New Organization</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createOrganization">
                            <div class="form-group">
                                <label for="add-nameOrg" class="col-md-4 control-label">Name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="nameOrg" placeholder="Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-descriptionOrg" class="col-md-4 control-label">Description</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="descriptionOrg" placeholder="Description"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-contactInfoOrg" class="col-md-4 control-label">Contact Info</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="contactInfoOrg" placeholder="Contact Info"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Create New Organization"/>
                                </div>
                            </div>
                        </form>

                    </div> 
                </sec:authorize>




            </div>         

        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


