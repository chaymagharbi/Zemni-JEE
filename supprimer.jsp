<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZEMNI - Supprimer un article</title>
    <link rel="stylesheet" href="supprimer.css">
    <!-- Ajout de Font Awesome pour les ic�nes -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="background-wrapper">
        <div class="background-image"></div>
        <div class="blur-overlay"></div>
    </div>

    <div class="add-form-container">
        <!-- Formulaire pour supprimer un article -->
        <form id="article-form" action="Controller" method="get" enctype="multipart/form-data">
            <div class="form-header">
                <img src="${pageContext.request.contextPath}/assets/zemnii.jpg" alt="Logo ZEHNI" class="site-logo">
                <h1>Supprimer un article</h1>
                <h2 class="error-title">Veuillez introduire des données valides!</h2>
            </div>
            
            <div class="form-section">
                <div class="form-row">
                    <div class="form-group half-width">
                        <label for="article-id">ID de l'article <span class="required">*</span></label>
                        <input type="text" id="article-id" name="id" readonly>
                    </div>
                    <div class="form-group half-width">
                        <label for="article-title">Titre de l'article <span class="required">*</span></label>
                        <input type="text" id="article-title" name="titre" required>
                    </div>
                </div>
            </div>
            
            <div class="form-actions">
                <button type="reset" class="btn-cancel"><i class="fas fa-times"></i> Annuler</button>
                <button type="submit" class="btn-submit" name="myBtn" value="supprimer"><i class="fas fa-trash"></i> Supprimer</button>
            </div>
        </form>
    </div>
    <!-- Afficher le message en haut de la page -->
    <c:if test="${not empty msg}">
        <div style="padding: 10px; margin: 10px 0; 
                    background-color: <c:out value="${msg.contains('succès') ? '#dff0d8' : '#f8d7da'}"/>; 
                    color: <c:out value="${msg.contains('succès') ? '#3c763d' : '#721c24'}"/>; 
                    border: 1px solid <c:out value="${msg.contains('succès') ? '#d6e9c6' : '#f5c6cb'}"/>; 
                    border-radius: 4px;">
            ${msg}
        </div>
        <c:remove var="msg" scope="session"/>
    </c:if>

    <script>
        // Validation basique du formulaire
        document.getElementById('article-form').addEventListener('submit', function(e) {
            const inputs = this.querySelectorAll('input[required], textarea[required]');
            let isValid = true;
            
            inputs.forEach(input => {
                if (!input.value.trim()) {
                    isValid = false;
                    input.style.borderColor = '#ff4444';
                } else {
                    input.style.borderColor = '#e0e0e0';
                }
            });
            
            if (!isValid) {
                e.preventDefault();
                document.querySelector('.error-title').style.display = 'block';
            } else {
                // Message de confirmation avant soumission (optionnel)
                if (!confirm('Confirmez-vous la suppression de cet article ?')) {
                    e.preventDefault();
                }
            }
        });
    </script>
</body>

</html>