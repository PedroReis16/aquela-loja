document.getElementById('deleteUserAccount').addEventListener('click', function () {
    document.getElementById('confirmDialog').showModal();
});

document.getElementById('cancelDelete').addEventListener('click', function () {
    document.getElementById('confirmDialog').close();
});

document.getElementById('confirmDelete').addEventListener('click', function () {
    window.location.href = '/user/delete';
});