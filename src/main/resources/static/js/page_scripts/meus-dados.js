//Dialog de confirmação de exclusão de conta
const accountDeleteDialog = document.getElementById('confirmDialog');
const deleteButton = document.getElementById('deleteUserAccount');
const cancelButton = document.getElementById('cancelDelete');
const confirmButton = document.getElementById('confirmDelete');
const closeDialog = document.getElementById('closeDialog');

//Dialog de adição ou edição do endereço
const addressDialog = document.getElementById('addressDialog');
const closeAddressDialogBtn = document.getElementById('closeAddressDialogBtn');
const cancelAddressBtn = document.getElementById('cancelAddressBtn');
const newAddressButton = document.getElementById('newAddressBtn');
// const addAddressButton = document.getElementById('addAddress');


//Dialog de confirmação de exclusão de conta
deleteButton.addEventListener('click', function () {
    accountDeleteDialog.showModal();
});

closeDialog.addEventListener('click', function () {
    accountDeleteDialog.close();
});

cancelButton.addEventListener('click', function () {
    accountDeleteDialog.close();
});

confirmButton.addEventListener('click', function () {
    window.location.href = '/user/delete';
});

//Dialog de adição ou edição do endereço
newAddressButton.addEventListener('click', function () {
    addressDialog.showModal();
});

closeAddressDialogBtn.addEventListener('click', function () {
    addressDialog.close();
});

cancelAddressBtn.addEventListener('click', function () {
    addressDialog.close();
});

window.addEventListener('click', function (event) {
    if (event.target === accountDeleteDialog) {
        accountDeleteDialog.close();
    }
    if (event.target === addressDialog) {
        addressDialog.close();
    }
});