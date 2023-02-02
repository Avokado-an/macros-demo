import { React, useState, useEffect } from 'react';
import { Dish } from '../common/entity-item/Dish';
import Button from 'react-bootstrap/Button';
import 'react-datepicker/dist/react-datepicker.css'
import DatePicker from 'react-datepicker'
import SideBar from '../common/navigation/SideBar';
import { DishDataModification } from '../common/modal/DishDataModificationModal';

export const DailyDiet = () => {
    const dailyMacros = {
        calories: 600,
        fats: 10,
        carbs: 10,
        proteins: 30
    }

    const ingredient1 = {
        name: "Chicken Breast",
        calories: 50,
        fats: 1,
        carbs: 0,
        proteins: 10,
        foodCategory: "MEAT",
        weight: 50.0
    }

    const ingredient2 = {
        name: "Black Pepper",
        calories: 30,
        fats: 1,
        carbs: 10,
        proteins: 2,
        foodCategory: "SPECIES",
        weight: 5.0
    }

    const ingredient3 = {
        name: "Paprika",
        calories: 30,
        fats: 1,
        carbs: 10,
        proteins: 2,
        foodCategory: "SPECIES",
        weight: 5.0
    }

    const ingredient4 = {
        name: "Dried Garlik",
        calories: 30,
        fats: 1,
        carbs: 10,
        proteins: 2,
        foodCategory: "SPECIES",
        weight: 5.0
    }

    const ingredients = [ingredient1, ingredient2, ingredient3, ingredient4]

    const dishMacros = {
        calories: 300,
        fats: 5,
        carbs: 5,
        proteins: 15
    }

    const dish1 = {
        id: "63d3b806d5761b2390570a45",
        name: "Roasted chicken breast",
        ingredients: ingredients,
        cookingMethod: "ROASTED",
        macros: dishMacros
    }

    const dish2 = {
        id: "63d3b806d5761b2390570a46",
        name: "Roasted chicken breast",
        ingredients: ingredients,
        cookingMethod: "ROASTED",
        macros: dishMacros
    }

    const dishesForDay = [dish1, dish2]

    const dailyDietMacrosMock = {
        id: "63d3b806d5761b2390570a45",
        documentedDate: "2022-01-01",
        userEmail: "prokotlet@gmail.com",
        dishesForDay: dishesForDay,
        macrosDtoForFullDay: dailyMacros
    }

    const [startDate, setStartDate] = useState(new Date().toISOString().split('T')[0]);

    const [isOpen, setIsOpen] = useState(false);
    const handleChange = (e) => {
        setIsOpen(!isOpen);
        let dateFormatted = e.toISOString().split('T')[0];
        setStartDate(dateFormatted);
        //send request to backend to change date 
    };
    const handleClick = (e) => {
        e.preventDefault();
        setIsOpen(!isOpen);
    };

    return (
        <div>
            <SideBar />
            <div className='m-60 m-bot-0 flex space-between'>
                
                <div>
                    <h1>Dishes: {dailyDietMacrosMock.dishesForDay.length}</h1>
                    <DishDataModification buttonName="Add Dish" modalHeader="Add Dish" />
                </div>
                <div >
                    <button className="example-custom-input" onClick={handleClick}>
                        {startDate.toString()}
                    </button>
                    {isOpen && (
                        <DatePicker selected={Date.parse(startDate)} onChange={handleChange} inline />
                    )}
                </div>
            </div>


            <div className='flex-table'>{dailyDietMacrosMock.dishesForDay.map((dish) => (
                <Dish dishData={dish} key={dish.id} />
            ))}
            </div>
        </div>
    )
}