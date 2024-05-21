import { apiClient } from "./ApiClient"

export const registerUserApi = (user) => apiClient.post(`/register`, user)