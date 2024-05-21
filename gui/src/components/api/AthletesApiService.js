import { apiClient } from "./ApiClient"

export const getAthletesByCoachIdApi = (id) => apiClient.get(`coaches/${id}/athletes`)

export const addNewAthleteApi = (athlete) => apiClient.post(`athletes`, athlete)

export const getAthleteById = (id) => apiClient.get(`athletes/${id}`)

export const getByLastnameApi = (lastname) => apiClient.get(`athletes`, {
    params: {
        lastname: lastname
    }
})