// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.google.android.exoplayer.util.Util;

public class NetflixFMP4Parser
{
    private static final String TAG = "NetflixFMP4Parser";
    private static final int TYPE_sidx;
    
    static {
        TYPE_sidx = Util.getIntegerCodeForString("sidx");
    }
    
    static NetflixFMP4Parser$SidxInfo parseSidxInfo(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          13
        //     3: ldc2_w          -1
        //     6: lstore          10
        //     8: lconst_0       
        //     9: lstore          4
        //    11: iconst_m1      
        //    12: istore_2       
        //    13: lload           4
        //    15: lstore          6
        //    17: lload           10
        //    19: lstore          8
        //    21: new             Ljava/io/File;
        //    24: dup            
        //    25: aload_0        
        //    26: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    29: invokevirtual   java/io/File.exists:()Z
        //    32: ifeq            183
        //    35: iconst_1       
        //    36: istore_1       
        //    37: iload_1        
        //    38: istore_2       
        //    39: lload           4
        //    41: lstore          6
        //    43: lload           10
        //    45: lstore          8
        //    47: new             Ljava/io/RandomAccessFile;
        //    50: dup            
        //    51: aload_0        
        //    52: ldc             "r"
        //    54: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    57: astore          12
        //    59: lload           4
        //    61: lstore          6
        //    63: lload           10
        //    65: lstore          8
        //    67: aload           12
        //    69: invokevirtual   java/io/RandomAccessFile.length:()J
        //    72: lstore          10
        //    74: lload           4
        //    76: lstore          6
        //    78: lload           10
        //    80: lstore          8
        //    82: aload           12
        //    84: invokevirtual   java/io/RandomAccessFile.readInt:()I
        //    87: istore_3       
        //    88: lload           4
        //    90: lstore          6
        //    92: lload           10
        //    94: lstore          8
        //    96: aload           12
        //    98: invokevirtual   java/io/RandomAccessFile.readInt:()I
        //   101: getstatic       com/netflix/mediaclient/service/offline/manifest/NetflixFMP4Parser.TYPE_sidx:I
        //   104: if_icmpne       188
        //   107: lload           4
        //   109: lstore          6
        //   111: lload           10
        //   113: lstore          8
        //   115: aload           12
        //   117: invokevirtual   java/io/RandomAccessFile.close:()V
        //   120: iload_1        
        //   121: istore_2       
        //   122: lload           4
        //   124: lstore          6
        //   126: lload           10
        //   128: lstore          8
        //   130: ldc             "NetflixFMP4Parser"
        //   132: ldc             "sidx offset=%d size=%d"
        //   134: iconst_2       
        //   135: anewarray       Ljava/lang/Object;
        //   138: dup            
        //   139: iconst_0       
        //   140: lload           4
        //   142: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   145: aastore        
        //   146: dup            
        //   147: iconst_1       
        //   148: iload_3        
        //   149: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   152: aastore        
        //   153: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   156: pop            
        //   157: iload_1        
        //   158: istore_2       
        //   159: lload           4
        //   161: lstore          6
        //   163: lload           10
        //   165: lstore          8
        //   167: new             Lcom/netflix/mediaclient/service/offline/manifest/NetflixFMP4Parser$SidxInfo;
        //   170: dup            
        //   171: iload_3        
        //   172: i2l            
        //   173: lload           4
        //   175: invokespecial   com/netflix/mediaclient/service/offline/manifest/NetflixFMP4Parser$SidxInfo.<init>:(JJ)V
        //   178: astore          12
        //   180: aload           12
        //   182: areturn        
        //   183: iconst_0       
        //   184: istore_1       
        //   185: goto            37
        //   188: lload           4
        //   190: lstore          6
        //   192: lload           10
        //   194: lstore          8
        //   196: aload           12
        //   198: iload_3        
        //   199: bipush          8
        //   201: isub           
        //   202: invokevirtual   java/io/RandomAccessFile.skipBytes:(I)I
        //   205: pop            
        //   206: lload           4
        //   208: iload_3        
        //   209: i2l            
        //   210: ladd           
        //   211: lstore          4
        //   213: goto            74
        //   216: astore          12
        //   218: iload_2        
        //   219: istore_1       
        //   220: aload           13
        //   222: ifnull          238
        //   225: ldc             "NetflixFMP4Parser"
        //   227: ldc             "closing RandomAccessFile"
        //   229: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   232: pop            
        //   233: aload           13
        //   235: invokevirtual   java/io/RandomAccessFile.close:()V
        //   238: ldc             "NetflixFMP4Parser"
        //   240: ldc             "ParseSidxInfo fileName=%s fileSize=%d offsetInFile=%d fileExists=%d exception=%s"
        //   242: iconst_5       
        //   243: anewarray       Ljava/lang/Object;
        //   246: dup            
        //   247: iconst_0       
        //   248: aload_0        
        //   249: aastore        
        //   250: dup            
        //   251: iconst_1       
        //   252: lload           8
        //   254: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   257: aastore        
        //   258: dup            
        //   259: iconst_2       
        //   260: lload           6
        //   262: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   265: aastore        
        //   266: dup            
        //   267: iconst_3       
        //   268: iload_1        
        //   269: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   272: aastore        
        //   273: dup            
        //   274: iconst_4       
        //   275: aload           12
        //   277: aastore        
        //   278: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   281: pop            
        //   282: new             Ljava/lang/Exception;
        //   285: dup            
        //   286: new             Ljava/lang/StringBuilder;
        //   289: dup            
        //   290: invokespecial   java/lang/StringBuilder.<init>:()V
        //   293: ldc             "filename="
        //   295: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   298: aload_0        
        //   299: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   302: ldc             " fileSize="
        //   304: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   307: lload           8
        //   309: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   312: ldc             " offsetInFile="
        //   314: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   317: lload           6
        //   319: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   322: ldc             " fileExists="
        //   324: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   327: iload_1        
        //   328: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   331: ldc             "  exception="
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: aload           12
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   341: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   344: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //   347: athrow         
        //   348: astore          13
        //   350: ldc             "NetflixFMP4Parser"
        //   352: ldc             "exception while closing RandomAccessFile:"
        //   354: aload           13
        //   356: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   359: pop            
        //   360: goto            238
        //   363: astore          14
        //   365: aload           12
        //   367: astore          13
        //   369: aload           14
        //   371: astore          12
        //   373: goto            220
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     35     216    220    Ljava/io/IOException;
        //  47     59     216    220    Ljava/io/IOException;
        //  67     74     363    376    Ljava/io/IOException;
        //  82     88     363    376    Ljava/io/IOException;
        //  96     107    363    376    Ljava/io/IOException;
        //  115    120    363    376    Ljava/io/IOException;
        //  130    157    216    220    Ljava/io/IOException;
        //  167    180    216    220    Ljava/io/IOException;
        //  196    206    363    376    Ljava/io/IOException;
        //  225    238    348    363    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
