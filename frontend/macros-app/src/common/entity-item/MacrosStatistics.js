import ListGroup from 'react-bootstrap/ListGroup';
import '../style/common-styles.css'

export const MacrosStatisticsVertical = ({ macros }) => {
    return (
        <div className='width-200'>
            <ListGroup>
                <ListGroup.Item variant='info'>Calories {macros.calories}</ListGroup.Item>
                <ListGroup.Item variant='warning'>Fats {macros.fats}</ListGroup.Item>
                <ListGroup.Item variant='danger'>Proteins {macros.proteins}</ListGroup.Item>
                <ListGroup.Item variant='dark'>Carbs {macros.carbs}</ListGroup.Item>
            </ListGroup>
        </div>
    )
}

export const MacrosStatisticsHorizontal = ({ macros }) => {
    return (
        <div className='width-200'>
            <ListGroup horizontal>
                <ListGroup.Item>Calories {macros.calories}</ListGroup.Item>
                <ListGroup.Item>Fats {macros.fats}</ListGroup.Item>
                <ListGroup.Item>Proteins {macros.proteins}</ListGroup.Item>
                <ListGroup.Item>Carbs {macros.carbs}</ListGroup.Item>
            </ListGroup>
        </div>
    )
}