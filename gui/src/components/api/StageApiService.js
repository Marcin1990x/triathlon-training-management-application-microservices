import { apiClient } from "./ApiClient"

export const addNewStageToTrainingPlanApi = (planId, stage, stageType) => 
    apiClient.post(`training-plans/${planId}/stages=${stageType}`, stage)