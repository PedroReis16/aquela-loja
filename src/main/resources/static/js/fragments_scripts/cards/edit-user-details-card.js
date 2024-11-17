import { validateUsername, validatePhone, validateEmail } from '../../helpers/validators.js';
import { maskPhone } from '../../helpers/masks.js';

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

