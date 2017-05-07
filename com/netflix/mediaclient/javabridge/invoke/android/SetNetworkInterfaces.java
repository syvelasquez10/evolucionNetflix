// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import android.content.Context;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetNetworkInterfaces extends BaseInvoke
{
    private static final String METHOD = "setNetworkInterfaces";
    private static final String PROPERTY_interfaceName = "interfaceName";
    private static final String PROPERTY_internetConnected = "internetConnected";
    private static final String PROPERTY_ipv4Address = "ipv4Address";
    private static final String PROPERTY_isDefault = "isDefault";
    private static final String PROPERTY_linkConnected = "linkConnected";
    private static final String PROPERTY_macAddress = "macAddress";
    private static final String PROPERTY_mobileCarrier = "mobileCarrier";
    private static final String PROPERTY_mobileCountryCode = "mobileCountryCode";
    private static final String PROPERTY_mobileNetworkCode = "mobileNetworkCode";
    private static final String PROPERTY_networkInterfaces = "networkInterfaces";
    private static final String PROPERTY_physicalLayerSubType = "physicalLayerSubType";
    private static final String PROPERTY_physicalLayerType = "physicalLayerType";
    private static final String PROPERTY_ssid = "ssid";
    private static final String PROPERTY_wirelessChannel = "wirelessChannel";
    private static final String TARGET = "android";
    
    public SetNetworkInterfaces(final Context arguments) {
        super("android", "setNetworkInterfaces");
        this.setArguments(arguments);
    }
    
    private void setArguments(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONArray;
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore          16
        //     9: aload_1        
        //    10: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.getNetworkInterfaces:(Landroid/content/Context;)[Landroid/net/NetworkInfo;
        //    13: astore          17
        //    15: aload_1        
        //    16: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.getActiveNetworkInfo:(Landroid/content/Context;)Landroid/net/NetworkInfo;
        //    19: astore          18
        //    21: aload           17
        //    23: arraylength    
        //    24: istore          5
        //    26: iconst_0       
        //    27: istore_2       
        //    28: aconst_null    
        //    29: astore          9
        //    31: iload_2        
        //    32: iload           5
        //    34: if_icmpge       703
        //    37: aload           17
        //    39: iload_2        
        //    40: aaload         
        //    41: astore          9
        //    43: new             Lorg/json/JSONObject;
        //    46: dup            
        //    47: invokespecial   org/json/JSONObject.<init>:()V
        //    50: astore          12
        //    52: aload           12
        //    54: ldc             "interfaceName"
        //    56: aload           9
        //    58: invokevirtual   android/net/NetworkInfo.getTypeName:()Ljava/lang/String;
        //    61: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    64: pop            
        //    65: aload_1        
        //    66: aload           9
        //    68: invokevirtual   android/net/NetworkInfo.getType:()I
        //    71: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.getNetworkTypePerLoggingSpecifcation:(Landroid/content/Context;I)I
        //    74: istore          6
        //    76: aload           12
        //    78: ldc             "physicalLayerType"
        //    80: iload           6
        //    82: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    85: pop            
        //    86: aload           12
        //    88: ldc             "physicalLayerSubType"
        //    90: aload           9
        //    92: invokestatic    com/netflix/mediaclient/net/LogMobileType.toLogMobileType:(Landroid/net/NetworkInfo;)Lcom/netflix/mediaclient/net/LogMobileType;
        //    95: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.getNetworkSubTypePerLoggingSpecification:(Lcom/netflix/mediaclient/net/LogMobileType;)I
        //    98: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   101: pop            
        //   102: iconst_0       
        //   103: istore          8
        //   105: iconst_0       
        //   106: istore          4
        //   108: iload           4
        //   110: istore_3       
        //   111: iload           8
        //   113: istore          7
        //   115: aload           18
        //   117: ifnull          164
        //   120: iload           4
        //   122: istore_3       
        //   123: iload           8
        //   125: istore          7
        //   127: aload           18
        //   129: invokevirtual   android/net/NetworkInfo.getType:()I
        //   132: aload           9
        //   134: invokevirtual   android/net/NetworkInfo.getType:()I
        //   137: if_icmpne       164
        //   140: iconst_1       
        //   141: istore          8
        //   143: iload           4
        //   145: istore_3       
        //   146: iload           8
        //   148: istore          7
        //   150: aload           9
        //   152: invokevirtual   android/net/NetworkInfo.isConnectedOrConnecting:()Z
        //   155: ifeq            164
        //   158: iconst_1       
        //   159: istore_3       
        //   160: iload           8
        //   162: istore          7
        //   164: iload           7
        //   166: ifeq            650
        //   169: aload           12
        //   171: ldc             "isDefault"
        //   173: iload           7
        //   175: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   178: pop            
        //   179: iload_3        
        //   180: ifeq            627
        //   183: aload           12
        //   185: ldc             "linkConnected"
        //   187: iconst_1       
        //   188: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   191: pop            
        //   192: aload           12
        //   194: ldc             "internetConnected"
        //   196: iconst_0       
        //   197: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   200: pop            
        //   201: aconst_null    
        //   202: astore          10
        //   204: iconst_4       
        //   205: iload           6
        //   207: if_icmpne       671
        //   210: aload_1        
        //   211: ldc             "wifi"
        //   213: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   216: checkcast       Landroid/net/wifi/WifiManager;
        //   219: astore          11
        //   221: aload           10
        //   223: astore          9
        //   225: aload           11
        //   227: ifnull          354
        //   230: aload           11
        //   232: invokevirtual   android/net/wifi/WifiManager.getConnectionInfo:()Landroid/net/wifi/WifiInfo;
        //   235: astore          11
        //   237: aload           10
        //   239: astore          9
        //   241: aload           11
        //   243: ifnull          354
        //   246: ldc             "nf_invoke"
        //   248: iconst_3       
        //   249: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   252: ifeq            295
        //   255: ldc             "nf_invoke"
        //   257: aload           11
        //   259: invokevirtual   android/net/wifi/WifiInfo.toString:()Ljava/lang/String;
        //   262: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   265: pop            
        //   266: ldc             "nf_invoke"
        //   268: new             Ljava/lang/StringBuilder;
        //   271: dup            
        //   272: invokespecial   java/lang/StringBuilder.<init>:()V
        //   275: ldc             ""
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: aload           11
        //   282: invokevirtual   android/net/wifi/WifiInfo.getSSID:()Ljava/lang/String;
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   294: pop            
        //   295: aload           11
        //   297: invokevirtual   android/net/wifi/WifiInfo.getSSID:()Ljava/lang/String;
        //   300: astore          10
        //   302: aload_1        
        //   303: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.getLocalWifiIP4Address:(Landroid/content/Context;)Ljava/lang/String;
        //   306: astore          9
        //   308: aload           11
        //   310: invokevirtual   android/net/wifi/WifiInfo.getMacAddress:()Ljava/lang/String;
        //   313: astore          11
        //   315: aload           10
        //   317: ifnull          330
        //   320: aload           12
        //   322: ldc             "ssid"
        //   324: aload           10
        //   326: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   329: pop            
        //   330: aload           11
        //   332: ifnull          345
        //   335: aload           12
        //   337: ldc             "macAddress"
        //   339: aload           11
        //   341: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   344: pop            
        //   345: aload           12
        //   347: ldc             "wirelessChannel"
        //   349: iconst_0       
        //   350: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   353: pop            
        //   354: aload           9
        //   356: ifnull          369
        //   359: aload           12
        //   361: ldc             "ipv4Address"
        //   363: aload           9
        //   365: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   368: pop            
        //   369: iload           6
        //   371: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.carrierInfoNeeded:(I)Z
        //   374: ifeq            608
        //   377: ldc             ""
        //   379: astore          9
        //   381: ldc             ""
        //   383: astore          14
        //   385: ldc             ""
        //   387: astore          15
        //   389: aload_1        
        //   390: ldc             "phone"
        //   392: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   395: checkcast       Landroid/telephony/TelephonyManager;
        //   398: astore          19
        //   400: aload           14
        //   402: astore          10
        //   404: aload           15
        //   406: astore          11
        //   408: aload           19
        //   410: ifnull          578
        //   413: aload           19
        //   415: invokevirtual   android/telephony/TelephonyManager.getNetworkOperatorName:()Ljava/lang/String;
        //   418: astore          13
        //   420: aload           19
        //   422: invokevirtual   android/telephony/TelephonyManager.getNetworkOperator:()Ljava/lang/String;
        //   425: astore          9
        //   427: ldc             "nf_invoke"
        //   429: iconst_3       
        //   430: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   433: ifeq            462
        //   436: ldc             "nf_invoke"
        //   438: new             Ljava/lang/StringBuilder;
        //   441: dup            
        //   442: invokespecial   java/lang/StringBuilder.<init>:()V
        //   445: ldc             "networkOperator: "
        //   447: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   450: aload           9
        //   452: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   455: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   458: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   461: pop            
        //   462: aload           9
        //   464: ifnull          680
        //   467: aload           9
        //   469: invokevirtual   java/lang/String.length:()I
        //   472: iconst_4       
        //   473: if_icmple       680
        //   476: aload           9
        //   478: iconst_0       
        //   479: iconst_3       
        //   480: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   483: astore          14
        //   485: aload           9
        //   487: iconst_3       
        //   488: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   491: astore          15
        //   493: aload           13
        //   495: astore          9
        //   497: aload           14
        //   499: astore          10
        //   501: aload           15
        //   503: astore          11
        //   505: ldc             "nf_invoke"
        //   507: iconst_3       
        //   508: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   511: ifeq            578
        //   514: ldc             "nf_invoke"
        //   516: new             Ljava/lang/StringBuilder;
        //   519: dup            
        //   520: invokespecial   java/lang/StringBuilder.<init>:()V
        //   523: ldc             "mcc: "
        //   525: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   528: aload           14
        //   530: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   533: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   536: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   539: pop            
        //   540: ldc             "nf_invoke"
        //   542: new             Ljava/lang/StringBuilder;
        //   545: dup            
        //   546: invokespecial   java/lang/StringBuilder.<init>:()V
        //   549: ldc             "mnc: "
        //   551: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   554: aload           15
        //   556: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   562: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   565: pop            
        //   566: aload           15
        //   568: astore          11
        //   570: aload           14
        //   572: astore          10
        //   574: aload           13
        //   576: astore          9
        //   578: aload           12
        //   580: ldc             "mobileCarrier"
        //   582: aload           9
        //   584: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   587: pop            
        //   588: aload           12
        //   590: ldc             "mobileCountryCode"
        //   592: aload           10
        //   594: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   597: pop            
        //   598: aload           12
        //   600: ldc             "mobileNetworkCode"
        //   602: aload           11
        //   604: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   607: pop            
        //   608: aload           16
        //   610: aload           12
        //   612: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   615: pop            
        //   616: iload_2        
        //   617: iconst_1       
        //   618: iadd           
        //   619: istore_2       
        //   620: aload           12
        //   622: astore          9
        //   624: goto            31
        //   627: aload           12
        //   629: ldc             "linkConnected"
        //   631: iconst_2       
        //   632: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   635: pop            
        //   636: goto            192
        //   639: astore_1       
        //   640: ldc             "nf_invoke"
        //   642: ldc             "Failed to create JSON object"
        //   644: aload_1        
        //   645: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   648: pop            
        //   649: return         
        //   650: aload           12
        //   652: ldc             "isDefault"
        //   654: iconst_0       
        //   655: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   658: pop            
        //   659: aload           12
        //   661: ldc             "linkConnected"
        //   663: iconst_2       
        //   664: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   667: pop            
        //   668: goto            192
        //   671: aload_1        
        //   672: invokestatic    com/netflix/mediaclient/util/ConnectivityUtils.getLocalMobileIP4Address:(Landroid/content/Context;)Ljava/lang/String;
        //   675: astore          9
        //   677: goto            354
        //   680: ldc             "nf_invoke"
        //   682: ldc             "Network operator less than 4 characters!"
        //   684: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   687: pop            
        //   688: aload           13
        //   690: astore          9
        //   692: aload           14
        //   694: astore          10
        //   696: aload           15
        //   698: astore          11
        //   700: goto            578
        //   703: new             Lorg/json/JSONObject;
        //   706: dup            
        //   707: invokespecial   org/json/JSONObject.<init>:()V
        //   710: astore_1       
        //   711: aload_1        
        //   712: ldc             "networkInterfaces"
        //   714: aload           16
        //   716: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   719: pop            
        //   720: aload_0        
        //   721: aload_1        
        //   722: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   725: putfield        com/netflix/mediaclient/javabridge/invoke/android/SetNetworkInterfaces.arguments:Ljava/lang/String;
        //   728: return         
        //   729: astore_1       
        //   730: goto            640
        //   733: astore_1       
        //   734: goto            640
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  21     26     639    640    Lorg/json/JSONException;
        //  43     52     729    733    Lorg/json/JSONException;
        //  52     102    639    640    Lorg/json/JSONException;
        //  127    140    639    640    Lorg/json/JSONException;
        //  150    158    639    640    Lorg/json/JSONException;
        //  169    179    639    640    Lorg/json/JSONException;
        //  183    192    639    640    Lorg/json/JSONException;
        //  192    201    639    640    Lorg/json/JSONException;
        //  210    221    639    640    Lorg/json/JSONException;
        //  230    237    639    640    Lorg/json/JSONException;
        //  246    295    639    640    Lorg/json/JSONException;
        //  295    315    639    640    Lorg/json/JSONException;
        //  320    330    639    640    Lorg/json/JSONException;
        //  335    345    639    640    Lorg/json/JSONException;
        //  345    354    639    640    Lorg/json/JSONException;
        //  359    369    639    640    Lorg/json/JSONException;
        //  369    377    639    640    Lorg/json/JSONException;
        //  389    400    639    640    Lorg/json/JSONException;
        //  413    462    639    640    Lorg/json/JSONException;
        //  467    493    639    640    Lorg/json/JSONException;
        //  505    566    639    640    Lorg/json/JSONException;
        //  578    608    639    640    Lorg/json/JSONException;
        //  608    616    639    640    Lorg/json/JSONException;
        //  627    636    639    640    Lorg/json/JSONException;
        //  650    668    639    640    Lorg/json/JSONException;
        //  671    677    639    640    Lorg/json/JSONException;
        //  680    688    639    640    Lorg/json/JSONException;
        //  703    711    729    733    Lorg/json/JSONException;
        //  711    728    733    737    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0164:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
