const StagesTable = ({stageType, trainingPlan}) => {

    return (
        <table className = "table">
            <thead>
                <tr>
                    <th>Sequence</th>
                    <th>Distance</th>
                    <th>Time</th>
                    <th>Heart rate</th>
                    {stageType == 'BIKE' && <th>Power</th>}
                    {(stageType == 'RUN' || stageType == 'SWIM') && <th>Pace</th>}
                    <th>Description</th>
                    <th>Repeat</th>
                </tr>
            </thead>
            <tbody>
            {
                trainingPlan?.stage?.map (
                    (stage) => (
                        <tr key={stage.id}>
                            <td>{stage.sequence}</td>
                            <td>{stage.distanceInMeters}</td>
                            <td>{stage.timeInSeconds}</td>
                            <td>{stage.heartRate}</td>
                            {stageType == 'BIKE' && <td>{stage.power}</td>}
                            {stageType == 'RUN' && <td>{stage.paceInSecondsPerKm}</td>}
                            {stageType == 'SWIM' && <td>{stage.paceInSeconds}</td>}
                            <td>{stage.description}</td>
                            <td>{stage.repeat}</td>
                        </tr>
                    )
                )
            }
            </tbody>
        </table>
        )
    }    

export default StagesTable