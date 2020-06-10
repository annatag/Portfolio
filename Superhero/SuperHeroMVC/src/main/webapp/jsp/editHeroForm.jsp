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
            <h1>Edit Hero</h1>
            <hr/>

            <div class="navbar">

                <ul class="nav nav-tabs" >
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/hello">
                            Home
                        </a>
                    </li>
                    <li role="presentation" class="active">
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

            <sf:form class="form-horizontal" role="form" modelAttribute="hero" action="editHero" method="POST">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name Hero:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name"
                                  path="nameHero" placeholder="NameHero"/>
                        <sf:errors path="nameHero" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Description Hero:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-descriptionHero"
                                  path="descriptionHero" placeholder="Description Hero"/>
                        <sf:errors path="descriptionHero" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-power" class="col-md-4 control-label">Power:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-power"
                                  path="powerHero" placeholder="Power Hero"/>
                        <sf:errors path="powerHero" cssclass="error"></sf:errors>
                        <sf:hidden path="idHero"/>
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
