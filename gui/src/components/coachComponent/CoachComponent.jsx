import { useEffect } from "react"
import CoachAthletesComponent from "./CoachAthletesComponent"
import CoachTrainingPlansComponent from "./CoachTrainingPlansComponent"
import { useDataContextAthletes } from "./contexts/DataContextAthletes"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"

export default function CoachComponent() {

    const dataContextAthletes = useDataContextAthletes()
    const dataContextTrainings = useDataContextTrainings()

    const buttonText = () => {
        return dataContextAthletes.athleteView ? 'See trainings page' : 'See athletes page';
    }

    useEffect( () => {
        dataContextAthletes.getAthletes()
        dataContextTrainings.getCoachTrainingPlans()
    }, [])

    const handleSwitchViewBtn = () => {
        dataContextAthletes.toggleView()
        dataContextTrainings.setNewTrainingView(false)
    }
    return (
        <div className = "CoachComponent">
            <div className="container">
                <div className="row">
                    <div className="col"></div>
                    <div className="col"></div>
                    <div className="col">
                        {!dataContextAthletes.addPlanMode && !dataContextTrainings.newTrainingView &&
                            <button className = "btn btn-outline-primary" onClick = {handleSwitchViewBtn}>{buttonText()}</button> 
                        }
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <h2>Coach page</h2> 
                        <br></br>
                        {dataContextAthletes.addPlanMode &&
                            <button className = "btn btn-warning m-2" 
                                onClick = {() => dataContextAthletes.handleAddPlanMode(false)}>Cancel adding plan
                            </button>
                        }
                    </div>
                </div>
                <div>
                    {dataContextAthletes.athleteView && <CoachAthletesComponent/>}
                    {!dataContextAthletes.athleteView && <CoachTrainingPlansComponent/>}
                </div>
            </div>
        </div>
    )
}