// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetVideoOutputMode extends BaseInvoke
{
    private static final String METHOD = "setVideoOutputMode";
    private static final String PROPERTY_mode = "mode";
    private static final String TARGET = "media";
    
    public SetVideoOutputMode(final boolean arguments) {
        super("media", "setVideoOutputMode");
        this.setArguments(arguments);
    }
    
    private void setArguments(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: iload_1        
        //     9: ifeq            32
        //    12: ldc             "internal"
        //    14: astore_2       
        //    15: aload_3        
        //    16: ldc             "mode"
        //    18: aload_2        
        //    19: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    22: pop            
        //    23: aload_0        
        //    24: aload_3        
        //    25: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    28: putfield        com/netflix/mediaclient/javabridge/invoke/media/SetVideoOutputMode.arguments:Ljava/lang/String;
        //    31: return         
        //    32: ldc             "external"
        //    34: astore_2       
        //    35: goto            15
        //    38: astore_2       
        //    39: ldc             "nf_invoke"
        //    41: ldc             "Failed to create JSON object"
        //    43: aload_2        
        //    44: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    47: pop            
        //    48: return         
        //    49: astore_2       
        //    50: goto            39
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      38     39     Lorg/json/JSONException;
        //  15     31     49     53     Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
