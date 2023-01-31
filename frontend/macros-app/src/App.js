import { Route, Routes } from 'react-router-dom';
import { CurrentUserProfile } from './page/CurrentUserProfile'
import './App.css';
import { MontlyCalendar } from './page/MonthlyResults';
import { Dishes } from './page/Dishes';
import { DailyDiet } from './page/DailyMonitoring';

function App() {
  return (
    <div>
      <Routes>
        <Route path='/' element={<CurrentUserProfile />} />
        <Route path='/montly-diet' element={<MontlyCalendar />} />
        <Route path='/dishes' element={<Dishes />} />
        <Route path='/daily-macros' element={<DailyDiet />} />
      </Routes>
    </div>
  );
}

export default App;
