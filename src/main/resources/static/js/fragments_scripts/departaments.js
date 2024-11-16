
const departaments = {};
const departamentsList = document.getElementsByClassName("departament-list");

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
                
                departamentItem.classList.add('dropbtn');

                //Criando o menu de opções
                const submenu = document.createElement('ul');

                departaments[departament].forEach(subdepartament => {
                    const subdepartamentItem = document.createElement('li');
                    subdepartamentItem.textContent = capitalizeWords(subdepartament);
                    submenu.appendChild(subdepartamentItem);
                });
                departamentItem.appendChild(submenu);

                //Adicionando o item ao menu 
                departamentsList.appendChild(departamentItem);
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