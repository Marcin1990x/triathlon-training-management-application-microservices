import { apiTrainingsClient } from "./ApiClient"

export const addNewStageToTrainingPlanApi = (planId, stage, stageType) => 
    apiTrainingsClient.post(`training-plans/${planId}/stages=${stageType}`, stage)