const productForm = document.getElementById('productForm');
const productDescription = document.getElementById('productDescription');
const productCategory = document.getElementById('productCategory');
const productPrice = document.getElementById('productPrice');
const productImage = document.getElementById('productImage');
const productBrand = document.getElementById('productBrand');

// Image preview
const imagePreview = document.getElementById('imagePreview');
const productCardImage = document.getElementById('productCardImage');
const productTitle = document.getElementById('productTitle');
const productTitleTooltip = document.getElementById('productTitleTooltip');
const productPreviewPrice = document.getElementById('productPreviewPrice');
const productInstallment = document.getElementById('productInstallment');

// const Butão salvar
const saveProductBtn = document.getElementById('saveNewProduct');
const dialog = document.getElementById('productDialog');
const closeDialogBtn = document.getElementById('closeNewProductDialog');

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
        }

        reader.readAsDataURL(file);
    }
});

// Live preview updates
productDescription.addEventListener('input', function (e) {
    productTitle.textContent = e.target.value || 'Nome do Produto';
    productTitleTooltip.textContent = e.target.value || 'Nome do Produto';
});

productPrice.addEventListener('input', function (e) {
    const value = parseFloat(e.target.value) || 0;
    const installmentValue = value / 10;

    productPreviewPrice.textContent = formatPrice(value);
    productInstallment.textContent = `ou 10x de ${formatPrice(installmentValue)} sem juros`;
});

const descriptionError = document.getElementById('productDescriptionError');
productDescription.addEventListener('blur', async function (e) {
    let value = e.target.value;

    const response = await fetch(`/product?description=${value}`)

    if (response.noContent) {
        descriptionError.textContent = '';
    }
    else {
        descriptionError.textContent = 'Produto já cadastrado';
    }
});


const productCategoryError = document.getElementById('productCategoryError');
const productPriceError = document.getElementById('productPriceError');
const productBrandError = document.getElementById('productBrandError');
const productImageError = document.getElementById('productImageError');


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
function validateFields() {
    if (productDescription.value === '' || productPrice.value === '' || productPrice <= 0 || productImage.value === '') {
        saveProductBtn.disabled = true;
    }
    return saveProductBtn.disabled = false;
}



document.addEventListener('DOMContentLoaded', async function () {

    await populateCategories();
    await populateBrands();

});

closeDialogBtn.addEventListener('click', function () {
    dialog.close();
});