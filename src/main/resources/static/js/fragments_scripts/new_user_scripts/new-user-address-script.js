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
                    } else {
                        alert('CEP não encontrado. Por favor, verifique e tente novamente.');
                    }
                })
                .catch(() => {
                    alert('Erro ao buscar o CEP. Por favor, tente novamente mais tarde.');
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


});