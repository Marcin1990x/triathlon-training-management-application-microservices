import { apiClient, apiTrainingsClient } from "./ApiClient"

export const getTrainingPlansByAthleteIdApi = (id) => apiClient.get(`athletes/${id}/training-plans`)

export const getTrainingPlansByCoachIdApi = (coachId) => apiTrainingsClient.get(`training-plans/coach`, {
    params: {
        coachId
    }
})

export const addTrainingPlanToAthleteWithDateApi = (athleteId, planId, plannedDate) => 
    apiTrainingsClient.post(`training-plans/${planId}`, {},
    {
        params: {
            athleteId,
            plannedDate
        }
    })

export const addNewTrainingPlanToCoachApi = (trainingPlan) => apiTrainingsClient.post(`training-plans`, trainingPlan)

export const removeTrainingPlanApi = (id) => apiTrainingsClient.delete(`training-plans/${id}`)

export const getTrainingPlanByIdApi = (id) => apiTrainingsClient.get(`training-plans/${id}`)