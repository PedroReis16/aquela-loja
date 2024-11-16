import { validateUsername, validateCPF, validatePhone, validateBirthdate, validateEmail, validatePassword } from '../helpers/validators.js';
import { maskCPF, maskCEP, maskPhone } from '../helpers/masks.js';

const step1Form = document.getElementById('step1');
const step2Form = document.getElementById('step2');

//Botoes de navegação entre os steps
const nextStepBtn = document.getElementById('nextStepBtn');
const previousStepBtn = document.getElementById('previousStepBtn');

//Campos do formulário
let username = "";
let cpf = "";
let gender = "";
let phone = "";
let birthdate = "";
let email = "";
let password = "";
let confirmedPassword = "";

//Campos de validações
const nameInput = document.querySelector('#nameInput');
const usernameError = document.querySelector('#personNameErrorMessage');

const cpfInput = document.getElementById('cpfInput');
const cpfError = document.getElementById('documentErrorMessage');

const inputPhone = document.getElementById("phoneInput");
const phoneError = document.getElementById('phoneErrorMessage');

const birthdateInput = document.getElementById('birthdateInput');
const birthdateError = document.getElementById('birthdateErrorMessage');

const emailInput = document.getElementById('emailInput');
const emailError = document.getElementById('emailErrorMessage');

const passwordInput = document.getElementById('passwordInput');
const passwordError = document.getElementById('passwordErrorMessage');

const confirmedPasswordInput = document.getElementById('confirmPasswordInput');
const confirmedPasswordError = document.getElementById('confirmPasswordErrorMessage');

nextStepBtn.addEventListener('click', function (e) {
    step1Form.classList.remove('active');
    step2Form.classList.add('active');
});

previousStepBtn.addEventListener('click', function (e) {
    step2Form.classList.remove('active');
    step1Form.classList.add('active');
});



//Validando o nome do usuário
nameInput.addEventListener('blur', function (e) {
    let value = e.target.value;

    if (!validateUsername(value)) {
        usernameError.textContent = 'Necessário informar o nome completo';
    } else {
        usernameError.textContent = '';
        username = value;
    }
    username = value;
});

//Validando o CPF
cpfInput.addEventListener('blur', function (e) {
    let value = maskCPF(e.target.value);

    e.target.value = value;

    if (!validateCPF(value)) {
        cpfError.textContent = 'CPF inválido';
        return;
    }

    fetch(`/user/documents?document=${value}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(response => {
        if (response.status == 200) {
            cpfError.textContent = 'CPF já cadastrado';
            return;
        }
        else if (response.status == 204) {
            cpfError.textContent = '';
        }
        else {
            cpfError.textContent = 'Erro ao validar CPF';
        }
    });
    cpf = value;
});

//Valida telefone

inputPhone.addEventListener('input', function (e) {
    if (e.target.value.length > 15)
        e.target.value = e.target.value.substring(0, 15);

    let value = e.target.value;
    e.target.value = maskPhone(value);
});

inputPhone.addEventListener('blur', function (e) {
    let value = e.target.value;

    if (!validatePhone(value)) {
        phoneError.textContent = 'Telefone inválido';
        return;
    }

    phoneError.textContent = '';
    phone = value;
});

//Valida data de nascimento

birthdateInput.addEventListener('focus', function () {
    birthdateError.textContent = 'A data deve estar no formato dia/mês/ano';
    birthdateError.style.color = 'gray';
});

birthdateInput.addEventListener('input', function (e) {
    let value = e.target.value;
    if (value.length > 10) {
        e.target.value = value.substring(0, 10);
    }
    else if (value.length === 2 || value.length === 5) {
        e.target.value = value + '/';
    }
});

birthdateInput.addEventListener('blur', function (e) {
    let value = e.target.value;

    var result = validateBirthdate(value);

    if (!result.valid) {
        birthdateError.textContent = result.error;
        birthdateError.style.color = 'red';
        return;
    }

    birthdateError.textContent = '';
    birthdate = value;
});


//Valida email

emailInput.addEventListener('blur', function (e) {
    let value = e.target.value;

    if (!validateEmail(value)) {
        emailError.textContent = 'Email inválido';
        return;
    }

    fetch(`/user/emails?email=${value}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(response => {
        if (response.status == 200) {
            emailError.textContent = 'Já existe um usuário cadastrado com esse email';
            return;
        }
        else if (response.status == 204) {
            emailError.textContent = '';
        }
        else {
            emailError.textContent = 'Erro ao validar CPF';
        }
    });

    emailError.textContent = '';
    email = value;
});


//Valida senha
passwordInput.addEventListener('blur', function (e) {
    const value = e.target.value;

    var result = validatePassword(value);

    if (!result.valid) {
        passwordError.textContent = result.message;
        return;
    }

    passwordError.textContent = '';
    password = value;
});

//Confirmação de senha

confirmedPasswordInput.addEventListener('blur', function (e) {
    const value = e.target.value;

    if (value !== passwordInput.value) {
        confirmedPasswordError.textContent = 'AS senhas não conferem';
        return;
    }

    confirmedPasswordError.textContent = '';
    confirmedPassword = value;
});

// const inputCEP = document.querySelector('input[placeholder="CEP"]');
// inputCEP.addEventListener('input', function (e) {
//     let value = e.target.value.replace(/\D/g, '');
//     if (value.length <= 8) {
//         value = value.replace(/(\d{5})(\d)/, '$1-$2');
//         e.target.value = value;
//     }
// });

// // Busca de CEP
// inputCEP.addEventListener('blur', async function (e) {
//     const cep = e.target.value.replace(/\D/g, '');
//     if (cep.length === 8) {
//         try {
//             const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
//             const data = await response.json();

//             if (!data.erro) {
//                 document.querySelector('input[placeholder="Endereço"]').value = data.logradouro;
//                 document.querySelector('input[placeholder="Bairro"]').value = data.bairro;
//                 document.querySelector('input[placeholder="Cidade"]').value = data.localidade;
//                 document.querySelector('input[placeholder="Estado"]').value = data.uf;
//             }
//             else {
//                 document.querySelector('input[placeholder="Endereço"]').value =
//                     document.querySelector('input[placeholder="Bairro"]').value =
//                     document.querySelector('input[placeholder="Cidade"]').value =
//                     document.querySelector('input[placeholder="Estado"]').value = '';
//             }
//         } catch (error) {
//             console.error('Erro ao buscar CEP:', error);
//         }
//     }
// });
