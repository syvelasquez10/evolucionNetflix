// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.pair;

import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class RegistrationPairingRequest extends BaseInvoke
{
    private static final String METHOD = "regPairRequest";
    private static final String PROPERTY_pin = "pin";
    private static final String PROPERTY_remotedevice = "remotedevice";
    private static final String TARGET = "mdx";
    
    public RegistrationPairingRequest(final String s, final String s2) {
        super("mdx", "regPairRequest");
        this.setArguments(s, s2);
    }
    
    private void setArguments(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: aload_3        
        //     9: ldc             "remotedevice"
        //    11: aload_1        
        //    12: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    15: pop            
        //    16: aload_2        
        //    17: ifnull          28
        //    20: aload_3        
        //    21: ldc             "pin"
        //    23: aload_2        
        //    24: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    27: pop            
        //    28: aload_0        
        //    29: aload_3        
        //    30: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    33: putfield        com/netflix/mediaclient/javabridge/invoke/mdx/pair/RegistrationPairingRequest.arguments:Ljava/lang/String;
        //    36: return         
        //    37: astore_1       
        //    38: ldc             "nf_invoke"
        //    40: ldc             "Failed to create JSON object"
        //    42: aload_1        
        //    43: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    46: pop            
        //    47: return         
        //    48: astore_1       
        //    49: goto            38
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      37     38     Lorg/json/JSONException;
        //  8      16     48     52     Lorg/json/JSONException;
        //  20     28     48     52     Lorg/json/JSONException;
        //  28     36     48     52     Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
