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
                        >
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
                    
                    <li role="presentation" class="active">
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
                Gallery of Heroes
            </h3>

            <div class="col-md-6">

                <form role="form" method="POST" 
                      action="addPicture" 
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="displayTitle">Display Title:</label>
                        <input type="text" 
                               id="displayTitle" 
                               name="displayTitle"/>
                    </div>
                    <div class="form-group">
                        <label for="picture">Upload File:</label> 
                        <input type="file" 
                               id="picture" 
                               name="picture"/>
                    </div>
                    <input type="submit" value="Upload Picture"/>
                </form>         

            </div>

            <div>
                <c:forEach var="currentPicture" items="${pictureList}">
                    <hr>
                    ${currentPicture.title}<br>
                    <img src="${pageContext.request.contextPath}/${currentPicture.filename}"
                         class="img-thumbnail" alt="${currentPicture.title}" width="300" height="300"><br> 
                </c:forEach>
            </div>
        
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

