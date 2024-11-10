const accountDeleteDialog = document.getElementById('confirmDialog');
const deleteButton = document.getElementById('deleteUserAccount');
const cancelButton = document.getElementById('cancelDelete');
const confirmButton = document.getElementById('confirmDelete');
const closeDialog = document.getElementById('closeDialog');


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

window.addEventListener('click', function (event) {
    if (event.target === accountDeleteDialog) {
        accountDeleteDialog.close();
    }
});