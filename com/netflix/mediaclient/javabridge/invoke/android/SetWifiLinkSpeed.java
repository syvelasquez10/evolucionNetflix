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
        //     7: astore_2       
        //     8: aload_2        
        //     9: ifnull          42
        //    12: aload_2        
        //    13: invokevirtual   android/net/wifi/WifiInfo.getLinkSpeed:()I
        //    16: istore_1       
        //    17: new             Lorg/json/JSONObject;
        //    20: dup            
        //    21: invokespecial   org/json/JSONObject.<init>:()V
        //    24: astore_2       
        //    25: aload_2        
        //    26: ldc             "wifi_linkspeed"
        //    28: iload_1        
        //    29: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    32: pop            
        //    33: aload_0        
        //    34: aload_2        
        //    35: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    38: putfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiLinkSpeed.arguments:Ljava/lang/String;
        //    41: return         
        //    42: aload_0        
        //    43: ldc             ""
        //    45: putfield        com/netflix/mediaclient/javabridge/invoke/android/SetWifiLinkSpeed.arguments:Ljava/lang/String;
        //    48: return         
        //    49: astore_2       
        //    50: ldc             "nf_invoke"
        //    52: ldc             "Failed to create JSON object"
        //    54: aload_2        
        //    55: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    58: pop            
        //    59: return         
        //    60: astore_2       
        //    61: ldc             "nf_invoke"
        //    63: ldc             "Unable to Log WifiLinkSpeed "
        //    65: aload_2        
        //    66: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    69: pop            
        //    70: return         
        //    71: astore_2       
        //    72: goto            61
        //    75: astore_2       
        //    76: goto            50
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      49     50     Lorg/json/JSONException;
        //  0      8      60     61     Ljava/lang/Exception;
        //  12     25     49     50     Lorg/json/JSONException;
        //  12     25     60     61     Ljava/lang/Exception;
        //  25     41     75     79     Lorg/json/JSONException;
        //  25     41     71     75     Ljava/lang/Exception;
        //  42     48     49     50     Lorg/json/JSONException;
        //  42     48     60     61     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
