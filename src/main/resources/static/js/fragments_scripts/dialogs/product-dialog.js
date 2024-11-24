const productForm = document.getElementById('productForm');
const productDescription = document.getElementById('productDescription');
const productCategory = document.getElementById('productCategory');
const productPrice = document.getElementById('productPrice');
const productImage = document.getElementById('productImage');
const productBrand = document.getElementById('productBrand');

// Image preview
const imagePreview = document.getElementById('imagePreview');
const previewCardImage = document.getElementById('previewCardImage');
const previewTitle = document.getElementById('previewTitle');
const previewPrice = document.getElementById('previewPrice');
const previewInstallment = document.getElementById('previewInstallment');

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
            previewCardImage.src = e.target.result;
        }

        reader.readAsDataURL(file);
    }
});

// Live preview updates
productDescription.addEventListener('input', function (e) {
    previewTitle.textContent = e.target.value || 'Nome do Produto';
});

productPrice.addEventListener('input', function (e) {
    const value = parseFloat(e.target.value) || 0;
    const installmentValue = value / 10;

    previewPrice.textContent = formatPrice(value);
    previewInstallment.textContent = `ou 10x de ${formatPrice(installmentValue)} sem juros`;
});

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

document.addEventListener('DOMContentLoaded', async function () {

    await populateCategories();
    await populateBrands();
});