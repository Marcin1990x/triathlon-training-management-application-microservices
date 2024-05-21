import { useState } from "react"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"
import { addNewTrainingPlanToCoachApi } from "../api/TrainingPlanApiService"
import { useAuth } from "../security/AuthContext"
import { toast } from "react-hot-toast"
import NewStageComponent from "./NewStageComponent"

const NewTrainingPlanComponent = () => {

    const successToast = (message) => toast.success(message)
    const errorToast = (message) => toast.error(message)

    const [sport, setSport] = useState('')
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [showStageQuestion, setShowStageQuestion] = useState(false)
    const [stageView, setStageView] = useState(false)

    const [stageType, setStageType] = useState('')
    const [addedPlanId, setAddedPlanId] = useState(null)

    const switchShowStageQuestion = () => {
        setShowStageQuestion(!showStageQuestion)
    }
    const switchStageView = () => {
        setStageView(!stageView)
    }

    const dataContextTrainings = useDataContextTrainings()
    const authContext = useAuth()

    const handleSelectSportChange = (event) => {
        setSport(event.target.value)
    }
    const handleNameChange = (event) => {
        setName(event.target.value)
    }
    const handleDescriptionChange = (event) => {
        setDescription(event.target.value)
    }
    const handleCancelButton = () => {
        dataContextTrainings.switchView()
    }
    const handleAddButton = () => {

        if(name != '' && sport != '' && !sport.includes('O') && description != '') {
            switchShowStageQuestion()
        } else {
            errorToast('Please fill out all fields.')
        }
    }

    const handleStageNoBtn = () => {

        const newTraining = {
            name : name,
            trainingType : sport,
            description : description
        }
        addNewTrainingPlanToCoachApi(authContext.coachId, newTraining)
            .then(response => {
                console.log(response)
                dataContextTrainings.switchView()
                switchShowStageQuestion()
                dataContextTrainings.getCoachTrainingPlans()
                successToast(`New training plan added successfully.`)
            })
            .catch(error => console.log(error))
    }   
    const handleStageYesButton = () => {

        const newTraining = {
            name : name,
            trainingType : sport,
            description : description
        }
        addNewTrainingPlanToCoachApi(authContext.coachId, newTraining)
            .then(response => {
                console.log(response)
                setAddedPlanId(response.data.id)
            })
            .catch(error => console.log(error))
        
        setStageType(sport)
        switchStageView()
    }   

    return (
        <div>
            {!showStageQuestion && !stageView &&
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        <label className = "form-label">Choose sport type:</label>
                        <select className="form-select m-2" value = {sport} onChange={handleSelectSportChange}>
                            <option selected>Open this select menu</option>
                            <option value="SWIM">Swim</option>
                            <option value="BIKE">Bike</option>
                            <option value="RUN">Run</option>
                            <option value="WEIGHT">Weight</option>
                        </select>
                    </div>
                    <div className="col"></div>
                </div>  
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        <label className = "form-label">Plan name:</label>
                        <input type = "text" name = "name" className = "form-control" value = {name} 
                                onChange = {handleNameChange} required/>
                    </div>
                    <div className="col"></div>    
                </div>  
                <div className="row">   
                    <div className="col"></div>
                    <div className="col">   
                        <label className = "form-label">Description:</label>
                        <input type = "text" name = "description" className = "form-control" value = {description} 
                                onChange = {handleDescriptionChange} required/>
                    </div>
                    <div className="col"></div>
                </div>
                <div className="row">
                    <div className="col"></div>
                    <div className="col">
                        <button className="btn btn-outline-success m-2" onClick = {() => handleAddButton()}>Add</button>
                        <button className="btn btn-outline-danger m-2" onClick = {() => handleCancelButton()}>Cancel</button>
                    </div>
                    <div className="col"></div>
                </div>
            </div>
            }
            {showStageQuestion && !stageView &&
            <div className="stages">
                <h4>Do you want to add stages?</h4>
                <button className="btn btn-outline-success m-2" onClick={() => handleStageYesButton()}>Yes</button>
                <button className="btn btn-outline-danger m-2" onClick={() => handleStageNoBtn()}>No</button>
            </div>
            }
            {stageView &&
                <NewStageComponent planId={addedPlanId} stageType={stageType}/>
            }
        </div>
    )
}

export default NewTrainingPlanComponent