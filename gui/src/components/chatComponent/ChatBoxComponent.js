import { useEffect, useState } from "react"
import { over } from "stompjs"
import SockJS from "sockjs-client"
import { getChatMessagesApi } from "../api/ChatApiService"
import './ChatBoxComponent.css'
import { sendMessageApi } from "../api/UserApiService"

var stompClient = null

const ChatBoxComponent = ({athleteId, coachId, name}) => {

    useEffect(() => disconnectFromChat(), [athleteId])

    const [chat, setChat] = useState(new Map())
    const [message, setMessage] = useState('')

    const handleMessageChange = (event) => {
        setMessage(event.target.value)
    }

    const handleSendMessage = () => {
        setMessage('')
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
        if(connection.connected) {
            stompClient.disconnect()
            setConnection({"connected":false})
        }
    }
    const getChatHistory = () => {

        getChatMessagesApi(athleteId, coachId)
            .then(response => {
                console.log(response)
                chat.set(athleteId, response.data)
            })
            .catch(error => console.log(error))
    }
    const onConnected = () => {
        setConnection({"connected":true})
        let source = athleteId + '_' + coachId
        stompClient.subscribe("/user/" + source + "/private", onMessageReceived)
    }
    const onMessageReceived = (payload) => {
        let payloadData = JSON.parse(payload.body)

        let athleteId = parseInt(payloadData.athleteId)

        if(chat.get(athleteId)){

            setChat(prevChat => {
                const updatedChat = new Map(prevChat)

                const currentArray = updatedChat.get(athleteId)

                const updatedArray = [...currentArray, payloadData]

                updatedChat.set(athleteId, updatedArray)

                return updatedChat
            })
        }
    }
    const onError = (error) => {
        console.log(error)
    }
    const activeChat = chat.get(athleteId) || []

    const chatMessage = (message) => {
        return (
            <div className="message">
                <ul className="list-group list-group-flush">
                    <li className="list-group-item list-group-item-dark"><b>{formatTimestamp(message.timestamp)}</b></li>
                    <li className="list-group-item ">{message.content}</li>
                </ul>
            </div>
        )
    }
    const formatTimestamp = (timestamp) => {
        const date = new Date(timestamp)
        const now = new Date()
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const time = `${hours}:${minutes}`

        if(date.toDateString() === now.toDateString()) {
            return `Today ${time}`
        }
        const msInDay = 24 * 60 * 60 * 1000;
        const dayDifference = Math.floor((now - date) / msInDay);

        if (dayDifference <= 6) {
            const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
            const dayOfWeek = daysOfWeek[date.getDay()];
            return `${dayOfWeek} ${time}`;
        }
        return 'Older';
    }

    return(
        <div className="chatBoxComponent">
            <div className="chatBox">
                {connection.connected &&
                <div>
                    <div className="chat-content">
                        <ul className="message-list">
                            {activeChat.map((message, index) =>(
                                <li className="message-item" key = {index}>
                                    {chatMessage(message)}
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
                <div className="connect">
                    {!connection.connected &&
                        <button className="btn btn-outline-info m-2" onClick={connectToChat}>Chat with {name}</button>
                    }
                    {connection.connected &&
                        <button className="btn btn-outline-info m-2" onClick={disconnectFromChat}>Close chat</button>
                    }
                </div>
            </div>
        </div>
    )
}
export default ChatBoxComponent