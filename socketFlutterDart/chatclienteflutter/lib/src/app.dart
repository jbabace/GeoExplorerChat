import 'package:flutter/material.dart';
import 'package:chatclienteflutter/src/modelChat/message.dart';

import 'package:get_ip/get_ip.dart';

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
  Socket socketChat;
  String nick = 'Likatz';
  String ruta = 'Ruta X';

  //la lista que contendra los mensajes
  List<MessageItem> items = List<MessageItem>();

  @override
  void initState() {
    connectToServer();
  }
  
  @override
  Widget build(BuildContext context) {
    
    return Center(
      child: Column(
        children: <Widget>[
          messageListArea(),
          submitArea(),
        ],
       ),
       
       
    );
  }

  void connectToServer() async {
    print('conectando ...');
    Socket.connect(localIP, port, timeout: Duration(seconds: 5))
      .then((misocket) {
        socketChat = misocket;
        print("connected to ${socketChat.remoteAddress.address}:${socketChat.remotePort}");
        sendLoginMsg("login");

        socketChat.listen(
          (data) => escucharServer(utf8.decode(data)),
        );
      } 
    );
  }

  void sendLoginMsg(String action){
    var loginMsg = Map();

    loginMsg["action"] = action;
    loginMsg["user"] = nick;
    loginMsg["route"] = ruta;

    socketChat?.write('${jsonEncode(loginMsg)}\n');

  }

  void escucharServer(json){
    var data = jsonDecode(json);
    print('onData: $data');
    setState(() {
      print("data");
      items.insert(0, MessageItem(data['from'], data['value']));
    });
  }

  Widget messageListArea() {
    return Expanded(
      child: ListView.builder(
        reverse: true,
        itemCount: items.length,
        itemBuilder: (context, index) {
          MessageItem item = items[index];
          return Container(
            alignment: (item.usuario == nick) ? Alignment.centerRight : Alignment.centerLeft,
            child: Container(
              margin: const EdgeInsets.symmetric(vertical: 5, horizontal: 10),
              padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 10),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(10),
                color: (item.usuario == nick) ? Colors.lightGreen : Colors.cyan
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    (item.usuario == nick) ? 'Yo' : item.usuario,
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.white70),
                  ),
                  Text(
                    item.contenido,
                    style: TextStyle(fontSize: 18, color: Colors.white),
                  ),
                ]
              ),
            ),
          );
        }
      )
    );
  }

  Widget submitArea() {
    return Card(
      child: ListTile(
        title: TextField(
          controller: messageController,
          decoration: InputDecoration(
            filled: true,
            fillColor: Colors.lightGreen,
          ),
        ),
        trailing: Ink(
          decoration: const ShapeDecoration(
            color: Colors.lightGreen,
            shape: CircleBorder(),
          ),
          child: IconButton(
            iconSize: 30,
            icon: Icon(Icons.send),
            color: Colors.white,
            onPressed: (socketChat != null) ? submitMessage : null,
            padding: EdgeInsets.fromLTRB(3, 0, 0, 0.0),
          ),
        ),
      ),
    );
  }

  void submitMessage() {
    if (messageController.text.isNotEmpty){
      var msg = messageController.text;
      messageController.clear();

      setState(() {
        items.insert(0, MessageItem(nick, msg));
      });

      var chatMsg = Map();

      chatMsg["action"] = "msg";
      chatMsg["user"] = nick;
      chatMsg["route"] = ruta;
      chatMsg["value"] = msg;

      socketChat?.write('${jsonEncode(chatMsg)}\n');
    }
    
  }

}