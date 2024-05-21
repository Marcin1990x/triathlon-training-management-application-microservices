import { createContext, useContext, useState } from "react";
import { useAuth } from "../../security/AuthContext";
import { getTrainingPlansByAthleteIdApi } from "../../api/TrainingPlanApiService";
import { getTrainingRealizationsByAthleteIdApi } from "../../api/TrainingRealizationApiService";
import { getAthleteById } from "../../api/AthletesApiService";
import { getCoachById } from "../../api/CoachApiService";

const DataContextAthlete = createContext()
export const useDataContextAthlete = () => useContext(DataContextAthlete)

const DataContextAthleteProvider = ({children}) => {

    const [trainingPlans, setTrainingPlans] = useState([])
    const [trainingRealizations, setTrainingRealizations] = useState([])
    const [activeTraining, setActiveTraining] = useState(null)
    const [athlete, setAthlete] = useState(null)
    const [coach, setCoach] = useState(null)

    const authContext = useAuth()
    
    const getTrainingPlans = () => {
        getTrainingPlansByAthleteIdApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setTrainingPlans(response.data)
            })
            .catch(error => console.log(error))
    }
    const getTrainingRealizations = () => {
        getTrainingRealizationsByAthleteIdApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setTrainingRealizations(response.data)
            })
            .catch(error => console.log(error))
    }
    const getAthlete = () => {
        getAthleteById(authContext.athleteId)
            .then(response => {
                console.log(response)
                setAthlete(response.data)
                getCoach(response.data.coachId)
            })
            .catch(error => console.log(error))
    }
    const getCoach = (id) => {
        getCoachById(id)
            .then(response => {
                console.log(response)
                setCoach(response.data)
            })
            .catch(error => console.log(error))
    }

    const setActiveTrainingFunction = (training) => {
        setActiveTraining(training)
    }

    return (
        <DataContextAthlete.Provider value = {{getTrainingPlans, getTrainingRealizations, trainingPlans, trainingRealizations,
            setActiveTrainingFunction, activeTraining, getAthlete, athlete, coach}}>
            {children}
        </DataContextAthlete.Provider>
    )
}
export default DataContextAthleteProvider