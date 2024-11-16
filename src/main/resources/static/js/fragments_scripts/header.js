const currentRoute = window.location.pathname;

const departaments = {};
const sideMenuList = document.getElementById('sideMenuList');

document.addEventListener('DOMContentLoaded', function () {
    fetch('/header/departaments', {
        method: 'GET'
    }).then(response => response.json())
        .then(responseDepartament => {

            // Verifique se responseDepartament é um objeto
            if (typeof responseDepartament === 'object' && responseDepartament !== null) {
                Object.keys(responseDepartament).forEach(key => {
                    departaments[key] = Array.from(responseDepartament[key]);
                });
            } else {
                console.error('Response is not an object:', responseDepartament);
            }

            Object.keys(departaments).forEach(departament => {
                //Criando o item do menu (Hardware)
                const departamentItem = document.createElement('li');
                departamentItem.textContent = capitalizeWords(departament);


                //Criando o menu de opções
                const submenu = document.createElement('ul');

                departaments[departament].forEach(subdepartament => {
                    const subdepartamentItem = document.createElement('li');
                    subdepartamentItem.textContent = capitalizeWords(subdepartament);
                    submenu.appendChild(subdepartamentItem);
                });
                departamentItem.appendChild(submenu);

                //Adicionando o item ao menu 
                sideMenuList.appendChild(departamentItem);
            });


        })
        .catch(error => {
            console.error('Error:', error);
        });
});

function capitalizeWords(str) {
    const words = str.split(' ');
    const capitalizedWords = words.map(word => {
        if (word === "E" || word === "DE" || word === "DA" || word === "DO" || word === "DAS" || word === "DOS")
            return word.toLowerCase();
        if (word === "PC" || word === "SSD" || word === "TV" || word === "RAM")
            return word;
        return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
    });
    return capitalizedWords.join(' ');
}

// const isMobile = window.innerWidth < 768;

// const menuToggle = document.getElementById("menuToggle");
// const logoCart = document.getElementById("logoCart");
// const logo = document.getElementsByClassName("logo-link");
// const searchBar = document.querySelector(".search-bar");
// const mainHeader = document.querySelector(".main-header");
// const departaments = document.querySelector(".departaments");
// const actionPages = document.querySelector(".action-pages");

// if (currentRoute == '/login' || currentRoute == '/cadastro') {
//     menuToggle.style.display = "none";
//     logoCart.style.display = "none";
//     logo[0].style.width = "100%";
//     logo[0].style.textAlign = "center";
//     searchBar.style.display = "none";

//     if (isMobile) {
//         mainHeader.style.height = "62px";
//     }
//     departaments.style.display = "none";
//     actionPages.style.display = "none";
// }

// if (location.pathname === '/login' || location.pathname === '/cadastro') {
//     const menuToggle = document.getElementById('menuToggle');
//     const logoCart = document.getElementById('logoCart');
//     const logo = document.getElementsByClassName('logo-link');
//     const searchBar = document.querySelector('.search-bar');
//     const mainHeader = document.querySelector('.main-header');
//     const departaments = document.querySelector('.departaments');
//     const actionPages = document.querySelector('.action-pages');

//     menuToggle.style.display = 'none';
//     logoCart.style.display = 'none';
//     logo[0].style.width = '100%';
//     logo[0].style.textAlign = 'center';
//     searchBar.style.display = 'none';
//     mainHeader.style.height = '62px';
//     departaments.style.display = 'none';
//     actionPages.style.display = 'none';
// }

// document.addEventListener("DOMContentLoaded", function () {
//     const currentPath = window.location.pathname;

//     console.log(currentPath);

//     const isMobile = window.innerWidth < 768;

//     const menuToggle = document.getElementById("menuToggle");
//     const logoCart = document.getElementById("logoCart");
//     const logo = document.getElementsByClassName("logo-link");
//     const searchBar = document.querySelector(".search-bar");
//     const mainHeader = document.querySelector(".main-header");
//     const departaments = document.querySelector(".departaments");
//     const actionPages = document.querySelector(".action-pages");

//     if (currentPath == '/login' || currentPath == '/cadastro') {
//         menuToggle.style.display = "none";
//         logoCart.style.display = "none";
//         logo[0].style.width = "100%";
//         logo[0].style.textAlign = "center";
//         searchBar.style.display = "none";

//         if (isMobile) {
//             mainHeader.style.height = "62px";
//         }
//         departaments.style.display = "none";
//         actionPages.style.display = "none";
//     }
// });
