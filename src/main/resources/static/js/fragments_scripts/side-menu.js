const menuBtn = document.querySelector('#menuToggle');

const component = document.getElementById('departamentList');

menuBtn.addEventListener('click', function () {
    component.classList.toggle('fullscreen');
});

// const sideMenu = document.querySelector('#sideMenu');

// menuBtn.addEventListener('click', () => {
//     sideMenu.classList.toggle('active');
// });

// const menuItems = document.querySelectorAll('.side-menu > ul > li');

// closeBtn.addEventListener('click', () => { sideMenu.classList.remove('active'); });

// menuItems.forEach(item => {
//     item.addEventListener('click', function (event) {
//         event.stopPropagation();

//         const submenu = item.querySelector('.submenu');
//         if (submenu) {
//             const isOpen = submenu.classList.contains('show');

//             if (isOpen) {
//                 submenu.style.maxHeight = submenu.scrollHeight + "px";
//                 setTimeout(() => {
//                     submenu.classList.remove('show');
//                     submenu.style.maxHeight = "0";
//                 }, 300);
//             } else {
//                 submenu.classList.add('show');
//                 submenu.style.maxHeight = submenu.scrollHeight + "px";
//             }
//         }
//     });
// });
