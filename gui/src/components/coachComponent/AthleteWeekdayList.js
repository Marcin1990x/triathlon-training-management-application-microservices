import { useState } from 'react'
import './styles.css'
import { useWeekdayListVisibility } from './contexts/WeekdayListVisibilityContext';
import { useDataContextAthletes } from './contexts/DataContextAthletes';
import { useDataContextTrainings } from './contexts/DataContextTrainings';

const  AthleteWeekdayList = () =>  {
  const [currentDate, setCurrentDate] = useState(new Date());

  const {setVisibility}= useWeekdayListVisibility()
  const DataContextAthletes = useDataContextAthletes()
  const {activateRealization} = useDataContextTrainings()

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

  function handleTrainingPlanField(date){
    const filtered = DataContextAthletes.athletePlans.filter((plan => plan.plannedDate === formatDate(date)))
    if(filtered.length > 0) {
      return (
        <div className = "row">
            {filtered.map((plan) => ( 
              <div className = "col">
                <div>
                  {planTextField(plan)}
                  {removeTrainingPlanBtn(plan.id)}
                </div> 
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
    const filtered = DataContextAthletes.athleteRealizations.filter((realization => realization.realizationDate === formatDate(date)))
    if(filtered.length > 0) {
        return(
          <div className = "row">
            {filtered.map((realization) => ( 
              <div className = "col">
                <div>
                  <button className = "btn btn-outline-dark btn-sm m-1" onClick={() => showTrainingRealizationDetails(realization)}>
                  {realizationTextField(realization)}</button>
                </div>
              </div>  
              ))}
          </div>
        )
    } return 'No realization'
  }
  function realizationTextField(realization) {
    return 'Realization: ' + realization.type + ' / ' + realization.name + ' / ' + (realization.distanceInMeters/1000).toFixed(1) + 'km'
  }

  const showTrainingRealizationDetails = (realization) => {
    activateRealization(realization)
  }

  function formatDate(date) {
    const year = date.getFullYear()
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const day = String(date.getDate()).padStart(2, '0')
    const formattedDate = `${year}-${month}-${day}`

    return formattedDate
  }
  // add training plan to athlete

  const addTrainingPlanBtn = (day) => {

    return  <button className = "btn btn-outline-success btn-sm m-1" disabled = {isDateTodayOrLater(day)} onClick = {() => {
                DataContextAthletes.handleAddPlanMode(true, day)}}>Add plan
            </button>
  }
  const isDateTodayOrLater = (date) => {
    const today = new Date()
    today.setHours(1, 0, 0, 0)
    date.setHours(1, 0, 0, 0)

    return date < today
  }

  // remove training plan from athlete

  const removeTrainingPlanBtn = (id) => {
    return <button className = "btn btn-outline-danger btn-sm m-1" onClick={() => DataContextAthletes.removeTrainingPlan(id)}>Remove</button>
  }

  const handleClosePanelBtn = () => {
    setVisibility(false)
    activateRealization(null)
  }

  return (
    <div>
        <div className= "weekdaysList">
          <h5>Athlete week:</h5>
            <button className = "btn btn-outline-primary m-2" onClick={() => handlePrevWeek()}>Previous Week</button>
            <button className = "btn btn-outline-primary m-2" onClick={() => handleNextWeek()}>Next Week</button>
            <ul className = "list-group">
              {getWeekdays(currentDate).map((day, index) => (
                <li className = "athlete-weekdays-list" key={index}>
                  <div className = "row">
                    <div className = "col">{day.toDateString()}</div>
                    <div className = "col">{addTrainingPlanBtn(day)}</div>
                    <div className = "col">{handleTrainingPlanField(day)}</div>
                    <div className = "col">{handleTrainingRealizationField(day)}</div>
                  </div>
                </li>
              ))}
            </ul>
            <button className="btn btn-outline-primary m-1 float-end" 
                onClick = {() => handleClosePanelBtn()}>Close panel</button>
        </div> 
    </div>
  )
}
export default AthleteWeekdayList