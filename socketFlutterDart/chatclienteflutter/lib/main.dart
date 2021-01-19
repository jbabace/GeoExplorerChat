import 'package:flutter/material.dart';

import 'package:chatclienteflutter/src/app.dart';
 
void main() => runApp(MyApp());
 
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Chat Flutter',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Chat'),
        ),
        body: Chat(),
      ),
    );
  }
}