import ListGroup from 'react-bootstrap/ListGroup';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import '../style/common-styles.css'

export const UserData = ({ userData }) => {
    return (
        <Card className='width-400'>
            <Card.Img className="size-400-400" variant="top" src="logo192.png" />
            <Card.Body>
                <Card.Title>{userData.name}</Card.Title>
                <Card.Text>
                    <ListGroup>
                        <ListGroup.Item>{userData.email}</ListGroup.Item>
                        <ListGroup.Item>{userData.height} sm</ListGroup.Item>
                        <ListGroup.Item>{userData.weight} kg</ListGroup.Item>
                        <ListGroup.Item>{userData.aimWeight} kg</ListGroup.Item>
                    </ListGroup>
                </Card.Text>
                <Button variant="dark">Edit</Button>
            </Card.Body>
        </Card>
    )
}