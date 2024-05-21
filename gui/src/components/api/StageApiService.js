import { apiClient } from "./ApiClient"

export const addNewSwimStageToTrainingPlanApi = (planId, stage, stageType) => 
    apiClient.post(`training-plans/${planId}/stages=${stageType}`, stage)