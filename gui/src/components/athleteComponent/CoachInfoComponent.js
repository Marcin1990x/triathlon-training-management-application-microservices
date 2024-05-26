import { useState } from "react"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import { toast } from "react-hot-toast"

const CoachInfoComponent = () => {

    const dataContextAthlete = useDataContextAthlete()
    const [checkView, setCheckView] = useState(false)

    const {coach, requestCount, getCoachingRequest, coachingRequest, sendCoachingReply} = useDataContextAthlete()

    const successToast = (message) => toast.success(message)

    const handleCheckViewBtn = () => {
        setCheckView(true)
        getCoachingRequest()
    }

    const handleAcceptBtn = () => {
        setCheckView(false)
        sendCoachingReply(true)
        dataContextAthlete.checkPendingCoachingRequests()
        successToast('Request accepted. ' + coachingRequest.coachFirstName + " " +  coachingRequest.coachLastName + " is now your coach.")

    }
    const handleDeclineBtn = () => {
        setCheckView(false)
        sendCoachingReply(false)
        dataContextAthlete.checkPendingCoachingRequests()
        successToast('Request declined.')
    }

    return (
        <div className="coachInfoComponent">
            <h6>My coach:</h6>
            {!coach && requestCount != 0 && !checkView &&
                <div>
                    You have {requestCount} coaching requests
                    <button className="btn btn-outline-primary btn-sm m-2" onClick = {() => handleCheckViewBtn()}>Check</button>
                </div>
            }
            {checkView && coachingRequest &&
                <div>
                    <p>
                        <b>{coachingRequest.coachFirstName} {coachingRequest.coachLastName}</b> wants to be your coach.
                    </p>
                    <button className="btn btn-outline-success btn-sm m-2" onClick = {() => handleAcceptBtn()}>Accept</button>
                    <button className="btn btn-outline-danger btn-sm m-2" onClick = {() => handleDeclineBtn()}>Decline</button>
                </div>
            }
            {coach && 
                <p>{dataContextAthlete.coach.firstName} {dataContextAthlete.coach.lastName}</p>
            }
        </div>
    )
}
export default CoachInfoComponent