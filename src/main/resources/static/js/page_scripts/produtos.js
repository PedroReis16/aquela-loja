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

//Query para editar o produto selecionado

productCard.forEach(product => {
    const editBtn = product.querySelector('.product-edit-btn');

    editBtn.addEventListener('click', (e) => {
        e.preventDefault();

        const productObj = editBtn.getAttribute('data-product');
        
        const regex = /(\w+)=([^,]*)/g;

        const result = {};
        let match;

        // Enquanto houver correspondências, adicionar ao objeto result
        while ((match = regex.exec(productObj)) !== null) {
            const key = match[1].trim();    // Nome da propriedade
            let value = match[2].trim();    // Valor da propriedade

            // Tentar converter para boolean, número ou manter string
            if (value === "true") value = true;
            else if (value === "false") value = false;
            else if (!isNaN(value)) value = Number(value);

            result[key] = value;
        }
        openEditProductDialog(result);
    });
});

function openEditProductDialog(result) {
    productDialog.querySelector("#productDialogHeader>h3").textContent = "Editar Produto";
    productDialog.querySelector("#productForm").action = "/products/update/" + result.id;

    productDialog.querySelector("#productDescription").value = result.description;
    productDialog.querySelector("#productCategory").value = result.category;
    productDialog.querySelector("#productPrice").value = result.price;
    productDialog.querySelector("#productBrand").value = result.brand;
    productDialog.querySelector("#productStock").value = result.stockCount;
    productDialog.querySelector("#productCardImage").value = result.image;

    productDialog.showModal();
}