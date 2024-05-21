import { useState } from "react"
import { addNewAthleteApi } from "../api/AthletesApiService"
import { addNewCoachApi } from "../api/CoachApiService"
import { toast } from "react-hot-toast"
import { addAthleteToUserApi, addCoachToUserApi } from "../api/UserApiService"
import { useAuth } from "../security/AuthContext"
import { useNavigate } from "react-router-dom"

const NewUserComponent = () => {

    const authContext = useAuth()
    const navigate = useNavigate()

    const successToast = (message) => toast.success(message)
    const errorToast = (message) => toast.error(message)

    const [selectedOption, setSelectedOption] = useState('...')
    const handleSelectOption = (event) => {
        setSelectedOption(event.target.value)
    }
    const [formFieldsRole, setFormFieldsRole] = useState({
        firstName: '',
        lastName: ''
    })
    const handleFieldChangeRole = (event) => {
        const {name, value} = event.target
        setFormFieldsRole({...formFieldsRole, [name]: value})
    }
    const handleRegisterRole = (event) => {

        event.preventDefault()
        
        const role = {
            firstName : formFieldsRole.firstName,
            lastName : formFieldsRole.lastName
        }
        if(role.firstName != '' && role.lastName && (selectedOption == 'coach' || selectedOption == 'athlete')) {
            if(selectedOption == 'athlete') {
                addNewAthleteApi(role)
                    .then(response => {
                        console.log(response)
                        addAthleteToUserApi(authContext.userId, response.data.id)
                            .then(response => {
                                console.log(response)
                                successToast('Congratulations, you are now an Athlete! Please log in again.')
                                authContext.logout()
                                navigate('/')
                            })
                            .catch(error => console.log(error))
                })
                    .catch(error => console.log(error))
            } else {
                addNewCoachApi(role)
                    .then(response => {
                        console.log(response)
                        addCoachToUserApi(authContext.userId, response.data.id)
                            .then(response => {
                                console.log(response)
                                successToast('Congratulations, you are now an Coach! Please log in again.')
                                authContext.logout()
                                navigate('/')
                            })
                            .catch(error => console.log(error))
                    })
                    .catch(error => console.log(error))
            }
        } else {
            errorToast('Please fill in all required fields')
        }
    }
    return (
        <div className="newUser">
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        <h5>Who you want to be? Coach or Athlete?</h5>
                        <br></br>
                            <div class="btn-group m-3" role="group" aria-label="Basic radio toggle button group">
                                <input type="radio" className="btn-check" value = "coach" name="options" id="option1" onChange={handleSelectOption}/>
                                <label class="btn btn-outline-success btn-lg" for="option1">Coach</label>
                                <input type="radio" className="btn-check" value = "athlete" name="options" id="option2" onChange={handleSelectOption}/>
                                <label class="btn btn-outline-success btn-lg" for="option2">Athlete</label>
                            </div>
                            <div className="form">
                                <form onSubmit={handleRegisterRole}>
                                    <label className = "form-label">First name:</label>
                                    <input type = "text" name = "firstName" className = "form-control"
                                        value = {formFieldsRole.firstName} onChange = {handleFieldChangeRole}/>
                                    <label className = "form-label">Last name:</label>
                                    <input type = "text" name = "lastName" className = "form-control" value = {formFieldsRole.lastName}
                                        onChange = {handleFieldChangeRole}/>
                                    <button className="btn btn-outline-success m-2" type = "submit">Make ma an {selectedOption}</button>
                                </form>
                            </div>
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        </div>
    )

} 
export default NewUserComponent