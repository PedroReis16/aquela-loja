document.addEventListener('DOMContentLoaded', function () {
    const menuList = document.getElementById('menuList');
    const isMobile = window.innerWidth <= 768;

    // Função para criar o menu
    function createMenu(departments) {
        departments.forEach(dept => {
            const menuItem = document.createElement('li');
            menuItem.className = 'menu-item';

            const menuLink = document.createElement('a');
            menuLink.href = '/itens/' + dept.Departamento;
            menuLink.className = 'menu-link';
            menuLink.textContent = capitalizeWords(dept.Departamento);

            const submenu = document.createElement('ul');
            submenu.className = 'submenu';

            dept.Values.forEach(value => {
                const submenuItem = document.createElement('li');
                submenuItem.className = 'submenu-item';

                const submenuLink = document.createElement('a');
                submenuLink.href = '/itens/' + value;
                submenuLink.textContent = capitalizeWords(value);

                submenuItem.appendChild(submenuLink);
                submenu.appendChild(submenuItem);
            });

            menuItem.appendChild(menuLink);
            menuItem.appendChild(submenu);
            menuList.appendChild(menuItem);

            // Adicionar evento de clique para mobile
            if (isMobile) {
                menuLink.addEventListener('click', function (e) {
                    e.preventDefault();
                    this.classList.toggle('active');
                    const submenu = this.nextElementSibling;
                    if (submenu.classList.contains('active')) {
                        submenu.style.maxHeight = "0px"
                        // submenu.classList.remove('active');
                    } else {
                        submenu.style.maxHeight = submenu.scrollHeight + 'px';
                    }
                    submenu.classList.toggle('active');
                });
            }
        });

    }

    // Função para capitalizar palavras
    function capitalizeWords(str) {
        return str.split(' ')
            .map(word => {
                if (word === "E" || word === "DE" || word === "DA") {
                    return word.toLowerCase();
                }
                else if (word === "PC" || word === "TV" || word === "DVD" || word === "CD" || word === "SSD" || word === "RAM")
                    return word;
                return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
            })
            .join(' ');
    }

    // Fazer a requisição para obter os dados dos departamentos
    fetch('/header/departaments', {
        method: 'GET'
    }).then(response => response.json())
        .then(responseDepartament => {
            // Verifique se responseDepartament é um objeto
            if (typeof responseDepartament === 'object' && responseDepartament !== null) {
                const departments = Object.keys(responseDepartament).map(key => ({
                    Departamento: key,
                    Values: Array.from(responseDepartament[key])
                }));
                createMenu(departments);
            } else {
                console.error('Response is not an object:', responseDepartament);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });

    window.addEventListener('resize', () => {
        const submenus = document.querySelectorAll('.submenu');
        submenus.forEach(submenu => {
            submenu.style.maxHeight = null;
            submenu.classList.remove('active');
        });

        const menuLinks = document.querySelectorAll('.menu-link');
        menuLinks.forEach(menuLink => {
            menuLink.classList.remove('active');
        });
    });
});