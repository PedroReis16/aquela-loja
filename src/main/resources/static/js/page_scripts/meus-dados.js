
const accountDeleteDialog = document.getElementById('deleteUserDialog');

//Dialog de adição ou edição do endereço
const addressDialog = document.getElementById('addressDialog');

window.addEventListener('click', function (event) {
    if (event.target === accountDeleteDialog) {
        accountDeleteDialog.close();
    }
    if (event.target === addressDialog) {
        addressDialog.querySelectorAll('input').forEach(input => { input.value = ""; });
        addressDialog.close();
    }
});