import { createContext, useContext, useState } from "react";
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService"
import { apiClient } from "../api/ApiClient";
import { refreshAccessTokenForUserApi } from "../api/UserApiService";

export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({children}) {

    const [isAuthenticated, setAuthenticated] = useState(false)
    const [token, setToken] = useState(null)
    const [userId, setUserId] = useState(null)
    const [athleteId, setAthleteId] = useState(null)
    const [isAthlete, setIsAthlete] = useState(false)
    const [coachId, setCoachId] = useState(null)
    const [isCoach, setIsCoach] = useState(false)
    const [hasRefreshToken, setHasRefreshToken] = useState(false)
    const [stravaAccessExpiresAt, setStravaAccessExpiresAt] = useState(null)

    const [interceptor, setInterceptor] = useState(null)

    async function login (username, password) {

        try {
            const response = await executeJwtAuthenticationService(username, password)
            
            if(response.status == 200) {

                const token = response.headers.get("Authorization")
                
                setToken(token)
                setAuthenticated(true)
                setUserId(response.data.userId)
                setAthleteId(response.data.athleteId)
                setIsAthlete(response.data.athlete)
                setCoachId(response.data.coachId)
                setIsCoach(response.data.coach)
                setHasRefreshToken(response.data.hasRefreshToken)
                setStravaAccessExpiresAt(response.data.stravaAccessExpiresAt)

                const newInterceptor = apiClient.interceptors.request.use (
                        (config) => {
                            config.headers.Authorization = token
                            return config
                        }
                    )
                setInterceptor(newInterceptor)
                
                if(response.data.athlete) {
                    return 'athlete'
                } else if(response.data.coach) {
                    return 'coach'
                } else {
                    return 'error'
                }
            } else {
                logout()
                return 'login failed'
            }
 
        } catch (error) {
            logout()
            return 'login failed'
        }
    }
    function logout() {
        setToken(null)
        apiClient.interceptors.request.eject(interceptor)
        setAuthenticated(false)
        setUserId(null)
        setAthleteId(null)
        setIsAthlete(false)
        setCoachId(null)
        setIsCoach(false)
        setHasRefreshToken(false)
        setStravaAccessExpiresAt(null)
    }

    const refreshAccessToken= () => {
         refreshAccessTokenForUserApi(userId)
            .then(response => {
                console.log(response)
                setStravaAccessExpiresAt(response.data)
                return true 
            })
            .catch(error => {
                console.log(error)
                return false
            })
    }

    return (
        <AuthContext.Provider value = {{login, logout, token, isAuthenticated, userId, athleteId, isAthlete, coachId, isCoach,
                                hasRefreshToken, stravaAccessExpiresAt, refreshAccessToken}}>
            {children}
        </AuthContext.Provider>
    )
}

