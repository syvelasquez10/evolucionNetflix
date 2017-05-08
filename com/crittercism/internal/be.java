// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build$VERSION;
import java.util.Date;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import android.os.Process;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.Collection;
import java.text.ParseException;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import com.crittercism.app.Transaction;

public final class be extends Transaction implements cf
{
    private static ExecutorService c;
    private static ScheduledExecutorService d;
    private static List o;
    private static volatile long p;
    private static volatile long q;
    private static volatile boolean r;
    private static final int[] s;
    private static be t;
    private static bf u;
    be$a b;
    private String e;
    private long f;
    private int g;
    private long h;
    private long i;
    private long j;
    private Map k;
    private String l;
    private long m;
    private ScheduledFuture n;
    
    static {
        be.c = Executors.newSingleThreadExecutor(new dy());
        be.d = Executors.newScheduledThreadPool(1, new dy());
        be.o = new LinkedList();
        be.p = 0L;
        be.q = 0L;
        be.r = false;
        s = new int[] { 32, 544, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 8224 };
        be.t = null;
        be.u = new bf();
    }
    
    public be(final ax a, final String e) {
        int optInt = -1;
        this.f = -1L;
        this.g = -1;
        this.n = null;
        if (e.length() > 255) {
            dw.b("Transaction name exceeds 255 characters! Truncating to first 255 characters.");
            this.e = e.substring(0, 255);
        }
        else {
            this.e = e;
        }
        this.b = be$a.a;
        this.k = new HashMap();
        this.a = a;
        this.l = ce.a.a();
        this.f = -1L;
        final JSONObject optJSONObject = be.u.d.optJSONObject(e);
        if (optJSONObject != null) {
            optInt = optJSONObject.optInt("value", -1);
        }
        this.g = optInt;
    }
    
    private be(final be be) {
        this.f = -1L;
        this.g = -1;
        this.n = null;
        this.e = be.e;
        this.f = be.f;
        this.g = be.g;
        this.h = be.h;
        this.i = be.i;
        this.b = be.b;
        this.k = be.k;
        this.l = be.l;
        this.j = be.j;
        this.m = be.m;
    }
    
    public be(final JSONArray jsonArray) {
        this.f = -1L;
        this.g = -1;
        this.n = null;
        this.e = jsonArray.getString(0);
        this.b = be$a.values()[jsonArray.getInt(1)];
        this.f = (int)(jsonArray.getDouble(2) * 1000.0);
        this.g = jsonArray.optInt(3, -1);
        this.k = new HashMap();
        final JSONObject jsonObject = jsonArray.getJSONObject(4);
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            this.k.put(s, jsonObject.getString(s));
        }
        this.h = eb.a.a(jsonArray.getString(5));
        this.i = eb.a.a(jsonArray.getString(6));
        this.j = (long)(jsonArray.optDouble(7, 0.0) * Math.pow(10.0, 9.0));
        this.l = ce.a.a();
    }
    
    public static List a(final ax p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.nanoTime:()J
        //     3: lstore_3       
        //     4: invokestatic    java/lang/System.currentTimeMillis:()J
        //     7: lstore          5
        //     9: new             Ljava/util/LinkedList;
        //    12: dup            
        //    13: invokespecial   java/util/LinkedList.<init>:()V
        //    16: astore          7
        //    18: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //    21: astore          8
        //    23: aload           8
        //    25: monitorenter   
        //    26: aload           7
        //    28: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //    31: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //    36: pop            
        //    37: aload           8
        //    39: monitorexit    
        //    40: aload           7
        //    42: invokeinterface java/util/List.size:()I
        //    47: iconst_1       
        //    48: isub           
        //    49: istore_2       
        //    50: iload_2        
        //    51: iflt            165
        //    54: aload           7
        //    56: iload_2        
        //    57: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    62: checkcast       Lcom/crittercism/internal/be;
        //    65: astore          8
        //    67: aload           8
        //    69: monitorenter   
        //    70: aload           8
        //    72: getfield        com/crittercism/internal/be.b:Lcom/crittercism/internal/be$a;
        //    75: getstatic       com/crittercism/internal/be$a.b:Lcom/crittercism/internal/be$a;
        //    78: if_acmpne       147
        //    81: aload           8
        //    83: lload           5
        //    85: putfield        com/crittercism/internal/be.i:J
        //    88: aload           8
        //    90: getstatic       com/crittercism/internal/be$a.g:Lcom/crittercism/internal/be$a;
        //    93: putfield        com/crittercism/internal/be.b:Lcom/crittercism/internal/be$a;
        //    96: invokestatic    com/crittercism/internal/be.m:()Z
        //    99: ifeq            126
        //   102: aload           8
        //   104: lload_3        
        //   105: getstatic       com/crittercism/internal/be.p:J
        //   108: aload           8
        //   110: getfield        com/crittercism/internal/be.m:J
        //   113: invokestatic    java/lang/Math.max:(JJ)J
        //   116: lsub           
        //   117: aload           8
        //   119: getfield        com/crittercism/internal/be.j:J
        //   122: ladd           
        //   123: putfield        com/crittercism/internal/be.j:J
        //   126: aload           8
        //   128: invokespecial   com/crittercism/internal/be.p:()V
        //   131: aload           8
        //   133: monitorexit    
        //   134: iload_2        
        //   135: iconst_1       
        //   136: isub           
        //   137: istore_2       
        //   138: goto            50
        //   141: astore_0       
        //   142: aload           8
        //   144: monitorexit    
        //   145: aload_0        
        //   146: athrow         
        //   147: aload           7
        //   149: iload_2        
        //   150: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //   155: pop            
        //   156: goto            126
        //   159: astore_0       
        //   160: aload           8
        //   162: monitorexit    
        //   163: aload_0        
        //   164: athrow         
        //   165: new             Ljava/util/concurrent/FutureTask;
        //   168: dup            
        //   169: new             Lcom/crittercism/internal/be$2;
        //   172: dup            
        //   173: aload_0        
        //   174: invokespecial   com/crittercism/internal/be$2.<init>:(Lcom/crittercism/internal/ax;)V
        //   177: aconst_null    
        //   178: invokespecial   java/util/concurrent/FutureTask.<init>:(Ljava/lang/Runnable;Ljava/lang/Object;)V
        //   181: astore          9
        //   183: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //   186: astore          8
        //   188: aload           8
        //   190: monitorenter   
        //   191: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //   194: aload           9
        //   196: invokeinterface java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
        //   201: iload_1        
        //   202: ifne            225
        //   205: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //   208: invokeinterface java/util/concurrent/ExecutorService.shutdown:()V
        //   213: aload           8
        //   215: monitorexit    
        //   216: aload           9
        //   218: invokevirtual   java/util/concurrent/FutureTask.get:()Ljava/lang/Object;
        //   221: pop            
        //   222: aload           7
        //   224: areturn        
        //   225: aload_0        
        //   226: getfield        com/crittercism/internal/ax.z:Ljava/util/Map;
        //   229: invokeinterface java/util/Map.clear:()V
        //   234: goto            213
        //   237: astore_0       
        //   238: aload           8
        //   240: monitorexit    
        //   241: aload_0        
        //   242: athrow         
        //   243: astore_0       
        //   244: aload_0        
        //   245: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   248: aload           7
        //   250: areturn        
        //   251: astore_0       
        //   252: aload_0        
        //   253: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   256: aload           7
        //   258: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  26     40     141    147    Any
        //  70     126    159    165    Any
        //  126    134    159    165    Any
        //  142    145    141    147    Any
        //  147    156    159    165    Any
        //  160    163    159    165    Any
        //  191    201    237    243    Any
        //  205    213    237    243    Any
        //  213    216    237    243    Any
        //  216    222    243    251    Ljava/lang/InterruptedException;
        //  216    222    251    259    Ljava/util/concurrent/ExecutionException;
        //  225    234    237    243    Any
        //  238    241    237    243    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    private void a(final long n) {
        synchronized (this) {
            this.a(be$a.c, n);
        }
    }
    
    public static void a(final au au) {
        bq x = null;
        try {
            x = au.x();
            final List c = x.c();
            final long currentTimeMillis = System.currentTimeMillis();
            final Iterator<bo> iterator = c.iterator();
            while (iterator.hasNext()) {
                final JSONArray jsonArray = (JSONArray)((bx)iterator.next()).a();
                if (jsonArray != null) {
                    try {
                        final be be = new be(jsonArray);
                        be.i = currentTimeMillis;
                        be.b = be$a.h;
                        au.y().a(be);
                    }
                    catch (JSONException ex) {
                        dw.b((Throwable)ex);
                    }
                    catch (ParseException ex2) {
                        dw.b(ex2);
                    }
                }
            }
        }
        catch (ThreadDeath threadDeath) {}
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        x.a();
    }
    
    public static void a(ax c) {
        LinkedList<be> list;
        while (true) {
            be.q = System.nanoTime();
            list = new LinkedList<be>();
            Object o = be.o;
            while (true) {
                Label_0128: {
                    synchronized (o) {
                        list.addAll((Collection<?>)be.o);
                        // monitorexit(o)
                        final Iterator<Object> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            o = iterator.next();
                            synchronized (o) {
                                if (((be)o).b == be$a.b) {
                                    if (((be)o).m < be.p) {
                                        break Label_0128;
                                    }
                                    if (((be)o).m <= be.q) {
                                        ((be)o).j += be.q - ((be)o).m;
                                    }
                                }
                                ((be)o).p();
                            }
                        }
                        break;
                    }
                }
                ((be)o).j += be.q - be.p;
                continue;
            }
        }
        final ax ax;
        final FutureTask futureTask = new FutureTask(new be$1(list, ax), null);
        c = (ax)be.c;
        synchronized (c) {
            be.c.execute(futureTask);
            // monitorexit(c)
            final FutureTask futureTask2 = futureTask;
            futureTask2.get();
            return;
        }
        try {
            final FutureTask futureTask2 = futureTask;
            futureTask2.get();
        }
        catch (InterruptedException ex) {
            dw.b(ex);
        }
        catch (ExecutionException ex2) {
            dw.b(ex2);
        }
    }
    
    private void a(final be$a be$a, final long n) {
        if (be$a != be$a.c && be$a != be$a.e) {
            final be$a h = be$a.h;
        }
        if (this.b == be$a.b) {
            this.p();
            this.b(be$a, n);
        }
        else if (this.b != be$a.f) {
            dw.b("Transaction " + this.e + " is not running. Either it has not been started or it has been stopped.");
            dw.a(new IllegalStateException("Transaction is not running"));
        }
    }
    
    public static void a(final bf u) {
        be.u = u;
    }
    
    public static void b(final ax p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/crittercism/internal/be;
        //     3: dup            
        //     4: aload_0        
        //     5: ldc_w           "App Load"
        //     8: invokespecial   com/crittercism/internal/be.<init>:(Lcom/crittercism/internal/ax;Ljava/lang/String;)V
        //    11: astore          5
        //    13: aload           5
        //    15: putstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    18: aload           5
        //    20: monitorenter   
        //    21: invokestatic    com/crittercism/internal/be.n:()J
        //    24: lstore_1       
        //    25: lload_1        
        //    26: ldc2_w          -1
        //    29: lcmp           
        //    30: ifeq            195
        //    33: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    36: getstatic       com/crittercism/internal/be$a.b:Lcom/crittercism/internal/be$a;
        //    39: putfield        com/crittercism/internal/be.b:Lcom/crittercism/internal/be$a;
        //    42: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    45: astore          6
        //    47: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    50: lstore_3       
        //    51: aload           6
        //    53: invokestatic    java/lang/System.currentTimeMillis:()J
        //    56: lload_3        
        //    57: lload_1        
        //    58: lsub           
        //    59: lsub           
        //    60: putfield        com/crittercism/internal/be.h:J
        //    63: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    66: astore          6
        //    68: getstatic       java/util/concurrent/TimeUnit.NANOSECONDS:Ljava/util/concurrent/TimeUnit;
        //    71: lload_1        
        //    72: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //    75: invokevirtual   java/util/concurrent/TimeUnit.convert:(JLjava/util/concurrent/TimeUnit;)J
        //    78: lstore_1       
        //    79: getstatic       java/util/concurrent/TimeUnit.NANOSECONDS:Ljava/util/concurrent/TimeUnit;
        //    82: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    85: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //    88: invokevirtual   java/util/concurrent/TimeUnit.convert:(JLjava/util/concurrent/TimeUnit;)J
        //    91: lstore_3       
        //    92: aload           6
        //    94: invokestatic    java/lang/System.nanoTime:()J
        //    97: lload_3        
        //    98: lload_1        
        //    99: lsub           
        //   100: lsub           
        //   101: putfield        com/crittercism/internal/be.m:J
        //   104: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //   107: getstatic       com/crittercism/internal/be.u:Lcom/crittercism/internal/bf;
        //   110: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //   113: getfield        com/crittercism/internal/be.e:Ljava/lang/String;
        //   116: invokevirtual   com/crittercism/internal/bf.a:(Ljava/lang/String;)J
        //   119: putfield        com/crittercism/internal/be.f:J
        //   122: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //   125: astore          6
        //   127: aload           6
        //   129: monitorenter   
        //   130: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //   133: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //   136: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   141: pop            
        //   142: aload           6
        //   144: monitorexit    
        //   145: new             Lcom/crittercism/internal/be$3;
        //   148: dup            
        //   149: aload_0        
        //   150: new             Lcom/crittercism/internal/be;
        //   153: dup            
        //   154: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //   157: invokespecial   com/crittercism/internal/be.<init>:(Lcom/crittercism/internal/be;)V
        //   160: invokespecial   com/crittercism/internal/be$3.<init>:(Lcom/crittercism/internal/ax;Lcom/crittercism/internal/be;)V
        //   163: astore          6
        //   165: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //   168: astore_0       
        //   169: aload_0        
        //   170: monitorenter   
        //   171: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //   174: aload           6
        //   176: invokeinterface java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
        //   181: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //   184: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //   187: getfield        com/crittercism/internal/be.f:J
        //   190: invokespecial   com/crittercism/internal/be.d:(J)V
        //   193: aload_0        
        //   194: monitorexit    
        //   195: aload           5
        //   197: monitorexit    
        //   198: return         
        //   199: astore_0       
        //   200: aload           6
        //   202: monitorexit    
        //   203: aload_0        
        //   204: athrow         
        //   205: astore_0       
        //   206: aload           5
        //   208: monitorexit    
        //   209: aload_0        
        //   210: athrow         
        //   211: astore_0       
        //   212: aload_0        
        //   213: athrow         
        //   214: astore          6
        //   216: aload_0        
        //   217: monitorexit    
        //   218: aload           6
        //   220: athrow         
        //   221: astore_0       
        //   222: aload_0        
        //   223: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/Throwable;)V
        //   226: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  0      21     211    214    Ljava/lang/ThreadDeath;
        //  0      21     221    227    Ljava/lang/Throwable;
        //  21     25     205    211    Any
        //  33     130    205    211    Any
        //  130    145    199    205    Any
        //  145    171    205    211    Any
        //  171    195    214    221    Any
        //  195    198    205    211    Any
        //  200    203    199    205    Any
        //  203    205    205    211    Any
        //  206    209    205    211    Any
        //  209    211    211    214    Ljava/lang/ThreadDeath;
        //  209    211    221    227    Ljava/lang/Throwable;
        //  216    218    214    221    Any
        //  218    221    205    211    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0195:
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
    
    private void b(be$a b, final long n) {
        this.b = b;
        dw.d("txn state: " + this.b.name());
        this.i = System.currentTimeMillis();
        if (m()) {
            this.j += n - Math.max(be.p, this.m);
        }
        b = (be$a)be.o;
        final be$6 be$6;
        synchronized (b) {
            be.o.remove(this);
            // monitorexit(b)
            be$6 = new be$6(this, new be(this));
            b = (be$a)be.c;
            // monitorenter(b)
            final ExecutorService executorService = be.c;
            final be$6 be$7 = be$6;
            executorService.execute(be$7);
            return;
        }
        try {
            final ExecutorService executorService = be.c;
            final be$6 be$7 = be$6;
            executorService.execute(be$7);
        }
        finally {
        }
        // monitorexit(b)
    }
    
    private void c(final long n) {
        synchronized (this) {
            this.a(be$a.h, n);
        }
    }
    
    private void d(final long n) {
        if (m()) {
            this.n = be.d.schedule(new be$5(this), n, TimeUnit.MILLISECONDS);
        }
    }
    
    public static void f() {
        be.r = true;
    }
    
    public static void g() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.nanoTime:()J
        //     3: putstatic       com/crittercism/internal/be.p:J
        //     6: new             Ljava/util/LinkedList;
        //     9: dup            
        //    10: invokespecial   java/util/LinkedList.<init>:()V
        //    13: astore_0       
        //    14: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //    17: astore_1       
        //    18: aload_1        
        //    19: monitorenter   
        //    20: aload_0        
        //    21: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //    24: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //    29: pop            
        //    30: aload_1        
        //    31: monitorexit    
        //    32: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    35: ifnull          77
        //    38: getstatic       com/crittercism/internal/be.q:J
        //    41: lconst_0       
        //    42: lcmp           
        //    43: ifne            77
        //    46: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    49: astore_1       
        //    50: aload_1        
        //    51: monitorenter   
        //    52: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    55: astore_2       
        //    56: aload_2        
        //    57: aload_2        
        //    58: getfield        com/crittercism/internal/be.j:J
        //    61: getstatic       com/crittercism/internal/be.p:J
        //    64: getstatic       com/crittercism/internal/be.t:Lcom/crittercism/internal/be;
        //    67: getfield        com/crittercism/internal/be.m:J
        //    70: lsub           
        //    71: ladd           
        //    72: putfield        com/crittercism/internal/be.j:J
        //    75: aload_1        
        //    76: monitorexit    
        //    77: aload_0        
        //    78: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    83: astore_1       
        //    84: aload_1        
        //    85: invokeinterface java/util/Iterator.hasNext:()Z
        //    90: ifeq            194
        //    93: aload_1        
        //    94: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    99: checkcast       Lcom/crittercism/internal/be;
        //   102: astore_0       
        //   103: aload_0        
        //   104: monitorenter   
        //   105: aload_0        
        //   106: getfield        com/crittercism/internal/be.b:Lcom/crittercism/internal/be$a;
        //   109: getstatic       com/crittercism/internal/be$a.b:Lcom/crittercism/internal/be$a;
        //   112: if_acmpne       156
        //   115: aload_0        
        //   116: getfield        com/crittercism/internal/be.n:Ljava/util/concurrent/ScheduledFuture;
        //   119: ifnull          176
        //   122: aload_0        
        //   123: getfield        com/crittercism/internal/be.n:Ljava/util/concurrent/ScheduledFuture;
        //   126: invokeinterface java/util/concurrent/ScheduledFuture.isCancelled:()Z
        //   131: ifeq            176
        //   134: aload_0        
        //   135: aload_0        
        //   136: getfield        com/crittercism/internal/be.f:J
        //   139: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   142: aload_0        
        //   143: getfield        com/crittercism/internal/be.j:J
        //   146: getstatic       java/util/concurrent/TimeUnit.NANOSECONDS:Ljava/util/concurrent/TimeUnit;
        //   149: invokevirtual   java/util/concurrent/TimeUnit.convert:(JLjava/util/concurrent/TimeUnit;)J
        //   152: lsub           
        //   153: invokespecial   com/crittercism/internal/be.d:(J)V
        //   156: aload_0        
        //   157: monitorexit    
        //   158: goto            84
        //   161: astore_1       
        //   162: aload_0        
        //   163: monitorexit    
        //   164: aload_1        
        //   165: athrow         
        //   166: astore_0       
        //   167: aload_1        
        //   168: monitorexit    
        //   169: aload_0        
        //   170: athrow         
        //   171: astore_0       
        //   172: aload_1        
        //   173: monitorexit    
        //   174: aload_0        
        //   175: athrow         
        //   176: aload_0        
        //   177: getfield        com/crittercism/internal/be.n:Ljava/util/concurrent/ScheduledFuture;
        //   180: ifnonnull       156
        //   183: aload_0        
        //   184: aload_0        
        //   185: getfield        com/crittercism/internal/be.f:J
        //   188: invokespecial   com/crittercism/internal/be.d:(J)V
        //   191: goto            156
        //   194: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  20     32     166    171    Any
        //  52     77     171    176    Any
        //  105    156    161    166    Any
        //  156    158    161    166    Any
        //  162    164    161    166    Any
        //  167    169    166    171    Any
        //  172    174    171    176    Any
        //  176    191    161    166    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    public static void h() {
        try {
            if (be.t != null) {
                be.t.b();
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void i() {
        final LinkedList<be> list = new LinkedList<be>();
        Object o = be.o;
        synchronized (o) {
            list.addAll((Collection<?>)be.o);
            // monitorexit(o)
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                o = iterator.next();
                synchronized (o) {
                    if (((be)o).b == be$a.b) {
                        ((be)o).f = be.u.a(((be)o).e);
                        ((be)o).p();
                        ((be)o).d(((be)o).f);
                    }
                }
            }
        }
    }
    
    private static boolean m() {
        return be.p > be.q;
    }
    
    private static long n() {
        final long[] array = { 0L };
        final String string = "/proc/" + Process.myPid() + "/stat";
        try {
            if (!(boolean)Process.class.getDeclaredMethod("readProcFile", String.class, int[].class, String[].class, long[].class, float[].class).invoke(null, string, be.s, null, array, null)) {
                return -1L;
            }
        }
        catch (NoSuchMethodException ex) {
            dw.b(ex);
            return -1L;
        }
        catch (IllegalArgumentException ex2) {
            dw.b(ex2);
            return -1L;
        }
        catch (IllegalAccessException ex3) {
            dw.b(ex3);
            return -1L;
        }
        catch (InvocationTargetException ex4) {
            dw.b(ex4);
            return -1L;
        }
        return array[0] * 10L;
    }
    
    private void o() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/crittercism/internal/be.b:Lcom/crittercism/internal/be$a;
        //     6: getstatic       com/crittercism/internal/be$a.a:Lcom/crittercism/internal/be$a;
        //     9: if_acmpne       125
        //    12: aload_0        
        //    13: getstatic       com/crittercism/internal/be$a.b:Lcom/crittercism/internal/be$a;
        //    16: putfield        com/crittercism/internal/be.b:Lcom/crittercism/internal/be$a;
        //    19: aload_0        
        //    20: invokestatic    java/lang/System.currentTimeMillis:()J
        //    23: putfield        com/crittercism/internal/be.h:J
        //    26: aload_0        
        //    27: invokestatic    java/lang/System.nanoTime:()J
        //    30: putfield        com/crittercism/internal/be.m:J
        //    33: aload_0        
        //    34: getstatic       com/crittercism/internal/be.u:Lcom/crittercism/internal/bf;
        //    37: aload_0        
        //    38: getfield        com/crittercism/internal/be.e:Ljava/lang/String;
        //    41: invokevirtual   com/crittercism/internal/bf.a:(Ljava/lang/String;)J
        //    44: putfield        com/crittercism/internal/be.f:J
        //    47: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //    50: astore_1       
        //    51: aload_1        
        //    52: monitorenter   
        //    53: getstatic       com/crittercism/internal/be.o:Ljava/util/List;
        //    56: aload_0        
        //    57: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    62: pop            
        //    63: aload_1        
        //    64: monitorexit    
        //    65: new             Lcom/crittercism/internal/be$4;
        //    68: dup            
        //    69: aload_0        
        //    70: new             Lcom/crittercism/internal/be;
        //    73: dup            
        //    74: aload_0        
        //    75: invokespecial   com/crittercism/internal/be.<init>:(Lcom/crittercism/internal/be;)V
        //    78: invokespecial   com/crittercism/internal/be$4.<init>:(Lcom/crittercism/internal/be;Lcom/crittercism/internal/be;)V
        //    81: astore_2       
        //    82: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //    85: astore_1       
        //    86: aload_1        
        //    87: monitorenter   
        //    88: getstatic       com/crittercism/internal/be.c:Ljava/util/concurrent/ExecutorService;
        //    91: aload_2        
        //    92: invokeinterface java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
        //    97: aload_0        
        //    98: aload_0        
        //    99: getfield        com/crittercism/internal/be.f:J
        //   102: invokespecial   com/crittercism/internal/be.d:(J)V
        //   105: aload_1        
        //   106: monitorexit    
        //   107: aload_0        
        //   108: monitorexit    
        //   109: return         
        //   110: astore_2       
        //   111: aload_1        
        //   112: monitorexit    
        //   113: aload_2        
        //   114: athrow         
        //   115: astore_1       
        //   116: aload_0        
        //   117: monitorexit    
        //   118: aload_1        
        //   119: athrow         
        //   120: astore_2       
        //   121: aload_1        
        //   122: monitorexit    
        //   123: aload_2        
        //   124: athrow         
        //   125: new             Ljava/lang/StringBuilder;
        //   128: dup            
        //   129: ldc_w           "Transaction "
        //   132: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   135: aload_0        
        //   136: getfield        com/crittercism/internal/be.e:Ljava/lang/String;
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: ldc_w           " has already been started."
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   151: new             Ljava/lang/IllegalStateException;
        //   154: dup            
        //   155: ldc_w           "Transaction has already started"
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: invokestatic    com/crittercism/internal/dw.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   164: goto            107
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      53     115    120    Any
        //  53     65     110    115    Any
        //  65     88     115    120    Any
        //  88     107    120    125    Any
        //  111    113    110    115    Any
        //  113    115    115    120    Any
        //  121    123    120    125    Any
        //  123    125    115    120    Any
        //  125    164    115    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0107:
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
    
    private void p() {
        synchronized (this) {
            if (this.n != null) {
                this.n.cancel(false);
            }
        }
    }
    
    private void q() {
        synchronized (this) {
            final long nanoTime = System.nanoTime();
            if (this.b == be$a.b) {
                this.b(be$a.f, nanoTime);
            }
        }
    }
    
    @Override
    public final void a() {
        try {
            this.o();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        JSONArray j = null;
        while (true) {
            try {
                j = this.j();
                if (j != null) {
                    outputStream.write(j.toString().getBytes());
                }
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public final void b() {
        try {
            this.a(System.nanoTime());
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public final void d() {
        try {
            this.c(System.nanoTime());
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @Override
    public final String e() {
        return this.l;
    }
    
    public final JSONArray j() {
        final JSONArray put = new JSONArray().put((Object)this.e).put(this.b.ordinal()).put(this.f / 1000.0);
        Object o;
        if (this.g == -1) {
            o = JSONObject.NULL;
        }
        else {
            o = this.g;
        }
        final JSONArray put2 = put.put(o).put((Object)new JSONObject(this.k)).put((Object)eb.a.a(new Date(this.h))).put((Object)eb.a.a(new Date(this.i)));
        if (Build$VERSION.SDK_INT >= 14) {
            put2.put(Math.round(this.j / Math.pow(10.0, 9.0) * 1000.0) / 1000.0);
            return put2;
        }
        put2.put(JSONObject.NULL);
        return put2;
    }
}
