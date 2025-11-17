<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un élément</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<link rel="stylesheet" href="style.css" />
</head>
<body>

<c:out value="${requestScope.msg}"/>
<div class="ajout-endroit-container">
  <div class="form-box">
  <form action="Controller">
    <button type="button" class="close-btn" onclick="closeForm()">&times;</button>
    <img src="${pageContext.request.contextPath}/assets/logopr.jpg" alt="Logo" class="logo" />
    <h2>Ajouter un élément</h2>
    <p class="subtext">Veuillez introduire des données valides!</p>
    <input type="hidden" name="action" value="ajouter" required>
    
    <label for="categorie">Catégorie</label>
    <select id="categorie" name="categorie" required onchange="updateFormFields()">
      <option value="" disabled selected>-- Choisir une catégorie --</option>
      <option value="patrimoine" ${param.categorie == 'patrimoine' ? 'selected' : ''}>Patrimoine</option>
      <option value="restauration" ${param.categorie == 'restauration' ? 'selected' : ''}>Restauration</option>
      <option value="gastronomie" ${param.categorie == 'gastronomie' ? 'selected' : ''}>Gastronomie</option>
      <option value="vetement" ${param.categorie == 'vetement' ? 'selected' : ''}>Vêtement</option>
    </select>

    
    <label for="nom">Nom</label>
    <input id="nom" type="text" name="nom" 
           value="${param.nom}" required>

    <label for="description">Description</label>
    <input id="description" type="text" name="description" 
           value="${param.description}" required>

    <label for="adresse">Adresse</label>
    <input id="adresse" type="text" name="adresse" 
           value="${param.adresse}" 
           placeholder="Rue, ville, etc." required>

    <label for="imageUrl">URL de l'image</label>
    <input id="imageUrl" type="text" name="imageUrl" 
           value="${param.imageUrl}" 
           placeholder="http://www.domain.com/image.jpg" required>

    <div id="intervallePrixDiv" style="display: none;">
      <label for="prix">Prix</label>
      <input type="number" step="0.01" min="0" id="prix" name="prix" 
             value="${param.prix}" 
             placeholder="exemple : 15.2 DT">
    </div>

    <div class="button-group">
      <button type="button" class="cancel-btn" onclick="closeForm()">
        <i class="fas fa-times-circle"></i> Annuler
      </button>
      <button type="submit" class="add-btn" name="myBtn" value="ajouter">
        <i class="fas fa-plus-circle"></i> Ajouter
      </button>
    </div>
  </form>
  </div>
</div>

<script>
function closeForm() {
    window.location.href = "index.jsp";
}

// Validation du formulaire
document.querySelector('form').addEventListener('submit', function(e) {
    
    return confirm('Confirmez-vous l\'ajout de cet article ?');
});

function updateFormFields() {
  try {
    const categorieSelect = document.getElementById("categorie");
    const intervalleDiv = document.getElementById("intervallePrixDiv");
    const prixInput = document.getElementById("prix");
    
    if (!categorieSelect || !intervalleDiv || !prixInput) {
      console.error("Éléments du formulaire non trouvés");
      return;
    }
    
    const categorie = categorieSelect.value;
    
    if (categorie === 'gastronomie' || categorie === 'vetement') {
      intervalleDiv.style.display = "block";
      prixInput.required = true;
    } else {
      intervalleDiv.style.display = "none";
      prixInput.required = false;
    }
  } catch (error) {
    console.error("Erreur dans la mise à jour des champs:", error);
  }
}

// Initialisation au chargement de la page
document.addEventListener('DOMContentLoaded', function() {
  // Appel initial pour configurer les champs selon la catégorie sélectionnée
  updateFormFields();
  
  // Vérifier si une catégorie est déjà sélectionnée (après soumission)
  if (document.getElementById("categorie").value) {
    updateFormFields();
  }
});
</script>
</body>
</html>