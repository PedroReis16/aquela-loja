const menuBtn = document.querySelector('#menuToggle');

const sideMenu = document.querySelector('#sideMenu');

menuBtn.addEventListener('click', () => {
    sideMenu.classList.toggle('active');
});


function handleResize() {
    const menu = document.getElementById('#sideMenu');
    const isOpen = menu.classList.contains('open');

    if (window.innerWidth > 768) {
        menu.classList.remove('open');
    } else if (isOpen) {
        menu.classList.add('open');
    }
}

window.addEventListener('resize', handleResize);
