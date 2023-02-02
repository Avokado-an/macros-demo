import ListGroup from 'react-bootstrap/ListGroup';
import Card from 'react-bootstrap/Card';
import '../style/common-styles.css'
import { EditPersonalDataModal } from '../modal/EditPersonalData';

export const UserData = ({ userData }) => {
    return (
        <Card className='width-400'>
            <Card.Img className="height-400" variant="top" src="logo192.png" />
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
                <EditPersonalDataModal initialData={userData}/>
            </Card.Body>
        </Card>
    )
}