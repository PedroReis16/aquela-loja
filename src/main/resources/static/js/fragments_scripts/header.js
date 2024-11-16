const currentRoute = window.location.pathname;

const menuBtn = document.querySelector('#menuToggle');

const component = document.getElementById('menuContainer');

menuBtn.addEventListener('click', function () {
    component.classList.toggle('fullscreen');
    document.body.classList.toggle('no-scroll', component.classList.contains('fullscreen'));

});
