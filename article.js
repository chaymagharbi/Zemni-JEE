document.addEventListener('DOMContentLoaded', function() {
    try {
        // 1. Elements du panneau admin
        const adminIcon = document.querySelector('.admin-icon');
        const adminPanel = document.getElementById('adminPanel');
        const formOverlay = document.getElementById('formOverlay');

        // 2. Gestion du toggle du panneau admin
        if (adminIcon && adminPanel) {
            adminIcon.addEventListener('click', function(e) {
                e.stopPropagation();
                adminPanel.style.display = adminPanel.style.display === 'none' ? 'block' : 'none';
            });

            // Ferme le panneau quand on clique ailleurs
            document.addEventListener('click', function() {
                adminPanel.style.display = 'none';
            });

            // Empêche la fermeture quand on clique dans le panneau
            adminPanel.addEventListener('click', function(e) {
                e.stopPropagation();
            });
        }

        // 3. Gestion de l'overlay (si présent)
        if (formOverlay) {
            formOverlay.addEventListener('click', function() {
                this.classList.add('hidden');
                // Cachez ici tous les formulaires modaux si nécessaire
            });
        }

        // 4. Vérification des images manquantes
        document.querySelectorAll('img').forEach(img => {
            img.onerror = function() {
                console.error('Image non trouvée:', this.src);
                this.style.display = 'none';
            };
        });

    } catch (error) {
        console.error("Erreur dans l'initialisation:", error);
    }
});