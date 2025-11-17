<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>recherche</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link 
  rel="stylesheet" 
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" 
  integrity="sha512-..." 
  crossorigin="anonymous" 
/>

<link 
  rel="stylesheet" 
  href="style.css" 
/>
</head>
<body>
    <div class="navbar">
        <div class="navbar-logo">
            <img src="${pageContext.request.contextPath}/assets/logopr.jpg" alt="zemni logo" />
        </div>

        <div class="navbar-start">
            <a class="navbar-item" href="index.jsp">Acceuil</a>
            <a class="navbar-item" href="apropos.jsp">A propos</a>
<a class="navbar-item" href="${pageContext.request.contextPath}/Controller?page=histoire">Histoire</a>        </div>
        
        <div class="navbar-end">
            <a href="https://www.facebook.com" target="_blank" class="navbar-item">
                <i class="fab fa-facebook"></i>
            </a>
            <a href="https://www.instagram.com" target="_blank" class="navbar-item">
                <i class="fab fa-instagram"></i>
            </a>
            <a href="mailto:example@email.com" class="navbar-item">
                <i class="fas fa-envelope"></i>
            </a>
            <a href="https://www.linkedin.com" target="_blank" class="navbar-item">
                <i class="fab fa-linkedin"></i>
            </a>
        </div>
        
        <div class="admin-icon-container">
            <button class="admin-main-btn" id="adminToggleBtn">
                <i class="fa fa-user-shield"></i>
            </button>
        </div>
    </div> <!-- Fermeture de la div navbar -->

    <div class="admin-menu" id="adminMenu">
        <a href="ajoutCategorie.jsp" class="admin-btn" id="addBtn">
            <i class="fas fa-plus"></i>
        </a>
        <a href="modifierCategorie.jsp" class="admin-btn" id="editBtn">
            <i class="fas fa-edit"></i>
        </a>
        <a href="supprimerCategorie.jsp" class="admin-btn" id="deleteBtn">
            <i class="fas fa-trash"></i>
        </a>
    </div>

    <div class="search-container">
        <!-- Affichage des messages -->
        <c:if test="${not empty requestScope.msg}">
            <div class="message-box">
                <c:out value="${requestScope.msg}"/>
            </div>
        </c:if>
        
        <!-- Affichage du terme recherché -->
        <c:if test="${not empty requestScope.nom}">
            <div class="search-term">
                Résultats pour : <span>"<c:out value="${requestScope.nom}"/>"</span>
            </div>
        </c:if>
        
        <form action="Controller" method="GET" class="search-form">
            <div class="form-group">
                <label for="nom">Recherchez un élément :</label>
                <div class="input-group">
                    <input type="text" 
                           name="nom" 
                           id="nom" 
                           placeholder="Entrez un nom..." 
                           value="<c:out value='${requestScope.nom}'/>"/>
                    <button type="submit" name="myBtn" value="Rechercher" class="orange-btn">
                        <i class="fas fa-search"></i> Rechercher
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="carte-container">
        <c:forEach var="item" items="${requestScope.listCategories}">
            <div class="carte">
                <img src="${pageContext.request.contextPath}${item.imageurl}" alt="${item.nom}">
                <div class="carte-content">
                    <h3>${item.nom}</h3>
                    <p>${item.description}</p>
                    <c:if test="${not empty item.adresse}">
                        <p><strong>Adresse :</strong> 
                           <a href="https://www.google.com/maps/search/?api=1&query=${item.adresse}" 
                              target="_blank"
                              class="map-link">
                              <i class="fas fa-map-marker-alt"></i>
                              ${item.adresse}
                           </a>
                        </p>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty requestScope.listCategories}">
        <p class="no-results">${requestScope.msg}</p>
    </c:if>

    <script src="script.js"></script>
</body>
</html>