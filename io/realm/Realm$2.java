// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

class Realm$2 implements Runnable
{
    final /* synthetic */ Realm this$0;
    final /* synthetic */ Realm$Transaction$OnError val$onError;
    final /* synthetic */ Realm$Transaction$OnSuccess val$onSuccess;
    final /* synthetic */ RealmConfiguration val$realmConfiguration;
    final /* synthetic */ Realm$Transaction val$transaction;
    
    Realm$2(final Realm this$0, final RealmConfiguration val$realmConfiguration, final Realm$Transaction val$transaction, final Realm$Transaction$OnSuccess val$onSuccess, final Realm$Transaction$OnError val$onError) {
        this.this$0 = this$0;
        this.val$realmConfiguration = val$realmConfiguration;
        this.val$transaction = val$transaction;
        this.val$onSuccess = val$onSuccess;
        this.val$onError = val$onError;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //     3: invokevirtual   java/lang/Thread.isInterrupted:()Z
        //     6: ifeq            10
        //     9: return         
        //    10: iconst_1       
        //    11: anewarray       Ljava/lang/Throwable;
        //    14: astore_2       
        //    15: aload_0        
        //    16: getfield        io/realm/Realm$2.val$realmConfiguration:Lio/realm/RealmConfiguration;
        //    19: invokestatic    io/realm/Realm.getInstance:(Lio/realm/RealmConfiguration;)Lio/realm/Realm;
        //    22: astore_3       
        //    23: aload_3        
        //    24: invokevirtual   io/realm/Realm.beginTransaction:()V
        //    27: aload_0        
        //    28: getfield        io/realm/Realm$2.val$transaction:Lio/realm/Realm$Transaction;
        //    31: aload_3        
        //    32: invokeinterface io/realm/Realm$Transaction.execute:(Lio/realm/Realm;)V
        //    37: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    40: invokevirtual   java/lang/Thread.isInterrupted:()Z
        //    43: ifne            607
        //    46: aload_3        
        //    47: iconst_0       
        //    48: invokevirtual   io/realm/Realm.commitTransaction:(Z)V
        //    51: aload_3        
        //    52: invokevirtual   io/realm/Realm.close:()V
        //    55: iconst_1       
        //    56: istore_1       
        //    57: aload_3        
        //    58: invokevirtual   io/realm/Realm.isClosed:()Z
        //    61: ifne            79
        //    64: aload_3        
        //    65: invokevirtual   io/realm/Realm.isInTransaction:()Z
        //    68: ifeq            165
        //    71: aload_3        
        //    72: invokevirtual   io/realm/Realm.cancelTransaction:()V
        //    75: aload_3        
        //    76: invokevirtual   io/realm/Realm.close:()V
        //    79: aload_2        
        //    80: iconst_0       
        //    81: aaload         
        //    82: astore_2       
        //    83: aload_0        
        //    84: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //    87: invokevirtual   io/realm/Realm.hasValidNotifier:()Z
        //    90: ifeq            208
        //    93: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    96: invokevirtual   java/lang/Thread.isInterrupted:()Z
        //    99: ifne            208
        //   102: iload_1        
        //   103: ifeq            129
        //   106: aload_0        
        //   107: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   110: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   113: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   116: new             Lio/realm/Realm$2$1;
        //   119: dup            
        //   120: aload_0        
        //   121: invokespecial   io/realm/Realm$2$1.<init>:(Lio/realm/Realm$2;)V
        //   124: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   129: aload_2        
        //   130: ifnull          9
        //   133: aload_0        
        //   134: getfield        io/realm/Realm$2.val$onError:Lio/realm/Realm$Transaction$OnError;
        //   137: ifnull          183
        //   140: aload_0        
        //   141: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   144: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   147: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   150: new             Lio/realm/Realm$2$2;
        //   153: dup            
        //   154: aload_0        
        //   155: aload_2        
        //   156: invokespecial   io/realm/Realm$2$2.<init>:(Lio/realm/Realm$2;Ljava/lang/Throwable;)V
        //   159: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   164: return         
        //   165: aload_2        
        //   166: iconst_0       
        //   167: aaload         
        //   168: ifnull          75
        //   171: ldc             "Could not cancel transaction, not currently in a transaction."
        //   173: iconst_0       
        //   174: anewarray       Ljava/lang/Object;
        //   177: invokestatic    io/realm/log/RealmLog.warn:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   180: goto            75
        //   183: aload_0        
        //   184: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   187: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   190: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   193: new             Lio/realm/Realm$2$3;
        //   196: dup            
        //   197: aload_0        
        //   198: aload_2        
        //   199: invokespecial   io/realm/Realm$2$3.<init>:(Lio/realm/Realm$2;Ljava/lang/Throwable;)V
        //   202: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   207: return         
        //   208: aload_2        
        //   209: ifnull          9
        //   212: aload_2        
        //   213: instanceof      Ljava/lang/RuntimeException;
        //   216: ifeq            224
        //   219: aload_2        
        //   220: checkcast       Ljava/lang/RuntimeException;
        //   223: athrow         
        //   224: aload_2        
        //   225: instanceof      Ljava/lang/Exception;
        //   228: ifeq            242
        //   231: new             Lio/realm/exceptions/RealmException;
        //   234: dup            
        //   235: ldc             "Async transaction failed"
        //   237: aload_2        
        //   238: invokespecial   io/realm/exceptions/RealmException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   241: athrow         
        //   242: aload_2        
        //   243: instanceof      Ljava/lang/Error;
        //   246: ifeq            9
        //   249: aload_2        
        //   250: checkcast       Ljava/lang/Error;
        //   253: athrow         
        //   254: astore          4
        //   256: aload_2        
        //   257: iconst_0       
        //   258: aload           4
        //   260: aastore        
        //   261: aload_3        
        //   262: invokevirtual   io/realm/Realm.isClosed:()Z
        //   265: ifne            283
        //   268: aload_3        
        //   269: invokevirtual   io/realm/Realm.isInTransaction:()Z
        //   272: ifeq            342
        //   275: aload_3        
        //   276: invokevirtual   io/realm/Realm.cancelTransaction:()V
        //   279: aload_3        
        //   280: invokevirtual   io/realm/Realm.close:()V
        //   283: aload_2        
        //   284: iconst_0       
        //   285: aaload         
        //   286: astore_2       
        //   287: aload_0        
        //   288: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   291: invokevirtual   io/realm/Realm.hasValidNotifier:()Z
        //   294: ifeq            385
        //   297: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   300: invokevirtual   java/lang/Thread.isInterrupted:()Z
        //   303: ifne            385
        //   306: aload_2        
        //   307: ifnull          9
        //   310: aload_0        
        //   311: getfield        io/realm/Realm$2.val$onError:Lio/realm/Realm$Transaction$OnError;
        //   314: ifnull          360
        //   317: aload_0        
        //   318: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   321: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   324: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   327: new             Lio/realm/Realm$2$2;
        //   330: dup            
        //   331: aload_0        
        //   332: aload_2        
        //   333: invokespecial   io/realm/Realm$2$2.<init>:(Lio/realm/Realm$2;Ljava/lang/Throwable;)V
        //   336: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   341: return         
        //   342: aload_2        
        //   343: iconst_0       
        //   344: aaload         
        //   345: ifnull          279
        //   348: ldc             "Could not cancel transaction, not currently in a transaction."
        //   350: iconst_0       
        //   351: anewarray       Ljava/lang/Object;
        //   354: invokestatic    io/realm/log/RealmLog.warn:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   357: goto            279
        //   360: aload_0        
        //   361: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   364: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   367: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   370: new             Lio/realm/Realm$2$3;
        //   373: dup            
        //   374: aload_0        
        //   375: aload_2        
        //   376: invokespecial   io/realm/Realm$2$3.<init>:(Lio/realm/Realm$2;Ljava/lang/Throwable;)V
        //   379: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   384: return         
        //   385: aload_2        
        //   386: ifnull          9
        //   389: aload_2        
        //   390: instanceof      Ljava/lang/RuntimeException;
        //   393: ifeq            401
        //   396: aload_2        
        //   397: checkcast       Ljava/lang/RuntimeException;
        //   400: athrow         
        //   401: aload_2        
        //   402: instanceof      Ljava/lang/Exception;
        //   405: ifeq            419
        //   408: new             Lio/realm/exceptions/RealmException;
        //   411: dup            
        //   412: ldc             "Async transaction failed"
        //   414: aload_2        
        //   415: invokespecial   io/realm/exceptions/RealmException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   418: athrow         
        //   419: aload_2        
        //   420: instanceof      Ljava/lang/Error;
        //   423: ifeq            9
        //   426: aload_2        
        //   427: checkcast       Ljava/lang/Error;
        //   430: athrow         
        //   431: astore          4
        //   433: aload_3        
        //   434: invokevirtual   io/realm/Realm.isClosed:()Z
        //   437: ifne            455
        //   440: aload_3        
        //   441: invokevirtual   io/realm/Realm.isInTransaction:()Z
        //   444: ifeq            516
        //   447: aload_3        
        //   448: invokevirtual   io/realm/Realm.cancelTransaction:()V
        //   451: aload_3        
        //   452: invokevirtual   io/realm/Realm.close:()V
        //   455: aload_2        
        //   456: iconst_0       
        //   457: aaload         
        //   458: astore_2       
        //   459: aload_0        
        //   460: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   463: invokevirtual   io/realm/Realm.hasValidNotifier:()Z
        //   466: ifeq            561
        //   469: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   472: invokevirtual   java/lang/Thread.isInterrupted:()Z
        //   475: ifne            561
        //   478: aload_2        
        //   479: ifnull          513
        //   482: aload_0        
        //   483: getfield        io/realm/Realm$2.val$onError:Lio/realm/Realm$Transaction$OnError;
        //   486: ifnull          534
        //   489: aload_0        
        //   490: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   493: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   496: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   499: new             Lio/realm/Realm$2$2;
        //   502: dup            
        //   503: aload_0        
        //   504: aload_2        
        //   505: invokespecial   io/realm/Realm$2$2.<init>:(Lio/realm/Realm$2;Ljava/lang/Throwable;)V
        //   508: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   513: aload           4
        //   515: athrow         
        //   516: aload_2        
        //   517: iconst_0       
        //   518: aaload         
        //   519: ifnull          451
        //   522: ldc             "Could not cancel transaction, not currently in a transaction."
        //   524: iconst_0       
        //   525: anewarray       Ljava/lang/Object;
        //   528: invokestatic    io/realm/log/RealmLog.warn:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   531: goto            451
        //   534: aload_0        
        //   535: getfield        io/realm/Realm$2.this$0:Lio/realm/Realm;
        //   538: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   541: getfield        io/realm/internal/SharedRealm.realmNotifier:Lio/realm/internal/RealmNotifier;
        //   544: new             Lio/realm/Realm$2$3;
        //   547: dup            
        //   548: aload_0        
        //   549: aload_2        
        //   550: invokespecial   io/realm/Realm$2$3.<init>:(Lio/realm/Realm$2;Ljava/lang/Throwable;)V
        //   553: invokeinterface io/realm/internal/RealmNotifier.post:(Ljava/lang/Runnable;)V
        //   558: goto            513
        //   561: aload_2        
        //   562: ifnull          513
        //   565: aload_2        
        //   566: instanceof      Ljava/lang/RuntimeException;
        //   569: ifeq            577
        //   572: aload_2        
        //   573: checkcast       Ljava/lang/RuntimeException;
        //   576: athrow         
        //   577: aload_2        
        //   578: instanceof      Ljava/lang/Exception;
        //   581: ifeq            595
        //   584: new             Lio/realm/exceptions/RealmException;
        //   587: dup            
        //   588: ldc             "Async transaction failed"
        //   590: aload_2        
        //   591: invokespecial   io/realm/exceptions/RealmException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   594: athrow         
        //   595: aload_2        
        //   596: instanceof      Ljava/lang/Error;
        //   599: ifeq            513
        //   602: aload_2        
        //   603: checkcast       Ljava/lang/Error;
        //   606: athrow         
        //   607: iconst_0       
        //   608: istore_1       
        //   609: goto            57
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  27     55     254    431    Ljava/lang/Throwable;
        //  27     55     431    607    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
