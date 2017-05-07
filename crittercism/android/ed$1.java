// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

final class ed$1 implements Runnable
{
    final /* synthetic */ Throwable a;
    final /* synthetic */ long b;
    final /* synthetic */ ed c;
    
    ed$1(final ed c, final Throwable a, final long b) {
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
        //     1: getfield        crittercism/android/ed$1.c:Lcrittercism/android/ed;
        //     4: getfield        crittercism/android/ed.d:Lcrittercism/android/dx;
        //     7: invokevirtual   crittercism/android/dx.b:()Z
        //    10: ifeq            14
        //    13: return         
        //    14: new             Lcrittercism/android/bk;
        //    17: dup            
        //    18: aload_0        
        //    19: getfield        crittercism/android/ed$1.a:Ljava/lang/Throwable;
        //    22: aload_0        
        //    23: getfield        crittercism/android/ed$1.b:J
        //    26: invokespecial   crittercism/android/bk.<init>:(Ljava/lang/Throwable;J)V
        //    29: astore_1       
        //    30: aload_1        
        //    31: ldc             "he"
        //    33: putfield        crittercism/android/bk.f:Ljava/lang/String;
        //    36: aload_1        
        //    37: getfield        crittercism/android/bk.g:Lorg/json/JSONObject;
        //    40: ldc             "app_version"
        //    42: ldc             "5.0.6"
        //    44: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    47: pop            
        //    48: aload_1        
        //    49: getfield        crittercism/android/bk.g:Lorg/json/JSONObject;
        //    52: ldc             "logcat"
        //    54: invokevirtual   org/json/JSONObject.remove:(Ljava/lang/String;)Ljava/lang/Object;
        //    57: pop            
        //    58: aload_0        
        //    59: getfield        crittercism/android/ed$1.c:Lcrittercism/android/ed;
        //    62: getfield        crittercism/android/ed.a:Lcrittercism/android/aw;
        //    65: invokeinterface crittercism/android/aw.p:()Lcrittercism/android/bs;
        //    70: aload_1        
        //    71: invokevirtual   crittercism/android/bs.a:(Lcrittercism/android/ch;)Z
        //    74: pop            
        //    75: return         
        //    76: astore_1       
        //    77: return         
        //    78: astore_1       
        //    79: aload_0        
        //    80: getfield        crittercism/android/ed$1.c:Lcrittercism/android/ed;
        //    83: astore_1       
        //    84: aload_0        
        //    85: getfield        crittercism/android/ed$1.a:Ljava/lang/Throwable;
        //    88: astore_1       
        //    89: return         
        //    90: astore_2       
        //    91: goto            48
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      13     76     78     Ljava/lang/ThreadDeath;
        //  0      13     78     90     Ljava/lang/Throwable;
        //  14     36     76     78     Ljava/lang/ThreadDeath;
        //  14     36     78     90     Ljava/lang/Throwable;
        //  36     48     90     94     Lorg/json/JSONException;
        //  36     48     76     78     Ljava/lang/ThreadDeath;
        //  36     48     78     90     Ljava/lang/Throwable;
        //  48     75     76     78     Ljava/lang/ThreadDeath;
        //  48     75     78     90     Ljava/lang/Throwable;
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
