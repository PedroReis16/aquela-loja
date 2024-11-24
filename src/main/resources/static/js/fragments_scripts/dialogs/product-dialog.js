const productForm = document.getElementById('productForm');
const productName = document.getElementById('productName');
const category = document.getElementById('category');
const price = document.getElementById('price');
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
            imagePreview.innerHTML = `<img src="${e.target.result}" alt="Preview">`;
            previewCardImage.src = e.target.result;
        }

        reader.readAsDataURL(file);
    }
});

// Live preview updates
productName.addEventListener('input', function (e) {
    previewTitle.textContent = e.target.value || 'Nome do Produto';
});

price.addEventListener('input', function (e) {
    const value = parseFloat(e.target.value) || 0;
    const installmentValue = value / 10;

    previewPrice.textContent = formatPrice(value);
    previewInstallment.textContent = `ou 10x de ${formatPrice(installmentValue)} sem juros`;
});