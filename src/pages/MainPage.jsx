import MovieCard from '../Moviecard/MovieCard';
import Banner from '../components/banner/Banner.jsx';

const MainPage = () => {
    const movieData = {
        title: 'Название фильма 1',
        description: 'Описание фильма 1.',
        imageUrl: 'https://via.placeholder.com/300x400',
      };
    return (
        <>
            <Banner />
            <>
            <div className="movie-container">
                <h1>Наиболее Популярное</h1>
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
                <h1>Может быть интересно</h1>
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
            <div className="movie-container">
                <h1>Лучшие отзывы</h1>
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
        </>
    );
};

export default MainPage;
