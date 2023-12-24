import { useState } from 'react';
import useModal from '../../modal/ModalHook';
import useLinesItemForm from './LinesItemFormHook';

const useLinesFormModal = (linesChangeHandle) => {
    const { isModalShow, showModal, hideModal } = useModal();
    const [currentId, setCurrentId] = useState(0);

    const {
        item,
        validated,
        handleSubmit,
        handleChange,
        resetValidity,
    } = useLinesItemForm(currentId, linesChangeHandle);

    const showModalDialog = (id) => {
        setCurrentId(id);
        resetValidity();
        showModal();
    };

    const onClose = () => {
        setCurrentId(-1);
        hideModal();
    };

    const onSubmit = async (event) => {
        if (await handleSubmit(event)) {
            onClose();
        }
    };

    return {
        isFormModalShow: isModalShow,
        isFormValidated: validated,
        showFormModal: showModalDialog,
        currentItem: item,
        handleItemChange: handleChange,
        handleFormSubmit: onSubmit,
        handleFormClose: onClose,
    };
};

export default useLinesFormModal;
