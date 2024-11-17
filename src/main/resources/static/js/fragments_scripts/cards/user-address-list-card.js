
const addressDialog = document.getElementById('addressDialog');
const closeAddressDialogBtn = document.getElementById('closeAddressDialogBtn');
const cancelAddressBtn = document.getElementById('cancelAddressBtn');
const newAddressButton = document.getElementById('newAddressBtn');
// const addAddressButton = document.getElementById('addAddress');

//Card de endereço
const addressCard = document.querySelectorAll('.address-card');


//Validação formulários de usuário

//Ações para a dialog de endereços
newAddressButton.addEventListener('click', function () {
    addressDialog.showModal();
});

closeAddressDialogBtn.addEventListener('click', function () {
    addressDialog.close();
});

cancelAddressBtn.addEventListener('click', function () {
    addressDialog.close();
});

//Editar Card
addressCard.forEach(card => {
    const editBtn = card.querySelector("Button");

    const addressData = card.getAttribute('data-address');

    const regex = /(\w+)=([^,]*)/g;

    const result = {};
    let match;

    // Enquanto houver correspondências, adicionar ao objeto result
    while ((match = regex.exec(addressData)) !== null) {
        const key = match[1].trim();    // Nome da propriedade
        let value = match[2].trim();    // Valor da propriedade

        // Tentar converter para boolean, número ou manter string
        if (value === "true") value = true;
        else if (value === "false") value = false;
        else if (!isNaN(value)) value = Number(value);

        result[key] = value;
    }

    const cep = result.cep

    editBtn.addEventListener('click', function () {

        addressDialog.querySelector("#cepInput").value = result.cep;
        addressDialog.querySelector("#addressIdInput").value = result.addressIdentification;
        addressDialog.querySelector("#streetInput").value = result.street;
        addressDialog.querySelector("#numberInput").value = result.number;
        addressDialog.querySelector("#neighborhoodInput").value = result.neighborhood;
        addressDialog.querySelector("#cityInput").value = result.city;
        addressDialog.querySelector("#stateInput").value = result.state;
        addressDialog.querySelector("#complementInput").value = result.complement;
        addressDialog.querySelector("#referenceInput").value = result.reference;

        const addressForm = addressDialog.querySelector("#addressForm");
        addressForm.action = "";
        addressForm.object = "";

        const deleteBtn = document.getElementById('deleteAddressBtn');
        const defaultBtn = document.getElementById('defaultAddressBtn');

        defaultBtn.style.display = "block";
        deleteBtn.style.display = "block";

        addressDialog.showModal();

        addressForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const formData = new FormData(addressForm);

            formData.append("id", result.id);

            var object = {};
            formData.forEach(function (value, key) {
                object[key] = value;
            });

            fetch('/user/update-address', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(object)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                }
            });

        });

        addressForm.querySelector("#deleteAddress").addEventListener('click', function () {
            fetch(`/user/delete-address/${result.id}`, {
                method: 'POST',
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                }
            });
        });
    });
});