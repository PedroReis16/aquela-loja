const currentRoute = window.location.pathname;

const menuBtn = document.querySelector('#menuToggle');

const component = document.getElementById('departamentList');

menuBtn.addEventListener('click', function () {
    component.classList.toggle('fullscreen');
});
