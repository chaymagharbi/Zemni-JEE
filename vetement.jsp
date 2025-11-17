<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Vetment</title>
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    integrity="sha512-papFyv+v+TQ9fCG5bvvVxM1okMCE8bxnD1trgM1V+q8I9MmWbNObScZs1L9fUcY2WbFvGy6SlrTpo2+V5l+ELg=="
    crossorigin="anonymous"
    referrerpolicy="no-referrer"
  />
   <link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
  <link rel="stylesheet" href="style.css" />
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

  <div class="imgpr">
    <!-- Utilisez pageContext.request.contextPath pour le chemin absolu -->
    <img src="${pageContext.request.contextPath}/assets/bgkachech.jpg" alt="kachech" />
    <div class="text-overlay">Kachech</div>  
</div>
    <div class="carte-container">
    <c:forEach var="vetement" items="${requestScope.listVetements}">
        <div class="carte">
		<img src="${pageContext.request.contextPath}${vetement.imageurl}" alt="${vetement.nom}">            
		<div class="carte-content">
                <h3>${vetement.nom}</h3>
                <p>${vetement.description}</p>
                <p><strong>Prix :</strong> ${vetement.prix} DNT</p>
				<p><strong>Adresse :</strong> 
				   <a href="https://www.google.com/maps/search/?api=1&query=${vetement.adresse}" 
				      target="_blank"
				      class="map-link"> <i class="fas fa-map-marker-alt"></i>
				      ${vetement.adresse}
				   </a>
				</p>                
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>