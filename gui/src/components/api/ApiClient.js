import axios from "axios"

let baseURL = 'http://localhost:8086'
// let baseURL = `http://gateway:8086`

export const apiClient = axios.create(
    {
        baseURL: baseURL
    }
)