function confirmDelete(productId) {
    Swal.fire({
        title: 'Você tem certeza?',
        text: 'Esta ação não pode ser desfeita!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sim, remover!',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            deleteProduct(productId);
        }
    });
}

function deleteProduct(productId) {
    // Envia uma requisição POST para a API do Spring Boot para excluir o produto
    $.ajax({
        url: '/product/delete/' + productId,
        type: 'POST',
        success: function (response) {
            Swal.fire(
                'Deletado!',
                'O produto foi removido com sucesso.',
                'success'
            ).then(() => {
                location.reload();
            });
        },
        error: function (error) {
            Swal.fire(
                'Erro!',
                'Ocorreu um erro ao tentar remover o produto.',
                'error'
            );
        }
    });
}

$(document).ready(function () {
    $('#searchInput').on('input', function () {
        var searchValue = $(this).val().toLowerCase();
        filterProducts(searchValue);
    });

    function filterProducts(searchValue) {
        $('table tbody tr').each(function () {
            var productName = $(this).find('td:nth-child(3)').text().toLowerCase();
            if (productName.indexOf(searchValue) !== -1) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }
});