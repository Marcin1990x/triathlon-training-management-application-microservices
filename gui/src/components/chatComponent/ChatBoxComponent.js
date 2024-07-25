import { useState } from "react"
import { over } from "stompjs"
import SockJS from "sockjs-client"
import { getChatMessagesApi } from "../api/ChatApiService"
import './ChatBoxComponent.css'
import { sendMessageApi } from "../api/UserApiService"

var stompClient = null

const ChatBoxComponent = ({athleteId, coachId}) => {

    const [chat, setChat] = useState([])
    const [message, setMessage] = useState('')

    const handleMessageChange = (event) => {
        setMessage(event.target.value)
    }

    const handleSendMessage = () => {
        sendMessage(message)
    }
    const sendMessage = (content) => {
        sendMessageApi(athleteId, coachId, content)
            .then(response => console.log(response))
            .catch(error => console.log(error))
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

        getChatMessagesApi(athleteId, coachId)
            .then(response => {
                console.log(response)
                setChat(prevChat => [...prevChat, ...response.data])
            })
            .catch(error => console.log(error))
    }

    const onConnected = () => {
        setConnection({"connected":true})
        let source = athleteId + '_' + coachId
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
        <div className="chatBoxComponent">
            <div className="chatBox">
                <div className="connect">
                    {!connection.connected &&
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
        </div>
    )
}
export default ChatBoxComponent