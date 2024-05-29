import { apiClient, apiTrainingsClient } from "./ApiClient"

export const synchronizeActivitiesForAthleteApi = (athleteId, userId) => apiTrainingsClient.post(`/training-realizations/synchronizeStrava`, {}, 
    {
        params: {
            athleteId,
            userId
        }
    }
)
export const updateTrainingRealizationByIdApi = (id, updateRequest) => apiTrainingsClient.put(`training-realizations/${id}`, updateRequest)

export const addNewTrainingRealization = (training) => apiTrainingsClient.post(`training-realizations`, training)

export const deleteByIdApi = (id) => apiTrainingsClient.delete(`training-realizations/${id}`)