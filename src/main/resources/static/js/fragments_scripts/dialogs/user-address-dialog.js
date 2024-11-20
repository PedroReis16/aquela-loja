import { initializeAddressForm, addAddressValidityObserver } from '../user-address-form.js';

const submitBtn = document.getElementById('saveAddressBtn');
const defaultBtn = document.getElementById('defaultAddressBtn');
const deleteBtn = document.getElementById('deleteAddressBtn');


document.addEventListener('DOMContentLoaded', () => {

    initializeAddressForm();

    addAddressValidityObserver(function (isValid) {
        submitBtn.disabled = !isValid;
    });
});

function getAddressId() {
    const id = document.getElementById("addressForm").getAttribute('data-address-id');
    return id;
}

defaultBtn.addEventListener('click', function () {

    fetch('/user/update-address/' + getAddressId(), {
        method: 'POST',
    });
});

deleteBtn.addEventListener('click', function () {
    const id = getAddressId();

    fetch(`/user/delete-address/${getAddressId()}`, {
        method: 'POST',
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        }
    });
});
