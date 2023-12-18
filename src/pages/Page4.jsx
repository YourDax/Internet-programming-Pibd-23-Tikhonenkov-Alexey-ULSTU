import MovieCard from '../Moviecard/MovieCard';

const Page4 = () => {
    const movieData = {
        title: 'Название фильма 1',
        description: 'Описание фильма 1.',
        imageUrl: 'https://via.placeholder.com/300x400',
      };
    return (
        <>
            <h1>Топ фильмов</h1>
            <div id="movie-container">
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
                <MovieCard {...movieData} />
                <MovieCard {...movieData} />
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

export default Page4;
