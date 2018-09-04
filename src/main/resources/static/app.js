var stompClient = null;
var serverUrl = null;

function connect() {
    serverUrl = "/secured/chat";
    var Auth_Token = null;
    $.ajax({
        url: '/csrf',
        method: 'get',
        async: false,
        success: function (data) {
            Auth_Token = data;
        }
    })
    var socket = new SockJS(serverUrl);

    stompClient = Stomp.over(socket);
    stompClient.connect({"X-CSRF-TOKEN":Auth_Token}, function (frame) {
        console.log('Connected: ' + frame);
        console.log('sessionId: '+/\/([^\/]+)\/websocket/.exec(socket._transport.url)[1]);
        stompClient.subscribe('/user/secured/history/ordersQueue', function (greeting) {
            console.log(greeting);
            alert(greeting.body)
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

