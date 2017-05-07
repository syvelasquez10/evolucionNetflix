// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.File;
import dalvik.system.DexClassLoader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import android.content.Context;
import java.lang.reflect.Method;

public abstract class i extends h
{
    private static Method kA;
    private static Method kB;
    private static Method kC;
    private static Method kD;
    private static Method kE;
    private static Method kF;
    private static Method kG;
    private static Method kH;
    private static Method kI;
    private static String kJ;
    private static String kK;
    private static String kL;
    private static o kM;
    static boolean kN;
    private static long startTime;
    
    static {
        i.startTime = 0L;
        i.kN = false;
    }
    
    protected i(final Context context, final m m, final n n) {
        super(context, m, n);
    }
    
    static String a(final Context context, final m m) {
        if (i.kK != null) {
            return i.kK;
        }
        if (i.kD == null) {
            throw new i$a();
        }
        try {
            if (i.kD.invoke(null, context) == null) {
                throw new i$a();
            }
            goto Label_0065;
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    static ArrayList<Long> a(final MotionEvent motionEvent, final DisplayMetrics displayMetrics) {
        if (i.kE == null || motionEvent == null) {
            throw new i$a();
        }
        try {
            return (ArrayList<Long>)i.kE.invoke(null, motionEvent, displayMetrics);
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    protected static void a(final String p0, final Context p1, final m p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             Lcom/google/android/gms/internal/i;.class
        //     2: monitorenter   
        //     3: getstatic       com/google/android/gms/internal/i.kN:Z
        //     6: istore_3       
        //     7: iload_3        
        //     8: ifne            44
        //    11: new             Lcom/google/android/gms/internal/o;
        //    14: dup            
        //    15: aload_2        
        //    16: aconst_null    
        //    17: invokespecial   com/google/android/gms/internal/o.<init>:(Lcom/google/android/gms/internal/m;Ljava/security/SecureRandom;)V
        //    20: putstatic       com/google/android/gms/internal/i.kM:Lcom/google/android/gms/internal/o;
        //    23: aload_0        
        //    24: putstatic       com/google/android/gms/internal/i.kJ:Ljava/lang/String;
        //    27: aload_1        
        //    28: invokestatic    com/google/android/gms/internal/i.g:(Landroid/content/Context;)V
        //    31: invokestatic    com/google/android/gms/internal/i.w:()Ljava/lang/Long;
        //    34: invokevirtual   java/lang/Long.longValue:()J
        //    37: putstatic       com/google/android/gms/internal/i.startTime:J
        //    40: iconst_1       
        //    41: putstatic       com/google/android/gms/internal/i.kN:Z
        //    44: ldc             Lcom/google/android/gms/internal/i;.class
        //    46: monitorexit    
        //    47: return         
        //    48: astore_0       
        //    49: ldc             Lcom/google/android/gms/internal/i;.class
        //    51: monitorexit    
        //    52: aload_0        
        //    53: athrow         
        //    54: astore_0       
        //    55: goto            44
        //    58: astore_0       
        //    59: goto            44
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  3      7      48     54     Any
        //  11     44     58     62     Lcom/google/android/gms/internal/i$a;
        //  11     44     54     58     Ljava/lang/UnsupportedOperationException;
        //  11     44     48     54     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
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
    
    static String b(final Context context, final m m) {
        if (i.kL != null) {
            return i.kL;
        }
        if (i.kG == null) {
            throw new i$a();
        }
        try {
            if (i.kG.invoke(null, context) == null) {
                throw new i$a();
            }
            goto Label_0065;
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    private static String b(final byte[] array, final String s) {
        try {
            return new String(i.kM.c(array, s), "UTF-8");
        }
        catch (o$a o$a) {
            throw new i$a(o$a);
        }
        catch (UnsupportedEncodingException ex) {
            throw new i$a(ex);
        }
    }
    
    static String d(final Context context) {
        if (i.kF == null) {
            throw new i$a();
        }
        String s;
        try {
            s = (String)i.kF.invoke(null, context);
            if (s == null) {
                throw new i$a();
            }
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
        return s;
    }
    
    static ArrayList<Long> e(final Context context) {
        if (i.kH == null) {
            throw new i$a();
        }
        ArrayList list;
        try {
            list = (ArrayList)i.kH.invoke(null, context);
            if (list == null || list.size() != 2) {
                throw new i$a();
            }
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
        return (ArrayList<Long>)list;
    }
    
    static int[] f(final Context context) {
        if (i.kI == null) {
            throw new i$a();
        }
        try {
            return (int[])i.kI.invoke(null, context);
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    private static void g(final Context context) {
        byte[] b;
        File file;
        try {
            b = i.kM.b(q.getKey());
            i.kM.c(b, q.B());
            if ((file = context.getCacheDir()) == null && (file = context.getDir("dex", 0)) == null) {
                throw new i$a();
            }
            goto Label_0065;
        }
        catch (FileNotFoundException ex) {
            throw new i$a(ex);
        }
        catch (IOException ex2) {
            throw new i$a(ex2);
        }
        catch (ClassNotFoundException ex3) {
            throw new i$a(ex3);
        }
        catch (o$a o$a) {
            throw new i$a(o$a);
        }
        catch (NoSuchMethodException ex4) {
            throw new i$a(ex4);
        }
        catch (NullPointerException ex5) {
            throw new i$a(ex5);
        }
        try {
            final File file2;
            final DexClassLoader dexClassLoader = new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), (String)null, context.getClassLoader());
            final Class loadClass = dexClassLoader.loadClass(b(b, q.E()));
            final Class loadClass2 = dexClassLoader.loadClass(b(b, q.Q()));
            final Class loadClass3 = dexClassLoader.loadClass(b(b, q.K()));
            final Class loadClass4 = dexClassLoader.loadClass(b(b, q.I()));
            final Class loadClass5 = dexClassLoader.loadClass(b(b, q.S()));
            final Class loadClass6 = dexClassLoader.loadClass(b(b, q.G()));
            final Class loadClass7 = dexClassLoader.loadClass(b(b, q.O()));
            final Class loadClass8 = dexClassLoader.loadClass(b(b, q.M()));
            final Class loadClass9 = dexClassLoader.loadClass(b(b, q.C()));
            i.kA = loadClass.getMethod(b(b, q.F()), (Class[])new Class[0]);
            i.kB = loadClass2.getMethod(b(b, q.R()), (Class[])new Class[0]);
            i.kC = loadClass3.getMethod(b(b, q.L()), (Class[])new Class[0]);
            i.kD = loadClass4.getMethod(b(b, q.J()), Context.class);
            i.kE = loadClass5.getMethod(b(b, q.T()), MotionEvent.class, DisplayMetrics.class);
            i.kF = loadClass6.getMethod(b(b, q.H()), Context.class);
            i.kG = loadClass7.getMethod(b(b, q.P()), Context.class);
            i.kH = loadClass8.getMethod(b(b, q.N()), Context.class);
            i.kI = loadClass9.getMethod(b(b, q.D()), Context.class);
        }
        finally {}
    }
    
    static String v() {
        if (i.kJ == null) {
            throw new i$a();
        }
        return i.kJ;
    }
    
    static Long w() {
        if (i.kA == null) {
            throw new i$a();
        }
        try {
            return (Long)i.kA.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    static String x() {
        if (i.kC == null) {
            throw new i$a();
        }
        try {
            return (String)i.kC.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    static Long y() {
        if (i.kB == null) {
            throw new i$a();
        }
        try {
            return (Long)i.kB.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException ex) {
            throw new i$a(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new i$a(ex2);
        }
    }
    
    @Override
    protected void b(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_1       
        //     2: invokestatic    com/google/android/gms/internal/i.x:()Ljava/lang/String;
        //     5: invokevirtual   com/google/android/gms/internal/i.a:(ILjava/lang/String;)V
        //     8: aload_0        
        //     9: iconst_2       
        //    10: invokestatic    com/google/android/gms/internal/i.v:()Ljava/lang/String;
        //    13: invokevirtual   com/google/android/gms/internal/i.a:(ILjava/lang/String;)V
        //    16: invokestatic    com/google/android/gms/internal/i.w:()Ljava/lang/Long;
        //    19: invokevirtual   java/lang/Long.longValue:()J
        //    22: lstore_2       
        //    23: aload_0        
        //    24: bipush          25
        //    26: lload_2        
        //    27: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    30: getstatic       com/google/android/gms/internal/i.startTime:J
        //    33: lconst_0       
        //    34: lcmp           
        //    35: ifeq            58
        //    38: aload_0        
        //    39: bipush          17
        //    41: lload_2        
        //    42: getstatic       com/google/android/gms/internal/i.startTime:J
        //    45: lsub           
        //    46: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    49: aload_0        
        //    50: bipush          23
        //    52: getstatic       com/google/android/gms/internal/i.startTime:J
        //    55: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    58: aload_1        
        //    59: invokestatic    com/google/android/gms/internal/i.e:(Landroid/content/Context;)Ljava/util/ArrayList;
        //    62: astore          4
        //    64: aload_0        
        //    65: bipush          31
        //    67: aload           4
        //    69: iconst_0       
        //    70: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    73: checkcast       Ljava/lang/Long;
        //    76: invokevirtual   java/lang/Long.longValue:()J
        //    79: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    82: aload_0        
        //    83: bipush          32
        //    85: aload           4
        //    87: iconst_1       
        //    88: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    91: checkcast       Ljava/lang/Long;
        //    94: invokevirtual   java/lang/Long.longValue:()J
        //    97: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //   100: aload_0        
        //   101: bipush          33
        //   103: invokestatic    com/google/android/gms/internal/i.y:()Ljava/lang/Long;
        //   106: invokevirtual   java/lang/Long.longValue:()J
        //   109: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //   112: aload_0        
        //   113: bipush          27
        //   115: aload_1        
        //   116: aload_0        
        //   117: getfield        com/google/android/gms/internal/i.ky:Lcom/google/android/gms/internal/m;
        //   120: invokestatic    com/google/android/gms/internal/i.a:(Landroid/content/Context;Lcom/google/android/gms/internal/m;)Ljava/lang/String;
        //   123: invokevirtual   com/google/android/gms/internal/i.a:(ILjava/lang/String;)V
        //   126: aload_0        
        //   127: bipush          29
        //   129: aload_1        
        //   130: aload_0        
        //   131: getfield        com/google/android/gms/internal/i.ky:Lcom/google/android/gms/internal/m;
        //   134: invokestatic    com/google/android/gms/internal/i.b:(Landroid/content/Context;Lcom/google/android/gms/internal/m;)Ljava/lang/String;
        //   137: invokevirtual   com/google/android/gms/internal/i.a:(ILjava/lang/String;)V
        //   140: aload_1        
        //   141: invokestatic    com/google/android/gms/internal/i.f:(Landroid/content/Context;)[I
        //   144: astore_1       
        //   145: aload_0        
        //   146: iconst_5       
        //   147: aload_1        
        //   148: iconst_0       
        //   149: iaload         
        //   150: i2l            
        //   151: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //   154: aload_0        
        //   155: bipush          6
        //   157: aload_1        
        //   158: iconst_1       
        //   159: iaload         
        //   160: i2l            
        //   161: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //   164: return         
        //   165: astore_1       
        //   166: return         
        //   167: astore_1       
        //   168: return         
        //   169: astore          4
        //   171: goto            140
        //   174: astore          4
        //   176: goto            126
        //   179: astore          4
        //   181: goto            112
        //   184: astore          4
        //   186: goto            100
        //   189: astore          4
        //   191: goto            58
        //   194: astore          4
        //   196: goto            16
        //   199: astore          4
        //   201: goto            8
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  0      8      199    204    Lcom/google/android/gms/internal/i$a;
        //  0      8      165    167    Ljava/io/IOException;
        //  8      16     194    199    Lcom/google/android/gms/internal/i$a;
        //  8      16     165    167    Ljava/io/IOException;
        //  16     58     189    194    Lcom/google/android/gms/internal/i$a;
        //  16     58     165    167    Ljava/io/IOException;
        //  58     100    184    189    Lcom/google/android/gms/internal/i$a;
        //  58     100    165    167    Ljava/io/IOException;
        //  100    112    179    184    Lcom/google/android/gms/internal/i$a;
        //  100    112    165    167    Ljava/io/IOException;
        //  112    126    174    179    Lcom/google/android/gms/internal/i$a;
        //  112    126    165    167    Ljava/io/IOException;
        //  126    140    169    174    Lcom/google/android/gms/internal/i$a;
        //  126    140    165    167    Ljava/io/IOException;
        //  140    164    167    169    Lcom/google/android/gms/internal/i$a;
        //  140    164    165    167    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0008:
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
    
    @Override
    protected void c(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_2       
        //     2: invokestatic    com/google/android/gms/internal/i.v:()Ljava/lang/String;
        //     5: invokevirtual   com/google/android/gms/internal/i.a:(ILjava/lang/String;)V
        //     8: aload_0        
        //     9: iconst_1       
        //    10: invokestatic    com/google/android/gms/internal/i.x:()Ljava/lang/String;
        //    13: invokevirtual   com/google/android/gms/internal/i.a:(ILjava/lang/String;)V
        //    16: aload_0        
        //    17: bipush          25
        //    19: invokestatic    com/google/android/gms/internal/i.w:()Ljava/lang/Long;
        //    22: invokevirtual   java/lang/Long.longValue:()J
        //    25: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/internal/i.kw:Landroid/view/MotionEvent;
        //    32: aload_0        
        //    33: getfield        com/google/android/gms/internal/i.kx:Landroid/util/DisplayMetrics;
        //    36: invokestatic    com/google/android/gms/internal/i.a:(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
        //    39: astore_1       
        //    40: aload_0        
        //    41: bipush          14
        //    43: aload_1        
        //    44: iconst_0       
        //    45: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    48: checkcast       Ljava/lang/Long;
        //    51: invokevirtual   java/lang/Long.longValue:()J
        //    54: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    57: aload_0        
        //    58: bipush          15
        //    60: aload_1        
        //    61: iconst_1       
        //    62: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    65: checkcast       Ljava/lang/Long;
        //    68: invokevirtual   java/lang/Long.longValue:()J
        //    71: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    74: aload_1        
        //    75: invokevirtual   java/util/ArrayList.size:()I
        //    78: iconst_3       
        //    79: if_icmplt       99
        //    82: aload_0        
        //    83: bipush          16
        //    85: aload_1        
        //    86: iconst_2       
        //    87: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    90: checkcast       Ljava/lang/Long;
        //    93: invokevirtual   java/lang/Long.longValue:()J
        //    96: invokevirtual   com/google/android/gms/internal/i.a:(IJ)V
        //    99: return         
        //   100: astore_1       
        //   101: return         
        //   102: astore_1       
        //   103: return         
        //   104: astore_1       
        //   105: goto            28
        //   108: astore_1       
        //   109: goto            16
        //   112: astore_1       
        //   113: goto            8
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  0      8      112    116    Lcom/google/android/gms/internal/i$a;
        //  0      8      100    102    Ljava/io/IOException;
        //  8      16     108    112    Lcom/google/android/gms/internal/i$a;
        //  8      16     100    102    Ljava/io/IOException;
        //  16     28     104    108    Lcom/google/android/gms/internal/i$a;
        //  16     28     100    102    Ljava/io/IOException;
        //  28     99     102    104    Lcom/google/android/gms/internal/i$a;
        //  28     99     100    102    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0008:
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
