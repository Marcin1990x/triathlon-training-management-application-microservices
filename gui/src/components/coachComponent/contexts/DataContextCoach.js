import { createContext, useContext, useState } from "react";
import { getTrainingPlansByAthleteIdApi, removeTrainingPlanFromAthleteApi, addTrainingPlanToAthleteWithDateApi } from "../../api/TrainingPlanApiService";
import { getTrainingRealizationsByAthleteIdApi } from "../../api/TrainingRealizationApiService";
import { getAthletesByCoachIdApi } from "../../api/AthletesApiService";
import { getCoachByIdApi } from "../../api/CoachApiService";
import { toast } from "react-hot-toast";
import { useAuth } from "../../security/AuthContext";

const DataContextCoach = createContext()

export const useDataContextCoach = () => useContext(DataContextCoach)

const DataContextCoachProvider = ({children}) => {
    
    const [coach, setCoach] = useState([])
    const [athletes, setAthletes] = useState([])
    const [trainingPlans, setTrainingPlans] = useState([])
    // const [athletePlans, setAthletePlans] = useState([])
    // const [athleteRealizations, setAthleteRealizations] = useState([])
    // const [athleteId, setAthleteId] = useState(null)

    const successToast = (message) => toast.success(message)

    const authContext = useAuth()

    const getCoach = () => {
        getCoachByIdApi(authContext.coachId)
            .then(response => {
                console.log(response)
                setCoach(response.data)
                setTrainingPlans(response.data.trainingPlans)
            })
            .catch(error => console.log(error))
    }

    return (
        <DataContextCoach.Provider value = {{getCoach}}>
            {children}
        </DataContextCoach.Provider>
    )
}
export default DataContextCoachProvider