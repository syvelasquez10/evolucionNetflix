// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.AsyncTask;

final class ActivityChooserModel$PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void>
{
    final /* synthetic */ ActivityChooserModel this$0;
    
    private ActivityChooserModel$PersistHistoryAsyncTask(final ActivityChooserModel this$0) {
        this.this$0 = this$0;
    }
    
    public Void doInBackground(final Object... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: aload_1        
        //     3: iconst_0       
        //     4: aaload         
        //     5: checkcast       Ljava/util/List;
        //     8: astore          4
        //    10: aload_1        
        //    11: iconst_1       
        //    12: aaload         
        //    13: checkcast       Ljava/lang/String;
        //    16: astore          5
        //    18: aload_0        
        //    19: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //    22: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$200:(Landroid/support/v7/internal/widget/ActivityChooserModel;)Landroid/content/Context;
        //    25: aload           5
        //    27: iconst_0       
        //    28: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    31: astore_1       
        //    32: invokestatic    android/util/Xml.newSerializer:()Lorg/xmlpull/v1/XmlSerializer;
        //    35: astore          5
        //    37: aload           5
        //    39: aload_1        
        //    40: aconst_null    
        //    41: invokeinterface org/xmlpull/v1/XmlSerializer.setOutput:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //    46: aload           5
        //    48: ldc             "UTF-8"
        //    50: iconst_1       
        //    51: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    54: invokeinterface org/xmlpull/v1/XmlSerializer.startDocument:(Ljava/lang/String;Ljava/lang/Boolean;)V
        //    59: aload           5
        //    61: aconst_null    
        //    62: ldc             "historical-records"
        //    64: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //    69: pop            
        //    70: aload           4
        //    72: invokeinterface java/util/List.size:()I
        //    77: istore_3       
        //    78: iload_2        
        //    79: iload_3        
        //    80: if_icmpge       213
        //    83: aload           4
        //    85: iconst_0       
        //    86: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //    91: checkcast       Landroid/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord;
        //    94: astore          6
        //    96: aload           5
        //    98: aconst_null    
        //    99: ldc             "historical-record"
        //   101: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   106: pop            
        //   107: aload           5
        //   109: aconst_null    
        //   110: ldc             "activity"
        //   112: aload           6
        //   114: getfield        android/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord.activity:Landroid/content/ComponentName;
        //   117: invokevirtual   android/content/ComponentName.flattenToString:()Ljava/lang/String;
        //   120: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   125: pop            
        //   126: aload           5
        //   128: aconst_null    
        //   129: ldc             "time"
        //   131: aload           6
        //   133: getfield        android/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord.time:J
        //   136: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   139: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   144: pop            
        //   145: aload           5
        //   147: aconst_null    
        //   148: ldc             "weight"
        //   150: aload           6
        //   152: getfield        android/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord.weight:F
        //   155: invokestatic    java/lang/String.valueOf:(F)Ljava/lang/String;
        //   158: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   163: pop            
        //   164: aload           5
        //   166: aconst_null    
        //   167: ldc             "historical-record"
        //   169: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   174: pop            
        //   175: iload_2        
        //   176: iconst_1       
        //   177: iadd           
        //   178: istore_2       
        //   179: goto            78
        //   182: astore_1       
        //   183: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   186: new             Ljava/lang/StringBuilder;
        //   189: dup            
        //   190: invokespecial   java/lang/StringBuilder.<init>:()V
        //   193: ldc             "Error writing historical recrod file: "
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: aload           5
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   206: aload_1        
        //   207: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   210: pop            
        //   211: aconst_null    
        //   212: areturn        
        //   213: aload           5
        //   215: aconst_null    
        //   216: ldc             "historical-records"
        //   218: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   223: pop            
        //   224: aload           5
        //   226: invokeinterface org/xmlpull/v1/XmlSerializer.endDocument:()V
        //   231: aload_0        
        //   232: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   235: iconst_1       
        //   236: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$502:(Landroid/support/v7/internal/widget/ActivityChooserModel;Z)Z
        //   239: pop            
        //   240: aload_1        
        //   241: ifnull          211
        //   244: aload_1        
        //   245: invokevirtual   java/io/FileOutputStream.close:()V
        //   248: aconst_null    
        //   249: areturn        
        //   250: astore_1       
        //   251: aconst_null    
        //   252: areturn        
        //   253: astore          4
        //   255: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   258: new             Ljava/lang/StringBuilder;
        //   261: dup            
        //   262: invokespecial   java/lang/StringBuilder.<init>:()V
        //   265: ldc             "Error writing historical recrod file: "
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload_0        
        //   271: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   274: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$400:(Landroid/support/v7/internal/widget/ActivityChooserModel;)Ljava/lang/String;
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   283: aload           4
        //   285: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   288: pop            
        //   289: aload_0        
        //   290: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   293: iconst_1       
        //   294: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$502:(Landroid/support/v7/internal/widget/ActivityChooserModel;Z)Z
        //   297: pop            
        //   298: aload_1        
        //   299: ifnull          211
        //   302: aload_1        
        //   303: invokevirtual   java/io/FileOutputStream.close:()V
        //   306: aconst_null    
        //   307: areturn        
        //   308: astore_1       
        //   309: aconst_null    
        //   310: areturn        
        //   311: astore          4
        //   313: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   316: new             Ljava/lang/StringBuilder;
        //   319: dup            
        //   320: invokespecial   java/lang/StringBuilder.<init>:()V
        //   323: ldc             "Error writing historical recrod file: "
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: aload_0        
        //   329: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   332: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$400:(Landroid/support/v7/internal/widget/ActivityChooserModel;)Ljava/lang/String;
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   341: aload           4
        //   343: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   346: pop            
        //   347: aload_0        
        //   348: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   351: iconst_1       
        //   352: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$502:(Landroid/support/v7/internal/widget/ActivityChooserModel;Z)Z
        //   355: pop            
        //   356: aload_1        
        //   357: ifnull          211
        //   360: aload_1        
        //   361: invokevirtual   java/io/FileOutputStream.close:()V
        //   364: aconst_null    
        //   365: areturn        
        //   366: astore_1       
        //   367: aconst_null    
        //   368: areturn        
        //   369: astore          4
        //   371: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   374: new             Ljava/lang/StringBuilder;
        //   377: dup            
        //   378: invokespecial   java/lang/StringBuilder.<init>:()V
        //   381: ldc             "Error writing historical recrod file: "
        //   383: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   386: aload_0        
        //   387: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   390: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$400:(Landroid/support/v7/internal/widget/ActivityChooserModel;)Ljava/lang/String;
        //   393: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   396: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   399: aload           4
        //   401: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   404: pop            
        //   405: aload_0        
        //   406: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   409: iconst_1       
        //   410: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$502:(Landroid/support/v7/internal/widget/ActivityChooserModel;Z)Z
        //   413: pop            
        //   414: aload_1        
        //   415: ifnull          211
        //   418: aload_1        
        //   419: invokevirtual   java/io/FileOutputStream.close:()V
        //   422: aconst_null    
        //   423: areturn        
        //   424: astore_1       
        //   425: aconst_null    
        //   426: areturn        
        //   427: astore          4
        //   429: aload_0        
        //   430: getfield        android/support/v7/internal/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/internal/widget/ActivityChooserModel;
        //   433: iconst_1       
        //   434: invokestatic    android/support/v7/internal/widget/ActivityChooserModel.access$502:(Landroid/support/v7/internal/widget/ActivityChooserModel;Z)Z
        //   437: pop            
        //   438: aload_1        
        //   439: ifnull          446
        //   442: aload_1        
        //   443: invokevirtual   java/io/FileOutputStream.close:()V
        //   446: aload           4
        //   448: athrow         
        //   449: astore_1       
        //   450: goto            446
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  18     32     182    211    Ljava/io/FileNotFoundException;
        //  37     78     253    311    Ljava/lang/IllegalArgumentException;
        //  37     78     311    369    Ljava/lang/IllegalStateException;
        //  37     78     369    427    Ljava/io/IOException;
        //  37     78     427    453    Any
        //  83     175    253    311    Ljava/lang/IllegalArgumentException;
        //  83     175    311    369    Ljava/lang/IllegalStateException;
        //  83     175    369    427    Ljava/io/IOException;
        //  83     175    427    453    Any
        //  213    231    253    311    Ljava/lang/IllegalArgumentException;
        //  213    231    311    369    Ljava/lang/IllegalStateException;
        //  213    231    369    427    Ljava/io/IOException;
        //  213    231    427    453    Any
        //  244    248    250    253    Ljava/io/IOException;
        //  255    289    427    453    Any
        //  302    306    308    311    Ljava/io/IOException;
        //  313    347    427    453    Any
        //  360    364    366    369    Ljava/io/IOException;
        //  371    405    427    453    Any
        //  418    422    424    427    Ljava/io/IOException;
        //  442    446    449    453    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
