var stompClient = null;
var user = null;

function connect() {
    user = localStorage.getItem('Auth-Token');
    var socket = new SockJS('/orderServer');

    stompClient = Stomp.over(socket);
    stompClient.connect({name:user}, function (frame) {
        console.log('Connected: ' + frame);
        console.log('sessionId: '+/\/([^\/]+)\/websocket/.exec(socket._transport.url)[1]);
        stompClient.subscribe('/user/queue/ordersQueue', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
            console.log(greeting)
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function showGreeting(message) {
    if (message.name === user){
        $("#greetings").append("<tr><td style='text-align: right;border: 0'>"+message.content+" <span class='label label-info'>" + message.name + "</span></td></tr>");
    } else {
        $("#greetings").append("<tr><td style='border: 0'><span class='label label-info'> "+message.name+"</span>" + message.content + "</td></tr>");
    }
}

$(function () {
   connect();
});

