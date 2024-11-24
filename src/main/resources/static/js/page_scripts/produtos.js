const newProductBtn = document.getElementById('newProductBtn');
const productDialog = document.getElementById('productDialog');

const productList = document.querySelector('.product-list');
const searchBar = document.querySelector('#storageSearchBar>form');

const productCard = document.querySelectorAll('.editable-product')

function filterProducts(searchValue) {
    productCard.forEach(product => {
        const productName = product.querySelector('.product-name').textContent.toLowerCase();
        if (productName.indexOf(searchValue) !== -1) {
            product.style.display = 'block';
        } else {
            product.style.display = 'none';
        }
    });
    // $('table tbody tr').each(function () {
    //     var productName = $(this).find('td:nth-child(3)').text().toLowerCase();
    //     if (productName.indexOf(searchValue) !== -1) {
    //         $(this).show();
    //     } else {
    //         $(this).hide();
    //     }
    // });
}

newProductBtn.addEventListener('click', () => {
    productDialog.showModal();
});

window.addEventListener('click', function (event) {
    if (event.target === productDialog) {
        productDialog.close();
    }
});

searchBar.addEventListener('input', function (e) {
    var searchValue = e.target.value.toLowerCase();
    filterProducts(searchValue);
});

// function confirmDelete(productId) {
//     Swal.fire({
//         title: 'Você tem certeza?',
//         text: 'Esta ação não pode ser desfeita!',
//         icon: 'warning',
//         showCancelButton: true,
//         confirmButtonText: 'Sim, remover!',
//         cancelButtonText: 'Cancelar',
//         reverseButtons: true
//     }).then((result) => {
//         if (result.isConfirmed) {
//             deleteProduct(productId);
//         }
//     });
// }

// function deleteProduct(productId) {
//     // Envia uma requisição POST para a API do Spring Boot para excluir o produto
//     $.ajax({
//         url: '/product/delete/' + productId,
//         type: 'POST',
//         success: function (response) {
//             Swal.fire(
//                 'Deletado!',
//                 'O produto foi removido com sucesso.',
//                 'success'
//             ).then(() => {
//                 location.reload();
//             });
//         },
//         error: function (error) {
//             Swal.fire(
//                 'Erro!',
//                 'Ocorreu um erro ao tentar remover o produto.',
//                 'error'
//             );
//         }
//     });
// }

// Query para filtro escrito dos produtos
