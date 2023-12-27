import { useState } from 'react';
import axios from 'axios';
import MovieCard from './MovieCard.jsx';
//import Input from '../components/input/Input.jsx'; // Замените на фактический путь
import { Button } from 'react-bootstrap';

const useMovieFilter = () => {
    const [searchText, setSearchText] = useState('');
    const [searchResult, setSearchResult] = useState([]);

    const handleSearchChange = (e) => {
        setSearchText(e.target.value);
    };

    const handleSearch = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/lines?name_like=${searchText}`);
            setSearchResult(response.data);
        } catch (error) {
            console.error('Error fetching movies:', error);
        }
    };
    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };
    return (
        <>
            
            <div class="input-group mb-3">
                <input name="search "type="text" value={searchText} onChange={handleSearchChange} className="form-control" placeholder="Введите название фильма" aria-label="Search movies..."  aria-describedby="button-addon2" onKeyUp={handleKeyPress}/>
                <button class="btn btn-outline-secondary" type="button" id="button-addon2" onClick={handleSearch}>Поиск</button>
            </div>
            
            
            <div className="movie-container">
                {searchResult.map((movie) => (
                    <MovieCard key={movie.id} {...movie} />
                ))}
            </div>
        </>
    );
};

export default useMovieFilter;
