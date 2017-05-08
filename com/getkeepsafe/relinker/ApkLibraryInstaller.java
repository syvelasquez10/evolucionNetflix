// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker;

import java.io.File;
import android.content.Context;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Closeable;

public class ApkLibraryInstaller implements ReLinker$LibraryInstaller
{
    private void closeSilently(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    private long copy(final InputStream inputStream, final OutputStream outputStream) {
        long n = 0L;
        final byte[] array = new byte[4096];
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        outputStream.flush();
        return n;
    }
    
    @Override
    public void installLibrary(final Context p0, final String[] p1, final String p2, final File p3, final ReLinkerInstance p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          14
        //     3: aload_1        
        //     4: invokevirtual   android/content/Context.getApplicationInfo:()Landroid/content/pm/ApplicationInfo;
        //     7: astore          15
        //     9: iconst_0       
        //    10: istore          6
        //    12: iload           6
        //    14: iconst_5       
        //    15: if_icmpge       546
        //    18: new             Ljava/util/zip/ZipFile;
        //    21: dup            
        //    22: new             Ljava/io/File;
        //    25: dup            
        //    26: aload           15
        //    28: getfield        android/content/pm/ApplicationInfo.sourceDir:Ljava/lang/String;
        //    31: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    34: iconst_1       
        //    35: invokespecial   java/util/zip/ZipFile.<init>:(Ljava/io/File;I)V
        //    38: astore_1       
        //    39: aload_1        
        //    40: astore          14
        //    42: aload           14
        //    44: ifnonnull       75
        //    47: aload           5
        //    49: ldc             "FATAL! Couldn't find application APK!"
        //    51: invokevirtual   com/getkeepsafe/relinker/ReLinkerInstance.log:(Ljava/lang/String;)V
        //    54: aload           14
        //    56: ifnull          64
        //    59: aload           14
        //    61: invokevirtual   java/util/zip/ZipFile.close:()V
        //    64: return         
        //    65: astore_1       
        //    66: iload           6
        //    68: iconst_1       
        //    69: iadd           
        //    70: istore          6
        //    72: goto            12
        //    75: iconst_0       
        //    76: istore          6
        //    78: iload           6
        //    80: iconst_1       
        //    81: iadd           
        //    82: istore          7
        //    84: iload           6
        //    86: iconst_5       
        //    87: if_icmpge       464
        //    90: aload_2        
        //    91: arraylength    
        //    92: istore          8
        //    94: aconst_null    
        //    95: astore          15
        //    97: aconst_null    
        //    98: astore_1       
        //    99: iconst_0       
        //   100: istore          6
        //   102: iload           6
        //   104: iload           8
        //   106: if_icmpge       537
        //   109: aload_2        
        //   110: iload           6
        //   112: aaload         
        //   113: astore_1       
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc             "lib"
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: getstatic       java/io/File.separatorChar:C
        //   129: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   132: aload_1        
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: getstatic       java/io/File.separatorChar:C
        //   139: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   142: aload_3        
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   149: astore_1       
        //   150: aload           14
        //   152: aload_1        
        //   153: invokevirtual   java/util/zip/ZipFile.getEntry:(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
        //   156: astore          15
        //   158: aload           15
        //   160: ifnull          223
        //   163: aload_1        
        //   164: astore          16
        //   166: aload           15
        //   168: astore_1       
        //   169: aload           16
        //   171: ifnull          190
        //   174: aload           5
        //   176: ldc             "Looking for %s in APK..."
        //   178: iconst_1       
        //   179: anewarray       Ljava/lang/Object;
        //   182: dup            
        //   183: iconst_0       
        //   184: aload           16
        //   186: aastore        
        //   187: invokevirtual   com/getkeepsafe/relinker/ReLinkerInstance.log:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   190: aload_1        
        //   191: ifnonnull       241
        //   194: aload           16
        //   196: ifnull          232
        //   199: new             Lcom/getkeepsafe/relinker/MissingLibraryException;
        //   202: dup            
        //   203: aload           16
        //   205: invokespecial   com/getkeepsafe/relinker/MissingLibraryException.<init>:(Ljava/lang/String;)V
        //   208: athrow         
        //   209: astore_1       
        //   210: aload           14
        //   212: astore_2       
        //   213: aload_2        
        //   214: ifnull          221
        //   217: aload_2        
        //   218: invokevirtual   java/util/zip/ZipFile.close:()V
        //   221: aload_1        
        //   222: athrow         
        //   223: iload           6
        //   225: iconst_1       
        //   226: iadd           
        //   227: istore          6
        //   229: goto            102
        //   232: new             Lcom/getkeepsafe/relinker/MissingLibraryException;
        //   235: dup            
        //   236: aload_3        
        //   237: invokespecial   com/getkeepsafe/relinker/MissingLibraryException.<init>:(Ljava/lang/String;)V
        //   240: athrow         
        //   241: aload           5
        //   243: ldc             "Found %s! Extracting..."
        //   245: iconst_1       
        //   246: anewarray       Ljava/lang/Object;
        //   249: dup            
        //   250: iconst_0       
        //   251: aload           16
        //   253: aastore        
        //   254: invokevirtual   com/getkeepsafe/relinker/ReLinkerInstance.log:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   257: aload           4
        //   259: invokevirtual   java/io/File.exists:()Z
        //   262: ifne            292
        //   265: aload           4
        //   267: invokevirtual   java/io/File.createNewFile:()Z
        //   270: istore          9
        //   272: iload           9
        //   274: ifne            292
        //   277: iload           7
        //   279: istore          6
        //   281: goto            78
        //   284: astore_1       
        //   285: iload           7
        //   287: istore          6
        //   289: goto            78
        //   292: aconst_null    
        //   293: astore          16
        //   295: aload           14
        //   297: aload_1        
        //   298: invokevirtual   java/util/zip/ZipFile.getInputStream:(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
        //   301: astore_1       
        //   302: new             Ljava/io/FileOutputStream;
        //   305: dup            
        //   306: aload           4
        //   308: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   311: astore          15
        //   313: aload_0        
        //   314: aload_1        
        //   315: aload           15
        //   317: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.copy:(Ljava/io/InputStream;Ljava/io/OutputStream;)J
        //   320: lstore          10
        //   322: aload           15
        //   324: invokevirtual   java/io/FileOutputStream.getFD:()Ljava/io/FileDescriptor;
        //   327: invokevirtual   java/io/FileDescriptor.sync:()V
        //   330: aload           4
        //   332: invokevirtual   java/io/File.length:()J
        //   335: lstore          12
        //   337: lload           10
        //   339: lload           12
        //   341: lcmp           
        //   342: ifeq            363
        //   345: aload_0        
        //   346: aload_1        
        //   347: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   350: aload_0        
        //   351: aload           15
        //   353: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   356: iload           7
        //   358: istore          6
        //   360: goto            78
        //   363: aload_0        
        //   364: aload_1        
        //   365: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   368: aload_0        
        //   369: aload           15
        //   371: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   374: aload           4
        //   376: iconst_1       
        //   377: iconst_0       
        //   378: invokevirtual   java/io/File.setReadable:(ZZ)Z
        //   381: pop            
        //   382: aload           4
        //   384: iconst_1       
        //   385: iconst_0       
        //   386: invokevirtual   java/io/File.setExecutable:(ZZ)Z
        //   389: pop            
        //   390: aload           4
        //   392: iconst_1       
        //   393: invokevirtual   java/io/File.setWritable:(Z)Z
        //   396: pop            
        //   397: aload           14
        //   399: ifnull          64
        //   402: aload           14
        //   404: invokevirtual   java/util/zip/ZipFile.close:()V
        //   407: return         
        //   408: astore_1       
        //   409: return         
        //   410: astore_1       
        //   411: aconst_null    
        //   412: astore_1       
        //   413: aconst_null    
        //   414: astore          15
        //   416: aload_0        
        //   417: aload_1        
        //   418: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   421: aload_0        
        //   422: aload           15
        //   424: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   427: iload           7
        //   429: istore          6
        //   431: goto            78
        //   434: aload_0        
        //   435: aload_1        
        //   436: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   439: aload_0        
        //   440: aload           15
        //   442: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   445: iload           7
        //   447: istore          6
        //   449: goto            78
        //   452: aload_0        
        //   453: aload_1        
        //   454: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   457: aload_0        
        //   458: aload_3        
        //   459: invokespecial   com/getkeepsafe/relinker/ApkLibraryInstaller.closeSilently:(Ljava/io/Closeable;)V
        //   462: aload_2        
        //   463: athrow         
        //   464: aload           5
        //   466: ldc             "FATAL! Couldn't extract the library from the APK!"
        //   468: invokevirtual   com/getkeepsafe/relinker/ReLinkerInstance.log:(Ljava/lang/String;)V
        //   471: aload           14
        //   473: ifnull          64
        //   476: aload           14
        //   478: invokevirtual   java/util/zip/ZipFile.close:()V
        //   481: return         
        //   482: astore_1       
        //   483: return         
        //   484: astore_1       
        //   485: return         
        //   486: astore_2       
        //   487: goto            221
        //   490: astore_1       
        //   491: aload           14
        //   493: astore_2       
        //   494: goto            213
        //   497: astore_2       
        //   498: aload           16
        //   500: astore_3       
        //   501: goto            452
        //   504: astore_2       
        //   505: aload           15
        //   507: astore_3       
        //   508: goto            452
        //   511: astore          15
        //   513: aconst_null    
        //   514: astore          15
        //   516: goto            434
        //   519: astore          16
        //   521: goto            434
        //   524: astore          15
        //   526: aconst_null    
        //   527: astore          15
        //   529: goto            416
        //   532: astore          16
        //   534: goto            416
        //   537: aload_1        
        //   538: astore          16
        //   540: aload           15
        //   542: astore_1       
        //   543: goto            169
        //   546: aconst_null    
        //   547: astore          14
        //   549: goto            42
        //   552: astore_1       
        //   553: aconst_null    
        //   554: astore_1       
        //   555: aconst_null    
        //   556: astore          15
        //   558: goto            434
        //   561: astore_2       
        //   562: aconst_null    
        //   563: astore_1       
        //   564: aload           16
        //   566: astore_3       
        //   567: goto            452
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  3      9      490    497    Any
        //  18     39     65     75     Ljava/io/IOException;
        //  18     39     490    497    Any
        //  47     54     209    213    Any
        //  59     64     484    486    Ljava/io/IOException;
        //  90     94     209    213    Any
        //  114    158    209    213    Any
        //  174    190    209    213    Any
        //  199    209    209    213    Any
        //  217    221    486    490    Ljava/io/IOException;
        //  232    241    209    213    Any
        //  241    257    209    213    Any
        //  257    272    284    292    Ljava/io/IOException;
        //  257    272    209    213    Any
        //  295    302    410    416    Ljava/io/FileNotFoundException;
        //  295    302    552    561    Ljava/io/IOException;
        //  295    302    561    570    Any
        //  302    313    524    532    Ljava/io/FileNotFoundException;
        //  302    313    511    519    Ljava/io/IOException;
        //  302    313    497    504    Any
        //  313    337    532    537    Ljava/io/FileNotFoundException;
        //  313    337    519    524    Ljava/io/IOException;
        //  313    337    504    511    Any
        //  345    356    209    213    Any
        //  363    397    209    213    Any
        //  402    407    408    410    Ljava/io/IOException;
        //  416    427    209    213    Any
        //  434    445    209    213    Any
        //  452    464    209    213    Any
        //  464    471    209    213    Any
        //  476    481    482    484    Ljava/io/IOException;
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
