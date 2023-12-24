import MovieList from '../Moviecard/MovieList.jsx';
import Banner from '../components/banner/Banner.jsx';

const MainPage = () => {
    return (
        <>
            <Banner />
            <h1>Популярное</h1>
            <div className="movie-container">
                    <MovieList />
            </div>
            <h1>Может быть интересно</h1>
            <div className="movie-container">
                    <MovieList />
            </div>

            <h1>Лучшие отзывы</h1>
            <div className="movie-container">
                    <MovieList />   
            </div>
            
            
            
        </>
    );
};

export default MainPage;
