import { Button, ButtonGroup, Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const AdminPage = () => {
    return (
        <>
            <h1 className="text-Gray text-center font-weight-bold">Панель администратора</h1>
           <div className="d-flex justify-content-center">
            <ButtonGroup>
                <Button as={Link} to="/page-edit" variant="secondary">Добавить фильм</Button>
            </ButtonGroup>
            </div>
            <h2 className="text-Gray text-center font-weight-bold">Таблица данных</h2>
            <Table className="mt-2" striped>
                
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
                <tbody></tbody>
            </Table>
        </>
    );
};

export default AdminPage;
