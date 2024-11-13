//Validação de nome de usuário
function validateUsername(username) {
    const words = input.trim().split(/\s+/);
    return words.length === 2 && words[0].length > 0 && words[1].length > 0;
}

//Validação de CPF
function validateCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, ''); // Remove caracteres não numéricos
    if (cpf.length !== 11 || /^(\d)\1{10}$/.test(cpf)) {
        return false; // Verifica se o CPF tem 11 dígitos ou se todos os dígitos são iguais
    }

    let sum = 0;
    let remainder;

    for (let i = 1; i <= 9; i++) {
        sum += parseInt(cpf.substring(i - 1, i)) * (11 - i);
    }
    remainder = (sum * 10) % 11;
    if (remainder === 10 || remainder === 11) {
        remainder = 0;
    }
    if (remainder !== parseInt(cpf.substring(9, 10))) {
        return false;
    }

    sum = 0;
    for (let i = 1; i <= 10; i++) {
        sum += parseInt(cpf.substring(i - 1, i)) * (12 - i);
    }
    remainder = (sum * 10) % 11;
    if (remainder === 10 || remainder === 11) {
        remainder = 0;
    }
    if (remainder !== parseInt(cpf.substring(10, 11))) {
        return false;
    }

    return true;
}

//Validação de telefone
function validatePhone(phone) {
    phone = phone.replace(/[^\d]+/g, ''); // Remove caracteres não numéricos
    if (phone.length === 10 || phone.length === 11) {
        const regex = /^[1-9]{2}[2-9][0-9]{7,8}$/;
        return regex.test(phone);
    }
    return false;
}


//Validação de data de nascimento
function validateBirthdate(birthdate) {
    const date = new Date(birthdate);
    const today = new Date();
    const minAge = 16;
    const maxAge = 100;

    if (isNaN(date.getTime())) {
        return false; // Data inválida
    }

    const age = today.getFullYear() - date.getFullYear();
    const monthDiff = today.getMonth() - date.getMonth();
    const dayDiff = today.getDate() - date.getDate();

    if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
        age--;
    }

    return age >= minAge && age <= maxAge && date <= today;
}

//Validação de email

function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

//Validação de senha
function validatePassword(password) {
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return regex.test(password);
}


export {
    validateUsername,
    validateCPF,
    validatePhone,
    validateBirthdate,
    validateEmail,
    validatePassword
};