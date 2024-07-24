import { createContext, useContext, useState } from "react";
import { getCoachByIdApi } from "../../api/CoachApiService";
import { useAuth } from "../../security/AuthContext";

const DataContextCoach = createContext()

export const useDataContextCoach = () => useContext(DataContextCoach)

const DataContextCoachProvider = ({children}) => {
    
    const [coach, setCoach] = useState([])
    const [trainingPlans, setTrainingPlans] = useState([])

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
        <DataContextCoach.Provider value = {{getCoach, coach}}>
            {children}
        </DataContextCoach.Provider>
    )
}
export default DataContextCoachProvider