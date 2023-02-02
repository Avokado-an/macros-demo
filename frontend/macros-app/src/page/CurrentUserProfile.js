import React from 'react';
import { MacrosStatisticsVertical } from '../common/entity-item/MacrosStatistics';
import { UserData } from '../common/entity-item/UserData';
import SideBar from '../common/navigation/SideBar';
import '../common/style/common-styles.css'

export const CurrentUserProfile = () => {
    const macros = {
        calories: 1000,
        fats: 10,
        proteins: 10,
        carbs: 10
    }

    const userData = {
        name: "test",
        email: "test@gmail.com",
        height: 180,
        weight: 80,
        aimWeight: 75
    }

    return (
        <div>
            <SideBar />
            <div className='flex m-top-60 m-left-60'>
                <UserData userData={userData}/>
                <div className='m-left-60'><MacrosStatisticsVertical macros={macros}/></div>
            </div>
        </div>
    )
}