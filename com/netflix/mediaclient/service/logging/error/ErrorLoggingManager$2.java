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
        //    12: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.inMementoTest:(Landroid/content/Context;)Z
        //    15: ifeq            71
        //    18: ldc             "Memento_%d "
        //    20: iconst_1       
        //    21: anewarray       Ljava/lang/Object;
        //    24: dup            
        //    25: iconst_0       
        //    26: aload_0        
        //    27: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    30: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getMemento:(Landroid/content/Context;)Lcom/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell;
        //    33: invokevirtual   com/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell.getCellId:()I
        //    36: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    39: aastore        
        //    40: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    43: aload_2        
        //    44: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //    47: astore          4
        //    49: aload           4
        //    51: astore_2       
        //    52: aload_0        
        //    53: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    56: invokestatic    com/netflix/mediaclient/ui/common/ExportDebugData.markCrashed:(Landroid/content/Context;)V
        //    59: aload_0        
        //    60: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$critDefaultHandler:Ljava/lang/Thread$UncaughtExceptionHandler;
        //    63: aload_1        
        //    64: aload_2        
        //    65: invokeinterface java/lang/Thread$UncaughtExceptionHandler.uncaughtException:(Ljava/lang/Thread;Ljava/lang/Throwable;)V
        //    70: return         
        //    71: aload_0        
        //    72: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    75: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.inMemento2Test:(Landroid/content/Context;)Z
        //    78: ifeq            118
        //    81: ldc             "Memento2_%d "
        //    83: iconst_1       
        //    84: anewarray       Ljava/lang/Object;
        //    87: dup            
        //    88: iconst_0       
        //    89: aload_0        
        //    90: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //    93: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getMemento2:(Landroid/content/Context;)Lcom/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell;
        //    96: invokevirtual   com/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell.getCellId:()I
        //    99: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   102: aastore        
        //   103: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   106: aload_2        
        //   107: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //   110: astore          4
        //   112: aload           4
        //   114: astore_2       
        //   115: goto            52
        //   118: aload_0        
        //   119: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //   122: invokestatic    com/netflix/mediaclient/ui/lolomo/PrefetchLolomoABTestUtils.isInTest:(Landroid/content/Context;)Z
        //   125: ifeq            201
        //   128: ldc             "AimLow7480_%d "
        //   130: iconst_1       
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: iconst_0       
        //   136: aload_0        
        //   137: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //   140: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getPrefetchLolomoConfig:(Landroid/content/Context;)Lcom/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell;
        //   143: invokevirtual   com/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell.getCellId:()I
        //   146: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   149: aastore        
        //   150: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   153: astore          4
        //   155: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   158: ifeq            187
        //   161: ldc             "nf_log_crit"
        //   163: new             Ljava/lang/StringBuilder;
        //   166: dup            
        //   167: invokespecial   java/lang/StringBuilder.<init>:()V
        //   170: ldc             "uncaughtException: message = "
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload           4
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   183: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   186: pop            
        //   187: aload           4
        //   189: aload_2        
        //   190: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //   193: astore          4
        //   195: aload           4
        //   197: astore_2       
        //   198: goto            52
        //   201: aload_0        
        //   202: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //   205: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getCoppola1ABTestCell:(Landroid/content/Context;)Lcom/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell;
        //   208: invokevirtual   com/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell.ordinal:()I
        //   211: iconst_1       
        //   212: iadd           
        //   213: istore_3       
        //   214: iload_3        
        //   215: iconst_1       
        //   216: if_icmple       247
        //   219: ldc             "Coppola_%d "
        //   221: iconst_1       
        //   222: anewarray       Ljava/lang/Object;
        //   225: dup            
        //   226: iconst_0       
        //   227: iload_3        
        //   228: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   231: aastore        
        //   232: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   235: aload_2        
        //   236: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //   239: astore          4
        //   241: aload           4
        //   243: astore_2       
        //   244: goto            52
        //   247: aload_0        
        //   248: getfield        com/netflix/mediaclient/service/logging/error/ErrorLoggingManager$2.val$globalContext:Landroid/content/Context;
        //   251: invokestatic    com/netflix/mediaclient/service/configuration/PersistentConfig.getCoppola2ABTestCell:(Landroid/content/Context;)Lcom/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell;
        //   254: invokevirtual   com/netflix/mediaclient/service/webclient/model/leafs/ABTestConfig$Cell.ordinal:()I
        //   257: iconst_1       
        //   258: iadd           
        //   259: istore_3       
        //   260: iload_3        
        //   261: iconst_1       
        //   262: if_icmple       338
        //   265: ldc             "Coppola2_%d "
        //   267: iconst_1       
        //   268: anewarray       Ljava/lang/Object;
        //   271: dup            
        //   272: iconst_0       
        //   273: iload_3        
        //   274: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   277: aastore        
        //   278: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   281: aload_2        
        //   282: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.access$000:(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/Exception;
        //   285: astore          4
        //   287: aload           4
        //   289: astore_2       
        //   290: goto            52
        //   293: astore          4
        //   295: new             Ljava/lang/StringBuilder;
        //   298: dup            
        //   299: invokespecial   java/lang/StringBuilder.<init>:()V
        //   302: ldc             "SPY-9027 - got throwable while wrapping stack trace: "
        //   304: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   307: aload           4
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   312: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   315: astore          4
        //   317: aload           4
        //   319: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/String;)V
        //   322: ldc             "nf_log_crit"
        //   324: aload           4
        //   326: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   329: pop            
        //   330: goto            59
        //   333: astore          4
        //   335: goto            295
        //   338: goto            52
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      49     293    295    Ljava/lang/Throwable;
        //  52     59     333    338    Ljava/lang/Throwable;
        //  71     112    293    295    Ljava/lang/Throwable;
        //  118    187    293    295    Ljava/lang/Throwable;
        //  187    195    293    295    Ljava/lang/Throwable;
        //  201    214    293    295    Ljava/lang/Throwable;
        //  219    241    293    295    Ljava/lang/Throwable;
        //  247    260    293    295    Ljava/lang/Throwable;
        //  265    287    293    295    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
