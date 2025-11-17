<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" 
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" 
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" 
      crossorigin="anonymous" 
      referrerpolicy="no-referrer" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<title>apropos </title>
</head>
<body>
<div class="navbar"> 
    <div class="navbar-logo">
      <img src="${pageContext.request.contextPath}/assets/logopr.jpg" alt="zemni logo" />
    </div>
    <div class="navbar-menu">
      <div class="navbar-start">
        <a class="navbar-item" href="index.jsp">Acceuil</a>
        <a class="navbar-item" href="apropos.jsp">A propos</a>
<a class="navbar-item" href="${pageContext.request.contextPath}/Controller?page=histoire">Histoire</a>      </div>

     

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
      <div class="admin-icon">
        <i class="fa fa-user-shield"></i>
      </div>
    </div>
  </div>

    <div class="a-propos-container">
        <div class="slider-section" id="imageSlider">
        </div>
        
        <div class="content">
           <h1 class="title">La Tunisie sous un autre angle</h1>
<h2 class="title">Zemni.tn - Là où les racines rencontrent l'avenir</h2>

<p class="metaphor">
    "Zemni, c'est comme un olivier millénaire : enraciné dans la terre tunisienne,
    nourri de traditions, et tourné vers le ciel de demain."
</p>

<p class="description">
    À travers Zemni.tn, nous faisons revivre l'âme de notre culture, ses parfums,
    ses couleurs, ses voix.<br />
    Nous semons des histoires, des plats, des habits et des lieux qui font la fierté de notre patrimoine.<br />
    Chaque page est une branche, chaque carte une feuille, chaque visite un souffle de vent qui fait vibrer cet arbre vivant.
</p>

<p class="description">
    Avec passion et curiosité, nous tissons le fil entre le passé et le présent.<br />
    Zemni.tn, ce n'est pas un simple site. C'est un carnet vivant de ce qui nous lie, un héritage à explorer, à aimer, à transmettre.
</p>

<div class="etudiantes">
    <h2>Présenté avec amour par :</h2>
    <ul>
        <li>Boughanmi Fatma</li>
        <li>Chayma Gharbi</li>
        <li>Mansouri Maram</li>
    </ul>
</div>
</div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const slider = document.getElementById('imageSlider');
        if (!slider) {
            console.error("Élément 'imageSlider' introuvable");
            return;
        }

        // CHEMIN ABSOLU CORRECT (adapté à votre configuration)
        const basePath = '/zemni/assets'; // ← C'est la clé !
        
        // Debug : Vérifiez que le chemin est correct
        console.log("Test URL:", basePath + "/p1.jpg"); // Doit afficher "/zemni/assets/p1.jpg"

        const imageCount = 14;
        slider.innerHTML = ''; // Nettoyage initial

        // Version optimisée avec gestion d'erreur
        for (let i = 1; i <= imageCount; i++) {
            const img = new Image();
            img.src = basePath + '/p' + i + '.jpg'; // Évite les littéraux de modèle pour plus de clarté
            img.alt = 'Image ' + i;
            img.classList.add('slider-image');
            
            // Gestion des erreurs
            img.onerror = function() {
                console.error("Échec du chargement: " + this.src);
                this.style.display = 'none'; // Cache les images manquantes
            };
            
            if (i === 1) img.style.display = 'block';
            else img.style.display = 'none';
            
            slider.appendChild(img);
        }

        // Animation du slider
        let currentIndex = 0;
        const interval = setInterval(() => {
            const images = slider.querySelectorAll('.slider-image');
            if (images.length === 0) {
                clearInterval(interval);
                return;
            }
            
            images[currentIndex].style.display = 'none';
            currentIndex = (currentIndex + 1) % images.length;
            images[currentIndex].style.display = 'block';
        }, 3000);
    });
</script>
</body>
</html>