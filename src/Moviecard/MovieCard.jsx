import PropTypes from 'prop-types';

const MovieCard = ({ title, description, imageUrl }) => {
  return (
    <div className="movie-card">
      <img src={imageUrl || "https://via.placeholder.com/300x400"} alt="Movie Image" />
      <div className="movie-card-content">
        <h2>{title}</h2>
        <p>{description}</p>
      </div>
    </div>
  );
};

MovieCard.propTypes = {
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    imageUrl: PropTypes.string,
  };

export default MovieCard;
