const inputs = {
    document: {
        element: document.getElementById('documentInput'),
        errorMessage: document.getElementById("documentErrorMessage"),
        validate: validateDocument,
        format: formatDocument,
        errorMessages: {
            empty: "Campo obrigatório",
            invalid: "CPF inválido"
        }
    },
    name: {
        element: document.getElementById('nameInput'),
        errorMessage: document.getElementById("personNameErrorMessage"),
        validate: validateName,
        errorMessages: {
            empty: "Campo obrigatório",
            invalid: "É preciso informar o nome completo",
            hasDigits: "O nome não pode conter números",
            hasSymbols: "O nome não pode conter símbolos"
        }
    },
    birthDate: {
        element: document.getElementById('birthDateInput'),
        errorMessage: document.getElementById("birthDateErrorMessage"),
        validate: isValidDate,
        errorMessages: {
            empty: "Campo obrigatório",
            invalid: "Data de nascimento inválida",
            format: "A data de nascimento deve estar no formato dd/MM/yyyy"
        }
    },
    phone: {
        element: document.getElementById('phoneInput'),
        errorMessage: document.getElementById("phoneErrorMessage"),
        validate: validatePhone,
        errorMessages: {
            empty: "Campo obrigatório",
            invalid: "O número de celular deve conter apenas dígitos",
            length: "O número de celular deve ter 10 ou 11 dígitos"
        }
    },
    email: {
        element: document.getElementById('emailInput'),
        errorMessage: document.getElementById("emailErrorMessage"),
        validate: validateEmail,
        errorMessages: {
            empty: "Campo obrigatório",
            invalid: "Email inválido"
        }
    },
    password: {
        element: document.getElementById('passwordInput'),
        errorMessage: document.getElementById('passwordErrorMessage'),
        validate: validatePassword,
        errorMessages: {
            empty: "Campo obrigatório",
            upperCase: "A senha deve conter ao menos uma letra maiúscula",
            lowerCase: "A senha deve conter ao menos uma letra minúscula",
            numberOrSpecialChar: "A senha deve conter ao menos um número ou caracter especial",
            length: "A senha deve ter ao menos 8 caracteres"
        }
    },
    confirmPassword: {
        element: document.getElementById('confirmPasswordInput'),
        errorMessage: document.getElementById('confirmPasswordErrorMessage'),
        validate: validateConfirmPassword,
        errorMessages: {
            mismatch: "As senhas não coincidem"
        }
    }
};
const passwordInputBtn = document.getElementById('passwordBtn');
const confirmPasswordInputBtn = document.getElementById('confirmPasswordBtn');

const submitButton = document.getElementById('detailsContinueBtn');

function formatDocument(cpf) {
    return cpf.replace(/\D/g, "")
        .replace(/(\d{3})(\d)/, "$1.$2")
        .replace(/(\d{3})(\d)/, "$1.$2")
        .replace(/(\d{3})(\d{1,2})$/, "$1-$2");
}

function validateDocument(cpf) {
    cpf = cpf.replace(/\D/g, '');
    if (cpf.length !== 11 || /^(\d)\1{10}$/.test(cpf)) return false;

    const calcResto = (len) => {
        let soma = 0;
        for (let i = 1; i <= len; i++) {
            soma += parseInt(cpf.substring(i - 1, i)) * (len + 2 - i);
        }
        let resto = (soma * 10) % 11;
        return (resto === 10 || resto === 11) ? 0 : resto;
    };

    return calcResto(9) === parseInt(cpf[9]) && calcResto(10) === parseInt(cpf[10]);
}

function validateName(name) {
    const isFullname = /\s[A-Za-z]/.test(name);
    const hasDigits = /\d/.test(name);
    const hasSymbols = /[!@#$%^&*(),.?":{}|<>]/.test(name);

    if (!isFullname) return 'invalid';
    if (hasDigits) return 'hasDigits';
    if (hasSymbols) return 'hasSymbols';
    return true;
}

function isValidDate(dateString) {
    const regex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (!regex.test(dateString)) return false;

    const parts = dateString.split('/');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1;
    const year = parseInt(parts[2], 10);

    const date = new Date(year, month, day);
    return date.getFullYear() === year && date.getMonth() === month && date.getDate() === day;
}

function validatePhone(phone) {
    const isNumeric = /^\d+$/.test(phone);
    const isValidLength = phone.length === 10 || phone.length === 11;
    if (!isNumeric) return 'invalid';
    if (!isValidLength) return 'length';
    return true;
}

function validateEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function validatePassword(password) {
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumberOrSpecialChar = /[\d!@#$%^&*(),.?":{}|<>]/.test(password);
    const isValidLength = password.length >= 8;

    if (!hasUpperCase) return 'upperCase';
    if (!hasLowerCase) return 'lowerCase';
    if (!hasNumberOrSpecialChar) return 'numberOrSpecialChar';
    if (!isValidLength) return 'length';
    return true;
}

function validateConfirmPassword(confirmPassword) {
    return confirmPassword === inputs.password.element.value;
}

function handleBlur(event, inputConfig) {
    const input = event.target;
    const value = input.value;

    if (inputConfig.format) {
        input.value = inputConfig.format(value);
    }

    if (value === "") {
        inputConfig.errorMessage.textContent = inputConfig.errorMessages.empty;
        inputConfig.errorMessage.classList.remove('hidden');
        updateSubmitButtonState();
        return;
    }

    const validationResult = inputConfig.validate(value);
    if (validationResult !== true) {
        inputConfig.errorMessage.textContent = inputConfig.errorMessages[validationResult] || inputConfig.errorMessages.invalid;
        inputConfig.errorMessage.classList.remove('hidden');
        updateSubmitButtonState();
        return;
    }

    inputConfig.errorMessage.textContent = "";
    inputConfig.errorMessage.classList.add('hidden')
    updateSubmitButtonState();
}

function handleInput(event, inputConfig) {
    const input = event.target;
    const value = input.value;

    if (inputConfig.validate !== validateConfirmPassword && inputConfig.validate !== validateDocument) {
        if (!/^[\d.-]*$/.test(value)) {
            input.value = value.replace(/[^\d.-]/g, '');
        }
    }

    if (inputConfig.validate === validateConfirmPassword) {
        const validationResult = inputConfig.validate(value);
        if (!validationResult) {
            inputConfig.errorMessage.textContent = inputConfig.errorMessages.mismatch;
            updateSubmitButtonState();
            return;
        }
        inputConfig.errorMessage.textContent = "";
        updateSubmitButtonState();
    }
}

function updateSubmitButtonState() {
    const isFormValid = Object.values(inputs).every(inputConfig => {
        const value = inputConfig.element.value;
        return value !== "" && inputConfig.validate(value) === true;
    });

    submitButton.disabled = !isFormValid;
}

Object.values(inputs).forEach(inputConfig => {
    if (inputConfig.element) {
        inputConfig.element.addEventListener('blur', (event) => handleBlur(event, inputConfig));
        if (inputConfig.validate === validateConfirmPassword || inputConfig.validate === validateDocument) {
            inputConfig.element.addEventListener('input', (event) => handleInput(event, inputConfig));
        }
    }
});


passwordInputBtn.addEventListener('click', () => {
    const passwordInput = inputs.password.element;
    const passwordType = passwordInput.type;
    passwordInput.type = passwordType === 'password' ? 'text' : 'password';
});

confirmPasswordInputBtn.addEventListener('click', () => {
    const confirmPasswordInput = inputs.confirmPassword.element;
    const confirmPasswordType = confirmPasswordInput.type;
    confirmPasswordInput.type = confirmPasswordType === 'password' ? 'text' : 'password';
});

// Initial check to disable the submit button if the form is not valid
updateSubmitButtonState();