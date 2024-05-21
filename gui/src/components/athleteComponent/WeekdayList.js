import { useState } from 'react';
import './styles.css'
import { useDataContextAthlete } from './contexts/DataContextAthlete';

const  WeekdayList = () =>  {

  const [currentDate, setCurrentDate] = useState(new Date());
  const dataContextAthlete = useDataContextAthlete()

  function getWeekdays(date) {
    const weekdays = [];
    date = new Date(date);
    date.setDate(date.getDate() - date.getDay() + 1)
    for (let i = 0; i < 7; i++) {
      weekdays.push(new Date(date))
      date.setDate(date.getDate() + 1)
    }
    return weekdays;
  }

  function handlePrevWeek(){
    const prevWeek = new Date(currentDate);
    prevWeek.setDate(prevWeek.getDate() - 7);
    setCurrentDate(prevWeek);
  }

  function handleNextWeek(){

    const nextWeek = new Date(currentDate);
    nextWeek.setDate(nextWeek.getDate() + 7);
    setCurrentDate(nextWeek);
  }
  const TrainingButton = (properties) => {
    return (
      <button className = "btn btn-outline-dark btn-sm m-1" onClick = {() => dataContextAthlete.setActiveTrainingFunction(properties.training)}>
        {properties.textField(properties.training)}
      </button>
    )
  }

  function handleTrainingPlanField(date){
    const filtered = dataContextAthlete.trainingPlans.filter((plan => plan.plannedDate === formatDate(date)))
    if(filtered.length > 0) {
      return (
        <div className = "row">
            {filtered.map((plan) => ( 
              <div className = "col">
                <div><TrainingButton training = {plan} textField = {planTextField}/></div> 
              </div>  
            ))}
        </div>
      )
    } return 'Rest day'
  }
  function planTextField(plan) {
    return 'Plan: ' + plan.trainingType + ' / ' + plan.name
  }

  function handleTrainingRealizationField(date){
    const filtered = dataContextAthlete.trainingRealizations.filter((realization => realization.realizationDate === formatDate(date)))
    if(filtered.length > 0) {
        return(
          <div className = "row">
            {filtered.map((realization) => ( 
              <div className = "col">
                <div><TrainingButton training = {realization} textField = {realizationTextField}/></div> 
              </div>  
              ))}
          </div>
        )
    } return 'No realization'
  }
  function realizationTextField(realization) {
    return 'Realization: ' + realization.type + ' / ' + realization.name + ' / ' + (realization.distanceInMeters/1000).toFixed(1) + 'km'
  }

  function formatDate(date) {
    const year = date.getFullYear()
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const day = String(date.getDate()).padStart(2, '0')
    const formattedDate = `${year}-${month}-${day}`

    return formattedDate
  }

  return (
    <div>
      <button className = "btn btn-outline-primary m-2" onClick={() => handlePrevWeek()}>Previous Week</button>
      <button className = "btn btn-outline-primary m-2" onClick={() => handleNextWeek()}>Next Week</button>
      <ul className = "list-group">
        {getWeekdays(currentDate).map((day, index) => (
          <li className = "weekdays-list" key={index}>
            <div className = "row">
              <div className = "col">{day.toDateString()}</div>
              <div className = "col">
                {handleTrainingPlanField(day)}
              </div>
              <div className = "col">
                {handleTrainingRealizationField(day)}
              </div>
            </div>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default WeekdayList