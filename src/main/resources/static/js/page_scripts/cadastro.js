import { validateUsername, validateCPF, validatePhone, validateBirthdate, validateEmail, validatePassword } from '../helpers/validators.js';
import { maskCPF, maskCEP, maskPhone } from '../helpers/masks.js';
import { getAddressByCEP } from '../helpers/helpers.js';

const step1Form = document.getElementById('step1');
const step2Form = document.getElementById('step2');

//Botoes de navegação entre os steps
const nextStepBtn = document.getElementById('nextStepBtn');
const previousStepBtn = document.getElementById('previousStepBtn');

//Botoes de visibilidade de senha
const togglePasswordBtn = document.getElementById('passwordBtn');
const confirmTogglePasswordBtn = document.getElementById('confirmPasswordBtn');

//Botão de submit
const submitBtn = document.getElementById('saveNewUserBtn');

//Campos do formulário
let formData = {
    userName: "",
    cpf: "",
    phone: "",
    birthdate: "",
    email: "",
    password: "",
    confirmedPassword: "",
    address: {
        cep: "",
        street: "",
        number: "",
        neighborhood: "",
        city: "",
        state: "",
        addressId: ""
    }
}

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

const inputCEP = document.getElementById('cepInput');
const cepError = document.getElementById('CEPInputErrorMessage');

const identificationInput = document.getElementById('addressIdInput');

const numberInput = document.getElementById('numberInput');

function checkDetailsFormValidity() {
    if (formData.userName && formData.cpf &&
        formData.phone && formData.birthdate &&
        formData.email && formData.password &&
        formData.confirmedPassword) {
        nextStepBtn.disabled = false;
    } else {
        nextStepBtn.disabled = true;
    }
}

function checkAddressFormValidity() {
    if (formData.address.cep && formData.address.street &&
        formData.address.number && formData.address.neighborhood &&
        formData.address.city &&
        formData.address.state && formData.address.addressId) {
        submitBtn.disabled = false;
    } else {
        submitBtn.disabled = true;
    }
}

document.addEventListener('DOMContentLoaded', function () {
    checkDetailsFormValidity();
    checkAddressFormValidity();
});

//Funções de navegação entre os steps
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
        formData.userName = value;
    }
    formData.userName = value;
    checkDetailsFormValidity();
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
    formData.cpf = value;
    checkDetailsFormValidity();
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
    formData.phone = value;
    checkDetailsFormValidity();
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
    formData.birthdate = value;
    checkDetailsFormValidity();
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
    formData.email = value;
    checkDetailsFormValidity();
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
    formData.password = value;
    checkDetailsFormValidity();
});

//Confirmação de senha

confirmedPasswordInput.addEventListener('blur', function (e) {
    const value = e.target.value;

    if (value !== passwordInput.value) {
        confirmedPasswordError.textContent = 'As senhas não conferem';
        return;
    }

    confirmedPasswordError.textContent = '';
    formData.confirmedPassword = value;
    checkDetailsFormValidity();
});

//Validações do endereço

//Valida CEP

inputCEP.addEventListener('input', function (e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length <= 8) {
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
        e.target.value = value;
    }
});

// // Busca de CEP
inputCEP.addEventListener('blur', async function (e) {
    const cep = e.target.value.replace(/\D/g, '');
    if (cep.length === 8) {
        try {
            const data = await getAddressByCEP(cep);

            const streetInput = document.getElementById('streetInput');
            const neighborhoodInput = document.getElementById('neighborhoodInput');
            const cityInput = document.getElementById('cityInput');
            const stateInput = document.getElementById('stateInput');


            if (!data.erro) {
                streetInput.value = data.logradouro;
                neighborhoodInput.value = data.bairro;
                cityInput.value = data.localidade;
                stateInput.value = data.uf;

                formData.address.cep = data.cep;
                formData.address.street = data.logradouro;
                formData.address.neighborhood = data.bairro;
                formData.address.city = data.localidade;
                formData.address.state = data.uf;
            }
            else {
                streetInput.value =
                    neighborhoodInput.value =
                    cityInput.value =
                    stateInput.value = '';
            }
        } catch (error) {
            console.error('Erro ao buscar CEP:', error);
        }
    }
    checkAddressFormValidity();
});

//Valida identificação
identificationInput.addEventListener('blur', function (e) {
    formData.address.addressId = e.target.value;
    checkAddressFormValidity();
});

//Valida número
numberInput.addEventListener('blur', function (e) {
    formData.address.number = e.target.value;
    checkAddressFormValidity();
});


//Visibilidade do campo de senha

togglePasswordBtn.addEventListener('click', function () {
    const type = passwordInput.getAttribute("type") === "password" ? "text" : "password";
    passwordInput.setAttribute("type", type);

    togglePasswordBtn.classList.toggle("visible");
});

confirmTogglePasswordBtn.addEventListener('click', function () {
    const type = confirmedPasswordInput.getAttribute("type") === "password" ? "text" : "password";
    confirmedPasswordInput.setAttribute("type", type);

    confirmTogglePasswordBtn.classList.toggle("visible");
});
