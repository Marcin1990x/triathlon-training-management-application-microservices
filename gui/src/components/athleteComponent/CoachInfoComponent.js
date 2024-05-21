import { useDataContextAthlete } from "./contexts/DataContextAthlete"

const CoachInfoComponent = () => {

    const dataContextAthlete = useDataContextAthlete()

    const {coach} = useDataContextAthlete()

    return (
        <div className="coachInfoComponent">
            <h6>My coach:</h6>
            {coach && 
            <p>{dataContextAthlete.coach.firstName} {dataContextAthlete.coach.lastName}</p>
            }
        </div>
    )
}
export default CoachInfoComponent