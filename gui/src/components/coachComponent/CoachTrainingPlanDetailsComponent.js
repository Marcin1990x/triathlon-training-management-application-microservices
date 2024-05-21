import { removeTrainingPlanApi } from "../api/TrainingPlanApiService"
import StagesTable from "./StagesTable"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"
import { toast } from "react-hot-toast"

const CoachTrainingPlanDetailsComponent = () => {

    const dataContextTrainings = useDataContextTrainings()

    const successToast = (message) => toast.success(message)

    const plan = dataContextTrainings.activePlan

    const handleDeleteBtn = (id) => {
        removeTrainingPlanApi(id)
            .then(response => { 
                console.log(response)
                dataContextTrainings.getCoachTrainingPlans()
                dataContextTrainings.activatePlan(null)
                successToast('Training plan deleted.')
            })
    }
    return (
        <div>
            {plan && 
            <ul className="list-group list-group-flush">
                <li className="list-group-item">Name: {plan.name}</li>
                <li className="list-group-item">Description: {plan.description}</li>
                <li className="list-group-item">
                    <button className="btn btn-outline-danger m-1" onClick={() => handleDeleteBtn(plan.id)}>Delete training plan</button>
                </li>
            </ul> }
            {plan && dataContextTrainings.activePlan &&
                <StagesTable trainingPlan={plan} stageType={plan.trainingType}/> 
            }  
            <br></br>         
        </div>
    )
}
export default CoachTrainingPlanDetailsComponent