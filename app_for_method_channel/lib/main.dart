import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(
      MaterialApp(
        debugShowCheckedModeBanner: false,
        home: MyApp(),
      ),
    );

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _State();
  }
}

class _State extends State<MyApp> {
  static const _platform = const MethodChannel("com.keicode.flutter/test1");

  String _label1 = '';

  void _getDataFromPlatform() async {
    var paramMap = <String, dynamic>{
      'a': 7,
      'b': 8,
    };
    var resMap = <dynamic, dynamic>{};

    try {
      resMap = await _platform.invokeMethod(
        "Func1",
        paramMap,
      );
      var calcResult = resMap["calcResult"];
      var deviceName = resMap["deviceName"];

      setState(() {
        _label1 = "$calcResult ($deviceName)";
      });
    } catch (e) {
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Channel - Java'),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.all(20.0),
              child: ElevatedButton(
                child: Text('Invoke Method'),
                onPressed: () => _getDataFromPlatform(),
              ),
            ),
            Container(
              padding: EdgeInsets.all(16),
              child: Text(
                _label1,
                style: TextStyle(fontSize: 24),
              ),
            ),
          ],
        ),
      ),
    );
  }
}