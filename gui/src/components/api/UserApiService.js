import { apiClient } from "./ApiClient";


export const refreshAccessTokenForUserApi = (id) => apiClient.put(`users/${id}/refreshAccessToken`)

export const addCoachToUserApi = (userId, coachId) => apiClient.put(`users/${userId}/coaches/${coachId}/add`)

export const addAthleteToUserApi = (userId, athleteId) => apiClient.put(`users/${userId}/athletes/${athleteId}/add`)

export const getUserByIdApi = (userId) => apiClient.get(`users/${userId}`)

export const sendMessageApi = (athleteId, coachId, message) => apiClient.post(`users/sendMessage`, {},
    {
        params: {
            athleteId,
            coachId,
            message
        }
    }
)

