import { useEffect, useState } from "react"
import WeekdayList from "./WeekdayList"
import TrainingView from "./TrainingView"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import CoachInfoComponent from "./CoachInfoComponent"
import NewTrainingRealization from "./NewTrainingRealization"

export default function AthleteComponent() {

    const dataContextAthlete = useDataContextAthlete()
    const [addRealizationView, setAddRealizationView] = useState(false)
    const [render, setRender] = useState(0)

    const reRender = () => {
        setRender(render + 1)
    }

    useEffect ( () => {
        dataContextAthlete.getTrainingPlans()
        dataContextAthlete.getTrainingRealizations()
        dataContextAthlete.getAthlete()
        }, [render])

    const handleToggleViewBtn = () => {
        toggleView()
    }
    const toggleView = () => {
        setAddRealizationView(!addRealizationView)
    }
    const toggleViewBtn = () => {
        if(addRealizationView) {
            return 'Cancel'
        } else {
            return 'Add realization'
        }
    }
    
    return(
        <div className="AthleteComponent">
            <h2>Athlete page</h2>
            <div className="row">
                <div className="col"></div>
                <div className="col"></div>
                <div className="col"><CoachInfoComponent/></div>
            </div>
            {!addRealizationView &&
                <div className = "weekdaysView">
                    <div className="row">
                        <div className="col"></div>
                        <div className="col-md-10">
                            <WeekdayList/>
                            {dataContextAthlete.activeTraining && 
                                <div className="training-box">
                                    <TrainingView refreshTrainings = {reRender}/> 
                                </div>
                            }
                        </div>
                        <div className="col"></div>
                    </div>
                </div>
            }
            {addRealizationView &&
                <div className="addRealizationView">
                    <NewTrainingRealization toggleView = {toggleView}/>
                </div>
            }
            <div className="row">
                <div className="col"></div>
                <div className="col"></div>
                <div className="col">
                    <button className="btn btn-outline-success m-2" onClick = {() => handleToggleViewBtn()}>{toggleViewBtn()}</button>
                </div>
            </div>
        </div>
    )
}