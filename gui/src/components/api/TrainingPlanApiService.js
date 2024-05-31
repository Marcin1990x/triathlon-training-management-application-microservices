import { apiClient } from "./ApiClient"

export const getTrainingPlansByCoachIdApi = (coachId) => apiClient.get(`training-plans/coach`, {
    params: {
        coachId
    }
})

export const addTrainingPlanToAthleteWithDateApi = (athleteId, planId, plannedDate) => 
    apiClient.post(`training-plans/${planId}`, {},
    {
        params: {
            athleteId,
            plannedDate
        }
    })

export const addNewTrainingPlanToCoachApi = (trainingPlan) => apiClient.post(`training-plans`, trainingPlan)

export const removeTrainingPlanApi = (id) => apiClient.delete(`training-plans/${id}`)

export const getTrainingPlanByIdApi = (id) => apiClient.get(`training-plans/${id}`)