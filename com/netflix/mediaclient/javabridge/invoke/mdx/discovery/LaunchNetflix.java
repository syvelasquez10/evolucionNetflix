// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.discovery;

import java.util.Map;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class LaunchNetflix extends BaseInvoke
{
    private static final String METHOD = "launchNetflix";
    private static final String PROPERTY_launchArgs = "launchArgs";
    private static final String PROPERTY_usn = "usn";
    private static final String TARGET = "mdx";
    
    public LaunchNetflix(final String s, final Map<String, String> map) {
        super("mdx", "launchNetflix");
        this.setArguments(s, map);
    }
    
    private void setArguments(final String p0, final Map<String, String> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore          5
        //     9: new             Ljava/lang/String;
        //    12: dup            
        //    13: invokespecial   java/lang/String.<init>:()V
        //    16: astore_3       
        //    17: aload_2        
        //    18: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //    23: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    28: astore          6
        //    30: aload           6
        //    32: invokeinterface java/util/Iterator.hasNext:()Z
        //    37: ifeq            128
        //    40: aload           6
        //    42: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    47: checkcast       Ljava/lang/String;
        //    50: astore          4
        //    52: new             Ljava/lang/StringBuilder;
        //    55: dup            
        //    56: invokespecial   java/lang/StringBuilder.<init>:()V
        //    59: aload_3        
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: aload           4
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: ldc             "="
        //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    73: aload_2        
        //    74: aload           4
        //    76: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    81: checkcast       Ljava/lang/String;
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    90: astore          4
        //    92: aload           4
        //    94: astore_3       
        //    95: aload_2        
        //    96: invokeinterface java/util/Map.isEmpty:()Z
        //   101: ifne            30
        //   104: new             Ljava/lang/StringBuilder;
        //   107: dup            
        //   108: invokespecial   java/lang/StringBuilder.<init>:()V
        //   111: aload           4
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: ldc             "&"
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   124: astore_3       
        //   125: goto            30
        //   128: aload           5
        //   130: ldc             "usn"
        //   132: aload_1        
        //   133: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   136: pop            
        //   137: aload           5
        //   139: ldc             "launchArgs"
        //   141: aload_3        
        //   142: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   145: pop            
        //   146: aload_0        
        //   147: aload           5
        //   149: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   152: putfield        com/netflix/mediaclient/javabridge/invoke/mdx/discovery/LaunchNetflix.arguments:Ljava/lang/String;
        //   155: return         
        //   156: astore_1       
        //   157: ldc             "nf_invoke"
        //   159: ldc             "Failed to create JSON object"
        //   161: aload_1        
        //   162: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   165: pop            
        //   166: return         
        //   167: astore_1       
        //   168: goto            157
        //    Signature:
        //  (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      9      156    157    Lorg/json/JSONException;
        //  9      30     167    171    Lorg/json/JSONException;
        //  30     92     167    171    Lorg/json/JSONException;
        //  95     125    167    171    Lorg/json/JSONException;
        //  128    155    167    171    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
