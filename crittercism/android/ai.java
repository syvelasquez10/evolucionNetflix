// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public final class ai implements Runnable
{
    private l a;
    private boolean b;
    private List c;
    
    public ai(final l a) {
        this.b = false;
        this.a = a;
        this.c = new ArrayList();
    }
    
    private void b() {
        synchronized (this) {
            this.b = true;
        }
    }
    
    private void c() {
        synchronized (this) {
            final Iterator<z> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                iterator.next().b();
            }
        }
        this.c.clear();
    }
    // monitorexit(this)
    
    public final void a(final z z) {
        synchronized (this) {
            if (!this.a()) {
                this.c.add(z);
            }
            else {
                z.b();
            }
        }
    }
    
    public final boolean a() {
        synchronized (this) {
            return this.b;
        }
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //     4: getfield        crittercism/android/l.e:Lcrittercism/android/at;
        //     7: astore_2       
        //     8: aload_0        
        //     9: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //    12: getfield        crittercism/android/l.f:Landroid/content/Context;
        //    15: astore_3       
        //    16: aload_2        
        //    17: aload_0        
        //    18: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //    21: aload_3        
        //    22: invokevirtual   crittercism/android/at.a:(Lcrittercism/android/h;Landroid/content/Context;)Z
        //    25: pop            
        //    26: aload_2        
        //    27: invokevirtual   crittercism/android/at.c:()Lcrittercism/android/ap;
        //    30: invokevirtual   crittercism/android/ap.a:()Z
        //    33: ifne            221
        //    36: aload_0        
        //    37: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //    40: getfield        crittercism/android/l.n:Lcrittercism/android/q;
        //    43: aload_0        
        //    44: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //    47: getstatic       crittercism/android/ae.b:Lcrittercism/android/ae;
        //    50: invokevirtual   crittercism/android/ae.a:()Ljava/lang/String;
        //    53: getstatic       crittercism/android/ae.b:Lcrittercism/android/ae;
        //    56: invokevirtual   crittercism/android/ae.b:()Ljava/lang/String;
        //    59: invokevirtual   crittercism/android/q.b:(Lcrittercism/android/h;Ljava/lang/String;Ljava/lang/String;)Z
        //    62: pop            
        //    63: aload_0        
        //    64: invokespecial   crittercism/android/ai.c:()V
        //    67: aload_0        
        //    68: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //    71: getfield        crittercism/android/l.p:Z
        //    74: ifeq            94
        //    77: aload_0        
        //    78: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //    81: getfield        crittercism/android/l.s:Ljava/io/File;
        //    84: astore_3       
        //    85: aload_3        
        //    86: ifnull          94
        //    89: aload_3        
        //    90: invokevirtual   java/io/File.delete:()Z
        //    93: pop            
        //    94: aload_2        
        //    95: invokevirtual   crittercism/android/at.b:()Lcrittercism/android/an;
        //    98: aload_0        
        //    99: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //   102: getstatic       crittercism/android/ae.e:Lcrittercism/android/ae;
        //   105: invokevirtual   crittercism/android/ae.a:()Ljava/lang/String;
        //   108: getstatic       crittercism/android/ae.e:Lcrittercism/android/ae;
        //   111: invokevirtual   crittercism/android/ae.b:()Ljava/lang/String;
        //   114: invokevirtual   crittercism/android/an.a:(Lcrittercism/android/h;Ljava/lang/String;Ljava/lang/String;)Z
        //   117: pop            
        //   118: aload_2        
        //   119: invokevirtual   crittercism/android/at.e:()Lcrittercism/android/aq;
        //   122: aload_0        
        //   123: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //   126: getstatic       crittercism/android/ae.d:Lcrittercism/android/ae;
        //   129: invokevirtual   crittercism/android/ae.a:()Ljava/lang/String;
        //   132: getstatic       crittercism/android/ae.d:Lcrittercism/android/ae;
        //   135: invokevirtual   crittercism/android/ae.b:()Ljava/lang/String;
        //   138: invokevirtual   crittercism/android/aq.a:(Lcrittercism/android/h;Ljava/lang/String;Ljava/lang/String;)Z
        //   141: pop            
        //   142: aload_0        
        //   143: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //   146: astore_2       
        //   147: aload_2        
        //   148: getfield        crittercism/android/l.g:Lcrittercism/android/n;
        //   151: invokevirtual   crittercism/android/n.a:()V
        //   154: aload_2        
        //   155: getfield        crittercism/android/l.t:Lcrittercism/android/m;
        //   158: invokevirtual   crittercism/android/m.delaySendingAppLoad:()Z
        //   161: ifne            179
        //   164: aload_2        
        //   165: getfield        crittercism/android/l.e:Lcrittercism/android/at;
        //   168: invokevirtual   crittercism/android/at.c:()Lcrittercism/android/ap;
        //   171: invokevirtual   crittercism/android/ap.a:()Z
        //   174: istore_1       
        //   175: iload_1        
        //   176: ifeq            226
        //   179: aload_0        
        //   180: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //   183: astore_2       
        //   184: aload_0        
        //   185: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //   188: getfield        crittercism/android/l.t:Lcrittercism/android/m;
        //   191: invokevirtual   com/crittercism/app/CrittercismConfig.isNdkCrashReportingEnabled:()Z
        //   194: istore_1       
        //   195: iload_1        
        //   196: ifeq            213
        //   199: aload_2        
        //   200: getfield        crittercism/android/l.f:Landroid/content/Context;
        //   203: aload_2        
        //   204: getfield        crittercism/android/l.t:Lcrittercism/android/m;
        //   207: invokevirtual   crittercism/android/m.getNativeDumpPath:()Ljava/lang/String;
        //   210: invokestatic    com/crittercism/app/CrittercismNDK.installNdkLib:(Landroid/content/Context;Ljava/lang/String;)V
        //   213: aload_0        
        //   214: getfield        crittercism/android/ai.a:Lcrittercism/android/l;
        //   217: iconst_1       
        //   218: putfield        crittercism/android/l.q:Z
        //   221: aload_0        
        //   222: invokespecial   crittercism/android/ai.b:()V
        //   225: return         
        //   226: new             Lcrittercism/android/af;
        //   229: dup            
        //   230: aload_2        
        //   231: getfield        crittercism/android/l.g:Lcrittercism/android/n;
        //   234: invokespecial   crittercism/android/af.<init>:(Lcrittercism/android/s;)V
        //   237: invokevirtual   crittercism/android/af.a:()V
        //   240: new             Lcrittercism/android/af;
        //   243: dup            
        //   244: aload_2        
        //   245: getfield        crittercism/android/l.h:Lcrittercism/android/x;
        //   248: invokespecial   crittercism/android/af.<init>:(Lcrittercism/android/s;)V
        //   251: invokevirtual   crittercism/android/af.a:()V
        //   254: new             Lcrittercism/android/af;
        //   257: dup            
        //   258: aload_2        
        //   259: getfield        crittercism/android/l.i:Lcrittercism/android/u;
        //   262: invokespecial   crittercism/android/af.<init>:(Lcrittercism/android/s;)V
        //   265: invokevirtual   crittercism/android/af.a:()V
        //   268: aload_2        
        //   269: invokevirtual   crittercism/android/l.k:()Z
        //   272: pop            
        //   273: goto            179
        //   276: astore_2       
        //   277: goto            179
        //   280: astore_2       
        //   281: new             Ljava/lang/StringBuilder;
        //   284: dup            
        //   285: ldc             "Exception installing ndk library: "
        //   287: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   290: aload_2        
        //   291: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   294: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: pop            
        //   301: goto            213
        //   304: astore_2       
        //   305: new             Ljava/lang/StringBuilder;
        //   308: dup            
        //   309: ldc             "Exception performing async disk io and finishing initialization: "
        //   311: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   314: aload_2        
        //   315: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   318: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: pop            
        //   325: aload_2        
        //   326: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   329: goto            221
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      85     304    332    Ljava/lang/Throwable;
        //  89     94     304    332    Ljava/lang/Throwable;
        //  94     154    304    332    Ljava/lang/Throwable;
        //  154    175    276    280    Ljava/lang/Exception;
        //  154    175    304    332    Ljava/lang/Throwable;
        //  179    195    304    332    Ljava/lang/Throwable;
        //  199    213    280    304    Ljava/lang/Throwable;
        //  213    221    304    332    Ljava/lang/Throwable;
        //  226    273    276    280    Ljava/lang/Exception;
        //  226    273    304    332    Ljava/lang/Throwable;
        //  281    301    304    332    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0213:
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
