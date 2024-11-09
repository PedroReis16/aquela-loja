function nextStep() {
    document.getElementById('step1').classList.remove('active');
    document.getElementById('step2').classList.add('active');
}

function prevStep() {
    document.getElementById('step2').classList.remove('active');
    document.getElementById('step1').classList.add('active');
}

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
    let value = e.target.value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
        e.target.value = value;
    }
});

const inputTelefone = document.querySelector('input[placeholder="Telefone"]');
inputTelefone.addEventListener('input', function (e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length <= 11) {
        value = value.replace(/(\d{2})(\d)/, '($1) $2');
        value = value.replace(/(\d{5})(\d)/, '$1-$2');
        e.target.value = value;
    }
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
        } catch (error) {
            console.error('Erro ao buscar CEP:', error);
        }
    }
});

// Submissão do formulário
document.getElementById('formEndereco').addEventListener('submit', function (e) {
    e.preventDefault();
    // Aqui você pode adicionar a lógica para enviar os dados para o servidor
    alert('Cadastro realizado com sucesso!');
});