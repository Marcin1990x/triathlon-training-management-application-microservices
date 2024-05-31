import axios from "axios"

export const apiClient = axios.create( // apiUsersClient
    {
        baseURL: 'http://localhost:8086'
    }
)