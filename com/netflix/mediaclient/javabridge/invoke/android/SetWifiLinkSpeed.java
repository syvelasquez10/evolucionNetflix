// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetWifiLinkSpeed extends BaseInvoke
{
    private static final String METHOD = "setWifiLinkSpeed";
    private static final String PROPERTY_WIFI_LINKSPEED = "wifi_linkspeed";
    private static final String PROPERTY_WIFI_SIGNALSTRENGTH = "wifi_signalstrength";
    private static final String TARGET = "android";
    WifiManager mainWifi;
    
    public SetWifiLinkSpeed(final Context context) {
        super("android", "setWifiLinkSpeed");
        this.mainWifi = (WifiManager)context.getSystemService("wifi");
        this.setArguments();
    }
    
    private void setArguments() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiLinkSpeed.mainWifi:Landroid/net/wifi/WifiManager;
        //     4: invokevirtual   android/net/wifi/WifiManager.getConnectionInfo:()Landroid/net/wifi/WifiInfo;
        //     7: astore_3       
        //     8: aload_3        
        //     9: ifnull          55
        //    12: aload_3        
        //    13: invokevirtual   android/net/wifi/WifiInfo.getLinkSpeed:()I
        //    16: istore_1       
        //    17: aload_3        
        //    18: invokevirtual   android/net/wifi/WifiInfo.getRssi:()I
        //    21: istore_2       
        //    22: new             Lorg/json/JSONObject;
        //    25: dup            
        //    26: invokespecial   org/json/JSONObject.<init>:()V
        //    29: astore_3       
        //    30: aload_3        
        //    31: ldc             "wifi_linkspeed"
        //    33: iload_1        
        //    34: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    37: pop            
        //    38: aload_3        
        //    39: ldc             "wifi_signalstrength"
        //    41: iload_2        
        //    42: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    45: pop            
        //    46: aload_0        
        //    47: aload_3        
        //    48: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    51: putfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiLinkSpeed.arguments:Ljava/lang/String;
        //    54: return         
        //    55: aload_0        
        //    56: ldc             ""
        //    58: putfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiLinkSpeed.arguments:Ljava/lang/String;
        //    61: return         
        //    62: astore_3       
        //    63: ldc             "nf_invoke"
        //    65: ldc             "Failed to create JSON object"
        //    67: aload_3        
        //    68: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    71: pop            
        //    72: return         
        //    73: astore_3       
        //    74: ldc             "nf_invoke"
        //    76: ldc             "Unable to Log WifiLinkSpeed "
        //    78: aload_3        
        //    79: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    82: pop            
        //    83: return         
        //    84: astore_3       
        //    85: goto            74
        //    88: astore_3       
        //    89: goto            63
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      62     63     Lorg/json/JSONException;
        //  0      8      73     74     Ljava/lang/Exception;
        //  12     30     62     63     Lorg/json/JSONException;
        //  12     30     73     74     Ljava/lang/Exception;
        //  30     54     88     92     Lorg/json/JSONException;
        //  30     54     84     88     Ljava/lang/Exception;
        //  55     61     62     63     Lorg/json/JSONException;
        //  55     61     73     74     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
