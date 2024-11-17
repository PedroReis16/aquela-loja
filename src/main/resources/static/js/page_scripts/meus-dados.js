import { validateUsername, validatePhone, validateBirthdate, validateEmail } from '../helpers/validators.js';
import { maskCPF, maskCEP, maskPhone } from '../helpers/masks.js';

//Formulário de dados do usuário
const nameInput = document.getElementById('nameInput');
const nameErrorMessage = document.getElementById('personNameErrorMessage');

const phoneInput = document.getElementById('phoneInput');
const phoneErrorMessage = document.getElementById('phoneErrorMessage');

const emailInput = document.getElementById('emailInput');
const emailErrorMessage = document.getElementById('emailErrorMessage');

let userFormData = {
    name: "",
    phone: "",
    email: ""
};

const saveUserDetailsBtn = document.getElementById('saveUserDetails');

//Dialog de confirmação de exclusão de conta
const accountDeleteDialog = document.getElementById('deleteUserDialog');
const deleteButton = document.getElementById('deleteUserAccount');

//Dialog de adição ou edição do endereço
const addressDialog = document.getElementById('addressDialog');
const closeAddressDialogBtn = document.getElementById('closeAddressDialogBtn');
const cancelAddressBtn = document.getElementById('cancelAddressBtn');
const newAddressButton = document.getElementById('newAddressBtn');
// const addAddressButton = document.getElementById('addAddress');

//Card de endereço
const addressCard = document.querySelectorAll('.address-card');

function canSaveUserDetails() {
    if (userFormData.name && userFormData.phone && userFormData.email) {
        saveUserDetailsBtn.disabled = false;

    } else {

        saveUserDetailsBtn.disabled = true;
    }
}

document.addEventListener('DOMContentLoaded', function () {
    userFormData.name = nameInput.value;
    userFormData.phone = phoneInput.value;
    userFormData.email = emailInput.value;

    canSaveUserDetails();
});

//Validação formulários de usuário

//Validação do Nome
nameInput.addEventListener('blur', function (e) {
    const value = e.target.value;

    if (!validateUsername(value)) {
        userFormData.name = "";
        nameErrorMessage.textContent = 'Necessário informar o nome completo';
    } else {
        nameErrorMessage.textContent = '';
        userFormData.name = value;
    }
    canSaveUserDetails();
});

//Validação do Telefone
phoneInput.addEventListener('input', function (e) {
    if (e.target.value.length > 15)
        e.target.value = e.target.value.substring(0, 15);

    let value = e.target.value;
    e.target.value = maskPhone(value);
});

phoneInput.addEventListener('blur', function (e) {
    let value = e.target.value;

    if (!validatePhone(value)) {
        userFormData.phone = "";
        phoneErrorMessage.textContent = 'Telefone inválido';

    }
    else {
        phoneErrorMessage.textContent = '';
        userFormData.phone = value;
    }
    canSaveUserDetails();
});

//Validação do email
emailInput.addEventListener('blur', function (e) {
    const value = e.target.value;

    if (!validateEmail(value)) {
        userFormData.email = "";
        emailErrorMessage.textContent = 'Email inválido';

    }
    else {
        emailErrorMessage.textContent = '';
        userFormData.email = value;
    }
    canSaveUserDetails();
});

//Dialog para exclusão de conta
deleteButton.addEventListener('click', function () {
    accountDeleteDialog.showModal();
});



//Dialog de adição ou edição do endereço
newAddressButton.addEventListener('click', function () {
    addressDialog.showModal();
});

closeAddressDialogBtn.addEventListener('click', function () {
    addressDialog.close();
});

cancelAddressBtn.addEventListener('click', function () {
    addressDialog.close();
});

//Editar Card
addressCard.forEach(card => {
    const editBtn = card.querySelector("Button");

    const addressData = card.getAttribute('data-address');

    const regex = /(\w+)=([^,]*)/g;

    const result = {};
    let match;

    // Enquanto houver correspondências, adicionar ao objeto result
    while ((match = regex.exec(addressData)) !== null) {
        const key = match[1].trim();    // Nome da propriedade
        let value = match[2].trim();    // Valor da propriedade

        // Tentar converter para boolean, número ou manter string
        if (value === "true") value = true;
        else if (value === "false") value = false;
        else if (!isNaN(value)) value = Number(value);

        result[key] = value;
    }

    const cep = result.cep

    editBtn.addEventListener('click', function () {

        addressDialog.querySelector("#editCEP").value = result.cep;
        addressDialog.querySelector("#editAddressId").value = result.addressIdentification;
        addressDialog.querySelector("#editStreet").value = result.street;
        addressDialog.querySelector("#editNumber").value = result.number;
        addressDialog.querySelector("#editNeighborhood").value = result.neighborhood;
        addressDialog.querySelector("#editCity").value = result.city;
        addressDialog.querySelector("#editState").value = result.state;
        addressDialog.querySelector("#editComplement").value = result.complement;
        addressDialog.querySelector("#editReference").value = result.reference;

        const addressForm = addressDialog.querySelector("#addressForm");
        addressForm.action = "";
        addressForm.object = "";

        addressDialog.showModal();

        addressForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const formData = new FormData(addressForm);

            formData.append("id", result.id);

            var object = {};
            formData.forEach(function (value, key) {
                object[key] = value;
            });

            fetch('/user/update-address', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(object)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                }
            });

        });

        addressForm.querySelector("#deleteAddress").addEventListener('click', function () {
            fetch(`/user/delete-address/${result.id}`, {
                method: 'POST',
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                }
            });
        });
    });
});

window.addEventListener('click', function (event) {
    if (event.target === accountDeleteDialog) {
        accountDeleteDialog.close();
    }
    if (event.target === addressDialog) {
        addressDialog.querySelectorAll('input').forEach(input => { input.value = ""; });
        addressDialog.close();
    }
});