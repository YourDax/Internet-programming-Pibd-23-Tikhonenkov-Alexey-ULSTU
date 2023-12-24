import { Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import Select from '../../input/Select.jsx';
import ModalConfirm from '../../modal/ModalConfirm.jsx';
import ModalForm from '../../modal/ModalForm.jsx';
import LinesItemForm from '../form/LinesItemForm.jsx';
import useLinesDeleteModal from '../hooks/LinesDeleteModalHook';
import useTypeFilter from '../hooks/LinesFilterHook';
import useLinesFormModal from '../hooks/LinesFormModalHook';
import useLines from '../hooks/LinesHook';
import LinesTable from './LinesTable.jsx';
import LinesTableRow from './LinesTableRow.jsx';

const Lines = () => {
    const { types, currentFilter, handleFilterChange } = useTypeFilter();

    const { lines, handleLinesChange } = useLines(currentFilter);

    const {
        isDeleteModalShow,
        showDeleteModal,
        handleDeleteConfirm,
        handleDeleteCancel,
    } = useLinesDeleteModal(handleLinesChange);

    const {
        isFormModalShow,
        isFormValidated,
        showFormModal,
        currentItem,
        handleItemChange,
        handleFormSubmit,
        handleFormClose,
    } = useLinesFormModal(handleLinesChange);

    const navigate = useNavigate();

    const showEditPage = (id) => {
        navigate(`/page-edit/${id}`);
    };

    return (
        <>
            <Button as={Link} to='/page-edit' variant='success'>
                Добавить фильм
            </Button>   
            <Select className='mt-2' values={types} label='Фильтр по жанрам'
                value={currentFilter} onChange={handleFilterChange} />
            <LinesTable>
                {
                    lines.map((line, index) =>
                        <LinesTableRow key={line.id}
                            index={index} line={line}
                            onDelete={() => showDeleteModal(line.id)}
                            onEdit={() => showFormModal(line.id)}
                            onEditInPage={() => showEditPage(line.id)}
                        />)
                }
            </LinesTable>
            <ModalConfirm show={isDeleteModalShow}
                onConfirm={handleDeleteConfirm} onClose={handleDeleteCancel}
                title='Удаление' message='Удалить элемент?' />
            <ModalForm show={isFormModalShow} validated={isFormValidated}
                onSubmit={handleFormSubmit} onClose={handleFormClose}
                title='Редактирование'>
                <LinesItemForm item={currentItem} handleChange={handleItemChange} />
            </ModalForm>
        </>
    );
};

export default Lines;
