import { useState } from "react"
import { useDataContextAthletes } from "./contexts/DataContextAthletes"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"

const TrainingPlansTable = () => {

  const dataContextAthletes = useDataContextAthletes()
  const dataContextTrainings = useDataContextTrainings()

  const [highlightedRow, setHighlightedRow] = useState(null)

    const handleRowClick = (index) => {
      setHighlightedRow(index)
    }

  const handlePreviewPlanBtn = (plan) => {
    dataContextTrainings.activatePlan(plan)
  }

  const handleAddPlanToAthleteBtn = (id) => {
    dataContextAthletes.addTrainingPlanToAthleteWithDate(id)
  }

  const handleAddNewTrainingBtn = () => {
    dataContextTrainings.setNewTrainingView(true)
  }
  
    return (
      <div>
        <table className="table">
          <thead>
            <tr>
              {dataContextAthletes.addPlanMode &&
                <th>Choose plan to add</th>
              }
              <th>Number</th>
              <th>Sport</th>
              <th>Name</th>
              <th>Description</th>
              <th>Stages</th>
            </tr>
          </thead>
          <tbody>
            {dataContextTrainings.trainingPlans.map((plan, index) => 
                  <tr key = {plan.index} className = {highlightedRow == index ? "table-warning" : ""}>
                    {dataContextAthletes.addPlanMode &&
                      <td>
                        <button className = "btn btn-success" onClick = {() => handleAddPlanToAthleteBtn(plan.id)}>Add</button>
                      </td>
                    }
                    <td>{index + 1}</td>
                    <td>{plan.trainingType}</td>
                    <td>{plan.name}</td>
                    <td>{plan.description}</td>
                    <td>{plan.stage.length}</td>
                    <td><button className="btn btn-outline-primary" onClick = {() => { handlePreviewPlanBtn(plan);
                      handleRowClick(index)}}>+</button></td>
                  </tr>
            )}
          </tbody>
        </table>
          <button className = "btn btn-outline-success m-2 float-end" onClick = {() => handleAddNewTrainingBtn()}>Add new training plan</button>
      </div>  
    )
}
export default TrainingPlansTable