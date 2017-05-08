// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.io.InputStream;
import com.android.volley.toolbox.DiskBasedCache;

public class VolleyCacheWrapper
{
    private static final String TAG = "VolleyCacheWrapper";
    final DiskBasedCache mCache;
    
    public VolleyCacheWrapper(final DiskBasedCache mCache) {
        this.mCache = mCache;
    }
    
    private static void readHeader(final InputStream inputStream) {
        final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.readByte();
        objectInputStream.readUTF();
        objectInputStream.readUTF();
        objectInputStream.readLong();
        objectInputStream.readLong();
        objectInputStream.readLong();
        final int int1 = objectInputStream.readInt();
        Map<String, String> emptyMap;
        if (int1 == 0) {
            emptyMap = Collections.emptyMap();
        }
        else {
            emptyMap = new HashMap<String, String>(int1);
        }
        for (int i = 0; i < int1; ++i) {
            emptyMap.put(objectInputStream.readUTF().intern(), objectInputStream.readUTF().intern());
        }
    }
    
    public VolleyCacheWrapper$CachedResourceMetaData getEntryMetaData(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper.mCache:Lcom/android/volley/toolbox/DiskBasedCache;
        //     4: aload_1        
        //     5: invokevirtual   com/android/volley/toolbox/DiskBasedCache.getFileForKey:(Ljava/lang/String;)Ljava/io/File;
        //     8: astore          4
        //    10: aload           4
        //    12: invokevirtual   java/io/File.exists:()Z
        //    15: ifeq            162
        //    18: aconst_null    
        //    19: astore_1       
        //    20: aconst_null    
        //    21: astore_3       
        //    22: new             Lcom/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream;
        //    25: dup            
        //    26: new             Ljava/io/FileInputStream;
        //    29: dup            
        //    30: aload           4
        //    32: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    35: aconst_null    
        //    36: invokespecial   com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$1;)V
        //    39: astore_2       
        //    40: aload_2        
        //    41: invokestatic    com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper.readHeader:(Ljava/io/InputStream;)V
        //    44: new             Lcom/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CachedResourceMetaData;
        //    47: dup            
        //    48: aload_0        
        //    49: aload           4
        //    51: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    54: aload_2        
        //    55: invokestatic    com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream.access$100:(Lcom/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream;)I
        //    58: i2l            
        //    59: aload           4
        //    61: invokevirtual   java/io/File.length:()J
        //    64: aload_2        
        //    65: invokestatic    com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream.access$100:(Lcom/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream;)I
        //    68: i2l            
        //    69: lsub           
        //    70: invokespecial   com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CachedResourceMetaData.<init>:(Lcom/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper;Ljava/lang/String;JJ)V
        //    73: astore_1       
        //    74: aload_2        
        //    75: ifnull          82
        //    78: aload_2        
        //    79: invokevirtual   com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream.close:()V
        //    82: aload_1        
        //    83: areturn        
        //    84: astore_2       
        //    85: ldc             "VolleyCacheWrapper"
        //    87: ldc             "%s: %s"
        //    89: iconst_2       
        //    90: anewarray       Ljava/lang/Object;
        //    93: dup            
        //    94: iconst_0       
        //    95: aload           4
        //    97: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_1       
        //   103: aload_2        
        //   104: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   107: aastore        
        //   108: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   111: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   114: pop            
        //   115: aload_1        
        //   116: areturn        
        //   117: astore_1       
        //   118: aload_3        
        //   119: astore_2       
        //   120: aload_1        
        //   121: astore_3       
        //   122: aload_2        
        //   123: astore_1       
        //   124: ldc             "VolleyCacheWrapper"
        //   126: ldc             "%s: %s"
        //   128: iconst_2       
        //   129: anewarray       Ljava/lang/Object;
        //   132: dup            
        //   133: iconst_0       
        //   134: aload           4
        //   136: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   139: aastore        
        //   140: dup            
        //   141: iconst_1       
        //   142: aload_3        
        //   143: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   146: aastore        
        //   147: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   150: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   153: pop            
        //   154: aload_2        
        //   155: ifnull          162
        //   158: aload_2        
        //   159: invokevirtual   com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream.close:()V
        //   162: aconst_null    
        //   163: areturn        
        //   164: astore_1       
        //   165: ldc             "VolleyCacheWrapper"
        //   167: ldc             "%s: %s"
        //   169: iconst_2       
        //   170: anewarray       Ljava/lang/Object;
        //   173: dup            
        //   174: iconst_0       
        //   175: aload           4
        //   177: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   180: aastore        
        //   181: dup            
        //   182: iconst_1       
        //   183: aload_1        
        //   184: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   187: aastore        
        //   188: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   191: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   194: pop            
        //   195: goto            162
        //   198: astore_3       
        //   199: aload_1        
        //   200: astore_2       
        //   201: aload_3        
        //   202: astore_1       
        //   203: aload_2        
        //   204: ifnull          211
        //   207: aload_2        
        //   208: invokevirtual   com/netflix/mediaclient/service/resfetcher/VolleyCacheWrapper$CountingInputStream.close:()V
        //   211: aload_1        
        //   212: athrow         
        //   213: astore_2       
        //   214: ldc             "VolleyCacheWrapper"
        //   216: ldc             "%s: %s"
        //   218: iconst_2       
        //   219: anewarray       Ljava/lang/Object;
        //   222: dup            
        //   223: iconst_0       
        //   224: aload           4
        //   226: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   229: aastore        
        //   230: dup            
        //   231: iconst_1       
        //   232: aload_2        
        //   233: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   236: aastore        
        //   237: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   240: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   243: pop            
        //   244: goto            211
        //   247: astore_1       
        //   248: goto            203
        //   251: astore_3       
        //   252: goto            122
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     40     117    122    Ljava/io/IOException;
        //  22     40     198    203    Any
        //  40     74     251    255    Ljava/io/IOException;
        //  40     74     247    251    Any
        //  78     82     84     117    Ljava/io/IOException;
        //  124    154    198    203    Any
        //  158    162    164    198    Ljava/io/IOException;
        //  207    211    213    247    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
