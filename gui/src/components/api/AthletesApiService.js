import { apiClient } from "./ApiClient"

export const getAthletesByCoachIdApi = (coachId) => apiClient.get(`athletes/coach`,
    {
        params: {
            coachId
        }
    }
)
export const addNewAthleteApi = (athlete) => apiClient.post(`athletes`, athlete)

export const getAthleteByIdApi = (id) => apiClient.get(`athletes/${id}`)

export const getByLastnameApi = (lastname) => apiClient.get(`athletes`, {
    params: {
        lastname: lastname
    }
})
export const checkPendingCoachingRequestsApi = (athleteId) => apiClient.get(`athletes/${athleteId}/checkRequests`)

export const getCoachingRequestApi = (athleteId) => apiClient.get(`/athletes/${athleteId}/getCoachingRequest`)

export const sendCoachingReplyApi = (athleteId, coachId, confirmation) => apiClient.put(`/athletes/${athleteId}/sendCoachingReply`, {}, {
    params: {
        confirmation, coachId
    }
})
