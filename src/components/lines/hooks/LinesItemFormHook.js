import { useState } from 'react';
import toast from 'react-hot-toast';
import getBase64FromFile from '../../utils/Base64';
import LinesApiService from '../service/LinesApiService';
import useLinesItem from './LinesItemHook';

const useLinesItemForm = (id, linesChangeHandle) => {
    const { item, setItem } = useLinesItem(id);

    const [validated, setValidated] = useState(false);

    const resetValidity = () => {
        setValidated(false);
    };

    const getLineObject = (formData) => {
        const typeId = formData.typeId;
        const name = formData.name;
        const rating = formData.rating;
        const release_date = formData.release_date;
        const director = formData.director;
        const image = formData.image.startsWith('data:image') ? formData.image : '';
        return {
            typeId: typeId,
            name: name,
            rating: rating,
            release_date: release_date,
            director: director,
            image,
        };
    };

    const handleImageChange = async (event) => {
        const { files } = event.target;
        const file = await getBase64FromFile(files.item(0));
        setItem({
            ...item,
            image: file,
        });
    };

    const handleChange = (event) => {
        
        if (event.target.type === 'file') {
            handleImageChange(event);
            return;
        }
        const inputName = event.target.name;
        let inputValue = event.target.type === 'checkbox' ? event.target.checked : event.target.value;
        console.log(inputName, inputValue);
        

        setItem({
            ...item,
            [inputName]: inputValue,
        });
    };

    

    

    const handleSubmit = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        event.stopPropagation();
        const body = getLineObject(item);
        if (form.checkValidity()) {
            if (id === undefined) {
                await LinesApiService.create(body);
            } else {
                await LinesApiService.update(id, body);
            }
            if (linesChangeHandle) linesChangeHandle();
            toast.success('Элемент успешно сохранен', { id: 'LinesTable' });
            return true;
        }
        setValidated(true);
        return false;
    };

    return {
        item,
        validated,
        handleSubmit,
        handleChange,
        resetValidity,
    };
};

export default useLinesItemForm;
