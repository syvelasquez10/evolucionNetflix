// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import android.content.Context;
import java.util.List;
import java.io.File;

public final class bs
{
    public final File a;
    public String b;
    public List c;
    private cj d;
    private int e;
    private int f;
    private int g;
    private bs$a h;
    private boolean i;
    
    public bs(final Context context, final br br) {
        this(new File(context.getFilesDir().getAbsolutePath() + "//com.crittercism//" + br.a()), br.c(), br.d(), br.e(), br.b(), br.f());
    }
    
    private bs(final File a, final bs$a h, final cj d, final int g, final int f, final String b) {
        this.i = false;
        this.h = h;
        this.d = d;
        this.g = g;
        this.f = f;
        this.b = b;
        (this.a = a).mkdirs();
        this.d();
        this.e = this.h().length;
        this.c = new LinkedList();
    }
    
    private boolean c(final ch p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/File;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        crittercism/android/bs.a:Ljava/io/File;
        //     8: aload_1        
        //     9: invokeinterface crittercism/android/ch.e:()Ljava/lang/String;
        //    14: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    17: astore          4
        //    19: aconst_null    
        //    20: astore_2       
        //    21: new             Ljava/io/BufferedOutputStream;
        //    24: dup            
        //    25: new             Ljava/io/FileOutputStream;
        //    28: dup            
        //    29: aload           4
        //    31: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    34: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    37: astore_3       
        //    38: aload_3        
        //    39: astore_2       
        //    40: aload_1        
        //    41: aload_2        
        //    42: invokeinterface crittercism/android/ch.a:(Ljava/io/OutputStream;)V
        //    47: aload_2        
        //    48: invokevirtual   java/io/OutputStream.close:()V
        //    51: iconst_1       
        //    52: ireturn        
        //    53: astore_3       
        //    54: new             Ljava/lang/StringBuilder;
        //    57: dup            
        //    58: ldc             "Could not open output stream to : "
        //    60: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    63: aload           4
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    68: pop            
        //    69: invokestatic    crittercism/android/dy.a:()V
        //    72: goto            40
        //    75: astore_1       
        //    76: aload           4
        //    78: invokevirtual   java/io/File.delete:()Z
        //    81: pop            
        //    82: new             Ljava/lang/StringBuilder;
        //    85: dup            
        //    86: ldc             "Unable to close "
        //    88: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    91: aload           4
        //    93: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   102: aload_1        
        //   103: invokestatic    crittercism/android/dy.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   106: iconst_0       
        //   107: ireturn        
        //   108: astore_1       
        //   109: aload           4
        //   111: invokevirtual   java/io/File.delete:()Z
        //   114: pop            
        //   115: new             Ljava/lang/StringBuilder;
        //   118: dup            
        //   119: ldc             "Unable to write to "
        //   121: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   124: aload           4
        //   126: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   135: aload_1        
        //   136: invokestatic    crittercism/android/dy.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   139: aload_2        
        //   140: invokevirtual   java/io/OutputStream.close:()V
        //   143: iconst_0       
        //   144: ireturn        
        //   145: astore_1       
        //   146: aload           4
        //   148: invokevirtual   java/io/File.delete:()Z
        //   151: pop            
        //   152: new             Ljava/lang/StringBuilder;
        //   155: dup            
        //   156: ldc             "Unable to close "
        //   158: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   161: aload           4
        //   163: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   172: aload_1        
        //   173: invokestatic    crittercism/android/dy.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   176: iconst_0       
        //   177: ireturn        
        //   178: astore_1       
        //   179: aload_2        
        //   180: invokevirtual   java/io/OutputStream.close:()V
        //   183: aload_1        
        //   184: athrow         
        //   185: astore_1       
        //   186: aload           4
        //   188: invokevirtual   java/io/File.delete:()Z
        //   191: pop            
        //   192: new             Ljava/lang/StringBuilder;
        //   195: dup            
        //   196: ldc             "Unable to close "
        //   198: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   201: aload           4
        //   203: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   212: aload_1        
        //   213: invokestatic    crittercism/android/dy.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   216: iconst_0       
        //   217: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  21     38     53     75     Ljava/io/FileNotFoundException;
        //  40     47     108    178    Ljava/io/IOException;
        //  40     47     178    218    Any
        //  47     51     75     108    Ljava/io/IOException;
        //  109    139    178    218    Any
        //  139    143    145    178    Ljava/io/IOException;
        //  179    183    185    218    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    private boolean d() {
        if (!this.a.isDirectory()) {
            this.i = true;
            final String absolutePath = this.a.getAbsolutePath();
            if (this.a.exists()) {
                new IOException(absolutePath + " is not a directory");
            }
            else {
                new FileNotFoundException(absolutePath + " does not exist");
            }
        }
        return !this.i;
    }
    
    private void e() {
        while (this.b() > this.i() && this.f()) {}
    }
    
    private boolean f() {
        final bs$a h = this.h;
        if (this.h != null) {
            final bs$a h2 = this.h;
            final File[] g = this.g();
            File file = null;
            if (g.length > h2.a) {
                file = g[h2.a];
            }
            if (file != null && file.delete()) {
                return true;
            }
        }
        return false;
    }
    
    private File[] g() {
        final File[] h = this.h();
        Arrays.sort(h);
        return h;
    }
    
    private File[] h() {
        File[] listFiles;
        if ((listFiles = this.a.listFiles()) == null) {
            listFiles = new File[0];
        }
        return listFiles;
    }
    
    private int i() {
        synchronized (this) {
            return this.f;
        }
    }
    
    public final bs a(final Context context) {
        return new bs(new File(context.getFilesDir().getAbsolutePath() + "//com.crittercism/pending/" + (this.a.getName() + "_" + UUID.randomUUID().toString())), this.h, this.d, this.g, this.f, this.b);
    }
    
    public final void a() {
        synchronized (this) {
            if (this.d()) {
                final File[] h = this.h();
                for (int i = 0; i < h.length; ++i) {
                    h[i].delete();
                }
            }
        }
    }
    
    public final void a(final bs p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       5
        //     4: return         
        //     5: aload_0        
        //     6: getfield        crittercism/android/bs.a:Ljava/io/File;
        //     9: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    12: aload_1        
        //    13: getfield        crittercism/android/bs.a:Ljava/io/File;
        //    16: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    19: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //    22: istore_2       
        //    23: iload_2        
        //    24: ifeq            4
        //    27: iload_2        
        //    28: ifge            66
        //    31: aload_1        
        //    32: astore          4
        //    34: aload_0        
        //    35: astore_3       
        //    36: aload_3        
        //    37: monitorenter   
        //    38: aload           4
        //    40: monitorenter   
        //    41: aload_0        
        //    42: invokespecial   crittercism/android/bs.d:()Z
        //    45: ifeq            55
        //    48: aload_1        
        //    49: invokespecial   crittercism/android/bs.d:()Z
        //    52: ifne            74
        //    55: aload           4
        //    57: monitorexit    
        //    58: aload_3        
        //    59: monitorexit    
        //    60: return         
        //    61: astore_1       
        //    62: aload_3        
        //    63: monitorexit    
        //    64: aload_1        
        //    65: athrow         
        //    66: aload_0        
        //    67: astore          4
        //    69: aload_1        
        //    70: astore_3       
        //    71: goto            36
        //    74: aload_0        
        //    75: invokespecial   crittercism/android/bs.g:()[Ljava/io/File;
        //    78: astore          5
        //    80: iconst_0       
        //    81: istore_2       
        //    82: iload_2        
        //    83: aload           5
        //    85: arraylength    
        //    86: if_icmpge       126
        //    89: new             Ljava/io/File;
        //    92: dup            
        //    93: aload_1        
        //    94: getfield        crittercism/android/bs.a:Ljava/io/File;
        //    97: aload           5
        //    99: iload_2        
        //   100: aaload         
        //   101: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   104: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   107: astore          6
        //   109: aload           5
        //   111: iload_2        
        //   112: aaload         
        //   113: aload           6
        //   115: invokevirtual   java/io/File.renameTo:(Ljava/io/File;)Z
        //   118: pop            
        //   119: iload_2        
        //   120: iconst_1       
        //   121: iadd           
        //   122: istore_2       
        //   123: goto            82
        //   126: aload_1        
        //   127: invokespecial   crittercism/android/bs.e:()V
        //   130: aload_0        
        //   131: getfield        crittercism/android/bs.c:Ljava/util/List;
        //   134: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   139: astore_1       
        //   140: aload_1        
        //   141: invokeinterface java/util/Iterator.hasNext:()Z
        //   146: ifeq            172
        //   149: aload_1        
        //   150: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   155: checkcast       Lcrittercism/android/bt;
        //   158: invokeinterface crittercism/android/bt.d:()V
        //   163: goto            140
        //   166: astore_1       
        //   167: aload           4
        //   169: monitorexit    
        //   170: aload_1        
        //   171: athrow         
        //   172: aload           4
        //   174: monitorexit    
        //   175: aload_3        
        //   176: monitorexit    
        //   177: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     41     61     66     Any
        //  41     55     166    172    Any
        //  55     58     166    172    Any
        //  58     60     61     66     Any
        //  74     80     166    172    Any
        //  82     119    166    172    Any
        //  126    140    166    172    Any
        //  140    163    166    172    Any
        //  167    172    61     66     Any
        //  172    175    166    172    Any
        //  175    177    61     66     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    
    public final void a(final String s) {
        synchronized (this) {
            if (this.d() && s != null) {
                final File file = new File(this.a.getAbsolutePath(), s);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }
    
    public final boolean a(final ch ch) {
        while (true) {
            final boolean b = false;
            Label_0046: {
                synchronized (this) {
                    boolean c;
                    if (!this.d()) {
                        c = b;
                    }
                    else {
                        if (this.e < this.g) {
                            break Label_0046;
                        }
                        dy.b();
                        c = b;
                    }
                    return c;
                }
            }
            final int b2 = this.b();
            if (b2 == this.i()) {
                final boolean c = b;
                if (!this.f()) {
                    return c;
                }
            }
            if (b2 > this.i()) {
                this.i = true;
                return b;
            }
            final ch ch2;
            boolean c = this.c(ch2);
            if (c) {
                ++this.e;
            }
            synchronized (this.c) {
                final Iterator<bt> iterator = this.c.iterator();
                while (iterator.hasNext()) {
                    iterator.next().c();
                }
            }
            // monitorexit(ch)
            return c;
        }
    }
    
    public final int b() {
        synchronized (this) {
            return this.h().length;
        }
    }
    
    public final boolean b(final ch ch) {
        synchronized (this) {
            boolean c;
            if (!this.d()) {
                c = false;
            }
            else {
                new File(this.a, ch.e()).delete();
                c = this.c(ch);
            }
            return c;
        }
    }
    
    public final List c() {
        synchronized (this) {
            final ArrayList<bq> list = new ArrayList<bq>();
            if (this.d()) {
                final cj d = this.d;
                final File[] g = this.g();
                for (int i = 0; i < g.length; ++i) {
                    list.add(this.d.a(g[i]));
                }
            }
            return list;
        }
    }
}
