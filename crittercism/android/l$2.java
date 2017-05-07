// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.concurrent.Callable;

final class l$2 implements Callable
{
    final /* synthetic */ l a;
    
    l$2(final l a) {
        this.a = a;
    }
    
    private Boolean a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //     3: aload_0        
        //     4: getfield        crittercism/android/l$2.a:Lcrittercism/android/l;
        //     7: getfield        crittercism/android/l.t:Lcrittercism/android/m;
        //    10: invokestatic    crittercism/android/v.a:(Lcrittercism/android/m;)Lcrittercism/android/v;
        //    13: putfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: ldc             "pendingNdkCrashes.getPendingDataVector().size = "
        //    22: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    25: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    28: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    31: invokevirtual   crittercism/android/v.f:()Ljava/util/Collection;
        //    34: invokeinterface java/util/Collection.size:()I
        //    39: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    42: pop            
        //    43: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    46: getfield        crittercism/android/l.j:Lcrittercism/android/v;
        //    49: invokevirtual   crittercism/android/v.f:()Ljava/util/Collection;
        //    52: invokeinterface java/util/Collection.size:()I
        //    57: ifne            94
        //    60: iconst_1       
        //    61: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    64: areturn        
        //    65: astore_1       
        //    66: new             Ljava/lang/StringBuilder;
        //    69: dup            
        //    70: ldc             "Exception in startNdkSendingThreads boolean callable: "
        //    72: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    75: aload_1        
        //    76: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    79: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: pop            
        //    86: aload_1        
        //    87: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    90: goto            43
        //    93: astore_1       
        //    94: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    97: invokevirtual   crittercism/android/l.l:()Z
        //   100: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   103: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      43     65     93     Ljava/lang/Exception;
        //  43     60     93     94     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
