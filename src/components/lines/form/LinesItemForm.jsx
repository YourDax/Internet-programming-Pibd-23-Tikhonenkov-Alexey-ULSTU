import PropTypes from 'prop-types';
import imgPlaceholder from '../../../assets/300x400.png';
import Input from '../../input/Input.jsx';
import Select from '../../input/Select.jsx';
import useTypes from '../../types/hooks/TypesHook';
import './LinesItemForm.css';

const LinesItemForm = ({ item, handleChange }) => {
    const { types } = useTypes();

    return (
        <>
            <div className='text-center'>
                <img id='image-preview' className='rounded' alt='placeholder'
                    src={item.image || imgPlaceholder} />
            </div>
            <Select values={types} name='typeId' label='Жанр' value={item.typeId} onChange={handleChange}
                required />
            <Input name='name' label='Название фильма' value={item.name} onChange={handleChange}
                type='text'  required />
            <Input name='rating' label='Рейтинг' value={item.rating} onChange={handleChange}
                type='number' min="0" max="10"  required />  
            <Input name='release_date' label='Дата производства' value={item.release_date} onChange={handleChange}
             type='date' required />

            <Input name='director' label='Режиссер' value={item.director} onChange={handleChange}
                type='text'  required />    
            <Input name='image' label='Изображение' onChange={handleChange}
                type='file' accept='image/*' />
                
        </>
    );
};

LinesItemForm.propTypes = {
    item: PropTypes.object,
    handleChange: PropTypes.func,
};

export default LinesItemForm;
