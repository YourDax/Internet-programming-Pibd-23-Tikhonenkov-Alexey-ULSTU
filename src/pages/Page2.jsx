import MovieCard from '../Moviecard/MovieCard';

const Page2 = () => {
    const movieData = {
        title: 'Название фильма 1',
        description: 'Описание фильма 1.',
        imageUrl: 'https://via.placeholder.com/300x400',
      };
    return (
        <>
            <div className="movie-container">
            <h1>Фантастика</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
            </div>

            <div className="movie-container">
                <h1>Фэнтези</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />

            </div>
            <div className="movie-container">
                <h1>Романтика</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
            </div>
            <div className="movie-container">
                <h1>Экшн</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
            </div>
            <div className="movie-container">
                <h1>Драма</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
            </div>
            <div className="movie-container">
                <h1>Боевик</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
            </div>
            <div className="movie-container">
                <h1>Научная фантастика</h1>
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
            </div>
        </>    
    );
};

export default Page2;
