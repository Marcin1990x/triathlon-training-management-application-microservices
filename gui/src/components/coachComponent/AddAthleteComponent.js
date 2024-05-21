import { useState } from "react"
import { getByLastnameApi } from "../api/AthletesApiService"
import { toast } from "react-hot-toast"
import { addAthleteToCoach } from "../api/CoachApiService"
import { useAuth } from "../security/AuthContext"
import { useNavigate } from "react-router-dom"

const AddAthleteComponent = () => {

    const authContext = useAuth()
    const navigate = useNavigate()

    const [search, setSearch] = useState(false)
    const [lastname, setLastname] = useState('')
    const [athletes, setAthletes] = useState([])

    const errorToast = (message) => toast.error(message)
    const successToast = (message) => toast.success(message)

    const handleFieldChange = (event) => {
        setLastname(event.target.value)
    }

    const handleSetSearchBtn = () => {
        

        if(lastname.length >= 2) {


        getByLastnameApi(lastname)
            .then(response => {
                console.log(response)
                setAthletes(response.data)
                if(response.data.length > 0) {
                    setSearch(true)
                } else {
                    errorToast('We are sorry, but we could not find any athletes that match your search.')
                }
            })
            .catch(error => console.error(error))
        } else {
            errorToast('Please enter at least two characters for search.')
        }
    }
    const handleAddBtn = (id, lastname) => {

        addAthleteToCoach(authContext.coachId, id)
            .then(response => {
                console.log(response)
                successToast('Athlete ' + lastname + ' successfully added!')
                navigate(`/coach`)
            })
            .catch(error => console.log(error))
    }
    const handleBackBtn = () => {

        navigate(`/coach`)
    }

    return (
        <div className="addAthleteComponent">
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col"></div>
                    <div className="col">
                        <button className="btn btn-outline-primary" onClick={() => handleBackBtn()}>Back</button>
                    </div>
                </div>
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        <label className = "form-label">Find by lastname:</label>
                        <input type = "text" name = "sequence" className = "form-control" onChange={handleFieldChange}/>
                        <button className="btn btn-outline-primary m-2" onClick={() => handleSetSearchBtn()}>Search</button>
                        {search && 
                            <table className="table">
                                <thead>
                                    <tr>
                                        <th>Lp.</th>
                                        <th>First name</th>
                                        <th>Last name</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                       athletes.map((athlete, index) => (
                                            <tr key = {index}>
                                                <td>{index + 1}</td>
                                                <td>{athlete.firstName}</td>
                                                <td>{athlete.lastName}</td>
                                                <td><button className="btn btn-outline-primary" 
                                                    onClick={() => handleAddBtn(athlete.id, athlete.lastName)}>Add</button></td>
                                            </tr>
                                       )) 
                                    }
                                </tbody>
                            </table>
                        }
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        </div>
    ) 
}
export default AddAthleteComponent