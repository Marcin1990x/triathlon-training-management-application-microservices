import { useEffect, useState } from "react"
import WeekdayList from "./WeekdayList"
import TrainingView from "./TrainingView"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import CoachInfoComponent from "./CoachInfoComponent"
import NewTrainingRealization from "./NewTrainingRealization"
import { over } from "stompjs"
import SockJS from "sockjs-client"

var stompClient = null

export default function AthleteComponent() {

    

    const dataContextAthlete = useDataContextAthlete()
    const [addRealizationView, setAddRealizationView] = useState(false)
    const [render, setRender] = useState(0)

    const [chat, setChat] = useState([])
    const [message, setMessage] = useState('')

    const handleMessageChange = (event) => {
        setMessage(event.target.value)
    }

    const sendMessage = () => {
        if(stompClient) {
            console.log('message to send: ' + message)
            //send message
        }
    }
    const [connection, setConnection] = useState({
        connected:false
    })

    const connectToChat = () => {
        let Sock = new SockJS('http://localhost:8088/ws')
        stompClient = over(Sock)
        stompClient.connect({}, onConnected, onError)
    }
    const onConnected = () => {
        setConnection({"connected":true})
        stompClient.subscribe("/chatroom", onPublicMessageReceived)
        
    }
    const onPublicMessageReceived = (payload) => {
        let payloadData = JSON.parse(payload.body)
        chat.push(payloadData)
        console.log(payload)
        setChat([...chat])
    }
    const onError = (error) => {
        console.log(error)
    }
    

    const reRender = () => {
        setRender(render + 1)
    }

    useEffect ( () => {
        dataContextAthlete.getAthlete()
        dataContextAthlete.checkPendingCoachingRequests()
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
                <div className="col">
                    <div className="border border-dark">
                    <CoachInfoComponent/>
                    </div>
                </div>
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
            <div className="chat">
                Chat
                {connection &&
                <div>
                    <div className="chat-content">
                        {chat.map((mess, index) =>(
                            <li className="message" key = {index}>
                                {mess.message}
                            </li>
                        ))
                        }
                        
                    </div>
                    <div className="send-message">
                        <input type = "text" value={message}/>
                        <button className="btn btn-outline-danger m-2" onClick={() =>sendMessage}>send</button>
                    </div>
                </div>
                }
                <div className="connect">
                        <button className="btn btn-outline-info m-2" onClick={connectToChat}>Connect</button>
                </div>
            </div>
        </div>
    )
}