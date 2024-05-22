import axios from "axios"

export const apiClient = axios.create( // apiUsersClient
    {
        baseURL: 'http://localhost:8080'
    }
)
export const apiFunctionsClient = axios.create(
    {
        baseURL: 'http://localhost:8081'
    }
)
export const apiTrainingsClient = axios.create(
    {
        baseURL: 'http://localhost:8084'
    }
)