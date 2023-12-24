import { useSearchParams } from 'react-router-dom';
import useTypes from '../../types/hooks/TypesHook';

const useTypeFilter = () => {
    const filterName = 'type';

    const [searchParams, setSearchParams] = useSearchParams();

    const { types } = useTypes();

    const handleFilterChange = (event) => {
        const type = event.target.value;
        if (type) {
            searchParams.set(filterName, event.target.value);
        } else {
            searchParams.delete(filterName);
        }
        setSearchParams(searchParams);
    };

    return {
        types,
        currentFilter: searchParams.get(filterName) || '',
        handleFilterChange,
    };
};

export default useTypeFilter;
