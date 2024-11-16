const currentRoute = window.location.pathname;
const menuBtn = document.querySelector('#menuToggle');
const component = document.getElementById('menuContainer');


//Listagem de categoriass 
menuBtn.addEventListener('click', function () {
    component.classList.toggle('fullscreen');
    document.body.classList.toggle('no-scroll', component.classList.contains('fullscreen'));

});


if (currentRoute == '/login' || currentRoute == '/cadastro') {
    const occultableItems = document.querySelectorAll('.occultable');
    const header = document.querySelector('.header');
    const logo = document.querySelector('.logo-container');

    occultableItems.forEach(item => {
        item.setAttribute('style', 'display: none');
    });

    header.setAttribute('style', 'height: 72px');
    logo.setAttribute('style', 'justify-content: center');

}
