import { useState } from "react"
import { over } from "stompjs"
import SockJS from "sockjs-client"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"
import { getChatMessagesApi } from "../api/ChatApiService"
import './ChatBoxComponent.css'

var stompClient = null

const ChatBoxComponent = () => {

    const {athlete, coach, sendMessage} = useDataContextAthlete()

    const [chat, setChat] = useState([])
    const [message, setMessage] = useState('')

    const handleMessageChange = (event) => {
        setMessage(event.target.value)
    }

    const handleSendMessage = () => {

        console.log('Message to send: ' + message)
        sendMessage(message)
    }
    const [connection, setConnection] = useState({
        connected:false
    })

    const connectToChat = () => {

        getChatHistory()

        let Sock = new SockJS('http://localhost:8088/ws')
        stompClient = over(Sock)
        stompClient.connect({}, onConnected, onError)
    }
    const disconnectFromChat = () => {
        stompClient.disconnect()
    }

    const getChatHistory = () => {
        getChatMessagesApi(athlete.id, coach.id)
            .then(response => {
                console.log(response)
                setChat(prevChat => [...prevChat, ...response.data])
            })
            .catch(error => console.log(error))
    }

    const onConnected = () => {
        setConnection({"connected":true})
        let source = athlete.id + '_' + coach.id
        stompClient.subscribe("/user/" + source + "/private", onPublicMessageReceived)
    }
    const onPublicMessageReceived = (payload) => {
        let payloadData = JSON.parse(payload.body)
        setChat(prevChat => [...prevChat, payloadData])
    }

    const onError = (error) => {
        console.log(error)
    }

    return(
        <div className="chatbox">
            <div className="connect">
                {!connection.connected && coach &&
                    <button className="btn btn-outline-info m-2" onClick={connectToChat}>Open chat</button>
                }
                {connection.connected &&
                    <button className="btn btn-outline-info m-2" onClick={disconnectFromChat}>Disconnect - test</button>
                }
            </div>
            {connection.connected &&
            <div>
                <div className="chat-content">
                    <ul className="message-list">
                        {chat.map((message, index) =>(
                            <li className="message-item" key = {index}>
                                {message.content}{message.athleteId}_{message.coachId} - {message.timestamp}
                            </li>
                        ))
                        }
                    </ul>
                </div>
                <div className="send-message">
                    <input type = "text" value={message} onChange={handleMessageChange}/>
                    <button className="btn btn-outline-danger m-2" onClick={handleSendMessage}>Send message</button>
                </div>
            </div>
            }
        </div>
    )
}
export default ChatBoxComponent