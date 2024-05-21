import { createContext, useContext, useState } from "react";
import { getTrainingPlansByAthleteIdApi, removeTrainingPlanFromAthleteApi, addTrainingPlanToAthleteWithDateApi } from "../../api/TrainingPlanApiService";
import { getTrainingRealizationsByAthleteIdApi } from "../../api/TrainingRealizationApiService";
import { getAthletesByCoachIdApi } from "../../api/AthletesApiService";
import { toast } from "react-hot-toast";
import { useAuth } from "../../security/AuthContext";

const DataContextAthletes = createContext()
export const useDataContextAthletes = () => useContext(DataContextAthletes)

const DataContextAthletesProvider = ({children}) => {
    
    const [athletes, setAthletes] = useState([])
    const [athletePlans, setAthletePlans] = useState([])
    const [athleteRealizations, setAthleteRealizations] = useState([])
    const [athleteId, setAthleteId] = useState(null)

    const successToast = (message) => toast.success(message)

    const authContext = useAuth()

    const getAthletes = () => {
        getAthletesByCoachIdApi(authContext.coachId)
            .then(response => {
                console.log(response)
                setAthletes(response.data)
            })
            .catch(error => console.log(error))
    }
    const getTrainingPlans= (id) => {
        getTrainingPlansByAthleteIdApi(id)
            .then(response => {
                console.log(response)
                setAthleteId(id)
                setAthletePlans(response.data)
            })
            .catch(error => console.log(error))
    }
    const getTrainingRealizations = (id) => {
        getTrainingRealizationsByAthleteIdApi(id)
            .then(response => {
                console.log(response)
                setAthleteRealizations(response.data)
            })
            .catch(error => console.log(error))
    }
    const setPlansAndRealizationsForAthlete = (id) => {
        getTrainingPlans(id)
        getTrainingRealizations(id)

    }
    const removeTrainingPlan = (id) => {
        removeTrainingPlanFromAthleteApi(athleteId, id)
            .then(reponse => {
                console.log(reponse)
                setPlansAndRealizationsForAthlete(athleteId)
                successToast('Training plan deleted successfully.')
            })
            .catch(error => console.log(error))
    }

    const [addPlanMode, setAddPlanMode] = useState(false)
    const [newPlanDate, setNewPlanDate] = useState(null)

    const addTrainingPlanToAthleteWithDate = (id) => {

        const extractedDate = extractDate(newPlanDate)

        addTrainingPlanToAthleteWithDateApi(athleteId, id, extractedDate)
            .then(response => {
                console.log(response)
                setPlansAndRealizationsForAthlete(athleteId)
                successToast('Training plan added successfully.')
                handleAddPlanMode(false, null)
            })
            .catch(error => console.log(error))
    }
    function extractDate(dateWithTime) {
        const date = new Date(dateWithTime)
        return date.toISOString().split('T')[0]
    }
    function handleAddPlanMode(status, day) {
        setAddPlanMode(status)
        setNewPlanDate(day)
        toggleView()
    }

    const [athleteView, setAthleteView] = useState(true) 

    const toggleView = () => {
        setAthleteView(!athleteView)
    }


    return (
        <DataContextAthletes.Provider value = {{getAthletes, athletes, athletePlans, athleteRealizations, athleteId, 
            setPlansAndRealizationsForAthlete, removeTrainingPlan, addPlanMode, setAddPlanMode, setNewPlanDate,
                addTrainingPlanToAthleteWithDate, handleAddPlanMode, toggleView, athleteView, setAthleteId}}>
            {children}
        </DataContextAthletes.Provider>
    )
}
export default DataContextAthletesProvider