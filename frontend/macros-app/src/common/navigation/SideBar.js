import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const SideBar = () => {
  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/">Poca</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/">Profile</Nav.Link>
          <Nav.Link href="/montly-diet">Macros Calendar</Nav.Link>
          <Nav.Link href="/dishes">Dishes</Nav.Link>
          <Nav.Link href="/daily-macros">Daily Diet</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}

export default SideBar;