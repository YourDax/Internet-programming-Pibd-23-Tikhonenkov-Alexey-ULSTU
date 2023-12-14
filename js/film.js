// film.js

import { getAllLines } from './lines-rest-api.js';

async function loadMovies() {
    try {
        const moviesData = await getAllLines();
        addMovieCards(moviesData);
    } catch (error) {
        console.error("Error loading movies:", error);
    }
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

document.addEventListener('DOMContentLoaded', loadMovies);
