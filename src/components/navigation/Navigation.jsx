
import PropTypes from 'prop-types';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link, useLocation } from 'react-router-dom';
import './Navigation.css';

const Navigation = ({ routes }) => {
    const location = useLocation();
    const pages = routes.filter((route) => Object.prototype.hasOwnProperty.call(route, 'title'));

  return (
    <div className="header">
      <Navbar expand="md" variant="dark">
        <Container fluid>
          <Link to="/" className="navbar-brand">
            <div className="header-logo">
              <img src="icon.png" alt="Logo" />
              <h2>Фильмы онлайн</h2>
            </div>
          </Link>
          <Navbar.Toggle aria-controls='main-navbar' />
          <Navbar.Collapse id='main-navbar'>
            <Nav className='link ml-auto' activeKey={location.pathname}>
              {
                pages.map((page) => (
                  <Nav.Link as={Link} key={page.path} eventKey={page.path} to={page.path ?? '/'}>
                    {page.title}
                  </Nav.Link>
                ))
              }
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

Navigation.propTypes = {
  routes: PropTypes.array,
};

export default Navigation;
