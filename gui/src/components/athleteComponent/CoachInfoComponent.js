import { useState } from "react"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import { toast } from "react-hot-toast"

const CoachInfoComponent = () => {

    const dataContextAthlete = useDataContextAthlete()
    const [checkView, setCheckView] = useState(false)

    const {coach, requestCount} = useDataContextAthlete()

    const successToast = (message) => toast.success(message)

    const handleCheckViewBtn = () => {
        setCheckView(true)

    }
    const handleAcceptBtn = () => {
        setCheckView(false)
        dataContextAthlete.checkPendingCoachingRequests()
        successToast('Accepted.')

    }
    const handleDeclineBtn = () => {
        setCheckView(false)
        dataContextAthlete.checkPendingCoachingRequests()
        successToast('Declined.')
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
            {checkView &&
                <div>
                    Your message
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