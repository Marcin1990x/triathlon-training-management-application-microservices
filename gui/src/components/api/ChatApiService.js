import { apiClient } from "./ApiClient"

export const getChatMessagesApi = (athleteId, coachId) => apiClient.get(`chat/getChatMessages`,
    {
        params: {
            athleteId: athleteId,
            coachId: coachId
        }
    }
)
