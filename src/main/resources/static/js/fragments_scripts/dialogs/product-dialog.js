const productForm = document.getElementById('productForm');
const productDescription = document.getElementById('productDescription');
const productCategory = document.getElementById('productCategory');
const productPrice = document.getElementById('productPrice');
const productImage = document.getElementById('productImage');
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