import { useEffect, useState } from "react"
import { removeAthleteFromCoach } from "../api/CoachApiService"
import { useAuth } from "../security/AuthContext"
import { useDataContextAthletes } from "./contexts/DataContextAthletes"
import { useWeekdayListVisibility } from "./contexts/WeekdayListVisibilityContext"
import { toast } from "react-hot-toast"

const RemoveAthlete = () => {

    const {athleteId, getAthletes, setAthleteId} = useDataContextAthletes()
    const {coachId} = useAuth()
    const {isListVisible, setVisibility} = useWeekdayListVisibility()
    const [removeConfirmation, setRemoveConfirmation] = useState()

    const successToast = (message) => toast.success(message)

    useEffect( () => {
        setRemoveConfirmation(false)
    }, [isListVisible]) 

    const handleRemoveAthleteBtn = () => {
        setRemoveConfirmation(true)
    }

    const handleYesBtn = () => {
        removeAthlete()
        setRemoveConfirmation(false)
    }
    const handleNoBtn = () => {
        setRemoveConfirmation(false)
    }
    const removeAthlete = () => {

        removeAthleteFromCoach(coachId, athleteId)
        .then(response => {
            console.log(response)
            getAthletes()
            setVisibility(false)
            setAthleteId(null)
            successToast('Athlete removed successfully.')
        })
        .catch(error => console.log(error))
    }

    return (
        <div className="removeAthlete">
            {isListVisible &&
            <div className="row">
                <div className="col"></div>
                    <div className="col">
                        {!removeConfirmation && athleteId != null &&
                            <button className="btn btn-outline-danger m-2 btn-sm" onClick={() => handleRemoveAthleteBtn()}>Remove athlete</button>
                        }
                        {removeConfirmation &&
                            <div>
                                <p>Are you sure you want to delete athlete?</p>
                                <button className="btn btn-outline-danger m-2 btn-sm" onClick={() => handleYesBtn()}>Yes</button>
                                <button className="btn btn-outline-danger m-2 btn-sm" onClick={() => handleNoBtn()}>No</button>
                            </div>
                        }
                    </div>
                <div className="col"></div>
            </div>
            }
        </div>
    )
}
export default RemoveAthlete