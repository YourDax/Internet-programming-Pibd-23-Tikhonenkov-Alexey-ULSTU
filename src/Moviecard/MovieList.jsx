// MovieList.jsx

import MovieCard from './MovieCard.jsx';
import useLines from '../components/lines/hooks/LinesHook.js';

const MovieList = () => {
    // Вызовите useLines без фильтра, чтобы получить все фильмы
    const { lines } = useLines();

    

    return (
        <div>
            
                {lines.map((movieData) => (
                    <MovieCard key={movieData.id} {...movieData} />
                ))}
            

            
        </div>
    );
};

export default MovieList;
