document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('userAddressForm');
    const submitButton = document.getElementById("addressContinueBtn");
    const inputs = form.querySelectorAll("input[required]:not([readonly])");

    document.getElementById('CEPInput').addEventListener('blur', function () {
        var cep = this.value.replace(/\D/g, ''); // Remove caracteres não numéricos

        if (cep.length === 8) {
            fetch('https://viacep.com.br/ws/' + cep + '/json/')
                .then(response => response.json())
                .then(data => {
                    if (!data.erro) {

                        document.getElementById('addressInput').value = data.logradouro;
                        document.getElementById('neighborhoodInput').value = data.bairro;
                        document.getElementById('cityInput').value = data.localidade;
                        document.getElementById('stateInput').value = data.uf;

                        console.log(data);
                    }
                })
                .catch(() => {
                    // alert('Erro ao buscar o CEP. Por favor, tente novamente mais tarde.');
                });
        }

        function checkFormValidity() {
            let allFilled = true;
            inputs.forEach((input) => {
                if (!input.value.trim()) {
                    allFilled = false;
                }
            });
            submitButton.disabled = !allFilled;
        }

        inputs.forEach((input) => {
            input.addEventListener("input", checkFormValidity);
        });

        checkFormValidity();
    });

    submitButton.addEventListener('click', function (event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        // Captura os valores dos campos do formulário

        const userAddressDTO = {
            identifier: document.getElementById('addressIdentifierInput').value,
            street: document.getElementById('addressInput').value,
            number: document.getElementById('numberInput').value,
            complement: document.getElementById('complementInput').value || null,
            neighborhood: document.getElementById('neighborhoodInput').value,
            city: document.getElementById('cityInput').value,
            state: document.getElementById('stateInput').value,
            zipCode: document.getElementById('CEPInput').value,
            reference: document.getElementById('referenceInput').value || null
        };

        console.log(userAddressDTO);

        // Envia o objeto na requisição
        fetch('/new-user-card', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userAddressDTO)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});