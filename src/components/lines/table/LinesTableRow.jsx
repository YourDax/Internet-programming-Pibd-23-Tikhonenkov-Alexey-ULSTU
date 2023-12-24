import PropTypes from 'prop-types';
import { PencilFill, PencilSquare, Trash3 } from 'react-bootstrap-icons';

const LinesTableRow = ({
    index, line, onDelete, onEdit, onEditInPage,
}) => {
    const handleAnchorClick = (event, action) => {
        event.preventDefault();
        action();
    };

    return (
        <tr>
            <th scope="row">{index + 1}</th>
            <td>{line.name}</td>
            <td>{line.type.name}</td>
            <td>{parseInt(line.rating)}</td>
            <td>{line.release_date}</td>
            <td>{line.director}</td>
            
            <td><a href="#" onClick={(event) => handleAnchorClick(event, onEdit)}><PencilFill /></a></td>
            <td><a href="#" onClick={(event) => handleAnchorClick(event, onEditInPage)}><PencilSquare /></a></td>
            <td><a href="#" onClick={(event) => handleAnchorClick(event, onDelete)}><Trash3 /></a></td>
        </tr>
    );
};

LinesTableRow.propTypes = {
    index: PropTypes.number,
    line: PropTypes.object,
    onDelete: PropTypes.func,
    onEdit: PropTypes.func,
    onEditInPage: PropTypes.func,
};

export default LinesTableRow;
