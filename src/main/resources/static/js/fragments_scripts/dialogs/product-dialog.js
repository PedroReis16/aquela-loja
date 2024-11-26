const productForm = document.getElementById('productForm');
const productDescription = document.getElementById('productDescription');
const productCategory = document.getElementById('productCategory');
const productPrice = document.getElementById('productPrice');
const productImage = document.getElementById('productImage');
const productStockCount = document.getElementById('productStock');
const productBrand = document.getElementById('productBrand');

// Mensagens de erro
const descriptionError = document.getElementById('productDescriptionError');
const productCategoryError = document.getElementById('productCategoryError');
const productPriceError = document.getElementById('productPriceError');
const productBrandError = document.getElementById('productBrandError');
const productImageError = document.getElementById('productImageError');
const productStockError = document.getElementById('productStockCountError');

// Image preview
const imagePreview = document.getElementById('imagePreview');
const productCardImage = document.getElementById('previewCardImage');
const productTitle = document.getElementById('productTitle');
const productTitleTooltip = document.getElementById('productTitleTooltip');
const productPreviewPrice = document.getElementById('productPreviewPrice');
const productInstallment = document.getElementById('productInstallment');

// const Butão salvar
const saveProductBtn = document.getElementById('saveNewProduct');
const cancelProductBtn = document.getElementById('cancelNewProduct');
const dialog = document.getElementById('productDialog');
const closeDialogBtn = document.getElementById('closeNewProductDialog');

let formData = {
    name: '',
    category: '',
    price: '',
    brand: '',
    stockCount: 0,
    image: ''
}


function canSaveProduct() {
    if (formData.name && formData.category && formData.price && formData.brand && formData.stockCount > 0 && formData.image) {
        saveProductBtn.disabled = false;
        return;
    }
    saveProductBtn.disabled = true;
}

// Format price to Brazilian currency
function formatPrice(value) {
    return value.toLocaleString('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    });
}

// Image preview functionality
productImage.addEventListener('change', function (e) {
    const file = e.target.files[0];
    if (file) {
        const reader = new FileReader();

        reader.onload = function (e) {
            // Update both preview areas
            productCardImage.src = e.target.result;

            console.log(e.target.result);
            formData.image = e.target.result;
            canSaveProduct();
        }

        reader.readAsDataURL(file);
    }
});

// Live preview updates
productDescription.addEventListener('input', function (e) {
    productTitle.textContent = e.target.value;
    productTitleTooltip.textContent = e.target.value;
});

productPrice.addEventListener('input', function (e) {
    // Remove caracteres não numéricos
    e.target.value = e.target.value.replace(/[^0-9,]/g, '');


    const value = parseFloat(e.target.value.replace(',', '.')) || 0;
    const installmentValue = value / 10;

    if (value <= 0) {
        productPriceError.textContent = 'Preço inválido';
        return;
    } else {
        productPriceError.textContent = '';
    }

    productPreviewPrice.textContent = formatPrice(value);
    productInstallment.textContent = `ou 10x de ${formatPrice(installmentValue)} sem juros`;

    formData.price = value;
    canSaveProduct();
});


productDescription.addEventListener('blur', async function (e) {
    let value = e.target.value;

    const response = await fetch(`/product?description=${value}`)

    if (response.status === 204) {
        descriptionError.textContent = '';
    }
    else {
        descriptionError.textContent = 'Produto já cadastrado';
        return;
    }

    formData.name = value;
    canSaveProduct();
});

productStockCount.addEventListener('input', function (e) {
    // Remove caracteres não numéricos
    e.target.value = e.target.value.replace(/[^0-9]/g, '');


    let value = parseInt(e.target.value) || 0;

    if (value <= 0) {
        productStockError.textContent = 'Quantidade inválida';
        return;
    }
    productStockError.textContent = '';
    formData.stockCount = value;
    canSaveProduct();
});

// Inicialização dos campos ao carregar o arquivo
async function populateCategories() {
    const response = await fetch('/header/categories');
    if (response.ok) {
        const categoriesJson = await response.json();
        if (typeof categoriesJson === 'object' && categoriesJson !== null) {

            Object.entries(categoriesJson).forEach(([key, category]) => {
                const option = document.createElement('option');
                option.value = key;
                option.textContent = category.split(' ')
                    .map(word => {
                        if (word === 'TV' || word === 'SSD' || word === 'HD' || word === 'RAM' || word === 'PC' || word === 'DJ')
                            return word;
                        if (word === 'DE' || word === 'DA' || word === 'DO' || word === 'DOS' || word === 'DAS' || word === 'E' || word == 'PARA')
                            return word.toLowerCase();
                        return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
                    })
                    .join(' ');
                productCategory.appendChild(option);
            });
        } else {
            console.error('Expected an object but got:', categoriesJson);
        }
    } else {
        console.error('Failed to fetch categories:', response.statusText);
    }
}

async function populateBrands() {
    const response = await fetch('/header/brands');

    if (response.ok) {
        const brandsJson = await response.json();
        if (typeof brandsJson === 'object' && brandsJson !== null) {

            Object.entries(brandsJson).forEach(([key, brand]) => {
                const option = document.createElement('option');
                option.value = key;
                option.textContent = brand;
                productBrand.appendChild(option);
            });
        } else {
            console.error('Expected an object but got:', brandsJson);
        }
    }

}

productCategory.addEventListener('change', function (e) {
    formData.category = e.target.value;
    canSaveProduct();
});
productBrand.addEventListener('change', function (e) {
    formData.brand = e.target.value;
    canSaveProduct();
});


document.addEventListener('DOMContentLoaded', async function () {
    await populateCategories();
    await populateBrands();

    formData.category = productCategory.value;
    formData.brand = productBrand.value;

    canSaveProduct();
});

saveProductBtn.addEventListener('click', async function () {
    const response = await fetch('/product/new-product', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    });

    if (response.ok) {
        dialog.close();
        location.reload();
    } else {
        console.error('Failed to save product:', response.statusText);
    }
});

cancelProductBtn.addEventListener('click', function () {
    dialog.close();
});

closeDialogBtn.addEventListener('click', function () {
    dialog.close();
});