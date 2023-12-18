import { useState } from 'react';
import { Button, Form } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const PageEdit = () => {
    const [validated, setValidated] = useState(false);

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }

        setValidated(true);
    };

    return (
        <>
            <div className="text-center">
                <img id="image-preview" src="https://via.placeholder.com/300x400"
                    alt="placeholder" />
            </div>
            <Form id="items-form" noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="item">
                    <Form.Label htmlFor="item" className="form-label">Жанр</Form.Label>
                    <Form.Select name='selected' required>
                    </Form.Select>
                </Form.Group>
                <Form.Group className="mb-3" controlId="name">
                    <Form.Label>Название фильма</Form.Label>
                    <Form.Control type="text" name="name" required />
                </Form.Group>
                <Form.Group className="mb-3" controlId="rating">
                    <Form.Label>Рейтинг</Form.Label>
                    <Form.Control type="number" name="rating" min="1" max="10" required />
                </Form.Group>
                <Form.Group className="mb-3" controlId="releaseDate">
                    <Form.Label>Дата создания</Form.Label>
                    <Form.Control type="date" name="releaseDate" required />
                </Form.Group>
                <Form.Group className="mb-3" controlId="director">
                    <Form.Label>Режиссер</Form.Label>
                    <Form.Control type="text" name="director" required />
                </Form.Group>
                <Form.Group className="mb-3" controlId="image">
                    <Form.Label>Обложка</Form.Label>
                    <Form.Control type="file" name="image" accept="image/*" />
                </Form.Group>
                <Form.Group className="d-flex flex-md-row flex-column justify-content-center">
                    <Button className="btn-mw me-md-3 mb-md-0 mb-2" as={Link} to="/adminpage" variant="secondary">Назад</Button>
                    <Button className="btn-mw me-md-3" type="submit" variant="primary">Сохранить</Button>
                </Form.Group>
            </Form>
        </>
    );
};

export default PageEdit;
