// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build$VERSION;
import java.util.concurrent.Callable;

final class i$a implements Callable
{
    private static boolean d;
    private static Object f;
    private StringBuilder a;
    private StringBuilder b;
    private String[] c;
    private Process e;
    private i$b g;
    private i$b h;
    
    static {
        i$a.d = false;
    }
    
    public i$a(final Object f) {
        this.a = new StringBuilder();
        this.b = new StringBuilder();
        this.e = null;
        i$a.f = f;
        if (Build$VERSION.SDK_INT >= 8) {
            (this.c = new String[5])[0] = "logcat";
            this.c[1] = "-t";
            this.c[2] = "100";
            this.c[3] = "-v";
            this.c[4] = "time";
            return;
        }
        (this.c = new String[4])[0] = "logcat";
        this.c[1] = "-d";
        this.c[2] = "-v";
        this.c[3] = "time";
    }
    
    public static boolean a() {
        return i$a.d;
    }
    
    public static void b() {
        i$a.d = true;
    }
    
    private StringBuilder d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aconst_null    
        //     2: putfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //     5: aload_0        
        //     6: aconst_null    
        //     7: putfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //    10: getstatic       crittercism/android/i$a.d:Z
        //    13: ifne            208
        //    16: aload_0        
        //    17: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
        //    20: aload_0        
        //    21: getfield        crittercism/android/i$a.c:[Ljava/lang/String;
        //    24: invokevirtual   java/lang/Runtime.exec:([Ljava/lang/String;)Ljava/lang/Process;
        //    27: putfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    30: aload_0        
        //    31: new             Lcrittercism/android/i$b;
        //    34: dup            
        //    35: aload_0        
        //    36: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    39: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //    42: invokespecial   crittercism/android/i$b.<init>:(Ljava/io/InputStream;)V
        //    45: putfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //    48: aload_0        
        //    49: new             Lcrittercism/android/i$b;
        //    52: dup            
        //    53: aload_0        
        //    54: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    57: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
        //    60: invokespecial   crittercism/android/i$b.<init>:(Ljava/io/InputStream;)V
        //    63: putfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //    66: aload_0        
        //    67: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //    70: invokevirtual   crittercism/android/i$b.start:()V
        //    73: aload_0        
        //    74: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //    77: invokevirtual   crittercism/android/i$b.start:()V
        //    80: aload_0        
        //    81: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    84: invokevirtual   java/lang/Process.waitFor:()I
        //    87: pop            
        //    88: aload_0        
        //    89: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //    92: ifnull          126
        //    95: aload_0        
        //    96: aload_0        
        //    97: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //   100: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
        //   103: putfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   106: new             Ljava/lang/StringBuilder;
        //   109: dup            
        //   110: ldc             "this.inputStreamStringBuilder.toString() = "
        //   112: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   115: aload_0        
        //   116: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: pop            
        //   126: aload_0        
        //   127: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //   130: ifnull          164
        //   133: aload_0        
        //   134: aload_0        
        //   135: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //   138: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
        //   141: putfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
        //   144: new             Ljava/lang/StringBuilder;
        //   147: dup            
        //   148: ldc             "this.errorStreamStringBuilder.toString() = "
        //   150: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   153: aload_0        
        //   154: getfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
        //   157: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: pop            
        //   164: aload_0        
        //   165: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   168: ifnull          208
        //   171: aload_0        
        //   172: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   175: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //   178: invokevirtual   java/io/InputStream.close:()V
        //   181: aload_0        
        //   182: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   185: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
        //   188: invokevirtual   java/io/InputStream.close:()V
        //   191: aload_0        
        //   192: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   195: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
        //   198: invokevirtual   java/io/OutputStream.close:()V
        //   201: aload_0        
        //   202: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   205: invokevirtual   java/lang/Process.destroy:()V
        //   208: aload_0        
        //   209: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   212: areturn        
        //   213: astore_1       
        //   214: iconst_1       
        //   215: putstatic       crittercism/android/i$a.d:Z
        //   218: aload_0        
        //   219: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //   222: ifnull          256
        //   225: aload_0        
        //   226: aload_0        
        //   227: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //   230: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
        //   233: putfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   236: new             Ljava/lang/StringBuilder;
        //   239: dup            
        //   240: ldc             "this.inputStreamStringBuilder.toString() = "
        //   242: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   245: aload_0        
        //   246: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   249: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   252: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: pop            
        //   256: aload_0        
        //   257: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //   260: ifnull          294
        //   263: aload_0        
        //   264: aload_0        
        //   265: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //   268: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
        //   271: putfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
        //   274: new             Ljava/lang/StringBuilder;
        //   277: dup            
        //   278: ldc             "this.errorStreamStringBuilder.toString() = "
        //   280: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   283: aload_0        
        //   284: getfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
        //   287: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: pop            
        //   294: aload_0        
        //   295: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   298: ifnull          208
        //   301: aload_0        
        //   302: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   305: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //   308: invokevirtual   java/io/InputStream.close:()V
        //   311: aload_0        
        //   312: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   315: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
        //   318: invokevirtual   java/io/InputStream.close:()V
        //   321: aload_0        
        //   322: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   325: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
        //   328: invokevirtual   java/io/OutputStream.close:()V
        //   331: aload_0        
        //   332: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   335: invokevirtual   java/lang/Process.destroy:()V
        //   338: goto            208
        //   341: astore_1       
        //   342: goto            208
        //   345: astore_1       
        //   346: aload_0        
        //   347: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //   350: ifnull          384
        //   353: aload_0        
        //   354: aload_0        
        //   355: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //   358: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
        //   361: putfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   364: new             Ljava/lang/StringBuilder;
        //   367: dup            
        //   368: ldc             "this.inputStreamStringBuilder.toString() = "
        //   370: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   373: aload_0        
        //   374: getfield        crittercism/android/i$a.a:Ljava/lang/StringBuilder;
        //   377: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   383: pop            
        //   384: aload_0        
        //   385: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //   388: ifnull          422
        //   391: aload_0        
        //   392: aload_0        
        //   393: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //   396: invokevirtual   crittercism/android/i$b.a:()Ljava/lang/StringBuilder;
        //   399: putfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
        //   402: new             Ljava/lang/StringBuilder;
        //   405: dup            
        //   406: ldc             "this.errorStreamStringBuilder.toString() = "
        //   408: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   411: aload_0        
        //   412: getfield        crittercism/android/i$a.b:Ljava/lang/StringBuilder;
        //   415: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: pop            
        //   422: aload_0        
        //   423: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   426: ifnull          466
        //   429: aload_0        
        //   430: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   433: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //   436: invokevirtual   java/io/InputStream.close:()V
        //   439: aload_0        
        //   440: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   443: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
        //   446: invokevirtual   java/io/InputStream.close:()V
        //   449: aload_0        
        //   450: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   453: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
        //   456: invokevirtual   java/io/OutputStream.close:()V
        //   459: aload_0        
        //   460: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //   463: invokevirtual   java/lang/Process.destroy:()V
        //   466: aload_1        
        //   467: athrow         
        //   468: astore_2       
        //   469: goto            466
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  16     88     213    341    Ljava/lang/Exception;
        //  16     88     345    472    Any
        //  171    208    341    345    Ljava/lang/Exception;
        //  214    218    345    472    Any
        //  301    338    341    345    Ljava/lang/Exception;
        //  429    466    468    472    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0208:
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
    
    public final void c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       crittercism/android/i$a.f:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: aload_0        
        //     7: getfield        crittercism/android/i$a.g:Lcrittercism/android/i$b;
        //    10: invokevirtual   crittercism/android/i$b.b:()V
        //    13: aload_0        
        //    14: getfield        crittercism/android/i$a.h:Lcrittercism/android/i$b;
        //    17: invokevirtual   crittercism/android/i$b.b:()V
        //    20: aload_0        
        //    21: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    24: ifnull          57
        //    27: aload_0        
        //    28: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    31: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //    34: invokevirtual   java/io/InputStream.close:()V
        //    37: aload_0        
        //    38: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    41: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
        //    44: invokevirtual   java/io/InputStream.close:()V
        //    47: aload_0        
        //    48: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    51: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
        //    54: invokevirtual   java/io/OutputStream.close:()V
        //    57: aload_0        
        //    58: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    61: ifnull          71
        //    64: aload_0        
        //    65: getfield        crittercism/android/i$a.e:Ljava/lang/Process;
        //    68: invokevirtual   java/lang/Process.destroy:()V
        //    71: aload_1        
        //    72: monitorexit    
        //    73: return         
        //    74: astore_2       
        //    75: aload_1        
        //    76: monitorexit    
        //    77: aload_2        
        //    78: athrow         
        //    79: astore_2       
        //    80: goto            71
        //    83: astore_2       
        //    84: goto            57
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      57     83     87     Ljava/lang/Exception;
        //  6      57     74     79     Any
        //  57     71     79     83     Ljava/lang/Exception;
        //  57     71     74     79     Any
        //  71     73     74     79     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
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
