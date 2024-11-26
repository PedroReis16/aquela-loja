// Botão de fechar
const closeBtn = document.querySelectorAll('.new-card-cancel');

// Dialog
const newCardDialog = document.getElementById('newUserCardDialog');

// Seleciona os elementos do DOM
const numberInput = document.getElementById('number');
const nameInput = document.getElementById('name');
const expiryInput = document.getElementById('expiry');
const cvvInput = document.getElementById('cvv');

// Seleciona os elementos de mensagem de erro
const cardNumberError = document.getElementById('cardNumberErrorMessage');
const holderNameError = document.getElementById('holderNameErrorMessage');
const expiryError = document.getElementById('ExpiracyErrorMessage');
const cvvError = document.getElementById('CVVErrorMessage');

// Seleciona os elementos do cartão visual (assumindo que você vai adicionar estes elementos)
const cardNumberBox = document.querySelector('.card-number-box');
const cardHolderName = document.querySelector('.card-holder-name');
const expDate = document.querySelector('.exp-date');
const cvvBox = document.querySelector('.cvv-box');
const frontCard = document.querySelector('.front');
const backCard = document.querySelector('.back');

//Save button
const saveButton = document.getElementById('saveNewCard');
const validationState = {
    cardNumber: false,
    cardHolderName: false,
    expiryDate: false,
    cvv: false
};

function updateSaveButtonState() {
    // Verifica se todos os campos estão válidos
    const isValid = Object.values(validationState).every(state => state === true);

    // Habilita/desabilita o botão baseado na validação
    saveButton.disabled = !isValid;
}

function updateValidationState(field, isValid) {
    validationState[field] = isValid;
    updateSaveButtonState();
}

// Função para formatar o número do cartão
numberInput.addEventListener('input', (e) => {
    let value = e.target.value;

    // Remove todos os espaços e caracteres não numéricos
    value = value.replace(/\D/g, '');

    // Adiciona espaços a cada 4 dígitos
    value = value.replace(/(\d{4})/g, '$1 ').trim();

    // Atualiza o valor do input
    e.target.value = value;

    // Atualiza o display do cartão
    if (cardNumberBox) {
        cardNumberBox.innerText = value || '#### #### #### ####';
    }

    // Validação do número do cartão
    let isValid = true;
    if (value.length > 0 && value.length < 19) {
        cardNumberError.textContent = 'Número do cartão incompleto';
        isValid = false;
    } else if (!luhnCheck(value.replace(/\s/g, ''))) {
        cardNumberError.textContent = 'Número do cartão inválido';
        isValid = false;
    } else {
        cardNumberError.textContent = '';
    }
    updateValidationState('cardNumber', isValid);
});

// Validação do nome do titular
nameInput.addEventListener('input', (e) => {
    let value = e.target.value;

    // Remove números do nome
    value = value.replace(/[0-9]/g, '');
    e.target.value = value;

    if (cardHolderName) {
        cardHolderName.innerText = value || 'NOME DO TITULAR';
    }

    // Validação do nome
    let isValid = true;
    if (value.length < 3) {
        holderNameError.textContent = 'Nome muito curto';
        isValid = false;
    } else if (!/^[a-zA-ZÀ-ÿ\s]*$/.test(value)) {
        holderNameError.textContent = 'Nome contém caracteres inválidos';
        isValid = false;
    } else {
        holderNameError.textContent = '';
    }
    updateValidationState('cardHolderName', isValid);
});

// Formatação e validação da data de validade
expiryInput.addEventListener('input', (e) => {
    let value = e.target.value;

    // Remove caracteres não numéricos
    value = value.replace(/\D/g, '');

    // Adiciona a barra após o mês
    if (value.length >= 2) {
        value = value.slice(0, 2) + '/' + value.slice(2);
    }

    e.target.value = value;

    if (expDate) {
        expDate.innerText = value || 'MM/AA';
    }

    // Validação da data
    let isValid = true;
    if (value.length >= 5) {
        const month = parseInt(value.slice(0, 2));
        const year = parseInt(value.slice(3));
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear() % 100;
        const currentMonth = currentDate.getMonth() + 1;

        if (month < 1 || month > 12) {
            expiryError.textContent = 'Mês inválido';
            isValid = false;
        } else if (year < currentYear || (year === currentYear && month < currentMonth)) {
            expiryError.textContent = 'Cartão vencido';
            isValid = false;
        } else {
            expiryError.textContent = '';
        }
    } else {
        expiryError.textContent = 'Data inválida';
        isValid = false;
    }
    updateValidationState('expiryDate', isValid);
});

// Validação do CVV
cvvInput.addEventListener('input', (e) => {
    let value = e.target.value;

    // Remove caracteres não numéricos
    value = value.replace(/\D/g, '');
    e.target.value = value;

    if (cvvBox) {
        cvvBox.innerText = value || 'CVV';
    }

    let isValid = true;
    if (value.length < 3) {
        cvvError.textContent = 'CVV incompleto';
        isValid = false;
    } else {
        cvvError.textContent = '';
    }
    updateValidationState('cvv', isValid);
});

// Rotação do cartão quando foca no CVV
cvvInput.addEventListener('focus', () => {
    if (frontCard && backCard) {
        frontCard.classList.add('flip');
        backCard.classList.add('flip');
    }
});

cvvInput.addEventListener('blur', () => {
    if (frontCard && backCard) {
        frontCard.classList.remove('flip');
        backCard.classList.remove('flip');
    }
});

// Função para validação do número do cartão (Algoritmo de Luhn)
function luhnCheck(cardNumber) {
    if (!cardNumber) return false;

    let sum = 0;
    let isEven = false;

    for (let i = cardNumber.length - 1; i >= 0; i--) {
        let digit = parseInt(cardNumber.charAt(i));

        if (isEven) {
            digit *= 2;
            if (digit > 9) {
                digit -= 9;
            }
        }

        sum += digit;
        isEven = !isEven;
    }

    return sum % 10 === 0;
}

closeBtn.forEach(btn => {
    btn.addEventListener('click', (event) => {
        event.preventDefault();
        newCardDialog.close();
        // Adicione qualquer outra lógica necessária para fechar a dialog
    });
});

//Salvar 

saveButton.addEventListener('click', async () => {
    const data = {
        number: numberInput.value.replace(/\s/g, ''),
        holderName: nameInput.value,
        expirationDate: expiryInput.value,
        cvv: cvvInput.value
    };

    fetch('/user/new-card', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            // Adicione a lógica para adicionar o cartão visualmente
            location.reload();
            newCardDialog.close();
        }
    });
});

document.addEventListener('DOMContentLoaded', () => {
    saveButton.disabled = true;
});