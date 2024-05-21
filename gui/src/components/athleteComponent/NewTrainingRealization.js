import { useState } from "react"
import { addNewTrainingRealizationForAthlete } from "../api/TrainingRealizationApiService"
import { useAuth } from "../security/AuthContext"
import { toast } from "react-hot-toast"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"

const NewTrainingRealization = ({toggleView}) => {

    const {athleteId} = useAuth()
    const {getTrainingRealizations} = useDataContextAthlete()
    const successToast = (message) => toast.success(message)
    const errorToast = (message) => toast.error(message)

    const [formFields, setFormFields] = useState({
        sport: 'RUN',
        name: 'Training',
        date: '',
        distanceKm: 0,
        distanceHundreds: 0,
        timeH: 0,
        timeM: 0,
        timeS: 0,
        averageWatts: 0,
        maxWatts: 0,
        averageHeartRate: 99,
        maxHeartRate: 99,
        description: '',
        feelings: 'NORMAL',
        rpeLevel: 0
    })
    const handleFieldChange = (event) => {
        const {name, value} = event.target
        setFormFields({...formFields, [name] : value})
    }

    const handleAddRealization = (event) => {

        event.preventDefault()

        if(isDateCorrect(formFields.date)) {

            const newTraining = {
                name: formFields.name,
                distanceInMeters: countDistance(formFields.distanceKm, formFields.distanceHundreds),
                timeInSeconds: countTime(formFields.timeH, formFields.timeM, formFields.timeS),
                type: formFields.sport,
                realizationDate: formFields.date,
                averageWarrs: formFields.averageWatts,
                maxWatts: formFields.maxWatts,
                averageHeartrate: formFields.averageHeartRate,
                maxHeartrate: formFields.maxHeartRate,
                realizationDescription: formFields.description,
                feelings: formFields.feelings,
                rpeLevel: formFields.rpeLevel
            }

            addNewTrainingRealizationForAthlete(athleteId, newTraining)
                .then(response => {
                    console.log(response)
                    successToast('New training realization added successfully.')
                    getTrainingRealizations()
                    toggleView()
                })
                .catch(error => console.log(error))
        } else {
            errorToast('Given date should not be in the future.')
        }
    }

    const isDateCorrect = (date) => {
        const today = new Date()
        const givenDate = new Date(date)

        return givenDate <= today
    }

    const countDistance = (km, hundreds) => {
        return km * 1000 + hundreds * 100
    }
    const countTime = (hours, minutes, seconds) => {
        return hours * 3600 + minutes * 60 + seconds * 1
    }

    return (
        <div className="newTrainingRealizaion">
            <div className="row">
                <div className="col"></div>
                <div className="col">
                <form onSubmit = {handleAddRealization}>   
                    <label className = "form-label">Choose sport type:</label>
                    <select className="form-select m-2" name = "sport" value = {formFields.sport} onChange={handleFieldChange}>
                        <option value="SWIM">Swim</option>
                        <option value="BIKE">Bike</option>
                        <option value="RUN">Run</option>
                        <option value="WEIGHT">Weight</option>
                    </select>
                    <label className = "form-label">Training name:</label>
                    <input type = "text" name = "name" className = "form-control" value = {formFields.name}
                        onChange = {handleFieldChange} required/>
                    <label className = "form-label">Realization date:</label>
                    <input type = "date" name = "date" className = "form-control" value = {formFields.date}
                        onChange = {handleFieldChange} required/>
                    {formFields.sport != 'WEIGHT' &&
                    <div>
                        <label className = "form-label">Distance [km]:</label>
                        <div className="row">
                            <div className="col-6">
                                <input type = "number" name = "distanceKm" className = "form-control" min = {0} max = {1000}
                                    value = {formFields.distanceKm} onChange = {handleFieldChange} required/>
                                </div>
                            <div className="col-1">
                                <h5>,</h5>
                            </div>
                            <div className="col-5">
                                <input type = "number" name = "distanceHundreds" className = "form-control" min = {0} max = {9}
                                    value = {formFields.distanceHundreds} onChange = {handleFieldChange} required/>
                            </div>
                        </div>
                    </div>
                    }
                    <label className = "form-label">Time:</label>
                    <div className="row">
                        <div className="col">
                            <input type = "number" name = "timeH" className = "form-control" value = {formFields.timeH}
                                min = {0} max = {99} onChange = {handleFieldChange} required/> 
                            <p>h</p>
                        </div>
                        <div className="col">
                            <input type = "number" name = "timeM" className = "form-control" value = {formFields.timeM}
                                min = {0} max = {59} onChange = {handleFieldChange} required/>
                            <p>m</p>
                        </div>
                        <div className="col">
                            <input type = "number" name = "timeS" className = "form-control" value = {formFields.timeS}
                                min = {0} max = {59} onChange = {handleFieldChange} required/>
                            <p>s</p>
                        </div>
                    </div>
                    {formFields.sport == 'BIKE' &&
                    <div>
                        <label className = "form-label">Average watts:</label>
                        <input type = "number" name = "averageWatts" className = "form-control" value = {formFields.averageWatts}
                                min = {1} max = {1000} onChange = {handleFieldChange}/>
                        <label className = "form-label">Maximum watts:</label>
                        <input type = "number" name = "maxWatts" className = "form-control" value = {formFields.maxWatts}
                            min = {1} max = {3000} onChange = {handleFieldChange}/>
                    </div>
                    }
                    {formFields.sport != 'SWIM' &&
                    <div>    
                        <label className = "form-label">Average heart rate:</label>
                        <input type = "number" name = "averageHeartRate" className = "form-control" value = {formFields.averageHeartRate}
                            min = {30} max = {220} onChange = {handleFieldChange}/>
                        <label className = "form-label">Maximum heart rate:</label>
                        <input type = "number" name = "maxHeartRate" className = "form-control" value = {formFields.maxHeartRate}
                            min = {30} max = {250} onChange = {handleFieldChange}/>
                    </div>
                    }    
                    <label className = "form-label">Description:</label>
                    <input type = "text" name = "description" className = "form-control" value = {formFields.description}
                        onChange = {handleFieldChange}/>
                    <label className = "form-label">Feelings:</label>
                    <select className="form-select m-2" name = "feelings" value = {formFields.feelings} onChange={handleFieldChange}>
                        <option value="WEAK">Weak</option>
                        <option value="BAD">Bad</option>
                        <option value="NORMAL">Normal</option>
                        <option value="GOOD">Good</option>
                        <option value="STRONG">Strong</option>
                    </select>
                    <label className = "form-label">RPE level:</label>
                    <select className="form-select m-2" name = "rpeLevel" value = {formFields.rpeLevel} onChange={handleFieldChange}>
                        {[...Array(11).keys()].map(option => (
                                        <option key={option} value={option}>{option}</option>
                        ))}
                    </select>
                    <button className="btn btn-outline-success m-2" type = "submit">Add training</button>
                </form>         
                </div>
                <div className="col"></div>
            </div>  
        </div>
    )
}
export default NewTrainingRealization