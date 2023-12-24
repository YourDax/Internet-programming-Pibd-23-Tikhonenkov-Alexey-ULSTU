import { useEffect, useState } from 'react';
import LinesApiService from '../service/LinesApiService';

const useLinesItem = (id) => {
    const emptyItem = {
        itemsId: "",
        name: '',
        rating: '',
        release_date: '',
        director: '',
        image: '',
        id: '',
    };
    const [item, setItem] = useState({ ...emptyItem });

    const getItem = async (itemId = undefined) => {
        if (itemId && itemId > 0) {
            const data = await LinesApiService.get(itemId);
            setItem(data);
        } else {
            setItem({ ...emptyItem });
        }
    };

    useEffect(() => {
        getItem(id);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [id]);

    return {
        item,
        setItem,
    };
};

export default useLinesItem;
