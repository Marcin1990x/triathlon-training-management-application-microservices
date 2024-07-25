import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginComponent from "./loginComponent/LoginComponent";
import AthleteComponent from "./athleteComponent/AthleteComponent";
import CoachComponent from "./coachComponent/CoachComponent";
import AuthProvider from "./security/AuthContext";
import WeekdayListVisibilityProvider from "./coachComponent/contexts/WeekdayListVisibilityContext";
import DataContextAthletesProvider from "./coachComponent/contexts/DataContextAthletes";
import DataContextTrainingsProvider from "./coachComponent/contexts/DataContextTrainings";
import RegisterComponent from "./registerComponent/RegisterComponent";
import HeaderComponent from "./headerComponent/HeaderComponent";
import NewUserComponent from "./newUserComponent/NewUserComponent";
import DataContextAthleteProvider from "./athleteComponent/contexts/DataContextAthlete";
import AddAthleteComponent from "./coachComponent/AddAthleteComponent";
import StravaComponent from "./athleteComponent/strava/StravaComponent";
import DataContextCoachProvider from "./coachComponent/contexts/DataContextCoach";

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
                                <Route path = '/athlete/strava' element = {
                                    <DataContextAthleteProvider>
                                        <StravaComponent />
                                    </DataContextAthleteProvider>
                                    } />
                                <Route path = '/register' element = { <RegisterComponent /> } />
                                <Route path = '/new' element = { <NewUserComponent /> } />
                                <Route path = '/coach' element = 
                                    { 
                                    <DataContextCoachProvider>
                                        <DataContextAthletesProvider>
                                            <DataContextTrainingsProvider>
                                                <WeekdayListVisibilityProvider>
                                                        <CoachComponent />
                                                </WeekdayListVisibilityProvider> 
                                            </DataContextTrainingsProvider>
                                        </DataContextAthletesProvider>
                                    </DataContextCoachProvider>
                                    } />
                                <Route path = '/coach/addAthlete' element = { <AddAthleteComponent /> } /> 
                            </Routes>
                        </WeekdayListVisibilityProvider>
                </BrowserRouter>
            </AuthProvider>    
        </div>
    )
}