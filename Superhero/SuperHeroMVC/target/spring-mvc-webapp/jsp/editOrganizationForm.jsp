<%-- 
    Document   : editHeroForm
    Created on : Dec 5, 2019, 3:10:37 PM
    Author     : Anna
--%>

<%-- 
    Document   : editContactForm
    Created on : May 14, 2019, 2:18:45 PM
    Author     : Anna
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Heros</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Edit Organization</h1>
            <hr/>

            <div class="navbar">

                <ul class="nav nav-tabs" >
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/hello">
                            Home
                        </a>
                    </li>
                    <li role="presentation" >
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
                </ul>    
            </div>

            <sf:form class="form-horizontal" role="form" modelAttribute="org" action="editOrganization" method="POST">
                <div class="form-group">
                    <label for="add-nameOrg" class="col-md-4 control-label">Name Organization</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-nameOrg"
                                  path="nameOrg" placeholder="Name"/>
                        <sf:errors path="nameOrg" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-descriptionOrg" class="col-md-4 control-label">Description: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-descriptionOrg"
                                  path="descriptionOrg" placeholder="Description "/>
                        <sf:errors path="descriptionOrg" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-contactInfoOrg" class="col-md-4 control-label">Contact Info: </label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-contactInfoOrg"
                                  path="contactInfoOrg" placeholder="Contact Info"/>
                        <sf:errors path="contactInfoOrg" cssclass="error"></sf:errors>
                        <sf:hidden path="idOrganization"/>
                        </div>
                    </div>



                    <div class="form-group" >
                        <div class="col-md-offset-4 col-md-8">

                            <input type="submit" class="btn btn-default" value="Update"/>
                        </div>
                    </div>
            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> <div class="container">

    </body>
</html>
