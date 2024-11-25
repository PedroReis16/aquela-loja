const productCard = document.querySelectorAll('.product-card');


productCard.forEach(card => {
    const buyBtn = card.querySelector('.confirm-button');
    const productPrice = card.querySelector('.product-card-price');
    const price = parseFloat(productPrice.innerText.replace('R$', '').replace(',', '.'));
    productPrice.innerText = price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });

    const installment = card.querySelector('.product-card-installment');
    installment.innerText = `ou 10x de ${(price / 10).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}`;

    buyBtn.addEventListener('click', (e) => {
        e.preventDefault();

        const productObj = buyBtn.getAttribute('data-product');


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
        addToCart(result.id);
    });


});


function addToCart(productId) {
    $.ajax({
        url: '/add-Cart/' + productId,
        type: 'POST',
        success: function (response) {
            Swal.fire(
                'Adicionado!',
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