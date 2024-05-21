import { useState } from "react"
import { toast } from "react-hot-toast"
import { registerUserApi } from "../api/RegisterApiService"
import { useNavigate } from "react-router-dom"

const RegisterComponent = () => {

    const successToast = (message) => toast.success(message)
    const errorToast = (message) => toast.error(message)

    const navigate = useNavigate()

    const [formFields, setFormFields] = useState({
        username: '',
        emailAddress: '',
        password: '',
        confirmPassword: '',
    })
    const handleFieldChange = (event) => {
        const {name, value} = event.target
        setFormFields({...formFields, [name]: value})
    }
    const handleRegisterUser = (event) => {

        event.preventDefault()
        
        const user = {
            username: formFields.username,
            emailAddress: formFields.emailAddress,
            password: formFields.password
        }
        if(!user.password == formFields.confirmPassword) {
            
        }

        if(user.password == formFields.confirmPassword) {
            if(user.username.length >= 8 && user.password.length >= 8) {
                registerUserApi(user)
                    .then(response => {
                        successToast('Registration successful! Welcome aboard ' + user.username + '! You can now log in and choose your role.')
                        navigate('/')
                        console.log(response)
                    })
                    .catch(error => {
                        console.log(error)
                        errorToast(error.response.data.message)
                    })
            } else {
                errorToast('Please fill in all required fields')
            }
        } else {
            errorToast("Oops! It seems like the passwords don't match.")
        }
    }
    return (
        <div className="register">
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        <h5>Register</h5>
                        <form onSubmit={handleRegisterUser}>
                            <label className = "form-label">Username:</label>
                            <input type = "text" name = "username" className = "form-control" minLength={5} maxLength={20} 
                                value = {formFields.username} onChange = {handleFieldChange}/>
                            <label className = "form-label">Password:</label>
                            <input type = "password" name = "password" className = "form-control" value = {formFields.password}
                                minLength={8} onChange = {handleFieldChange}/>
                            <label className = "form-label">Repeat password:</label>
                            <input type = "password" name = "confirmPassword" className = "form-control" value = {formFields.confirmPassword}
                                onChange = {handleFieldChange}/>
                            <label className = "form-label">E-mail address:</label>
                            <input type = "email" name = "emailAddress" className = "form-control" value = {formFields.emailAddress}
                                onChange = {handleFieldChange}/>
                            <button className="btn btn-outline-success m-2" type = "submit">Register</button>
                        </form>
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        </div>
    )
} 
export default RegisterComponent