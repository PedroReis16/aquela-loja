// Elementos do DOM
const card = document.querySelector('.card');
const cardNumber = document.getElementById('cardNumber');
const cardName = document.getElementById('cardName');
const cardExpiry = document.getElementById('cardExpiry');
const cardCvv = document.getElementById('cardCvv');
const cardBrand = document.getElementById('cardBrand');

// Inputs do formulário
const numberInput = document.getElementById('number');
const nameInput = document.getElementById('name');
const expiryInput = document.getElementById('expiry');
const cvvInput = document.getElementById('cvv');
const form = document.getElementById('creditCardForm');

// Padrões de bandeiras de cartão
const cardPatterns = {
    visa: /^4[0-9]{12}(?:[0-9]{3})?$/,
    mastercard: /^5[1-5][0-9]{14}$/,
    amex: /^3[47][0-9]{13}$/,
    elo: /^((((636368)|(438935)|(504175)|(451416)|(636297))[0-9]{10})|((5067)|(4576)|(4011))[0-9]{12})$/,
    hipercard: /^(606282\d{10}(\d{3})?)|(3841\d{15})$/,
};

// Função para formatar número do cartão
function formatCardNumber(value) {
    const regex = /^(\d{0,4})(\d{0,4})(\d{0,4})(\d{0,4})$/g;
    const onlyNumbers = value.replace(/[^\d]/g, '');

    return onlyNumbers.replace(regex, (regex, $1, $2, $3, $4) =>
        [$1, $2, $3, $4].filter(group => !!group).join(' ')
    );
}

// Função para detectar bandeira do cartão
function detectCardBrand(number) {
    const cleanNumber = number.replace(/\D/g, '');

    if (cardPatterns.visa.test(cleanNumber)) return '💳 VISA';
    if (cardPatterns.mastercard.test(cleanNumber)) return '💳 MASTERCARD';
    if (cardPatterns.amex.test(cleanNumber)) return '💳 AMEX';
    if (cardPatterns.elo.test(cleanNumber)) return '💳 ELO';
    if (cardPatterns.hipercard.test(cleanNumber)) return '💳 HIPERCARD';

    return '💳';
}

// Event Listeners
numberInput.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '');
    e.target.value = formatCardNumber(value);
    cardNumber.textContent = e.target.value || '•••• •••• •••• ••••';
    cardBrand.textContent = detectCardBrand(value);
});

nameInput.addEventListener('input', (e) => {
    cardName.textContent = e.target.value.toUpperCase() || 'NOME DO TITULAR';
});

expiryInput.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length >= 2) {
        value = value.slice(0, 2) + '/' + value.slice(2);
    }
    e.target.value = value;
    cardExpiry.textContent = value || 'MM/AA';
});

cvvInput.addEventListener('focus', () => {
    card.classList.add('flipped');
});

cvvInput.addEventListener('blur', () => {
    card.classList.remove('flipped');
});

cvvInput.addEventListener('input', (e) => {
    cardCvv.textContent = e.target.value || '•••';
});

form.addEventListener('submit', (e) => {
    e.preventDefault();
    // Aqui você pode adicionar a lógica para enviar os dados do cartão
    alert('Cartão cadastrado com sucesso!');
});