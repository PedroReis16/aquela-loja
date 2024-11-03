document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('CEPInput').addEventListener('blur', function () {
        var cep = this.value.replace(/\D/g, ''); // Remove caracteres não numéricos

        if (cep.length === 8) {
            fetch('https://viacep.com.br/ws/' + cep + '/json/')
                .then(response => response.json())
                .then(data => {
                    if (!data.erro) {
                        // Supondo que a API retorna os campos: logradouro, bairro, cidade, estado
                        document.getElementById('addressInput').value = data.logradouro;
                        document.getElementById('neighborhoodInput').value = data.bairro;
                        document.getElementById('cityInput').value = data.localidade; // Note that the correct field is 'localidade'
                        document.getElementById('stateInput').value = data.uf; // Note that the correct field is 'uf'

                        console.log(data);
                    } else {
                        alert('CEP não encontrado. Por favor, verifique e tente novamente.');
                    }
                })
                .catch(() => {
                    alert('Erro ao buscar o CEP. Por favor, tente novamente mais tarde.');
                });
        } else {
            alert('Por favor, insira um CEP válido.');
        }
    });

    // Adicione a lógica para o envio do formulário conforme necessário
    document.getElementById('addressForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar envio padrão

        // Aqui você pode processar o formulário conforme necessário
        console.log('Formulário enviado com os dados:', new FormData(this));
        // Execute sua lógica de envio de formulário
    });
});