import { apiClient, apiFunctionsClient } from "./ApiClient"

export const getAthletesByCoachIdApi = (id) => apiClient.get(`coaches/${id}/athletes`)

export const addNewAthleteApi = (athlete) => apiFunctionsClient.post(`athletes`, athlete)

export const getAthleteById = (id) => apiFunctionsClient.get(`athletes/${id}`)

export const getByLastnameApi = (lastname) => apiFunctionsClient.get(`athletes`, {
    params: {
        lastname: lastname
    }
})