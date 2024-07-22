import { useState } from "react"
import { over } from "stompjs"
import SockJS from "sockjs-client"
import { useDataContextAthlete } from "./contexts/DataContextAthlete"

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
        let Sock = new SockJS('http://localhost:8088/ws')
        stompClient = over(Sock)
        stompClient.connect({}, onConnected, onError)
    }
    const disconnectFromChat = () => {
        stompClient.disconnect()
    }
    const onConnected = () => {
        //add need to have coach
        setConnection({"connected":true})
        let source = athlete.id + '_' + coach.id
        stompClient.subscribe("/user/" + source + "/private", onPublicMessageReceived)
    }
    const onPublicMessageReceived = (payload) => {
        let payloadData = JSON.parse(payload.body)
        chat.push(payloadData)
        setChat([...chat])
    }

    const onError = (error) => {
        console.log(error)
    }

    return(
        <div className="chatbox">
            <div className="connect">
                {!connection.connected &&
                    <button className="btn btn-outline-info m-2" onClick={connectToChat}>Chat with coach</button>
                }
                {connection.connected &&
                    <button className="btn btn-outline-info m-2" onClick={disconnectFromChat}>Disconnect - test</button>
                }
            </div>
            {connection.connected &&
            <div>
                <div className="chat-content">
                    {chat.map((mess, index) =>(
                        <li className="message" key = {index}>
                            {mess.content}{mess.athleteId}_{mess.coachId} - {mess.timestamp}
                        </li>
                    ))
                    }
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