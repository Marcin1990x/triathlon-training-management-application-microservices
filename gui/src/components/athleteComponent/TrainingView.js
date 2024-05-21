import { useState } from "react"
import FeelingBox from "./FeelingBox"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import StagesTable from "../coachComponent/StagesTable"

const TrainingView = (props) => {

    const {activeTraining} = useDataContextAthlete()

    const training = activeTraining

    const [feelingsBoxVisible, setFeelingsBoxVisible] = useState(false)
    function handleAddFeelings() {
        if(feelingsBoxVisible){
            setFeelingsBoxVisible(false)
        } else setFeelingsBoxVisible(true)
    }
    if(training && training.trainingPlanStatus) {
        return (
            <div>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item">Planned date: {training.plannedDate}</li>
                    <li className="list-group-item">Training name: {training.name}</li>
                    <li className="list-group-item">Description: {training.description}</li>
                </ul>
                    {training.stage && 
                        <StagesTable trainingPlan={training} stageType={training.trainingType}/> 
                    }
                <br></br>         
            </div>
        )
    }
    if(training && training.timeInSeconds >= 0) {
        return (
            <div className="trainingView">
                <div>
                    <ul className="list-group list-group-flush">
                        <li className="list-group-item">Realization date: {training.realizationDate}</li>
                        <li className="list-group-item">Training type: {training.type}</li>
                        <li className="list-group-item">Total time: {(training.timeInSeconds/60).toFixed(1)} minutes</li>
                        <li className="list-group-item">Total distance: {(training.distanceInMeters/1000).toFixed(2)} km</li>
                        {training.feelings && 
                            <div>
                                <li className="list-group-item">
                                    Feelings: {training.feelings} / RPE level: {training.rpeLevel}
                                </li>
                                <li className="list-group-item">Training description: {training.realizationDescription}</li>
                            </div>
                        }
                    </ul>      
                </div>
                <div>
                    <button className = "btn btn-outline-primary m-1" 
                        onClick = {() => handleAddFeelings(training.id)}>Add feelings
                    </button>
                    {
                        feelingsBoxVisible && <FeelingBox trainingId = {training.id} 
                            refreshTrainings = {props.refreshTrainings}/>
                    }
                </div>
            </div>
        )
    }
}
export default TrainingView