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
        productDialog.querySelector("#productDialogHeader>h3").textContent = "Novo Produto";
        productDialog.querySelector("#productForm").action = "/products/new-product";

        productDialog.querySelector("#productDescription").value = "";
        productDialog.querySelector("#productCategory").value = "";
        productDialog.querySelector("#productPrice").value = "";
        productDialog.querySelector("#productBrand").value = "";
        productDialog.querySelector("#productStock").value = "";
        productDialog.querySelector("#previewCardImage").src = "";
        productDialog.querySelector("#saveNewProduct").disabled = false;

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

        result.brand = result.brand.replace(' ', '_').toUpperCase();
        result.image = (productObj.match(/image=(.*)/)[1]).slice(0, -1);


        openEditProductDialog(result);
    });
});

import { openEditableDialog } from '../fragments_scripts/dialogs/product-dialog.js';

let data = {
    name: '',
    category: '',
    price: '',
    brand: '',
    stockCount: 0,
    image: ''
}

function openEditProductDialog(result) {


    productDialog.setAttribute('data-id', result.id);
    productDialog.querySelector("#productDialogHeader>h3").textContent = "Editar Produto";
    productDialog.querySelector("#productForm").action = "/products/update/" + result.id;


    // productDialog.querySelector("#productDescription").value = result.name;
    // productDialog.querySelector("#productCategory").value = result.category;
    // productDialog.querySelector("#productPrice").value = result.price;
    // productDialog.querySelector("#productBrand").value = result.brand;
    // productDialog.querySelector("#productStock").value = result.stockCount;
    // productDialog.querySelector("#previewCardImage").src = result.image;
    // productDialog.querySelector("#saveNewProduct").disabled = false;

    data.name = result.name;
    data.category = result.category;
    data.price = result.price;
    data.brand = result.brand;
    data.stockCount = result.stockCount;
    data.image = result.image;

    openEditableDialog(data);

    // productDialog.showModal();
}