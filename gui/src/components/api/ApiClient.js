import axios from "axios"

let baseURL = 'http://localhost:8086'

if(process.env.NODE_ENV == 'production') {
    baseURL = process.env.REACT_APP_API_GATEWAY_URL
}

export const apiClient = axios.create(
    {
        baseURL: baseURL
    }
)

// export const apiClient = axios.create(
//     {
//         baseURL: process.env.REACT_APP_API_GATEWAY_URL || 'http://localhost:8086'
//     }
// )