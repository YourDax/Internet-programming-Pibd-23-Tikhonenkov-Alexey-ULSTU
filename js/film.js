// film.js

import { getAllLines, getAllItemTypes } from './lines-rest-api.js';

async function loadMovies() {
    try {
        const moviesData = await getAllLines();
        addMovieCards(moviesData);
    } catch (error) {
        console.error("Error loading movies:", error);
    }
}
async function loadMoviesindex() {
    try {
        const moviesData = await getAllLines();
        addMovieCardsindex(moviesData);
    } catch (error) {
        console.error("Error loading movies:", error);
    }
}
async function loadMoviesBygenre() {
    try {
        const moviesData = await getAllLines();
        
        const itemTypes = await getAllItemTypes();

        addMoviesByGenre(moviesData, itemTypes);
    } catch (error) {
        console.error("Error loading movies:", error);
    }
}
function addMoviesByGenre(moviesData, itemTypes) {
    var containers = document.querySelectorAll(".movie-container");

    containers.forEach(function (container) {
        var genre = container.querySelector("h1").textContent;

        // Находим все фильмы для данного жанра
        var genreMovies = moviesData.filter(function (movie) {
            var itemType = itemTypes[movie.itemsId-1];

            if (itemType && itemType.genre === genre) {
                return true;
            } else {
                console.error("Invalid itemTypes for movie:", movie);
                console.log("itemType:", itemType);
                console.log("genre:", genre);
                return false;
            }
        });

        genreMovies.forEach(function (movie) {
            addMovieCardsByContainer(container, movie);
        });
    });
}

function addMovieCardsByContainer(container,movie) {
    var container = container

    if (!container) {
        console.error("Movie container not found");
        return;""
    }

    
    var movieCard = document.createElement("div");
    movieCard.className = "movie-card";

    var image = document.createElement("img");
    // Проверяем, есть ли у фильма изображение, и вставляем соответствующее изображение
    image.src = movie.image ? movie.image : "https://via.placeholder.com/300x400";
    image.alt = "Movie Image";

    var content = document.createElement("div");
    content.className = "movie-card-content";

    var title = document.createElement("h2");
    title.textContent = movie.name;

    var description = document.createElement("p");
    description.textContent = "Рейтинг: " + movie.rating;

    content.appendChild(title);
    content.appendChild(description);

    movieCard.appendChild(image);
    movieCard.appendChild(content);

    container.appendChild(movieCard);
    
}
function addMovieCards(moviesData) {
    var container = document.getElementById("movie-container");

    if (!container) {
        console.error("Movie container not found");
        return;
    }

    moviesData.forEach(function (movie) {
        var movieCard = document.createElement("div");
        movieCard.className = "movie-card";

        var image = document.createElement("img");
        // Проверяем, есть ли у фильма изображение, и вставляем соответствующее изображение
        image.src = movie.image ? movie.image : "https://via.placeholder.com/300x400";
        image.alt = "Movie Image";

        var content = document.createElement("div");
        content.className = "movie-card-content";

        var title = document.createElement("h2");
        title.textContent = movie.name;

        var description = document.createElement("p");
        description.textContent = "Рейтинг: " + movie.rating;

        content.appendChild(title);
        content.appendChild(description);

        movieCard.appendChild(image);
        movieCard.appendChild(content);

        container.appendChild(movieCard);
    });
}
function addMovieCardsindex(moviesData) {
    var containers = document.querySelectorAll(".movie-container");

    if (!containers || containers.length === 0) {
        console.error("Movie containers not found");
        return;
    }

    containers.forEach(function (container) {
        moviesData.forEach(function (movie) {
            var movieCard = document.createElement("div");
            movieCard.className = "movie-card";

            var image = document.createElement("img");
            // Проверяем, есть ли у фильма изображение, и вставляем соответствующее изображение
            image.src = movie.image ? movie.image : "https://via.placeholder.com/300x400";
            image.alt = "Movie Image";

            var content = document.createElement("div");
            content.className = "movie-card-content";

            var title = document.createElement("h2");
            title.textContent = movie.name;

            var description = document.createElement("p");
            description.textContent = "Рейтинг: " + movie.rating;

            content.appendChild(title);
            content.appendChild(description);

            movieCard.appendChild(image);
            movieCard.appendChild(content);

            container.appendChild(movieCard);
        });
    });
}


document.addEventListener('DOMContentLoaded', function () {
    if (window.location.pathname.includes("page2.html")) {
        // Запуск функции для page2.html
        loadMovies();
    }else if (window.location.pathname.includes("page5.html")) {
        // Запуск функции для page5.html
        loadMovies();
    }else if (window.location.pathname.includes("page3.html")) {
        // Запуск функции для page3.html
        loadMoviesBygenre();
    }else if (window.location.pathname.includes("index.html")) {
        // Запуск функции для index.html
        loadMoviesindex();
    }
});
