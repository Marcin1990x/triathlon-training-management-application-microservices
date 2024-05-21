import { useState } from "react"
import { updateTrainingRealizationByIdApi } from "../api/TrainingRealizationApiService"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import { toast } from "react-hot-toast"

const FeelingBox = (props) => {

    const [feelings, setFeelings] = useState('NORMAL')
    const [rpe, setRpe] = useState(0)
    const [description, setDescription] = useState('')
    const dataContextAthlete = useDataContextAthlete()

    const successToast = (message) => toast.success(message)

    const updateTraining = event => {

        event.preventDefault()

        const updateRequest = {
            feelings : feelings,
            rpeLevel : rpe,
            realizationDescription : description
        }

        setDescription('')
        setFeelings('NORMAL')
        setRpe(0)

        updateTrainingRealizationByIdApi(props.trainingId, updateRequest)
            .then(response => {
                console.log(response)
                dataContextAthlete.setActiveTrainingFunction(response.data)
                props.refreshTrainings()
                successToast('Feelings added successfully.')
            })
            .catch(error => console.log(error))
    }
    const feelingsChangeHandler = event => {
        setFeelings(event.target.value)
    }
    const rpeChangeHandler = event => {
        setRpe(event.target.value)
    }
    const descriptionChangeHandler = event => {
        setDescription(event.target.value)
    }
    
    return (
    <div>
        <form onSubmit = {updateTraining}>
            <label>Feelings:</label>
                <select className = "form-select" onChange = {feelingsChangeHandler} value = {feelings}>
                    <option value = 'STRONG'>STRONG</option>
                    <option value = 'GOOD'>GOOD</option>
                    <option value = 'NORMAL'>NORMAL</option>
                    <option value = 'BAD'>BAD</option>
                    <option value = 'WEAK'>WEAK</option>
                </select>
            <label>RPE:</label>    
                <select className = "form-select" onChange = {rpeChangeHandler} value = {rpe}>
                    {[...Array(11).keys()].map(option => (
                        <option key={option} value={option}>{option}</option>
                    ))}
                </select>
            <label>Description:</label> 
                <input type = "text" maxLength = {80} value = {description} className="form-control" onChange = {descriptionChangeHandler}></input>
            <button className = "btn btn-outline-primary m-2" type = "submit">Save</button>
        </form>
    </div>
    )
}
export default FeelingBox