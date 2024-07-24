import { useEffect } from "react"
import CoachAthletesComponent from "./CoachAthletesComponent"
import CoachTrainingPlansComponent from "./CoachTrainingPlansComponent"
import { useDataContextAthletes } from "./contexts/DataContextAthletes"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"
import { useDataContextCoach } from "./contexts/DataContextCoach"
import { getCoachingReplyApi } from "../api/CoachApiService"
import { toast } from "react-hot-toast"
import { useAuth } from "../security/AuthContext"
import ChatBoxComponent from "../chatComponent/ChatBoxComponent"

export default function CoachComponent() {

    const dataContextAthletes = useDataContextAthletes()
    const dataContextTrainings = useDataContextTrainings()
    const dataContextCoach = useDataContextCoach()
    const authContext = useAuth()

    const successToast = (message) => toast.success(message)
    const errorToast = (message) => toast.error(message)

    const buttonText = () => {
        return dataContextAthletes.athleteView ? 'See trainings page' : 'See athletes page';
    }

    useEffect( () => {
        dataContextCoach.getCoach()
        dataContextTrainings.getCoachTrainingPlans()
        dataContextAthletes.getAthletes()
        getCoachingReply()
    }, [])

    const getCoachingReply = () => {
        getCoachingReplyApi(authContext.coachId)
            .then(response => {
                console.log(response)
                if(response.data.confirmation) {
                    successToast('Athlete ' + response.data.athleteFirstName + ' ' + 
                        response.data.athleteLastName + ' accepted your coaching request')
                } else {
                    errorToast('Athlete ' + response.data.athleteFirstName + ' ' + 
                        response.data.athleteLastName + ' declined your coaching request')
                }
            })
            .catch(error => console.log(error))
    }
    

    const handleSwitchViewBtn = () => {
        dataContextAthletes.toggleView()
        dataContextTrainings.setNewTrainingView(false)
    }
    return (
        <div className = "CoachComponent">
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col"></div>
                    <div className="col">
                        {!dataContextAthletes.addPlanMode && !dataContextTrainings.newTrainingView &&
                            <button className = "btn btn-outline-primary" onClick = {handleSwitchViewBtn}>{buttonText()}</button> 
                        }
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <h2>Coach page</h2> 
                        <br></br>
                        {dataContextAthletes.addPlanMode &&
                            <button className = "btn btn-warning m-2" 
                                onClick = {() => dataContextAthletes.handleAddPlanMode(false)}>Cancel adding plan
                            </button>
                        }
                    </div>
                </div>
                <div>
                    {dataContextAthletes.athleteView && 
                        <div>
                            <CoachAthletesComponent/> 
                            <ChatBoxComponent/>
                        </div>
                    }
                    {!dataContextAthletes.athleteView && <CoachTrainingPlansComponent/>}
                </div>
            </div>
        </div>
    )
}