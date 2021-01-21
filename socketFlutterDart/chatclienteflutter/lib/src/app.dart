import 'package:flutter/material.dart';
import 'package:chatclienteflutter/src/modelChat/message.dart';

import 'dart:convert';
import 'dart:io';

class Chat extends StatefulWidget {
  Chat({Key key}) : super(key: key);

  @override
  _ChatState createState() => _ChatState();
}

class _ChatState extends State<Chat> {

  //datos del servidor del chat
  String localIP = "10.10.12.115";
  int port = 1234;

  //el controlador para los mensajes
  TextEditingController messageController = TextEditingController();

  //los datos del usuario
  Socket socket;
  String nick = 'Babace';
  String ruta = 'Ruta X';

  //la lista que contendra los mensajes
  List<MessageItem> items = List<MessageItem>();

  @override
  void initState() {
    //connectToServer()
  }
  
  @override
  Widget build(BuildContext context) {
    
    return Center(
       child: Container(
          child: Text('Hola mundo'),
          //messageListArea(),
          //submitArea(),
       ),
    );
  }

  void connectToServer() async {
    print('conectando ...');

    Socket.connect(localIP, port, timeout: Duration(seconds: 5))
      .then((value) {
        socket = value;
      } 
    );
    print("connected to ${socket.remoteAddress.address}:${socket.remotePort}");

    sendLoginMsg("login");
  }

  void sendLoginMsg(String action, ){

  }

}