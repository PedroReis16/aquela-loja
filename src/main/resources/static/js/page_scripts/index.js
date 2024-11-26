const productCard = document.querySelectorAll('.product-card');

document.addEventListener('DOMContentLoaded', function () {
    const carouselInner = document.querySelector('.promo-carousel-inner');
    const items = document.querySelectorAll('.promo-carousel-item');
    const prevButton = document.getElementById('promo-prev');
    const nextButton = document.getElementById('promo-next');
    let currentIndex = 0;

    function showItem(index) {
        carouselInner.style.transform = `translateX(-${index * 100}%)`;
    }

    function showNextItem() {
        currentIndex = (currentIndex + 1) % items.length;
        showItem(currentIndex);
    }

    function showPrevItem() {
        currentIndex = (currentIndex - 1 + items.length) % items.length;
        showItem(currentIndex);
    }

    nextButton.addEventListener('click', showNextItem);
    prevButton.addEventListener('click', showPrevItem);

    // Optional: Auto-rotate the carousel every 3 seconds
    setInterval(showNextItem, 3000);

});

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

    const carousel = document.querySelector('.carousel');
    const prevButton = document.querySelector('.carousel-button.prev');
    const nextButton = document.querySelector('.carousel-button.next');
    const cards = document.querySelectorAll('.product-card');

    let currentIndex = 0;

    function updateCarousel() {
        const isMobile = window.innerWidth <= 768;
        const cardWidth = isMobile
            ? carousel.querySelector('.product-card').offsetWidth
            : 350; // Desktop card width + margin
        const visibleCards = isMobile ? 1 : 4;
        const maxIndex = cards.length - visibleCards;

        carousel.style.transform = `translateX(-${currentIndex * cardWidth}px)`;

        // Update button states
        prevButton.disabled = currentIndex === 0;
        nextButton.disabled = currentIndex >= maxIndex;
    }

    // Update on window resize
    window.addEventListener('resize', updateCarousel);

    prevButton.addEventListener('click', () => {
        if (currentIndex > 0) {
            currentIndex--;
            updateCarousel();
        }
    });

    nextButton.addEventListener('click', () => {
        const isMobile = window.innerWidth <= 768;
        const visibleCards = isMobile ? 1 : 4;
        const maxIndex = cards.length - visibleCards;

        if (currentIndex < maxIndex) {
            currentIndex++;
            updateCarousel();
        }
    });

    // Touch support
    let touchStartX = 0;
    let touchEndX = 0;

    carousel.addEventListener('touchstart', e => {
        touchStartX = e.touches[0].clientX;
    });

    carousel.addEventListener('touchmove', e => {
        touchEndX = e.touches[0].clientX;
    });

    carousel.addEventListener('touchend', () => {
        const difference = touchStartX - touchEndX;
        if (Math.abs(difference) > 50) {
            const isMobile = window.innerWidth <= 768;
            const visibleCards = isMobile ? 1 : 4;
            const maxIndex = cards.length - visibleCards;

            if (difference > 0 && currentIndex < maxIndex) {
                currentIndex++;
            } else if (difference < 0 && currentIndex > 0) {
                currentIndex--;
            }
            updateCarousel();
        }
    });

    // Initial setup
    updateCarousel();
});


function addToCart(productId) {
    $.ajax({
        url: '/add-Cart/' + productId,
        type: 'POST',
        success: function (response) {
            Swal.fire({
              title: 'Produto Adicionado!',
              text: 'Veja no carrinho.',
              icon: 'success',
              timer: 2000,
              showConfirmButton: false,
              toast: true,
              position: 'top-end',
            }).then(() => {
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
// Função genérica para controlar carrosséis
// function setupCarousel(carouselInner, prevBtn, nextBtn, itemWidth, itemsToShow = 1) {
//     let currentIndex = 0;
//     const items = carouselInner.children;
//     const maxIndex = Math.max(0, items.length - itemsToShow);

//     function updateCarousel() {
//         const offset = itemWidth * currentIndex;
//         carouselInner.style.transform = `translateX(-${offset}px)`;
//     }

//     prevBtn.addEventListener('click', () => {
//         currentIndex = Math.max(currentIndex - 1, 0);
//         updateCarousel();
//     });

//     nextBtn.addEventListener('click', () => {
//         currentIndex = Math.min(currentIndex + 1, maxIndex);
//         updateCarousel();
//     });

//     return updateCarousel;
// }


// // Configurar carrossel de produtos (4 itens visíveis por vez)
// const productsCarousel = setupCarousel(
//     document.querySelector('.products-carousel-inner'),
//     document.querySelector('#products-prev'),
//     document.querySelector('#products-next'),
//     250 + 16, // largura do card + gap
//     5
// );

// // Autoplay do carrossel de promoções
// setInterval(() => {
//     const promoInner = document.querySelector('.promo-carousel-inner');
//     const items = promoInner.children;
//     let currentIndex = parseInt(promoInner.style.transform.replace(/[^0-9-]/g, '')) || 0;
//     currentIndex = (Math.abs(currentIndex) / items[0].offsetWidth + 1) % items.length;
//     promoInner.style.transform = `translateX(-${currentIndex * 100}%)`;
// }, 5000);

// // Interatividade das tabs de categoria
// const categoryTabs = document.querySelectorAll('.category-tab');
// categoryTabs.forEach(tab => {
//     tab.addEventListener('click', () => {
//         categoryTabs.forEach(t => t.classList.remove('active'));
//         tab.classList.add('active');
//         // Aqui você pode adicionar lógica para filtrar os produtos por categoria
//     });
// });