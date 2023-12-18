import MovieCard from '../Moviecard/MovieCard';

const Page1 = () => {
    const movieData = {
        title: 'Название фильма 1',
        description: 'Описание фильма 1.',
        imageUrl: 'https://via.placeholder.com/300x400',
      };
    return (
            <>
            <h1>Каталог</h1>
    
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
            <MovieCard {...movieData} />
            <MovieCard {...movieData} />
            </div>
            </>
    );
};

export default Page1;
