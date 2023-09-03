package com.example.app_for_method_channel;

import android.os.Bundle;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterFragmentActivity {

     private static final String CHANNEL = "com.keicode.flutter/test1";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL).setMethodCallHandler(
        (call, result) -> {
            final Map<String, Object> args = call.arguments();
            Dictionary<String, String> results = new Hashtable<String, String>();

            results.put("calcResult", "");
            results.put("deviceName", android.os.Build.MODEL);

            // Get Parameters
            int a, b, c;

            try {
                a = (int) args.get("a");
                b = (int) args.get("b");
                c = a * b;
            } catch (Exception ex) {
                c = -1;
            }

            // Return Results
            if (call.method.equals("Func1")) {
                if (c == -1) {
                    results.put("calcResult", "Error n/a");
                    result.success(results);
                } else {
                    results.put("calcResult", "a * b = " + c);
                    result.success(results);
                }
            }
        });
    }

    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     GeneratedPluginRegistrant.registerWith(this);

    //     new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

    //         @Override
    //         public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
    //             final Map<String, Object> args = methodCall.arguments();
    //             Dictionary<String, String> results = new Hashtable<String, String>();

    //             results.put("calcResult", "");
    //             results.put("deviceName", android.os.Build.MODEL);

    //             // Get Parameters
    //             int a, b, c;

    //             try {
    //                 a = (int) args.get("a");
    //                 b = (int) args.get("b");
    //                 c = a * b;
    //             } catch (Exception ex) {
    //                 c = -1;
    //             }

    //             // Return Results
    //             if (methodCall.method.equals("Func1")) {
    //                 if (c == -1) {
    //                     results.put("calcResult", "Error n/a");
    //                     result.success(results);
    //                 } else {
    //                     results.put("calcResult", "a * b = " + c);
    //                     result.success(results);
    //                 }
    //             }
    //         }
    //     });
    // }
}