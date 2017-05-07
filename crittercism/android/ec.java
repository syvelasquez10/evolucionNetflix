// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.File;

public final class ec
{
    public static void a(final File file) {
        if (!file.getAbsolutePath().contains("com.crittercism")) {
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                a(listFiles[i]);
            }
        }
        file.delete();
    }
    
    public static String b(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: new             Ljava/lang/StringBuilder;
        //     5: dup            
        //     6: invokespecial   java/lang/StringBuilder.<init>:()V
        //     9: astore          4
        //    11: new             Ljava/io/FileInputStream;
        //    14: dup            
        //    15: aload_0        
        //    16: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    19: astore_0       
        //    20: new             Ljava/io/InputStreamReader;
        //    23: dup            
        //    24: aload_0        
        //    25: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    28: astore_2       
        //    29: aload_2        
        //    30: invokevirtual   java/io/InputStreamReader.read:()I
        //    33: istore_1       
        //    34: iload_1        
        //    35: iconst_m1      
        //    36: if_icmpeq       75
        //    39: aload           4
        //    41: iload_1        
        //    42: i2c            
        //    43: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    46: pop            
        //    47: goto            29
        //    50: astore          4
        //    52: aload_0        
        //    53: astore_3       
        //    54: aload           4
        //    56: astore_0       
        //    57: aload_3        
        //    58: ifnull          65
        //    61: aload_3        
        //    62: invokevirtual   java/io/InputStream.close:()V
        //    65: aload_2        
        //    66: ifnull          73
        //    69: aload_2        
        //    70: invokevirtual   java/io/InputStreamReader.close:()V
        //    73: aload_0        
        //    74: athrow         
        //    75: aload_0        
        //    76: invokevirtual   java/io/InputStream.close:()V
        //    79: aload_2        
        //    80: invokevirtual   java/io/InputStreamReader.close:()V
        //    83: aload           4
        //    85: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    88: areturn        
        //    89: astore_0       
        //    90: aconst_null    
        //    91: astore_2       
        //    92: goto            57
        //    95: astore          4
        //    97: aconst_null    
        //    98: astore_2       
        //    99: aload_0        
        //   100: astore_3       
        //   101: aload           4
        //   103: astore_0       
        //   104: goto            57
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  11     20     89     95     Any
        //  20     29     95     107    Any
        //  29     34     50     57     Any
        //  39     47     50     57     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
