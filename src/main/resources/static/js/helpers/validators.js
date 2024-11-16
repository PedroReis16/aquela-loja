//Validação de nome de usuário
export function validateUsername(username) {
    const words = username.trim().split(/\s+/);
    return words.length === 2 && words[0].length > 0 && words[1].length > 0;
}

//Validação de CPF
export function validateCPF(cpf) {
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
export function validatePhone(phone) {
    phone = phone.replace(/[^\d]+/g, ''); // Remove caracteres não numéricos
    if (phone.length === 10 || phone.length === 11) {
        const regex = /^[1-9]{2}[2-9][0-9]{7,8}$/;
        return regex.test(phone);
    }
    return false;
}


//Validação de data de nascimento
export function validateBirthdate(birthdate) {
    const [day, month, year] = birthdate.split('/').map(Number);
    const date = new Date(year, month - 1, day);

    if (date.getFullYear() !== year || date.getMonth() !== month - 1 || date.getDate() !== day) {
        return { valid: false, error: 'Data inválida' }; // Data inválida
    }

    const today = new Date();
    const minAge = 16;
    const maxAge = 100;

    let age = today.getFullYear() - date.getFullYear();
    const monthDiff = today.getMonth() - date.getMonth();
    const dayDiff = today.getDate() - date.getDate();

    if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
        age--;
    }

    if (age < minAge) {
        return { valid: false, error: 'É necessário ter ao menos 16 anos' }; // Idade menor que 16 anos
    }

    if (age > maxAge || date > today) {
        return { valid: false, error: 'Data inválida' }; // Idade inválida
    }

    return { valid: true };
}

//Validação de email

export function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

//Validação de senha
export function validatePassword(password) {
    const criteria = [
        { regex: /[a-z]/, message: "pelo menos uma letra minúscula" },
        { regex: /[A-Z]/, message: "pelo menos uma letra maiúscula" },
        { regex: /\d/, message: "pelo menos um dígito" },
        { regex: /[@$!%*?&]/, message: "pelo menos um caractere especial (@$!%*?&)" },
        { regex: /.{8,}/, message: "pelo menos 8 caracteres" }
    ];

    const missingCriteria = criteria.filter(criterion => !criterion.regex.test(password)).map(criterion => criterion.message);

    if (missingCriteria.length === 0) {
        return { valid: true, message: "Senha válida" };
    } else {
        return { valid: false, message: "Senha inválida. Ela deve conter " + missingCriteria.join(", ") };
    }
}

