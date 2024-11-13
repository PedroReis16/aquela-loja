//CPF mask
export function maskCPF(cpf) {
    let value = cpf.replace(/\D/g, '');
    if (cpf.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    }
    return value;
}

//Phone mask
export function maskPhone(phone) {
    phone = phone.replace(/\D/g, '');
    if (phone.length <= 11) {
        phone = phone.replace(/(\d{2})(\d)/, '($1) $2');
        phone = phone.replace(/(\d{5})(\d)/, '$1-$2');
    }
    return phone;
}

//CEP mask
export function maskCEP(cep) {
    let value = cep.replace(/\D/g, '');
    
    if (value.length <= 8) {
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }
    return value;
}

