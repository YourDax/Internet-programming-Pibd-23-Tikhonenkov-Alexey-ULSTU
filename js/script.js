
const movieCards = document.querySelectorAll('.movie-card');

// Добавляем обработчик события нажатия для каждой карточки фильма
movieCards.forEach(card => {
    const link = card.querySelector('.movie-link');
    const kinopoiskId = card.dataset.kinopoiskId;

    card.addEventListener('click', () => {
        // Формируем новый URL с использованием ID Кинопоиска
        const redirectUrl = `https://kinopoisk-watch-dsze5.ondigitalocean.app/player/?id=${kinopoiskId}`;

        // Перенаправляем пользователя на полученный URL
        window.location.href = redirectUrl;
    });
});
$(document).ready(function () {
    var url = window.location.href;
    $('.navbar-nav a').filter(function () {
        return this.href == url;
    }).addClass('active');
});

