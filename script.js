document.addEventListener('DOMContentLoaded', function() {
    const toggleBtn = document.getElementById('adminToggleBtn');
    const adminMenu = document.getElementById('adminMenu');

    toggleBtn.addEventListener('click', function(e) {
        e.stopPropagation(); 
        adminMenu.classList.toggle('show');
    });

    document.addEventListener('click', function() {
        adminMenu.classList.remove('show');
    });

    adminMenu.addEventListener('click', function(e) {
        e.stopPropagation();
    });
});
