
const deleteDialog = document.getElementById('deleteUserDialog');
const cancelButton = document.getElementById('cancelDelete');
const confirmButton = document.getElementById('confirmDelete');
const closeDialog = document.getElementById('closeDialog');

//Dialog de confirmação de exclusão de conta

closeDialog.addEventListener('click', function () {
    deleteDialog.close();
});

cancelButton.addEventListener('click', function () {
    deleteDialog.close();
});

confirmButton.addEventListener('click', function () {
    window.location.href = '/user/delete';
});

