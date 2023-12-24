import { useEffect, useState } from 'react';
import TypesApiService from '../service/TypesApiService';

const useTypes = () => {
    const [types, setTypes] = useState([]);

    const getTypes = async () => {
        const data = await TypesApiService.getAll();
        setTypes(data ?? []);
    };

    useEffect(() => {
        getTypes();
    }, []);

    return {
        types,
    };
};

export default useTypes;
