import { apiFunctionsClient } from "./ApiClient"

export const getAthletesByCoachIdApi = (coachId) => apiFunctionsClient.get(`athletes/coach`,
    {
        params: {
            coachId
        }
    }
)
export const addNewAthleteApi = (athlete) => apiFunctionsClient.post(`athletes`, athlete)

export const getAthleteByIdApi = (id) => apiFunctionsClient.get(`athletes/${id}`)

export const getByLastnameApi = (lastname) => apiFunctionsClient.get(`athletes`, {
    params: {
        lastname: lastname
    }
})
export const checkPendingCoachingRequestsApi = (athleteId) => apiFunctionsClient.get(`athletes/${athleteId}/checkRequests`)

export const getCoachingRequestApi = (athleteId) => apiFunctionsClient.get(`/athletes/${athleteId}/getCoachingRequest`)

export const sendCoachingReplyApi = (athleteId, coachId, confirmation) => apiFunctionsClient.put(`/athletes/${athleteId}/sendCoachingReply`, {}, {
    params: {
        confirmation, coachId
    }
})
