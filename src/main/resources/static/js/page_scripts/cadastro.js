import { validateUsername, validateCPF, validatePhone, validateBirthdate, validateEmail, validatePassword } from '../helpers/validators.js';
import { maskCPF, maskCEP, maskPhone } from '../helpers/masks.js';

const step1Form = document.getElementById('step1');
const step2Form = document.getElementById('step2');

const nextStepBtn = document.getElementById('nextStepBtn');
nextStepBtn.addEventListener('click', function (e) {
    step1Form.classList.remove('active');
    step2Form.classList.add('active');
});

const previousStepBtn = document.getElementById('previousStepBtn');
previousStepBtn.addEventListener('click', function (e) {
    step2Form.classList.remove('active');
    step1Form.classList.add('active');
});


// Toggle de visibilidade da senha
document.querySelectorAll('.toggle-password').forEach(button => {
    button.addEventListener('click', function () {
        const input = this.previousElementSibling;
        const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
        input.setAttribute('type', type);
        this.textContent = type === 'password' ? '👁️' : '👁️‍🗨️';
    });
});

// Máscaras para os campos
const inputCPF = document.querySelector('input[placeholder="CPF"]');
inputCPF.addEventListener('input', function (e) {
    let value = e.target.value;
    e.target.value = maskCPF(value);
});

const inputTelefone = document.querySelector('input[placeholder="Telefone"]');
inputTelefone.addEventListener('input', function (e) {
    if (e.target.value.length > 15)
        e.target.value = e.target.value.substring(0, 15);

    let value = e.target.value;
    e.target.value = maskPhone(value);
});

const inputCEP = document.querySelector('input[placeholder="CEP"]');
inputCEP.addEventListener('input', function (e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length <= 8) {
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
        e.target.value = value;
    }
});

// Busca de CEP
inputCEP.addEventListener('blur', async function (e) {
    const cep = e.target.value.replace(/\D/g, '');
    if (cep.length === 8) {
        try {
            const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
            const data = await response.json();

            if (!data.erro) {
                document.querySelector('input[placeholder="Endereço"]').value = data.logradouro;
                document.querySelector('input[placeholder="Bairro"]').value = data.bairro;
                document.querySelector('input[placeholder="Cidade"]').value = data.localidade;
                document.querySelector('input[placeholder="Estado"]').value = data.uf;
            }
            else {
                document.querySelector('input[placeholder="Endereço"]').value =
                    document.querySelector('input[placeholder="Bairro"]').value =
                    document.querySelector('input[placeholder="Cidade"]').value =
                    document.querySelector('input[placeholder="Estado"]').value = '';
            }
        } catch (error) {
            console.error('Erro ao buscar CEP:', error);
        }
    }
});
