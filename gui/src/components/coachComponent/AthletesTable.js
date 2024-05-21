import { useState } from "react"
import { useDataContextAthletes } from "./contexts/DataContextAthletes"
import { useWeekdayListVisibility } from "./contexts/WeekdayListVisibilityContext"
import { useNavigate } from "react-router-dom"
import { useDataContextTrainings } from "./contexts/DataContextTrainings"

const AthletesTable = () => {

    const listVisibility = useWeekdayListVisibility()
    const dataContextAthletes = useDataContextAthletes()
    const {activateRealization} = useDataContextTrainings()
    const navigate = useNavigate()

    const [highlightedRow, setHighlightedRow] = useState(null)

    const handleRowClick = (index) => {
      setHighlightedRow(index)
    }

    const handleAddAthleteBtn = () => {
      navigate(`/coach/addAthlete`)
    }

    return (
      <table className="table">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Preview</th>
          </tr>
        </thead>
        <tbody>
          {dataContextAthletes.athletes.map((athlete, index) => (
            <tr key={athlete.id} className = {highlightedRow == index ? "table-primary" : ""}>
              <td>{athlete.firstName}</td>
              <td>{athlete.lastName}</td>
              <td>
                <button className="btn btn-outline-primary" onClick={() => 
                  {dataContextAthletes.setPlansAndRealizationsForAthlete(athlete.id);
                    listVisibility.setVisibility(true)
                    activateRealization(null)
                    handleRowClick(index)}}>+</button>
              </td>
            </tr>
          ))}
          <tr>
            <td></td>
            <td></td>
            <td><button className="btn btn-outline-primary btn-sm" onClick={() => handleAddAthleteBtn()}>Add athlete</button></td>
          </tr>
        </tbody>
      </table>
    )
}

export default AthletesTable