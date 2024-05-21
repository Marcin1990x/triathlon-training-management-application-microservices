import { useDataContextTrainings } from "./contexts/DataContextTrainings"

const TrainingRealizationDetails = () => {

    const {activeRealization} = useDataContextTrainings()

    if(activeRealization) {
        return (
            <div className="TrainingRealizationDetails">
                <h5>{activeRealization.type} : {activeRealization.realizationDate}</h5>
                <div>
                    <ul className="list-group list-group-flush">
                        <li className="list-group-item">Total time: {(activeRealization.timeInSeconds/60).toFixed(1)} minutes</li>
                        <li className="list-group-item">Total distance: {(activeRealization.distanceInMeters/1000).toFixed(2)} km</li>
                        {activeRealization.type != 'SWIM' &&
                            <div>
                                <li className="list-group-item">Average heart rate: {activeRealization.averageHeartrate} bpm</li>
                                <li className="list-group-item">Maximum heart rate: {activeRealization.maxHeartrate} bpm</li>
                            </div>
                        }
                        {activeRealization.type == 'BIKE' &&
                            <div>
                                <li className="list-group-item">Average watts: {activeRealization.averageWatts} watts</li>
                                <li className="list-group-item">Maximum watts: {activeRealization.maxWatts} watts</li>
                            </div>
                        }
                        {activeRealization.feelings && 
                            <div>
                                <li className="list-group-item">
                                    Feelings: {activeRealization.feelings} / RPE level: {activeRealization.rpeLevel}
                                </li>
                                <li className="list-group-item">Training description: {activeRealization.realizationDescription}</li>
                            </div>
                        }
                    </ul>      
                </div>
            </div>
        )
    }
}
export default TrainingRealizationDetails