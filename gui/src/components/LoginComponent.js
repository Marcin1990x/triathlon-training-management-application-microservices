import { useState } from "react"
import { useAuth } from "./security/AuthContext"
import { useNavigate, Link } from "react-router-dom"
import { toast } from "react-hot-toast"

const LoginComponent = () => {

    const authContext = useAuth()
    const navigate = useNavigate()

    const errorToast = (message) => toast.error(message)
    const successToast = (message) => toast.success(message)

    const [username, setUsername] = useState('login')
    const [password, setPassword] = useState('password')

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }
    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }
    async function handleSubmit() {
        const response = await authContext.login(username, password)

        if(response != 'login failed'){
            successToast('User ' + username + ' logged in succesfully.')
            if(response == 'athlete'){
                navigate('/athlete') 
            } else if (response == 'coach') {
                navigate('/coach')
            } else {
                navigate('/new')
            }
        } else {
            errorToast('Login failed.')
        }
    }
    return(
        <div className="LoginComponent">
           <h3>Login</h3> 
           
           <div className="row">
            <div className="col"></div>
            <div className="col">
                <div className="loginForm">
                        <div className="mb-3">
                            <label className = "form-label">Username:</label>
                            <input type = "text" name = "username" className = "form-control" value = {username} 
                                onChange = {handleUsernameChange} required/>
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Password:</label>
                            <input type = "password" name = "password" className="form-control" value = {password} 
                                onChange = {handlePasswordChange} required/>
                        </div>
                        <button className = "btn btn-outline-primary m-3" onClick = {handleSubmit}>Login</button>
                </div>
            </div>
            <div className="col"></div>
           </div>
           <div className="row">
                <div className="col"></div>
                <div className="col">New to Triathlon Training Management?
                    <Link to = "/register">Create an account</Link>
                </div>
                <div className="col"></div>
           </div>
        </div>
    )
}
export default LoginComponent