import { useState } from "react"
import { useAuth } from "../../security/AuthContext"
import { toast } from "react-hot-toast"
import moment from "moment"
import { synchronizeActivitiesForAthleteApi } from "../../api/TrainingRealizationApiService"

const StravaComponent = () => {

    const successToast = (message) => toast.success(message)

    const {hasRefreshToken, refreshAccessToken, athleteId, stravaAccessExpiresAt} = useAuth()
    const [syncBtnVisible, setSyncBtnVisible] = useState(false)

    const handleConnectStravaBtn = () => {
        if(isAccessTokenExpired()){
            refreshAccessToken()
        }
        setSyncBtnVisible(true)
    }
    const isAccessTokenExpired = () => {

        if(stravaAccessExpiresAt == null) {
            return true
        }
        var currentTime = moment()
        var givenTime = moment(stravaAccessExpiresAt * 1000)

        if(givenTime.isBefore(currentTime)) {
            return true
        } else return false
    }
    const handleSynchronizeBtn = () => {
        synchronizeActivitiesForAthlete()
    }
    const synchronizeActivitiesForAthlete = () => {
        synchronizeActivitiesForAthleteApi(athleteId)
            .then(response => {
                console.log(response)
                if(response.data > 0) {
                    successToast('Your activities have been updated! Added ' + response.data + ' activities.')
                } else {
                    successToast('Your activities are already synchronized. Nothing new to update.')
                }
            })
            .catch(error => console.log(error))
    }

    return (
        <div className="stravaComponent">
            <h5>Communication with Strava</h5>
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        {!hasRefreshToken && 
                            <div className="notConnected">
                                <p>You need to authenticate with your strava credentials...</p>
                            </div>
                        }
                        {hasRefreshToken && 
                            <div className="connected">
                                {!syncBtnVisible &&
                                <button className = "btn btn-outline-primary m-3" 
                                    onClick = {() => handleConnectStravaBtn()}>Connect with Strava</button>
                                }
                                {syncBtnVisible &&
                                <button className = "btn btn-outline-primary m-3" 
                                    onClick = {() => handleSynchronizeBtn()}>Synchronize activities</button>
                                }
                            </div>
                        }
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        </div>
    )
}
export default StravaComponent