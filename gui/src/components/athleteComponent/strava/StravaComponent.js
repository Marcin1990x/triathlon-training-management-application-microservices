import { useEffect, useState } from "react"
import { useAuth } from "../../security/AuthContext"
import { toast } from "react-hot-toast"
import moment from "moment"
import { synchronizeActivitiesForAthleteApi } from "../../api/TrainingRealizationApiService"
import { useDataContextAthlete } from "../contexts/DataContextAthlete"

const StravaComponent = () => {

    const successToast = (message) => toast.success(message)

    const {getUserStravaData, stravaUserData} = useDataContextAthlete()
    const {refreshAccessToken, athleteId, userId} = useAuth()
    const [syncBtnVisible, setSyncBtnVisible] = useState(false)

    useEffect ( () => {
        getUserStravaData()
        }, [])

    const handleConnectStravaBtn = () => {
        if(isAccessTokenExpired()){
            refreshAccessToken()
        }
        setSyncBtnVisible(true)
    }
    const isAccessTokenExpired = () => {

        if(stravaUserData.expirationTime == null) {
            return true
        }
        var currentTime = moment()
        var givenTime = moment(stravaUserData.expirationTime * 1000)

        if(givenTime.isBefore(currentTime)) {
            return true
        } else return false
    }
    const handleSynchronizeBtn = () => {
        synchronizeActivitiesForAthlete()
    }
    const synchronizeActivitiesForAthlete = () => {
        synchronizeActivitiesForAthleteApi(athleteId, userId)
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
                        {!stravaUserData?.refreshToken && 
                            <div className="notConnected">
                                <p>You need to authenticate with your strava credentials...</p>
                            </div>
                        }
                        {stravaUserData?.refreshToken && 
                            <div className="connected">
                                {!syncBtnVisible &&
                                <button className = "btn btn-outline-primary m-3" 
                                    onClick = {() => handleConnectStravaBtn()}>Connect with Strava</button>
                                }
                                {syncBtnVisible &&
                                <button className = "btn btn-outline-primary m-3" 
                                    onClick = {() => handleSynchronizeBtn()}>Synchronize activities (last 14 days)</button>
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