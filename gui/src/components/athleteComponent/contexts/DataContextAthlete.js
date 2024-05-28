import { createContext, useContext, useState } from "react";
import { useAuth } from "../../security/AuthContext";
import { getTrainingPlansByAthleteIdApi } from "../../api/TrainingPlanApiService";
import { getTrainingRealizationsByAthleteIdApi } from "../../api/TrainingRealizationApiService";
import { getAthleteByIdApi, checkPendingCoachingRequestsApi, getCoachingRequestApi, sendCoachingReplyApi } from "../../api/AthletesApiService";
import { getCoachByIdApi } from "../../api/CoachApiService";
import { getUserStravaDataApi } from "../../api/UserApiService";

const DataContextAthlete = createContext()
export const useDataContextAthlete = () => useContext(DataContextAthlete)

const DataContextAthleteProvider = ({children}) => {

    const [trainingPlans, setTrainingPlans] = useState([])
    const [trainingRealizations, setTrainingRealizations] = useState([])
    const [activeTraining, setActiveTraining] = useState(null)
    const [athlete, setAthlete] = useState(null)
    const [coach, setCoach] = useState(null)
    const [requestCount, setRequestCount] = useState(0)
    const [coachingRequest, setCoachingRequest] = useState()
    const [stravaUserData, setStravaUserData] = useState(null)

    const authContext = useAuth()
    
    const getTrainingPlans = () => { // no useful ? data from athlete
        getTrainingPlansByAthleteIdApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setTrainingPlans(response.data)
            })
            .catch(error => console.log(error))
    }
    const getTrainingRealizations = () => { // no useful ? data from athlete
        getTrainingRealizationsByAthleteIdApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setTrainingRealizations(response.data)
            })
            .catch(error => console.log(error))
    }
    const getAthlete = () => {
        getAthleteByIdApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setAthlete(response.data)
                //
                setTrainingRealizations(response.data.trainingRealizations)
                setTrainingPlans(response.data.trainingPlans)
                //
                getCoach(response.data.coachId)
            })
            .catch(error => console.log(error))
    }
    const getCoach = (id) => {
        getCoachByIdApi(id)
            .then(response => {
                console.log(response)
                setCoach(response.data)
            })
            .catch(error => console.log(error))
    }
    const getUserStravaData = () => {
        getUserStravaDataApi(authContext.userId)
            .then(response => {
                console.log(response)
                setStravaUserData(response.data)
            })
            .catch(error => console.log(error))
    }

    const setActiveTrainingFunction = (training) => {
        setActiveTraining(training)
    }

    const checkPendingCoachingRequests = () => {

        checkPendingCoachingRequestsApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setRequestCount(response.data)
            })
            .catch(error => console.log(error))
    }
    const getCoachingRequest = () => {

        getCoachingRequestApi(authContext.athleteId)
            .then(response => {
                console.log(response)
                setCoachingRequest(response.data)
            })
            .catch(error => console.log(error))
    }
    const sendCoachingReply = (confirmation) => {
        sendCoachingReplyApi(authContext.athleteId, coachingRequest.coachId, confirmation)
            .then(response => console.log(response))
            .catch(error => console.log(error))
    }

    return (
        <DataContextAthlete.Provider value = {{getTrainingPlans, getTrainingRealizations, trainingPlans, trainingRealizations,
                setActiveTrainingFunction, activeTraining, getAthlete, athlete, coach, checkPendingCoachingRequests, requestCount,
                getCoachingRequest, coachingRequest, sendCoachingReply, getUserStravaData, stravaUserData
            }}>
            {children}
        </DataContextAthlete.Provider>
    )
}
export default DataContextAthleteProvider