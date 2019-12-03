var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#comentarios").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/sesiones', function (greeting) {            
        	mensaje=JSON.parse(greeting.body);
        	mostrarMensaje(mensaje.fechaComentario,mensaje.mensaje.remitente+':<h2>'+mensaje.mensaje.titulo+'</h2><p>'+mensaje.mensaje.cuerpo+'</p>');        	
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

function sendName() {
    stompClient.send("/app/comentarios", {}, JSON.stringify({'remitente': $("#nombre").val(),'titulo':$("#titulo").val(),'cuerpo':$("#comentario").val()}));
}

function mostrarMensaje(fecha,message) {
    $("#comentarios").append("<tr><td>"+fecha+"</td><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

