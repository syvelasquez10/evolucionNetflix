// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SelectTracks extends BaseInvoke
{
    private static final String METHOD = "selectTracks";
    private static final String PROPERTY_audio = "audio";
    private static final String PROPERTY_subtitle = "subtitle";
    private static final String TARGET = "media";
    
    public SelectTracks(final AudioSource audioSource, final Subtitle subtitle) {
        super("media", "selectTracks");
        if (audioSource == null) {
            throw new IllegalArgumentException("Audio can not be null!");
        }
        this.setArguments(audioSource, subtitle);
    }
    
    private void setArguments(final AudioSource p0, final Subtitle p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             ""
        //     2: astore          4
        //     4: aload           4
        //     6: astore_3       
        //     7: aload_1        
        //     8: ifnull          26
        //    11: aload           4
        //    13: astore_3       
        //    14: aload_1        
        //    15: invokevirtual   com/netflix/mediaclient/media/AudioSource.getId:()Ljava/lang/String;
        //    18: ifnull          26
        //    21: aload_1        
        //    22: invokevirtual   com/netflix/mediaclient/media/AudioSource.getId:()Ljava/lang/String;
        //    25: astore_3       
        //    26: aload_2        
        //    27: ifnull          75
        //    30: aload_2        
        //    31: invokevirtual   com/netflix/mediaclient/media/Subtitle.getId:()Ljava/lang/String;
        //    34: ifnull          75
        //    37: aload_2        
        //    38: invokevirtual   com/netflix/mediaclient/media/Subtitle.getId:()Ljava/lang/String;
        //    41: astore_1       
        //    42: new             Lorg/json/JSONObject;
        //    45: dup            
        //    46: invokespecial   org/json/JSONObject.<init>:()V
        //    49: astore_2       
        //    50: aload_2        
        //    51: ldc             "audio"
        //    53: aload_3        
        //    54: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    57: pop            
        //    58: aload_2        
        //    59: ldc             "subtitle"
        //    61: aload_1        
        //    62: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    65: pop            
        //    66: aload_0        
        //    67: aload_2        
        //    68: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    71: putfield        com/netflix/mediaclient/javabridge/invoke/media/SelectTracks.arguments:Ljava/lang/String;
        //    74: return         
        //    75: ldc             "-1"
        //    77: astore_1       
        //    78: goto            42
        //    81: astore_1       
        //    82: ldc             "nf_invoke"
        //    84: ldc             "Failed to create JSON object"
        //    86: aload_1        
        //    87: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    90: pop            
        //    91: return         
        //    92: astore_1       
        //    93: goto            82
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  14     26     81     82     Lorg/json/JSONException;
        //  30     42     81     82     Lorg/json/JSONException;
        //  42     50     81     82     Lorg/json/JSONException;
        //  50     74     92     96     Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
