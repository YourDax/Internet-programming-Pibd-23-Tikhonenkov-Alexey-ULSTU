import { useState } from 'react';
import axios from 'axios';
import MovieCard from './MovieCard.jsx';
import Input from '../components/input/Input.jsx'; // Замените на фактический путь
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
            <Input
                name="search"
                label="Search movies..."
                value={searchText}
                onChange={handleSearchChange}
                type="text"
                placeholder="Search movies..."
                onKeyPress={handleKeyPress}
            />
            <Button className='col-5 col-lg-2 m-0 ms-2' type='submit' variant='primary' onClick={handleSearch}>
                Поиск
            </Button>
            
            <div className="movie-container">
                {searchResult.map((movie) => (
                    <MovieCard key={movie.id} {...movie} />
                ))}
            </div>
        </>
    );
};

export default useMovieFilter;
