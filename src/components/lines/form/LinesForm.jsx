import PropTypes from 'prop-types';
import { Button, Form } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import useLinesItemForm from '../hooks/LinesItemFormHook';
import LinesItemForm from './LinesItemForm.jsx';

const LinesForm = ({ id }) => {
    const navigate = useNavigate();

    const {
        item,
        validated,
        handleSubmit,
        handleChange,
    } = useLinesItemForm(id);

    const onBack = () => {
        navigate(-1);
    };

    const onSubmit = async (event) => {
        if (await handleSubmit(event)) {
            onBack();
        }
    };

    return (
        <>
            <Form className='m-0 p-2' noValidate validated={validated} onSubmit={onSubmit}>
                <LinesItemForm item={item} handleChange={handleChange} />
                <Form.Group className='row justify-content-center m-0 mt-3'>
                    <Button className='col-5 col-lg-2 m-0 me-2' variant='secondary' onClick={() => onBack()}>
                        Назад
                    </Button>
                    <Button className='col-5 col-lg-2 m-0 ms-2' type='submit' variant='primary'>
                        Сохранить
                    </Button>
                </Form.Group>
            </Form>
        </>
    );
};

LinesForm.propTypes = {
    id: PropTypes.string,
};

export default LinesForm;
