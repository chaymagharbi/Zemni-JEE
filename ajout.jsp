<!DOCTYPE html>
<html lang="fr">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZEMNI - Ajouter un article</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="background-wrapper">
        <div class="background-image"></div>
        <div class="blur-overlay"></div>
    </div>

    <!-- Message de notification -->
<c:if test="${not empty requestScope.msg}">
    <c:set var="messageType" value="${requestScope.msg.startsWith('success:') ? 'success' : 'error'}" />
    <c:set var="messageText" value="${requestScope.msg.substring(requestScope.msg.indexOf(':') + 1)}" />
    
    <div class="message-box ${messageType}">
        <c:out value="${messageText}" />
    </div>
</c:if>
    <div class="ajout-endroit-container">
        <div class="form-box">
            <form action="Controller" method="POST">
                <button type="button" class="close-btn" onclick="closeForm()">&times;</button>
                <img src="${pageContext.request.contextPath}/assets/zemnii.jpg" alt="Logo ZEHNI" class="logo">
                <h2>Ajouter un article</h2>
                <p class="subtext">Veuillez introduire des données valides!</p>
                
                <input type="hidden" name="action" value="ajouterarticle">

                <label for="titre">Titre de l'article</label>
                <input id="titre" type="text" name="titre" 
                       value="${param.titre}" required>

                <label for="description">Description</label>
                <textarea id="description" name="description" required>${param.description}</textarea>

                <label for="datepub">Date de publication</label>
                <input id="datepub" type="date" name="datepub" 
                       value="${param.datepub}" 
                       pattern="\d{4}-\d{2}-\d{2}" required>

                <label for="imageurl">URL de l'image</label>
                <input id="imageurl" type="text" name="imageurl" 
                       value="${param.imageurl}" 
                       placeholder="http://www.domain.com/images.jpg" required>

                <div class="button-group">
                    <button type="button" class="cancel-btn" onclick="closeForm()">
                        <i class="fas fa-times-circle"></i> Annuler
                    </button>
                    <button type="submit" class="add-btn" name="myBtn" value="ajouterarticle">
                        <i class="fas fa-plus-circle"></i> Ajouter
                    </button>
                </div>
            </form>
        </div>
    </div>

    <script>
    function closeForm() {
        if (confirm('Voulez-vous vraiment annuler ? Les données non enregistrées seront perdues.')) {
            window.location.href = "index.jsp";
        }
    }
    // Validation du formulaire
    document.querySelector('form').addEventListener('submit', function(e) {
        
    	// Validation améliorée
        document.getElementById('article-form').addEventListener('submit', function(e) {
            const fields = [
                {id: 'titre', name: 'Titre'},
                {id: 'datepub', name: 'Date de publication'}, 
                {id: 'imageurl', name: 'URL de l\'image'}
            ];
            
            let isValid = true;
            let errorMessage = '';
            
            fields.forEach(field => {
                const el = document.getElementById(field.id);
                if (!el.value.trim()) {
                    el.style.borderColor = '#ff4444';
                    errorMessage = 'Veuillez remplir tous les champs obligatoires';
                    isValid = false;
                } else {
                    el.style.borderColor = '';
                }
            });

            // Validation spécifique de l'URL
            const imageUrl = document.getElementById('imageurl');
            if (imageUrl.value && !/^https?:\/\/.+\..+/.test(imageUrl.value)) {
                imageUrl.style.borderColor = '#ff4444';
                errorMessage = 'L\'URL de l\'image doit commencer par http:// ou https://';
                isValid = false;
            }

            if (!isValid) {
                e.preventDefault();
                document.querySelector('.error-message').textContent = errorMessage;
                document.querySelector('.error-message').style.display = 'block';
                return false;
            }

            return confirm('Confirmez-vous l\'ajout de cet article ?');
        });

        // Réinitialiser les messages après 5 secondes
        setTimeout(() => {
            const msgBox = document.querySelector('.message-box');
            if (msgBox) msgBox.style.display = 'none';
        }, 5000);
    </script>
</body>
</html>