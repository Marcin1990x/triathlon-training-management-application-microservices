import { apiClient } from "./ApiClient"

export const synchronizeActivitiesForAthleteApi = (athleteId, userId) => apiClient.post(`/training-realizations/synchronizeStrava`, {}, 
    {
        params: {
            athleteId,
            userId
        }
    }
)
export const updateTrainingRealizationByIdApi = (id, updateRequest) => apiClient.put(`training-realizations/${id}`, updateRequest)

export const addNewTrainingRealization = (training) => apiClient.post(`training-realizations`, training)

export const deleteByIdApi = (id) => apiClient.delete(`training-realizations/${id}`)