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
            // console.log(greeting);
            console.log(greeting.body);
            if (Notification.permission == "granted") {
                var notification = new Notification("新订单提醒：", {
                    body: greeting.body,
                    icon: 'https://www.baeldung.com/wp-content/uploads/2017/06/homepage-other_spring.jpg'
                });

                // notification.onclick = function() {
                //     text.innerHTML = '张小姐已于' + new Date().toTimeString().split(' ')[0] + '加你为好友！';
                //     notification.close();
                // };
            }
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


$(function () {
   connect();
});

