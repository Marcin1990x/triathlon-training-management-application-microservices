import TrainingPlansTable from "./TrainingPlansTable"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"
import CoachTrainingPlanDetailsComponent from "./CoachTrainingPlanDetailsComponent"
import NewTrainingPlanComponent from "./NewTrainingPlanComponent"

const CoachTrainingPlansComponent = () => {

    const dataContextTrainings = useDataContextTrainings()

    return (
        <div>
            <br/>
            <div className="container">
                <div className="row">
                    <div className="col">
                        {!dataContextTrainings.newTrainingView && <TrainingPlansTable/>}
                        {dataContextTrainings.newTrainingView && <NewTrainingPlanComponent/>}
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        {!dataContextTrainings.newTrainingView && <CoachTrainingPlanDetailsComponent/>}
                    </div>
                </div>
            </div>
        </div>
    )
}
export default CoachTrainingPlansComponent