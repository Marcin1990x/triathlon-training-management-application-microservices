import { apiClient } from "./ApiClient";

export const getUserStravaDataApi = (userId) => apiClient.get(`strava/${userId}`)