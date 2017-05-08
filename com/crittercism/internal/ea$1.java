// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

final class ea$1 implements Runnable
{
    final /* synthetic */ Throwable a;
    final /* synthetic */ long b;
    final /* synthetic */ ea c;
    
    ea$1(final ea c, final Throwable a, final long b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/crittercism/internal/ea$1.c:Lcom/crittercism/internal/ea;
        //     4: getfield        com/crittercism/internal/ea.d:Lcom/crittercism/internal/dr;
        //     7: invokevirtual   com/crittercism/internal/dr.a:()Z
        //    10: ifeq            14
        //    13: return         
        //    14: new             Lcom/crittercism/internal/bi;
        //    17: dup            
        //    18: aload_0        
        //    19: getfield        com/crittercism/internal/ea$1.a:Ljava/lang/Throwable;
        //    22: aload_0        
        //    23: getfield        com/crittercism/internal/ea$1.b:J
        //    26: invokespecial   com/crittercism/internal/bi.<init>:(Ljava/lang/Throwable;J)V
        //    29: astore_1       
        //    30: aload_1        
        //    31: ldc             "he"
        //    33: putfield        com/crittercism/internal/bi.e:Ljava/lang/String;
        //    36: aload_1        
        //    37: getfield        com/crittercism/internal/bi.f:Lorg/json/JSONObject;
        //    40: ldc             "app_version"
        //    42: ldc             "5.6.4"
        //    44: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    47: pop            
        //    48: aload_1        
        //    49: getfield        com/crittercism/internal/bi.f:Lorg/json/JSONObject;
        //    52: ldc             "logcat"
        //    54: invokevirtual   org/json/JSONObject.remove:(Ljava/lang/String;)Ljava/lang/Object;
        //    57: pop            
        //    58: aload_0        
        //    59: getfield        com/crittercism/internal/ea$1.c:Lcom/crittercism/internal/ea;
        //    62: getfield        com/crittercism/internal/ea.a:Lcom/crittercism/internal/au;
        //    65: invokeinterface com/crittercism/internal/au.q:()Lcom/crittercism/internal/bq;
        //    70: aload_1        
        //    71: invokevirtual   com/crittercism/internal/bq.a:(Lcom/crittercism/internal/cf;)Z
        //    74: pop            
        //    75: return         
        //    76: astore_1       
        //    77: return         
        //    78: astore_1       
        //    79: return         
        //    80: astore_2       
        //    81: goto            48
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      13     76     78     Ljava/lang/ThreadDeath;
        //  0      13     78     80     Ljava/lang/Throwable;
        //  14     36     76     78     Ljava/lang/ThreadDeath;
        //  14     36     78     80     Ljava/lang/Throwable;
        //  36     48     80     84     Lorg/json/JSONException;
        //  36     48     76     78     Ljava/lang/ThreadDeath;
        //  36     48     78     80     Ljava/lang/Throwable;
        //  48     75     76     78     Ljava/lang/ThreadDeath;
        //  48     75     78     80     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
