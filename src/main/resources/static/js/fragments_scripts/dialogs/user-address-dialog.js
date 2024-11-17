import { maskCPF, maskCEP, maskPhone } from '../../helpers/masks.js';

import {  initializeAddressForm, addAddressValidityObserver } from '../user-address-form.js';

const submitBtn = document.getElementById('saveAddressBtn');

document.addEventListener('DOMContentLoaded', () => {
    submitBtn.disabled = true;
    initializeAddressForm();

    addAddressValidityObserver(function (isValid) {
        submitBtn.disabled = !isValid;
    });
});