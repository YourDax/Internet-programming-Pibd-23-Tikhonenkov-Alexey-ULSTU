import PropTypes from 'prop-types';

const MovieCard = ({ name, rating, image }) => {
  return (
    <div className="movie-card">
      <img src={image || "https://via.placeholder.com/300x400"} alt="Movie Image" />
      <div className="movie-card-content">
        <h2>{name}</h2>
        <p>Рейтинг: {rating}</p>
      </div>
    </div>
  );
};

MovieCard.propTypes = {
    name: PropTypes.string.isRequired,
    rating: PropTypes.string.isRequired,
    image: PropTypes.string,
  };

export default MovieCard;
