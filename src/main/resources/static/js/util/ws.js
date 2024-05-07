import { Stomp } from "@stomp/stompjs"
import SockJS from 'sockjs-client'

let stompClient = Stomp.over(() => new SockJS('http://localhost:8081/gs-guide-websocket'))
const handlers = []

stompClient.debug = function (){}

stompClient.onConnect = frame => {
    console.log('Connected: ' + frame)
    stompClient.subscribe('/topic/activity', message => {
        handlers.forEach(handler => handler(JSON.parse(message.body)))
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error)
}

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message'])
    console.error('Additional details: ' + frame.body)
}

export function connect() {
    stompClient.activate()
}

export function disconnect() {
    stompClient.deactivate().then(r => console.log("Disconnected"))
}

export function sendMessage(message) {
    stompClient.publish({
        destination: "/app/changeMessage",
        body: JSON.stringify(message)
    })
}