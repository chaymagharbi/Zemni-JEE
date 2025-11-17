<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="style.css" />
    <style >
    .modifier-endroit-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f2e3cb;
  background-image: /assets/logopr.jpg;
  background-size: cover;
  background-position: center;
  padding: 2rem;
  position: relative;
}

.modifier-endroit-container::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('/assets/logopr.jpg');
  background-size: cover;
  background-position: center;
  filter: blur(6px);
  z-index: 0;
}

.modifier-endroit-container .search-btn {
  flex: 1;
  padding: 0.6rem;
  font-size: 1rem;
  color: rgb(9, 9, 9);
  border: none;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;
  background-color: orange;
}

.modifier-endroit-container .form-box {
  background-color: white;
  padding: 2rem;
  border-radius: 15px;
  box-shadow: 0 4px 25px rgba(0, 0, 0, 0.2);
  max-width: 500px;
  width: 100%;
  position: relative;
  z-index: 1;
}

.modifier-endroit-container .form-box .close-btn {
  position: absolute;
  top: 10px;
  right: 15px;
  font-size: 1.5rem;
  background: none;
  border: none;
  cursor: pointer;
}

.modifier-endroit-container .form-box .logo {
  display: block;
  margin: 0 auto 20px;
  width: 120px;
  border-radius: 30px;
}

.modifier-endroit-container .form-box h2 {
  text-align: center;
  margin-bottom: 0.5rem;
}

.modifier-endroit-container .form-box .subtext {
  text-align: center;
  font-size: 0.9rem;
  color: #888;
  margin-bottom: 1.5rem;
}

.modifier-endroit-container .form-box form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.modifier-endroit-container .form-box form label {
  text-align: left;
  font-weight: bold;
  font-size: 0.9rem;
}

.modifier-endroit-container .form-box form input {
  padding: 0.6rem;
  font-size: 1rem;
  border-radius: 6px;
  border: 1px solid #ccc;
}

.modifier-endroit-container .form-box form .button-group {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
}

.modifier-endroit-container .form-box form .button-group button {
  flex: 1;
  padding: 0.6rem;
  font-size: 1rem;
  color: #fff;
  border: none;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;
}

.modifier-endroit-container .form-box form .button-group button.add-btn {
  background-color: #222;
  margin-left: 0;
  margin-right: 0.5rem;
}

.modifier-endroit-container .form-box form .button-group button.cancel-btn {
  background-color: #888;
}

.modifier-endroit-container .form-box form .button-group button i {
  font-size: 1.2rem;
}

.modifier-endroit-container .form-box .error {
  color: red;
  margin-top: 1rem;
  font-weight: bold;
  text-align: center;
}

/* Styles supplémentaires pour les messages */
.success-message {
  color: #155724;
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
  padding: 10px;
  margin: 15px 0;
  border-radius: 4px;
  text-align: center;
}

.error-message {
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  padding: 10px;
  margin: 15px 0;
  border-radius: 4px;
  text-align: center;
}

/* Styles pour les groupes de formulaire */
.form-group {
  margin-bottom: 1rem;
}

.form-input {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.form-textarea {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  min-height: 100px;
  resize: vertical;
}

.submit-btn {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.back-link {
  display: inline-block;
  background-color: #f44336;
  color: white;
  padding: 10px 15px;
  text-decoration: none;
  border-radius: 4px;
  font-size: 1rem;
}

.search-section {
  margin-bottom: 1.5rem;
}

.search-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}</style>
</head>
<body>
<div class="modifier-endroit-container">
  <div class="form-box">
    <button class="close-btn" onclick="window.location.href='index.jsp'">&times;</button>
    <img src="assets/logopr.jpg" alt="Zemni Logo" class="logo" />
    
    <!-- Messages de succès/erreur -->
    <c:if test="${not empty msg}">
        <div class="${msgType == 'success' ? 'success-message' : 'error-message'}">
            <c:out value="${msg}"/>
        </div>
    </c:if>
    
    <!-- Bloc de recherche -->
    <c:if test="${empty element}">
      <div class="search-section">
        <h3>Rechercher l'élément à modifier</h3>
        <form action="Controller" method="GET" class="search-form">
          <input type="hidden" name="myBtn" value="rechercherPourModification"/>
          
          <div class="form-group">
            <label>Entrez l'ID ou le nom :</label>
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
    
    <!-- Formulaire de modification -->
    <c:if test="${not empty element}">
      <form action="Controller" method="GET" class="modification-form">
        <input type="hidden" name="myBtn" value="modifier"/>
        <input type="hidden" name="id" value="<c:out value='${element.id}'/>"/>
        <input type="hidden" name="categorie" value="<c:out value='${categorie}'/>"/>
        
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
          <input type="text" name="nom" value="<c:out value='${element.nom}'/>" required class="form-input"/>
        </div>
        
        <c:if test="${param.categorie == 'gastronomie' or param.categorie == 'vetement'}">
          <div class="form-group">
            <label>Prix :</label>
            <input type="number" step="0.01" name="prix" 
                   value='<c:out value="${element.prix}"/>'
                   class="form-input" min="0" placeholder="0.00"/>
          </div>
        </c:if>
        
        <div class="form-group">
          <label>Description :</label>
          <textarea name="description" rows="4" required class="form-textarea"><c:out value="${element.description}"/></textarea>
        </div>
        
        <div class="form-group">
          <label>Adresse :</label>
          <input type="text" name="adresse" value="<c:out value='${element.adresse}'/>" class="form-input"/>
        </div>
        
        <div class="form-group">
          <label>Image URL :</label>
          <input type="text" name="imageurl" value="<c:out value='${element.imageurl}'/>" class="form-input"/>
        </div>
        
        <div class="button-group">
          <button type="submit" class="add-btn">
            <i class="fas fa-save"></i> Enregistrer
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