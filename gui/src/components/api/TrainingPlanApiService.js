import { apiClient } from "./ApiClient"

export const getTrainingPlansByAthleteIdApi = (id) => apiClient.get(`athletes/${id}/training-plans`)

export const getTrainingPlansByCoachIdApi = (id) => apiClient.get(`coaches/${id}/training-plans`)

export const removeTrainingPlanFromAthleteApi = (athleteId, trainingPlanId) => 
    apiClient.put(`athletes/${athleteId}/training-plans/${trainingPlanId}`)

export const addTrainingPlanToAthleteWithDateApi = (athleteId, planId, plannedDate) => 
    apiClient.post(`athletes/${athleteId}/training-plans/${planId}`, {},
    {
        params: {
            plannedDate
        }
    })

export const addNewTrainingPlanToCoachApi = (coachId, trainingPlan) => apiClient.post(`coaches/${coachId}/training-plans`, trainingPlan)

export const removeTrainingPlanApi = (id) => apiClient.delete(`training-plans/${id}`)

export const getTrainingPlanByIdApi = (id) => apiClient.get(`training-plans/${id}`)