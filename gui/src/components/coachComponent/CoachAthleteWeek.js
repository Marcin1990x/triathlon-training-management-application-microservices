import AthleteWeekdayList from "./AthleteWeekdayList"
import TrainingRealizationDetails from "./TrainingRealizationDetails"
import { useWeekdayListVisibility } from "./contexts/WeekdayListVisibilityContext"

const CoachAthleteWeek = () => {

    const {isListVisible} = useWeekdayListVisibility()

    return(
        <div className="coachAthleteWeek">
            {isListVisible &&
                <div className="container">
                    <div className="row">
                        <AthleteWeekdayList/>
                    </div>
                    <div className="row">
                        <TrainingRealizationDetails/>
                    </div>
                </div>
            }
        </div>
    )
}

export default CoachAthleteWeek