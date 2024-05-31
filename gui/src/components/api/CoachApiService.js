import { apiClient } from "./ApiClient"

export const addNewCoachApi = (coach) => apiClient.post(`coaches`, coach)

export const addAthleteToCoach = (coachId, athleteId) => apiClient.put(`coaches/${coachId}/athletes/${athleteId}/add`)

export const removeAthleteFromCoach = (coachId, athleteId) => apiClient.put(`coaches/${coachId}/athletes/${athleteId}/remove`)

export const getCoachByIdApi = (id) => apiClient.get(`coaches/${id}`)

export const addAthleteToCoachRequestApi = (coachId, athleteId) => 
    apiClient.get(`coaches/${coachId}/sendCoachingRequest`, 
        {
            params: {
                athleteId
            }
        }
    )
export const getCoachingReplyApi = (coachId) => apiClient.get(`coaches/${coachId}/getCoachingReply`)
