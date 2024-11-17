import { getAddressByCEP } from '../helpers/helpers.js';

const address = {
    cep: "",
    street: "",
    neighborhood: "",
    city: "",
    state: "",
    addressId: "",
    number: ""
};

let addressValidityObservers = [];

function checkAddressFormValidity() {
    console.log("form validation");
    const isValid = address.cep && address.street &&
        address.number && address.neighborhood &&
        address.city &&
        address.state && address.addressId;

    notifyAddressValidityObservers(isValid);
    return isValid;
}

export function addAddressValidityObserver(observer) {
    addressValidityObservers.push(observer);
}

function notifyAddressValidityObservers(isValid) {
    addressValidityObservers.forEach(observer => observer(isValid));
}

export function initializeAddressForm() {
    const inputCEP = document.getElementById('cepInput');
    const identificationInput = document.getElementById('addressIdInput');
    const numberInput = document.getElementById('numberInput');

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
                const data = await getAddressByCEP(cep);

                const streetInput = document.getElementById('streetInput');
                const neighborhoodInput = document.getElementById('neighborhoodInput');
                const cityInput = document.getElementById('cityInput');
                const stateInput = document.getElementById('stateInput');

                if (!data.erro) {
                    streetInput.value = data.logradouro;
                    neighborhoodInput.value = data.bairro;
                    cityInput.value = data.localidade;
                    stateInput.value = data.uf;

                    address.cep = data.cep;
                    address.street = data.logradouro;
                    address.neighborhood = data.bairro;
                    address.city = data.localidade;
                    address.state = data.uf;
                } else {
                    streetInput.value =
                        neighborhoodInput.value =
                        cityInput.value =
                        stateInput.value = '';
                }
            } catch (error) {
                console.error('Erro ao buscar CEP:', error);
            }
        }
        checkAddressFormValidity();
    });

    // Valida identificação
    identificationInput.addEventListener('blur', function (e) {
        address.addressId = e.target.value;
        checkAddressFormValidity();
    });

    // Valida número
    numberInput.addEventListener('blur', function (e) {
        address.number = e.target.value;
        checkAddressFormValidity();
    });
}