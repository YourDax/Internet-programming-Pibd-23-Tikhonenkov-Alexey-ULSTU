import MovieCard from './MovieCard.jsx';
import useTypes from '../components/types/hooks/TypesHook.js'; 
import useLines from '../components/lines/hooks/LinesHook.js';

const useMovieFilter = () => {
    const { types } = useTypes();
    const { lines } = useLines();

    console.log("All lines:", lines);

    const filterMoviesByType = (typeId) => {
        const filteredMovies = lines.filter((line) => line.typeId === typeId);
        console.log(`Type ID: ${typeId}, Filtered Movies:`, filteredMovies);
        return filteredMovies;
    };

    return (
        <>
            {types.map((type) => (
                <div className="movie-container" key={type.id}>
                    <h1>{type.name}</h1>
                    {filterMoviesByType(type.id.toString()).map((filteredMovie) => (
                        <MovieCard key={filteredMovie.id} {...filteredMovie} />
                    ))}
                </div>
            ))}
        </>
    );
};



export default useMovieFilter;
