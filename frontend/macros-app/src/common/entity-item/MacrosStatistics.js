import ListGroup from 'react-bootstrap/ListGroup';
import '../style/common-styles.css'

export const MacrosStatistics = ({ macros }) => {
    return (
        <div className='width-200'>
            <ListGroup>
                <ListGroup.Item active>Calories {macros.calories}</ListGroup.Item>
                <ListGroup.Item>Fats {macros.fats}</ListGroup.Item>
                <ListGroup.Item>Proteins {macros.proteins}</ListGroup.Item>
                <ListGroup.Item>Carbs {macros.carbs}</ListGroup.Item>
            </ListGroup>
        </div>
    )
}