import PropTypes from 'prop-types';
import { Table } from 'react-bootstrap';

const LinesTable = ({ children }) => {
    return (
        <Table className='mt-2' striped responsive>
            <thead>
                <tr>
                    <th scope="col">№</th>
                    <th scope="col" className="w-25">Фильм</th>
                    <th scope="col" className="w-25">Жанр</th>
                    <th scope="col" className="w-15">Рейтинг</th>
                    <th scope="col" className="w-25">Дата производства</th>
                    <th scope="col" className="w-25">Режиссер</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                {children}
            </tbody >
        </Table >
    );
};

LinesTable.propTypes = {
    children: PropTypes.node,
};

export default LinesTable;
