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


// // Função genérica para controlar carrosséis
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

// // Configurar carrossel de promoções
// const promoCarousel = setupCarousel(
//     document.querySelector('.promo-carousel-inner'),
//     document.querySelector('#promo-prev'),
//     document.querySelector('#promo-next'),
//     document.querySelector('.promo-carousel-item').offsetWidth
// );

// // Configurar carrossel de produtos (4 itens visíveis por vez)
// const productsCarousel = setupCarousel(
//     document.querySelector('.products-carousel-inner'),
//     document.querySelector('#products-prev'),
//     document.querySelector('#products-next'),
//     250 + 16, // largura do card + gap
//     4
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