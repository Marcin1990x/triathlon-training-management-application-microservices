import { createContext, useContext, useState } from "react";
import { getTrainingPlansByCoachIdApi } from "../../api/TrainingPlanApiService";
import { useAuth } from "../../security/AuthContext";

const DataContextTrainings = createContext()
export const useDataContextTrainings = () => useContext(DataContextTrainings)

const DataContextTrainingsProvider = ({children}) => {

    const authContext = useAuth()
    
    const [trainingPlans, setTrainingPlans] = useState([])
    
    const [newTrainingView, setNewTrainingView] = useState(false)

    const [activePlan, setActivePlan] = useState(null)

    const [activeRealization, setActiveRealization] = useState()

    const getCoachTrainingPlans= () => {
        getTrainingPlansByCoachIdApi(authContext.coachId)
            .then(response => {
                console.log(response)
                setTrainingPlans(response.data)
            })
            .catch(error => console.log(error))
    }
    const activateRealization = (realization) => {
        setActiveRealization(realization)
    }

    function activatePlan(plan) {
        setActivePlan(plan)
    }
    const switchView = () => {
        setNewTrainingView(!newTrainingView)
    }

    return (
        <DataContextTrainings.Provider value = {{getCoachTrainingPlans, trainingPlans, activatePlan, activePlan, 
            newTrainingView, setNewTrainingView, switchView, activateRealization, activeRealization}}>
            {children}
        </DataContextTrainings.Provider>
    )
}
export default DataContextTrainingsProvider