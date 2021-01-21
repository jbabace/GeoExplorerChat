import 'package:flutter/material.dart';

import 'package:chatclienteflutter/src/app.dart';
 
void main() => runApp(MyApp());
 
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Chat Flutter',
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Center(
            child: Text('CHAT')
          ),
        ),
        body: Chat(),
      ),
    );
  }
}