document.addEventListener('DOMContentLoaded', function () {
    const menuList = document.getElementById('menuList');
    const isMobile = window.innerWidth <= 768;

    // Função para criar o menu
    function createMenu(departments) {
        departments.forEach(dept => {
            const menuItem = document.createElement('li');
            menuItem.className = 'menu-item';

            const menuLink = document.createElement('a');
            menuLink.href = '#';
            menuLink.className = 'menu-link';
            menuLink.textContent = capitalizeWords(dept.Departamento);

            const submenu = document.createElement('ul');
            submenu.className = 'submenu';

            dept.Values.forEach(value => {
                const submenuItem = document.createElement('li');
                submenuItem.className = 'submenu-item';

                const submenuLink = document.createElement('a');
                submenuLink.href = '#';
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
                    submenu.classList.toggle('active');
                });
            }
        });

    }

    // Função para capitalizar palavras
    function capitalizeWords(str) {
        return str.split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
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


});