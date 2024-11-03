document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname;

    console.log(currentPath);

    const isMobile = window.innerWidth < 768;

    const menuToggle = document.getElementById("menuToggle");
    const logoCart = document.getElementById("logoCart");
    const logo = document.getElementsByClassName("logo-link");
    const searchBar = document.querySelector(".search-bar");
    const mainHeader = document.querySelector(".main-header");
    const departaments = document.querySelector(".departaments");
    const actionPages = document.querySelector(".action-pages");

    if (currentPath == '/login' || currentPath == '/cadastro') {
        menuToggle.style.display = "none";
        logoCart.style.display = "none";
        logo[0].style.width = "100%";
        logo[0].style.textAlign = "center";
        searchBar.style.display = "none";

        if (isMobile) {
            mainHeader.style.height = "62px";
        }
        departaments.style.display = "none";
        actionPages.style.display = "none";
    }
});
