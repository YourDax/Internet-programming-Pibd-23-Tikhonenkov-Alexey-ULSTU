import './Footer.css';
import { Link } from 'react-router-dom'; // Импортируйте Link из библиотеки react-router-dom

const Footer = () => {
    const year = new Date().getFullYear();

    return (
        <>
            <footer className="footer mt-auto d-flex justify-content-center align-items-center">
                <div className="container-fluid">
                    <p>© {year} Просмотр фильмов. <Link to="/adminpage">Администратор</Link></p>
                </div>
            </footer>
        </>
    );
};

export default Footer;
