// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.AsyncTask;

final class ActivityChooserModel$PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void>
{
    final /* synthetic */ ActivityChooserModel this$0;
    
    ActivityChooserModel$PersistHistoryAsyncTask(final ActivityChooserModel this$0) {
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
        //    19: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //    22: getfield        android/support/v7/widget/ActivityChooserModel.mContext:Landroid/content/Context;
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
        //    91: checkcast       Landroid/support/v7/widget/ActivityChooserModel$HistoricalRecord;
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
        //   114: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.activity:Landroid/content/ComponentName;
        //   117: invokevirtual   android/content/ComponentName.flattenToString:()Ljava/lang/String;
        //   120: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   125: pop            
        //   126: aload           5
        //   128: aconst_null    
        //   129: ldc             "time"
        //   131: aload           6
        //   133: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.time:J
        //   136: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   139: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   144: pop            
        //   145: aload           5
        //   147: aconst_null    
        //   148: ldc             "weight"
        //   150: aload           6
        //   152: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.weight:F
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
        //   183: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   186: new             Ljava/lang/StringBuilder;
        //   189: dup            
        //   190: invokespecial   java/lang/StringBuilder.<init>:()V
        //   193: ldc             "Error writing historical record file: "
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
        //   232: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   235: iconst_1       
        //   236: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
        //   239: aload_1        
        //   240: ifnull          211
        //   243: aload_1        
        //   244: invokevirtual   java/io/FileOutputStream.close:()V
        //   247: aconst_null    
        //   248: areturn        
        //   249: astore_1       
        //   250: aconst_null    
        //   251: areturn        
        //   252: astore          4
        //   254: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   257: new             Ljava/lang/StringBuilder;
        //   260: dup            
        //   261: invokespecial   java/lang/StringBuilder.<init>:()V
        //   264: ldc             "Error writing historical record file: "
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: aload_0        
        //   270: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   273: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   282: aload           4
        //   284: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   287: pop            
        //   288: aload_0        
        //   289: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   292: iconst_1       
        //   293: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
        //   296: aload_1        
        //   297: ifnull          211
        //   300: aload_1        
        //   301: invokevirtual   java/io/FileOutputStream.close:()V
        //   304: aconst_null    
        //   305: areturn        
        //   306: astore_1       
        //   307: aconst_null    
        //   308: areturn        
        //   309: astore          4
        //   311: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   314: new             Ljava/lang/StringBuilder;
        //   317: dup            
        //   318: invokespecial   java/lang/StringBuilder.<init>:()V
        //   321: ldc             "Error writing historical record file: "
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: aload_0        
        //   327: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   330: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   339: aload           4
        //   341: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   344: pop            
        //   345: aload_0        
        //   346: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   349: iconst_1       
        //   350: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
        //   353: aload_1        
        //   354: ifnull          211
        //   357: aload_1        
        //   358: invokevirtual   java/io/FileOutputStream.close:()V
        //   361: aconst_null    
        //   362: areturn        
        //   363: astore_1       
        //   364: aconst_null    
        //   365: areturn        
        //   366: astore          4
        //   368: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   371: new             Ljava/lang/StringBuilder;
        //   374: dup            
        //   375: invokespecial   java/lang/StringBuilder.<init>:()V
        //   378: ldc             "Error writing historical record file: "
        //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   383: aload_0        
        //   384: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   387: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   396: aload           4
        //   398: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   401: pop            
        //   402: aload_0        
        //   403: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   406: iconst_1       
        //   407: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
        //   410: aload_1        
        //   411: ifnull          211
        //   414: aload_1        
        //   415: invokevirtual   java/io/FileOutputStream.close:()V
        //   418: aconst_null    
        //   419: areturn        
        //   420: astore_1       
        //   421: aconst_null    
        //   422: areturn        
        //   423: astore          4
        //   425: aload_0        
        //   426: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   429: iconst_1       
        //   430: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
        //   433: aload_1        
        //   434: ifnull          441
        //   437: aload_1        
        //   438: invokevirtual   java/io/FileOutputStream.close:()V
        //   441: aload           4
        //   443: athrow         
        //   444: astore_1       
        //   445: goto            441
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  18     32     182    211    Ljava/io/FileNotFoundException;
        //  37     78     252    309    Ljava/lang/IllegalArgumentException;
        //  37     78     309    366    Ljava/lang/IllegalStateException;
        //  37     78     366    423    Ljava/io/IOException;
        //  37     78     423    448    Any
        //  83     175    252    309    Ljava/lang/IllegalArgumentException;
        //  83     175    309    366    Ljava/lang/IllegalStateException;
        //  83     175    366    423    Ljava/io/IOException;
        //  83     175    423    448    Any
        //  213    231    252    309    Ljava/lang/IllegalArgumentException;
        //  213    231    309    366    Ljava/lang/IllegalStateException;
        //  213    231    366    423    Ljava/io/IOException;
        //  213    231    423    448    Any
        //  243    247    249    252    Ljava/io/IOException;
        //  254    288    423    448    Any
        //  300    304    306    309    Ljava/io/IOException;
        //  311    345    423    448    Any
        //  357    361    363    366    Ljava/io/IOException;
        //  368    402    423    448    Any
        //  414    418    420    423    Ljava/io/IOException;
        //  437    441    444    448    Ljava/io/IOException;
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
