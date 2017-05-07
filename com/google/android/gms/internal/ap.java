// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import android.util.Base64OutputStream;

public class ap
{
    private final int nJ;
    private final int nK;
    private final ao nL;
    private Base64OutputStream nM;
    private ByteArrayOutputStream nN;
    
    public ap(final int nk) {
        this.nL = new ar();
        this.nK = nk;
        this.nJ = 6;
    }
    
    private String m(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             "\n"
        //     3: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //     6: astore_1       
        //     7: aload_1        
        //     8: ifnull          16
        //    11: aload_1        
        //    12: arraylength    
        //    13: ifne            19
        //    16: ldc             ""
        //    18: areturn        
        //    19: aload_0        
        //    20: new             Ljava/io/ByteArrayOutputStream;
        //    23: dup            
        //    24: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    27: putfield        com/google/android/gms/internal/ap.nN:Ljava/io/ByteArrayOutputStream;
        //    30: aload_0        
        //    31: new             Landroid/util/Base64OutputStream;
        //    34: dup            
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/internal/ap.nN:Ljava/io/ByteArrayOutputStream;
        //    39: bipush          10
        //    41: invokespecial   android/util/Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    44: putfield        com/google/android/gms/internal/ap.nM:Landroid/util/Base64OutputStream;
        //    47: aload_1        
        //    48: new             Lcom/google/android/gms/internal/ap$1;
        //    51: dup            
        //    52: aload_0        
        //    53: invokespecial   com/google/android/gms/internal/ap$1.<init>:(Lcom/google/android/gms/internal/ap;)V
        //    56: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;Ljava/util/Comparator;)V
        //    59: iconst_0       
        //    60: istore_2       
        //    61: iload_2        
        //    62: aload_1        
        //    63: arraylength    
        //    64: if_icmpge       124
        //    67: iload_2        
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/internal/ap.nK:I
        //    72: if_icmpge       124
        //    75: aload_1        
        //    76: iload_2        
        //    77: aaload         
        //    78: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    81: invokevirtual   java/lang/String.length:()I
        //    84: ifne            94
        //    87: iload_2        
        //    88: iconst_1       
        //    89: iadd           
        //    90: istore_2       
        //    91: goto            61
        //    94: aload_0        
        //    95: getfield        com/google/android/gms/internal/ap.nM:Landroid/util/Base64OutputStream;
        //    98: aload_0        
        //    99: getfield        com/google/android/gms/internal/ap.nL:Lcom/google/android/gms/internal/ao;
        //   102: aload_1        
        //   103: iload_2        
        //   104: aaload         
        //   105: invokevirtual   com/google/android/gms/internal/ao.l:(Ljava/lang/String;)[B
        //   108: invokevirtual   android/util/Base64OutputStream.write:([B)V
        //   111: goto            87
        //   114: astore_3       
        //   115: ldc             "Error while writing hash to byteStream"
        //   117: aload_3        
        //   118: invokestatic    com/google/android/gms/internal/gs.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   121: goto            87
        //   124: aload_0        
        //   125: getfield        com/google/android/gms/internal/ap.nM:Landroid/util/Base64OutputStream;
        //   128: invokevirtual   android/util/Base64OutputStream.flush:()V
        //   131: aload_0        
        //   132: getfield        com/google/android/gms/internal/ap.nM:Landroid/util/Base64OutputStream;
        //   135: invokevirtual   android/util/Base64OutputStream.close:()V
        //   138: aload_0        
        //   139: getfield        com/google/android/gms/internal/ap.nN:Ljava/io/ByteArrayOutputStream;
        //   142: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
        //   145: astore_1       
        //   146: aload_1        
        //   147: areturn        
        //   148: astore_1       
        //   149: ldc             "HashManager: Unable to convert to base 64"
        //   151: aload_1        
        //   152: invokestatic    com/google/android/gms/internal/gs.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   155: ldc             ""
        //   157: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  94     111    114    124    Ljava/io/IOException;
        //  124    146    148    158    Ljava/io/IOException;
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
    
    public String a(final ArrayList<String> list) {
        final StringBuffer sb = new StringBuffer();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toLowerCase());
            sb.append('\n');
        }
        switch (false) {
            default: {
                return "";
            }
            case 0: {
                return this.n(sb.toString());
            }
            case 1: {
                return this.m(sb.toString());
            }
        }
    }
    
    String n(String s) {
        final String[] split = s.split("\n");
        if (split == null || split.length == 0) {
            return "";
        }
        this.nN = new ByteArrayOutputStream();
        this.nM = new Base64OutputStream((OutputStream)this.nN, 10);
        final PriorityQueue<as$a> priorityQueue = new PriorityQueue<as$a>(this.nK, new ap$2(this));
        for (int i = 0; i < split.length; ++i) {
            final String[] p = aq.p(split[i]);
            if (p.length >= this.nJ) {
                as.a(p, this.nK, this.nJ, priorityQueue);
            }
        }
        s = (String)priorityQueue.iterator();
        while (((Iterator)s).hasNext()) {
            final as$a as$a = ((Iterator<as$a>)s).next();
            try {
                this.nM.write(this.nL.l(as$a.nQ));
            }
            catch (IOException ex) {
                gs.b("Error while writing hash to byteStream", ex);
            }
        }
        try {
            this.nM.flush();
            this.nM.close();
            s = this.nN.toString();
            return s;
        }
        catch (IOException ex2) {
            gs.b("HashManager: unable to convert to base 64", ex2);
            return "";
        }
    }
}
