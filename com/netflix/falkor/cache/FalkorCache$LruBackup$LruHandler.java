// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import android.os.Message;
import android.os.Handler;

class FalkorCache$LruBackup$LruHandler extends Handler
{
    private void evictRealmKeys() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: iconst_0       
        //     3: istore_3       
        //     4: iconst_0       
        //     5: istore_1       
        //     6: invokestatic    com/netflix/falkor/cache/FalkorCache$LruBackup.access$300:()[Ljava/lang/Object;
        //     9: astore          9
        //    11: aload           9
        //    13: monitorenter   
        //    14: invokestatic    com/netflix/falkor/cache/FalkorCache$LruBackup.access$400:()Ljava/util/LinkedList;
        //    17: astore          11
        //    19: new             Ljava/util/LinkedList;
        //    22: dup            
        //    23: invokespecial   java/util/LinkedList.<init>:()V
        //    26: invokestatic    com/netflix/falkor/cache/FalkorCache$LruBackup.access$402:(Ljava/util/LinkedList;)Ljava/util/LinkedList;
        //    29: pop            
        //    30: aload           9
        //    32: monitorexit    
        //    33: aconst_null    
        //    34: astore          9
        //    36: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.getInstance:()Lio/realm/Realm;
        //    39: astore          10
        //    41: aload           10
        //    43: astore          9
        //    45: aload           9
        //    47: ldc             Lcom/netflix/falkor/cache/FalkorRealmCacheLruBased;.class
        //    49: invokevirtual   io/realm/Realm.where:(Ljava/lang/Class;)Lio/realm/RealmQuery;
        //    52: invokevirtual   io/realm/RealmQuery.findAll:()Lio/realm/RealmResults;
        //    55: invokevirtual   io/realm/RealmResults.size:()I
        //    58: istore          8
        //    60: iconst_0       
        //    61: istore          4
        //    63: iload_1        
        //    64: istore_2       
        //    65: aload           11
        //    67: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //    70: ifne            348
        //    73: iload_1        
        //    74: istore_2       
        //    75: aload           11
        //    77: invokevirtual   java/util/LinkedList.remove:()Ljava/lang/Object;
        //    80: checkcast       Ljava/lang/String;
        //    83: astore          10
        //    85: iload_1        
        //    86: istore_2       
        //    87: aload           10
        //    89: iconst_0       
        //    90: aload           10
        //    92: ldc             "]"
        //    94: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //    97: iconst_1       
        //    98: iadd           
        //    99: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   102: astore          12
        //   104: iload           4
        //   106: istore          7
        //   108: iload_1        
        //   109: istore          5
        //   111: iload_1        
        //   112: istore_2       
        //   113: aload           9
        //   115: aload           10
        //   117: aload           10
        //   119: ldc             "]"
        //   121: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   124: iconst_1       
        //   125: iadd           
        //   126: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   129: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   132: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   135: invokevirtual   io/realm/Realm.where:(Ljava/lang/Class;)Lio/realm/RealmQuery;
        //   138: ldc             "path"
        //   140: aload           12
        //   142: invokevirtual   io/realm/RealmQuery.equalTo:(Ljava/lang/String;Ljava/lang/String;)Lio/realm/RealmQuery;
        //   145: invokevirtual   io/realm/RealmQuery.findAll:()Lio/realm/RealmResults;
        //   148: astore          12
        //   150: iload           4
        //   152: istore_3       
        //   153: iload_1        
        //   154: istore          6
        //   156: iload           4
        //   158: istore          7
        //   160: iload_1        
        //   161: istore          5
        //   163: iload_1        
        //   164: istore_2       
        //   165: aload           12
        //   167: invokevirtual   io/realm/RealmResults.size:()I
        //   170: ifle            251
        //   173: iload_1        
        //   174: istore_3       
        //   175: iload_1        
        //   176: ifne            195
        //   179: iload           4
        //   181: istore          7
        //   183: iload_1        
        //   184: istore          5
        //   186: iload_1        
        //   187: istore_2       
        //   188: aload           9
        //   190: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.beginTransaction:(Lio/realm/Realm;)V
        //   193: iconst_1       
        //   194: istore_3       
        //   195: iload           4
        //   197: istore          7
        //   199: iload_3        
        //   200: istore          5
        //   202: iload_3        
        //   203: istore_2       
        //   204: iload           4
        //   206: aload           12
        //   208: invokevirtual   io/realm/RealmResults.size:()I
        //   211: iadd           
        //   212: istore_1       
        //   213: iload_1        
        //   214: istore          7
        //   216: iload_3        
        //   217: istore          5
        //   219: iload_3        
        //   220: istore_2       
        //   221: invokestatic    com/netflix/falkor/cache/FalkorCache.getMonitor:()Lcom/netflix/falkor/cache/FalkorCacheMonitor;
        //   224: aload           12
        //   226: invokevirtual   io/realm/RealmResults.size:()I
        //   229: invokevirtual   com/netflix/falkor/cache/FalkorCacheMonitor.delete:(I)V
        //   232: iload_1        
        //   233: istore          7
        //   235: iload_3        
        //   236: istore          5
        //   238: iload_3        
        //   239: istore_2       
        //   240: aload           12
        //   242: invokevirtual   io/realm/RealmResults.deleteAllFromRealm:()Z
        //   245: pop            
        //   246: iload_3        
        //   247: istore          6
        //   249: iload_1        
        //   250: istore_3       
        //   251: iload           6
        //   253: istore_1       
        //   254: iload_3        
        //   255: istore          4
        //   257: goto            63
        //   260: astore          10
        //   262: aload           9
        //   264: monitorexit    
        //   265: aload           10
        //   267: athrow         
        //   268: astore          12
        //   270: iload           5
        //   272: istore_2       
        //   273: new             Ljava/lang/StringBuilder;
        //   276: dup            
        //   277: invokespecial   java/lang/StringBuilder.<init>:()V
        //   280: ldc             "FalkorCache.LruBackup.onRemoved("
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload           10
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   290: ldc             ") -> "
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: aload           12
        //   297: invokevirtual   java/lang/ClassNotFoundException.toString:()Ljava/lang/String;
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   306: invokestatic    com/netflix/mediaclient/util/LogUtils.reportErrorSafely:(Ljava/lang/String;)V
        //   309: iload           7
        //   311: istore_3       
        //   312: iload           5
        //   314: istore          6
        //   316: goto            251
        //   319: astore          10
        //   321: iload_2        
        //   322: ifeq            330
        //   325: aload           9
        //   327: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.cancelTransaction:(Lio/realm/Realm;)V
        //   330: aload           10
        //   332: athrow         
        //   333: astore          10
        //   335: aload           9
        //   337: ifnull          345
        //   340: aload           9
        //   342: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.close:(Lio/realm/Realm;)V
        //   345: aload           10
        //   347: athrow         
        //   348: iload_1        
        //   349: ifeq            359
        //   352: iload_1        
        //   353: istore_2       
        //   354: aload           9
        //   356: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.commitTransaction:(Lio/realm/Realm;)V
        //   359: iload_1        
        //   360: istore_2       
        //   361: ldc             "FalkorCache.LruBackup"
        //   363: ldc             "Entries deleted : %d (%d -> %d)"
        //   365: iconst_3       
        //   366: anewarray       Ljava/lang/Object;
        //   369: dup            
        //   370: iconst_0       
        //   371: iload           4
        //   373: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   376: aastore        
        //   377: dup            
        //   378: iconst_1       
        //   379: iload           8
        //   381: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   384: aastore        
        //   385: dup            
        //   386: iconst_2       
        //   387: aload           9
        //   389: ldc             Lcom/netflix/falkor/cache/FalkorRealmCacheLruBased;.class
        //   391: invokevirtual   io/realm/Realm.where:(Ljava/lang/Class;)Lio/realm/RealmQuery;
        //   394: invokevirtual   io/realm/RealmQuery.findAll:()Lio/realm/RealmResults;
        //   397: invokevirtual   io/realm/RealmResults.size:()I
        //   400: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   403: aastore        
        //   404: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   407: pop            
        //   408: aload           9
        //   410: ifnull          418
        //   413: aload           9
        //   415: invokestatic    com/netflix/falkor/cache/FalkorCache$RealmAccess.close:(Lio/realm/Realm;)V
        //   418: return         
        //   419: astore          10
        //   421: aconst_null    
        //   422: astore          9
        //   424: goto            335
        //   427: astore          10
        //   429: goto            335
        //   432: astore          10
        //   434: iload_3        
        //   435: istore_2       
        //   436: goto            321
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  14     33     260    268    Any
        //  36     41     432    439    Ljava/lang/RuntimeException;
        //  36     41     419    427    Any
        //  45     60     319    321    Ljava/lang/RuntimeException;
        //  45     60     427    432    Any
        //  65     73     319    321    Ljava/lang/RuntimeException;
        //  65     73     427    432    Any
        //  75     85     319    321    Ljava/lang/RuntimeException;
        //  75     85     427    432    Any
        //  87     104    319    321    Ljava/lang/RuntimeException;
        //  87     104    427    432    Any
        //  113    150    268    319    Ljava/lang/ClassNotFoundException;
        //  113    150    319    321    Ljava/lang/RuntimeException;
        //  113    150    427    432    Any
        //  165    173    268    319    Ljava/lang/ClassNotFoundException;
        //  165    173    319    321    Ljava/lang/RuntimeException;
        //  165    173    427    432    Any
        //  188    193    268    319    Ljava/lang/ClassNotFoundException;
        //  188    193    319    321    Ljava/lang/RuntimeException;
        //  188    193    427    432    Any
        //  204    213    268    319    Ljava/lang/ClassNotFoundException;
        //  204    213    319    321    Ljava/lang/RuntimeException;
        //  204    213    427    432    Any
        //  221    232    268    319    Ljava/lang/ClassNotFoundException;
        //  221    232    319    321    Ljava/lang/RuntimeException;
        //  221    232    427    432    Any
        //  240    246    268    319    Ljava/lang/ClassNotFoundException;
        //  240    246    319    321    Ljava/lang/RuntimeException;
        //  240    246    427    432    Any
        //  262    265    260    268    Any
        //  273    309    319    321    Ljava/lang/RuntimeException;
        //  273    309    427    432    Any
        //  325    330    333    335    Any
        //  330    333    333    335    Any
        //  354    359    319    321    Ljava/lang/RuntimeException;
        //  354    359    427    432    Any
        //  361    408    319    321    Ljava/lang/RuntimeException;
        //  361    408    427    432    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    public void handleMessage(final Message message) {
        if (message.what == 1) {
            this.evictRealmKeys();
        }
    }
}
