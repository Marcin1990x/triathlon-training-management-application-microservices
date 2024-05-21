import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginComponent from "./LoginComponent";
import AthleteComponent from "./athleteComponent/AthleteComponent";
import CoachComponent from "./coachComponent/CoachComponent";
import AuthProvider from "./security/AuthContext";
import WeekdayListVisibilityProvider from "./coachComponent/contexts/WeekdayListVisibilityContext";
import DataContextAthletesProvider from "./coachComponent/contexts/DataContextAthletes";
import DataContextTrainingsProvider from "./coachComponent/contexts/DataContextTrainings";
import RegisterComponent from "./registerComponent/RegisterComponent";
import HeaderComponent from "./header/HeaderComponent";
import NewUserComponent from "./newUserComponent/NewUserComponent";
import DataContextAthleteProvider from "./athleteComponent/contexts/DataContextAthlete";
import AddAthleteComponent from "./coachComponent/AddAthleteComponent";
import StravaComponent from "./athleteComponent/strava/StravaComponent";

export default function TriathlonTrainingManagement() {
    return(
        <div className="TriathlonTrainingManager">
            <AuthProvider>
                <BrowserRouter>
                    <HeaderComponent/>
                        <WeekdayListVisibilityProvider>
                            <Routes>
                                <Route path = '/' element = { <LoginComponent /> } />
                                <Route path = '/athlete' element = 
                                    { 
                                    <DataContextAthleteProvider>
                                        <AthleteComponent /> 
                                    </DataContextAthleteProvider>
                                    } />
                                <Route path = '/athlete/strava' element = { <StravaComponent /> } />
                                <Route path = '/register' element = { <RegisterComponent /> } />
                                <Route path = '/new' element = { <NewUserComponent /> } />
                                <Route path = '/coach' element = 
                                    { 
                                    <DataContextAthletesProvider>
                                        <DataContextTrainingsProvider>
                                            <WeekdayListVisibilityProvider>
                                                <CoachComponent />
                                            </WeekdayListVisibilityProvider> 
                                        </DataContextTrainingsProvider>
                                    </DataContextAthletesProvider>
                                    } />
                                <Route path = '/coach/addAthlete' element = { <AddAthleteComponent /> } /> 
                            </Routes>
                        </WeekdayListVisibilityProvider>
                </BrowserRouter>
            </AuthProvider>    
        </div>
    )
}