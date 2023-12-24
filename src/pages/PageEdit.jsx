import { useParams } from 'react-router-dom';
import LinesForm from '../components/lines/form/LinesForm.jsx';

const PageEdit = () => {
    const { id } = useParams();

    return (
        <LinesForm id={id} />
    );
};

export default PageEdit;
