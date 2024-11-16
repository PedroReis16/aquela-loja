import { validateUsername, validateCPF, validatePhone, validateBirthdate, validateEmail, validatePassword } from '../helpers/validators.js';
import { maskCPF, maskCEP, maskPhone } from '../helpers/masks.js';

const step1Form = document.getElementById('step1');
const step2Form = document.getElementById('step2');

//Botoes de navegação entre os steps
const nextStepBtn = document.getElementById('nextStepBtn');
const previousStepBtn = document.getElementById('previousStepBtn');

//Campos do formulário
const username = "";
const cpf = "";
const gender = "";
const phone = "";
const birthdate = "";
const email = "";
const password = "";
const confirmedPassword = "";

//Campos de validações
const nameInput = document.querySelector('#nameInput');
const usernameError = document.querySelector('#personNameErrorMessage');

const cpfInput = document.getElementById('cpfInput');
const cpfError = document.getElementById('documentErrorMessage');

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
});

//Valida telefone
const inputPhone = document.getElementById("phoneInput");
const phoneError = document.getElementById('phoneErrorMessage');

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

// Máscaras para os campos

// const inputTelefone = document.querySelector('input[placeholder="Telefone"]');
// inputTelefone.addEventListener('input', function (e) {
//     if (e.target.value.length > 15)
//         e.target.value = e.target.value.substring(0, 15);

//     let value = e.target.value;
//     e.target.value = maskPhone(value);
// });

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
