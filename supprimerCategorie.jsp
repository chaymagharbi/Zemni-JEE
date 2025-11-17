<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Supprimer un élément</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<style>
/* Styles généraux */
body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #f5f5f5;
  margin: 0;
  padding: 0;
  color: #333;
}

/* Conteneur principal */
.supprimer-endroit-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 2rem;
}

/* Boîte de formulaire */
.form-box {
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  padding: 2rem;
  width: 100%;
  max-width: 600px;
  position: relative;
}

/* Bouton de fermeture */
.close-btn {
  position: absolute;
  top: 15px;
  right: 15px;
  font-size: 1.5rem;
  background: none;
  border: none;
  cursor: pointer;
  color: #666;
}

.close-btn:hover {
  color: #333;
}

/* Logo */
.logo {
  display: block;
  margin: 0 auto 1.5rem;
  max-width: 150px;
  height: auto;
}

/* Messages */
.success-message {
  background-color: #e8f5e9;
  color: #2e7d32;
  padding: 1rem;
  border-left: 4px solid #4caf50;
  border-radius: 4px;
  margin-bottom: 1.5rem;
}

.error-message {
  background-color: #ffebee;
  color: #c62828;
  padding: 1rem;
  border-left: 4px solid #f44336;
  border-radius: 4px;
  margin-bottom: 1.5rem;
}

/* Section de recherche */
.search-section {
  margin-bottom: 1.5rem;
}

.search-section h3 {
  color: #333;
  margin-bottom: 1rem;
  text-align: center;
}

/* Groupes de formulaire */
.form-group {
  margin-bottom: 1.2rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
}

.form-input {
  width: 100%;
  padding: 0.8rem;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 1rem;
  box-sizing: border-box;
}

.form-input:read-only {
  background-color: #f9f9f9;
  color: #666;
}

/* Boutons */
.button-group {
  display: flex;
  gap: 1rem;
  margin-top: 1.5rem;
}

.search-btn, .cancel-btn {
  flex: 1;
  padding: 0.8rem;
  font-size: 1rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  text-decoration: none;
  transition: background-color 0.3s;
}

.search-btn {
  background-color: #4285f4;
  color: white;
}

.search-btn:hover {
  background-color: #3367d6;
}

.cancel-btn {
  background-color: #f1f1f1;
  color: #333;
}

.cancel-btn:hover {
  background-color: #e0e0e0;
}

.confirmation-message {
  text-align: center;
  margin-bottom: 2rem;
  padding: 1rem;
  background-color: #fff8f8;
  border-left: 4px solid #ff6b6b;
  border-radius: 4px;
}

.confirmation-message h3 {
  color: #d63031;
  margin: 0.5rem 0;
}

.warning-icon {
  color: #d63031;
  font-size: 2rem;
  margin-bottom: 0.5rem;
}

.delete-btn {
  background-color: #d63031 !important;
  flex: 1;
  padding: 0.6rem;
  font-size: 1rem;
  color: white !important;
  border: none;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;
}

.delete-btn:hover {
  background-color: #b71c1c !important;
}

/* Responsive */
@media (max-width: 768px) {
  .form-box {
    padding: 1.5rem;
  }
  
  .button-group {
    flex-direction: column;
  }
}
.alert {
    padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
}

.alert-success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
}

.alert-error {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
}
</style>
</head>
</head>
<body>
    <div class="supprimer-endroit-container">
  <div class="form-box">
    <button class="close-btn" onclick="window.location.href='index.jsp'">&times;</button>
    <img src="${pageContext.request.contextPath}/assets/logopr.jpg" alt="Zemni Logo" class="logo" />
    
    <!-- Messages de succès/erreur -->
    <c:if test="${not empty msg}">
    <div class="alert alert-${msgType}">
        <c:out value="${msg}"/>
    </div>
</c:if>
    <!-- Bloc de recherche -->
<c:if test="${empty element}">
  <div class="search-section">
    <h3>Rechercher l'élément à supprimer</h3>
    <form action="Controller" method="GET" class="search-form">
      <input type="hidden" name="myBtn" value="rechercherPourSuppression"/>
      <div class="form-group">
        <label>Entrez le nom :</label>
        <input type="text" name="searchTerm" 
               value="<c:out value='${param.searchTerm}'/>" 
               required class="form-input"/>
      </div>
      
      <div class="button-group">
        <button type="submit" class="search-btn">
          <i class="fas fa-search"></i> Rechercher
        </button>
      </div>
    </form>
  </div>
</c:if>
    
    <!-- Formulaire de confirmation de suppression -->
    <c:if test="${not empty element}">
     <form action="Controller" method="get" class="suppression-form">
        <input type="hidden" name="myBtn" value="supprimer"/>
        <input type="hidden" name="id" value="<c:out value='${element.id}'/>"/>
        <input type="hidden" name="type" value="${element.getClass().simpleName}"/>        
        <div class="confirmation-message">
          <i class="fas fa-exclamation-triangle warning-icon"></i>
          <h3>Confirmer la suppression</h3>
          <p>Êtes-vous sûr de vouloir supprimer cet élément définitivement ?</p>
        </div>
        
        <div class="form-group">
          <label>ID :</label>
          <input type="text" value="<c:out value='${element.id}'/>" readonly class="form-input"/>
        </div>
        
        <div class="form-group">
          <label>Catégorie :</label>
          <input type="text" value="<c:out value='${categorie}'/>" readonly class="form-input"/>
        </div>
        
        <div class="form-group">
          <label>Nom :</label>
          <input type="text" value="<c:out value='${element.nom}'/>" readonly class="form-input"/>
        </div>
        
        <div class="button-group">
          <button type="submit" class="delete-btn">
            <i class="fas fa-trash-alt"></i> Confirmer 
          </button>
          <a href="index.jsp" class="cancel-btn">
            <i class="fas fa-arrow-left"></i> Annuler
          </a>
        </div>
      </form>
</c:if>
  </div>
</div>
</body>
</html>