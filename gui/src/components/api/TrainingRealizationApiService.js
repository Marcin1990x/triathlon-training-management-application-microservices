import { apiClient, apiTrainingsClient } from "./ApiClient"

export const getTrainingRealizationsByAthleteIdApi = (id) => apiClient.get(`athletes/${id}/training-realizations`)

export const synchronizeActivitiesForAthleteApi = (id) => apiClient.put(`athletes/${id}/training-realizations`)

export const updateTrainingRealizationByIdApi = (id, updateRequest) => apiTrainingsClient.put(`training-realizations/${id}`, updateRequest)

export const addNewTrainingRealization = (training) => apiTrainingsClient.post(`training-realizations`, training)