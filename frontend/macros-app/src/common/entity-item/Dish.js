import React, { useState } from 'react';
import Accordion from 'react-bootstrap/Accordion';
import Card from 'react-bootstrap/Card';
import { AddIngredientModal } from '../modal/AddIngredientModal'
import { BsFillTrashFill } from "react-icons/bs";
import '../style/common-styles.css'
import Button from 'react-bootstrap/Button';
import { MacrosStatisticsHorizontal } from './MacrosStatistics';
import { DishDataModification } from '../modal/DishDataModificationModal';

export const Dish = ({ dishData }) => {
    const [isOpen, setOpen] = useState(false);

    const toggleModal = () => {
        setOpen(!isOpen);
    }

    const onSave = () => {
        setOpen(!isOpen);
        //send request to server
    }

    const deleteDish = () => {

    }

    const addIngredient = () => {

    }

    const deleteIngredient = () => {

    }

    const editIngredient = () => {

    }

    const editDish = () => {

    }

    return (
        <Card className='width-400 m-top-60'>
            <Card.Img className="size-200-200" variant="top" src="logo192.png" />
            <Card.Body>
                <Card.Title>{dishData.name}</Card.Title>
                <div className='flex space-between'>
                    <Card.Text>Cooking method: {dishData.cookingMethod.toLowerCase()}</Card.Text>
                </div>
                <MacrosStatisticsHorizontal macros={dishData.macros} />
                <Accordion alwaysOpen className='m-top-20'>
                    {dishData.ingredients.map((ingredient) => (
                        <Accordion.Item eventKey={ingredient.name} key={ingredient.name}>
                            <Accordion.Header>
                                <div className='flex space-between'>
                                    {ingredient.name}
                                </div>
                            </Accordion.Header>
                            <Accordion.Body>
                                <div className='flex space-between m-bot-20'>
                                    <div>
                                        <div>{ingredient.foodCategory.toLowerCase()}</div>
                                        <div>{ingredient.weight} grams</div>
                                    </div>
                                    <div>
                                        <Button className="m-left-20" onClick={deleteIngredient} variant="dark">
                                            <BsFillTrashFill />
                                        </Button>
                                    </div>
                                </div>
                                <MacrosStatisticsHorizontal macros={{
                                    proteins: ingredient.proteins,
                                    fats: ingredient.fats,
                                    carbs: ingredient.carbs,
                                    calories: ingredient.calories
                                }} />
                            </Accordion.Body>
                        </Accordion.Item>
                    ))}
                </Accordion>
                <div className='flex space-between'>
                    <div className='flex space-between'>
                        <div>
                            <DishDataModification buttonName="Edit" modalHeader="Edit Dish"/>
                        </div>
                        <div>
                            <Button className="margin-bot-20 m-top-20 m-left-20" onClick={deleteDish} variant="danger">Delete</Button>
                        </div>
                    </div>
                    <div>
                        <AddIngredientModal />
                    </div>
                </div>
            </Card.Body>
        </Card >
    )
}