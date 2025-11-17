<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<link rel="stylesheet" href="acceuil1.css" />
<title>Acceuil</title>
</head>
<body>

<div class="navbar">
    <div class="navbar-logo">
        <img src="${pageContext.request.contextPath}/assets/logopr.jpg" alt="zemni logo" />
    </div>

    <div class="navbar-start">
        <a class="navbar-item" href="index.jsp">Acceuil</a>
        <a class="navbar-item" href="apropos.jsp">A propos</a>
        <a class="navbar-item" href="${pageContext.request.contextPath}/Controller?page=histoire">Histoire</a>        <a class="navbar-item" href="recherche.jsp">Rechercher</a>
    </div>
    <div class="navbar-end">
        <a href="https://www.facebook.com" target="_blank" class="navbar-item"><i class="fab fa-facebook"></i></a>
        <a href="https://www.instagram.com" target="_blank" class="navbar-item"><i class="fab fa-instagram"></i></a>
        <a href="mailto:example@email.com" class="navbar-item"><i class="fas fa-envelope"></i></a>
        <a href="https://www.linkedin.com" target="_blank" class="navbar-item"><i class="fab fa-linkedin"></i></a>
    </div>
    
</div>
   <div class="hero-banner">
    <div class="banner-image-container">
        <img src="${pageContext.request.contextPath}/assets/baner.jpg" alt="Bannière Tunisie" class="banner-image">
    </div>
   
</div>

 <div class="cartes-grid">
        <!-- Carte 1 -->
        <div class="carte-container">
            <img src="${pageContext.request.contextPath}/assets/dmak.jpg" alt="Titre carte 1" class="carte-image">
            <div class="card-body">
                <h3 class="carte-title">Dmak</h3>
                <p class="card-description">
                    L“Dmak ”  c’ est un mot en tunisien qui signifie que c’est un bon plat en fait “Dmak”  est une catégorie des plats traditionnels tunisiens, riches en saveurs et en héritage...
                </p>
                <p>Date de publication : <span class="publication-date">01/01/2023</span></p>
                <a href="Controller?page=gastronomie"><button class="explore-btn">Explorer</button></a>
            </div>
            
        </div>

        <!-- Carte 2 -->
        <div class="carte-container">
            <img src="${pageContext.request.contextPath}/assets/kachech.jpg" alt="Titre carte 2" class="carte-image">
            <div class="card-body">
                <h3 class="carte-title">Kachech</h3>
                <p class="card-description">
                    <span id="short-desc-2">
                        “kachech”  c’ est un mot en tunisien qui signifie  “Habillez-vous bien.”C’est une catégorie de vêtements traditionnels tunisiens, alliant élégance et héritage... 
                        
                    </span>
                    <span id="full-desc-2" style="display:none;">
                        Description complète de la deuxième carte. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.
                       
                    </span>
                </p>
                <p>Date de publication : <span class="publication-date">15/03/2023</span></p>
                 <a href="Controller?page=vetement"><button class="explore-btn">Explorer</button></a>
            </div>
        </div>

        <!-- Carte 3 -->
        <div class="carte-container">
            <img src="${pageContext.request.contextPath}/assets/kayda.jpg" alt="Titre carte 3" class="carte-image">
            <div class="card-body">
                <h3 class="carte-title">Kaayda al kif</h3>
                <p class="card-description">
                    <span id="short-desc-3">
                    “Kaaydda al kif ” veut dire faire un bon kif et savourer un bon moment dans un resto ou un café.C’est une catégorie de restos et cafés tunisiens au charme authentique... 
                        
                    </span>
                    <span id="full-desc-3" style="display:none;">
                        Description complète de la troisième carte. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                       
                    </span>
                </p>
                <p>Date de publication : <span class="publication-date">22/05/2023</span></p>
                 <a href="Controller?page=restauration"><button class="explore-btn">Explorer</button></a>
            </div>
        </div>

        <!-- Carte 4 -->
        <div class="carte-container">
            <img src="${pageContext.request.contextPath}/assets/narjalekdima.jpg" alt="Titre carte 4" class="carte-image">
            <div class="card-body">
                <h3 class="carte-title">Narjaalek dima</h3>
                <p class="card-description">
                    <span id="short-desc-4">
                        “Narjaalek dima” signifie  Revenez toujours et surout aux meilleurs endroits en Tunisie.C’est une catégorie dédiée aux paysages, musées et sites archéologiques tunisiens. Témoins d’un riche passé...
                       
                    </span>
                    <span id="full-desc-4" style="display:none;">
                        Description complète de la quatrième carte. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                       
                    </span>
                </p>
                <p>Date de publication : <span class="publication-date">10/07/2023</span></p>
                <a href="Controller?page=patrimoine">
                 <button class="explore-btn">Explorer</button></a>
            </div>
        </div>
    </div>
    <script src="script.js"></script> 


</body>
</html>
