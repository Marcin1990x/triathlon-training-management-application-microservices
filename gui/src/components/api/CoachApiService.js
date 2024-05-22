import { apiClient, apiFunctionsClient } from "./ApiClient"

export const addNewCoachApi = (coach) => apiFunctionsClient.post(`coaches`, coach)

export const addAthleteToCoach = (coachId, athleteId) => apiFunctionsClient.put(`coaches/${coachId}/athletes/${athleteId}/add`)

export const removeAthleteFromCoach = (coachId, athleteId) => apiFunctionsClient.put(`coaches/${coachId}/athletes/${athleteId}/remove`)

export const getCoachById = (id) => apiFunctionsClient.get(`coaches/${id}`)
