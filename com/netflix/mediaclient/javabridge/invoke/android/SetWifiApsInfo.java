// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetWifiApsInfo extends BaseInvoke
{
    private static final String METHOD = "setWifiApsInfo";
    private static final String PROPERTY_connectedApProp = "connectedap";
    private static final String PROPERTY_deviceCategory = "deviceCategory";
    private static final String PROPERTY_frequency = "f";
    private static final String PROPERTY_listOfAps = "wifiprop";
    private static final String PROPERTY_numWifiAps = "numwifiaps";
    private static final String PROPERTY_signalStrength = "ss";
    private static final String PROPERTY_wifiApsInfo = "wifiapsinfo";
    private static final String TARGET = "android";
    WifiManager mainWifi;
    
    public SetWifiApsInfo(Context context, final String s, final boolean b) {
        super("android", "setWifiApsInfo");
        this.mainWifi = (WifiManager)context.getSystemService("wifi");
        if (b) {
            this.setArguments(context, s);
        }
        else {
            context = (Context)new JSONObject();
            while (true) {
                try {
                    ((JSONObject)context).put("wifiapsinfo", (Object)"");
                    this.arguments = ((JSONObject)context).toString();
                    if (Log.isLoggable("nf_invoke", 3)) {
                        Log.d("nf_invoke", "WiFi APs Info: " + ((JSONObject)context).toString());
                    }
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
    
    private void setArguments(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONArray;
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore          6
        //     9: aload_0        
        //    10: getfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiApsInfo.mainWifi:Landroid/net/wifi/WifiManager;
        //    13: invokevirtual   android/net/wifi/WifiManager.getConnectionInfo:()Landroid/net/wifi/WifiInfo;
        //    16: astore          7
        //    18: aconst_null    
        //    19: astore_1       
        //    20: aload           7
        //    22: ifnull          72
        //    25: aload           7
        //    27: invokevirtual   android/net/wifi/WifiInfo.getSSID:()Ljava/lang/String;
        //    30: astore          4
        //    32: aload           4
        //    34: astore_1       
        //    35: aload           4
        //    37: ldc             "\""
        //    39: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    42: ifeq            72
        //    45: aload           4
        //    47: astore_1       
        //    48: aload           4
        //    50: ldc             "\""
        //    52: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    55: ifeq            72
        //    58: aload           4
        //    60: iconst_1       
        //    61: aload           4
        //    63: invokevirtual   java/lang/String.length:()I
        //    66: iconst_1       
        //    67: isub           
        //    68: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    71: astore_1       
        //    72: new             Lorg/json/JSONObject;
        //    75: dup            
        //    76: invokespecial   org/json/JSONObject.<init>:()V
        //    79: astore          8
        //    81: aload_0        
        //    82: getfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiApsInfo.mainWifi:Landroid/net/wifi/WifiManager;
        //    85: invokevirtual   android/net/wifi/WifiManager.getScanResults:()Ljava/util/List;
        //    88: astore          9
        //    90: aload           9
        //    92: ifnull          540
        //    95: iconst_0       
        //    96: istore_3       
        //    97: aconst_null    
        //    98: astore          4
        //   100: aconst_null    
        //   101: astore          5
        //   103: iload_3        
        //   104: aload           9
        //   106: invokeinterface java/util/List.size:()I
        //   111: if_icmpge       419
        //   114: new             Lorg/json/JSONObject;
        //   117: dup            
        //   118: invokespecial   org/json/JSONObject.<init>:()V
        //   121: astore          5
        //   123: aload           5
        //   125: ldc             "f"
        //   127: aload           9
        //   129: iload_3        
        //   130: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   135: checkcast       Landroid/net/wifi/ScanResult;
        //   138: getfield        android/net/wifi/ScanResult.frequency:I
        //   141: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   144: pop            
        //   145: aload           5
        //   147: ldc             "ss"
        //   149: aload           9
        //   151: iload_3        
        //   152: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   157: checkcast       Landroid/net/wifi/ScanResult;
        //   160: getfield        android/net/wifi/ScanResult.level:I
        //   163: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   166: pop            
        //   167: ldc             "nf_invoke"
        //   169: iconst_3       
        //   170: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   173: ifeq            227
        //   176: ldc             "nf_invoke"
        //   178: new             Ljava/lang/StringBuilder;
        //   181: dup            
        //   182: invokespecial   java/lang/StringBuilder.<init>:()V
        //   185: ldc             "WiFi ssid: "
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: aload           9
        //   192: iload_3        
        //   193: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   198: checkcast       Landroid/net/wifi/ScanResult;
        //   201: getfield        android/net/wifi/ScanResult.SSID:Ljava/lang/String;
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: ldc             " Current Ap: "
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: aload           7
        //   214: invokevirtual   android/net/wifi/WifiInfo.getSSID:()Ljava/lang/String;
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   223: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   226: pop            
        //   227: aload_1        
        //   228: ifnull          629
        //   231: aload_1        
        //   232: aload           9
        //   234: iload_3        
        //   235: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   240: checkcast       Landroid/net/wifi/ScanResult;
        //   243: getfield        android/net/wifi/ScanResult.SSID:Ljava/lang/String;
        //   246: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   249: ifeq            629
        //   252: new             Lorg/json/JSONObject;
        //   255: dup            
        //   256: invokespecial   org/json/JSONObject.<init>:()V
        //   259: astore          4
        //   261: ldc             "nf_invoke"
        //   263: ldc             "WiFi Ap match available"
        //   265: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   268: pop            
        //   269: aload           4
        //   271: ldc             "f"
        //   273: aload           9
        //   275: iload_3        
        //   276: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   281: checkcast       Landroid/net/wifi/ScanResult;
        //   284: getfield        android/net/wifi/ScanResult.frequency:I
        //   287: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   290: pop            
        //   291: aload           4
        //   293: ldc             "ss"
        //   295: aload           9
        //   297: iload_3        
        //   298: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   303: checkcast       Landroid/net/wifi/ScanResult;
        //   306: getfield        android/net/wifi/ScanResult.level:I
        //   309: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   312: pop            
        //   313: ldc             "nf_invoke"
        //   315: iconst_3       
        //   316: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   319: ifeq            404
        //   322: ldc             "nf_invoke"
        //   324: new             Ljava/lang/StringBuilder;
        //   327: dup            
        //   328: invokespecial   java/lang/StringBuilder.<init>:()V
        //   331: ldc             "WiFi prop ssid: "
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: aload           9
        //   338: iload_3        
        //   339: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   344: checkcast       Landroid/net/wifi/ScanResult;
        //   347: getfield        android/net/wifi/ScanResult.SSID:Ljava/lang/String;
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: ldc             " f: "
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: aload           9
        //   360: iload_3        
        //   361: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   366: checkcast       Landroid/net/wifi/ScanResult;
        //   369: getfield        android/net/wifi/ScanResult.frequency:I
        //   372: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   375: ldc             " ss: "
        //   377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: aload           9
        //   382: iload_3        
        //   383: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   388: checkcast       Landroid/net/wifi/ScanResult;
        //   391: getfield        android/net/wifi/ScanResult.level:I
        //   394: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   397: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   400: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   403: pop            
        //   404: aload           6
        //   406: aload           5
        //   408: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   411: pop            
        //   412: iload_3        
        //   413: iconst_1       
        //   414: iadd           
        //   415: istore_3       
        //   416: goto            103
        //   419: new             Lorg/json/JSONObject;
        //   422: dup            
        //   423: invokespecial   org/json/JSONObject.<init>:()V
        //   426: astore_1       
        //   427: aload_1        
        //   428: ldc             "numwifiaps"
        //   430: aload           9
        //   432: invokeinterface java/util/List.size:()I
        //   437: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   440: pop            
        //   441: aload_1        
        //   442: ldc             "wifiprop"
        //   444: aload           6
        //   446: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   449: pop            
        //   450: aload           4
        //   452: ifnull          472
        //   455: ldc             "nf_invoke"
        //   457: ldc             "WiFi Ap connected available"
        //   459: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   462: pop            
        //   463: aload_1        
        //   464: ldc             "connectedap"
        //   466: aload           4
        //   468: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   471: pop            
        //   472: aload_1        
        //   473: ldc             "deviceCategory"
        //   475: aload_2        
        //   476: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   479: pop            
        //   480: aload           8
        //   482: ldc             "wifiapsinfo"
        //   484: aload_1        
        //   485: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   488: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   491: pop            
        //   492: ldc             "nf_invoke"
        //   494: iconst_3       
        //   495: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   498: ifeq            530
        //   501: ldc             "nf_invoke"
        //   503: new             Ljava/lang/StringBuilder;
        //   506: dup            
        //   507: invokespecial   java/lang/StringBuilder.<init>:()V
        //   510: ldc             "WiFi APs Info: "
        //   512: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   515: aload           8
        //   517: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   520: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   523: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   526: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   529: pop            
        //   530: aload_0        
        //   531: aload           8
        //   533: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   536: putfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiApsInfo.arguments:Ljava/lang/String;
        //   539: return         
        //   540: aload           8
        //   542: ldc             "wifiapsinfo"
        //   544: ldc             ""
        //   546: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   549: pop            
        //   550: ldc             "nf_invoke"
        //   552: iconst_3       
        //   553: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   556: ifeq            530
        //   559: ldc             "nf_invoke"
        //   561: new             Ljava/lang/StringBuilder;
        //   564: dup            
        //   565: invokespecial   java/lang/StringBuilder.<init>:()V
        //   568: ldc             "WiFiList unavailable, APs Info: "
        //   570: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   573: aload           8
        //   575: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   578: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   581: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   584: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   587: pop            
        //   588: goto            530
        //   591: astore_1       
        //   592: ldc             "nf_invoke"
        //   594: ldc             "Failed to create JSON object"
        //   596: aload_1        
        //   597: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   600: pop            
        //   601: return         
        //   602: astore_1       
        //   603: ldc             "nf_invoke"
        //   605: ldc             "Unable to Log WiFiApsInfo "
        //   607: aload_1        
        //   608: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   611: pop            
        //   612: return         
        //   613: astore_1       
        //   614: goto            603
        //   617: astore_1       
        //   618: goto            603
        //   621: astore_1       
        //   622: goto            592
        //   625: astore_1       
        //   626: goto            592
        //   629: goto            313
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  9      18     591    592    Lorg/json/JSONException;
        //  9      18     602    603    Ljava/lang/Exception;
        //  25     32     591    592    Lorg/json/JSONException;
        //  25     32     602    603    Ljava/lang/Exception;
        //  35     45     591    592    Lorg/json/JSONException;
        //  35     45     602    603    Ljava/lang/Exception;
        //  48     72     591    592    Lorg/json/JSONException;
        //  48     72     602    603    Ljava/lang/Exception;
        //  72     90     591    592    Lorg/json/JSONException;
        //  72     90     602    603    Ljava/lang/Exception;
        //  103    123    621    625    Lorg/json/JSONException;
        //  103    123    613    617    Ljava/lang/Exception;
        //  123    227    625    629    Lorg/json/JSONException;
        //  123    227    617    621    Ljava/lang/Exception;
        //  231    261    625    629    Lorg/json/JSONException;
        //  231    261    617    621    Ljava/lang/Exception;
        //  261    313    591    592    Lorg/json/JSONException;
        //  261    313    602    603    Ljava/lang/Exception;
        //  313    404    591    592    Lorg/json/JSONException;
        //  313    404    602    603    Ljava/lang/Exception;
        //  404    412    591    592    Lorg/json/JSONException;
        //  404    412    602    603    Ljava/lang/Exception;
        //  419    450    621    625    Lorg/json/JSONException;
        //  419    450    613    617    Ljava/lang/Exception;
        //  455    472    621    625    Lorg/json/JSONException;
        //  455    472    613    617    Ljava/lang/Exception;
        //  472    530    621    625    Lorg/json/JSONException;
        //  472    530    613    617    Ljava/lang/Exception;
        //  530    539    591    592    Lorg/json/JSONException;
        //  530    539    602    603    Ljava/lang/Exception;
        //  540    588    591    592    Lorg/json/JSONException;
        //  540    588    602    603    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 284, Size: 284
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
