// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.content.Context;

final class ErrorLoggingManager$2 implements UncaughtExceptionHandler
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ UncaughtExceptionHandler val$critDefaultHandler;
    final /* synthetic */ Context val$globalContext;
    
    ErrorLoggingManager$2(final Context val$context, final Context val$globalContext, final UncaughtExceptionHandler val$critDefaultHandler) {
        this.val$context = val$context;
        this.val$globalContext = val$globalContext;
        this.val$critDefaultHandler = val$critDefaultHandler;
    }
    
    @Override
    public void uncaughtException(final Thread p0, final Throwable p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$context:Landroid/content/Context;
        //     4: aload_2        
        //     5: invokestatic    com/netflix/mediaclient/util/log/ExceptionLogClUtils.reportUnhandledExceptionToCL:(Landroid/content/Context;Ljava/lang/Throwable;)V
        //     8: aload_0        
        //     9: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    12: invokestatic    com/netflix/mediaclient/ui/lolomo/PrefetchLolomoABTestUtils.isInTest:(Landroid/content/Context;)Z
        //    15: ifeq            106
        //    18: ldc             "AimLow7480_%d "
        //    20: iconst_1       
        //    21: anewarray       Ljava/lang/Object;
        //    24: dup            
        //    25: iconst_0       
        //    26: ldc             Lcom/netflix/mediaclient/service/configuration/persistent/PrefetchLolomoConfig;.class
        //    28: aload_0        
        //    29: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    32: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getCellIdForTest:(Ljava/lang/Class;Landroid/content/Context;)I
        //    35: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    38: aastore        
        //    39: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    42: astore          4
        //    44: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    47: ifeq            76
        //    50: ldc             "nf_log_crit"
        //    52: new             Ljava/lang/StringBuilder;
        //    55: dup            
        //    56: invokespecial   java/lang/StringBuilder.<init>:()V
        //    59: ldc             "uncaughtException: message = "
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    64: aload           4
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    75: pop            
        //    76: aload           4
        //    78: aload_2        
        //    79: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //    82: astore          4
        //    84: aload           4
        //    86: astore_2       
        //    87: aload_0        
        //    88: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    91: invokestatic    com/netflix/mediaclient/ui/common/ExportDebugData.markCrashed:(Landroid/content/Context;)V
        //    94: aload_0        
        //    95: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$critDefaultHandler:Ljava/lang/Thread$UncaughtExceptionHandler;
        //    98: aload_1        
        //    99: aload_2        
        //   100: invokeinterface java/lang/Thread$UncaughtExceptionHandler.uncaughtException:(Ljava/lang/Thread;Ljava/lang/Throwable;)V
        //   105: return         
        //   106: ldc             Lcom/netflix/mediaclient/service/configuration/persistent/CoppolaOne;.class
        //   108: aload_0        
        //   109: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //   112: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getCellOrdinalForTest:(Ljava/lang/Class;Landroid/content/Context;)I
        //   115: iconst_1       
        //   116: iadd           
        //   117: istore_3       
        //   118: iload_3        
        //   119: iconst_1       
        //   120: if_icmple       151
        //   123: ldc             "Coppola_%d "
        //   125: iconst_1       
        //   126: anewarray       Ljava/lang/Object;
        //   129: dup            
        //   130: iconst_0       
        //   131: iload_3        
        //   132: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   135: aastore        
        //   136: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   139: aload_2        
        //   140: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //   143: astore          4
        //   145: aload           4
        //   147: astore_2       
        //   148: goto            87
        //   151: ldc             Lcom/netflix/mediaclient/service/configuration/persistent/CoppolaTwo;.class
        //   153: aload_0        
        //   154: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //   157: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getCellOrdinalForTest:(Ljava/lang/Class;Landroid/content/Context;)I
        //   160: iconst_1       
        //   161: iadd           
        //   162: istore_3       
        //   163: iload_3        
        //   164: iconst_1       
        //   165: if_icmple       241
        //   168: ldc             "Coppola2_%d "
        //   170: iconst_1       
        //   171: anewarray       Ljava/lang/Object;
        //   174: dup            
        //   175: iconst_0       
        //   176: iload_3        
        //   177: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   180: aastore        
        //   181: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   184: aload_2        
        //   185: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //   188: astore          4
        //   190: aload           4
        //   192: astore_2       
        //   193: goto            87
        //   196: astore          4
        //   198: new             Ljava/lang/StringBuilder;
        //   201: dup            
        //   202: invokespecial   java/lang/StringBuilder.<init>:()V
        //   205: ldc             "SPY-9027 - got throwable while wrapping stack trace: "
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: aload           4
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   215: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   218: astore          4
        //   220: aload           4
        //   222: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/String;)V
        //   225: ldc             "nf_log_crit"
        //   227: aload           4
        //   229: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   232: pop            
        //   233: goto            94
        //   236: astore          4
        //   238: goto            198
        //   241: goto            87
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      76     196    198    Ljava/lang/Throwable;
        //  76     84     196    198    Ljava/lang/Throwable;
        //  87     94     236    241    Ljava/lang/Throwable;
        //  106    118    196    198    Ljava/lang/Throwable;
        //  123    145    196    198    Ljava/lang/Throwable;
        //  151    163    196    198    Ljava/lang/Throwable;
        //  168    190    196    198    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
