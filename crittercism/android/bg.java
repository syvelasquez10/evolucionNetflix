// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build$VERSION;
import java.util.Date;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import android.os.Process;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.Collection;
import java.text.ParseException;
import org.json.JSONException;
import java.util.concurrent.TimeUnit;
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

public final class bg extends Transaction implements ch
{
    private static ExecutorService b;
    private static ScheduledExecutorService c;
    private static List o;
    private static volatile long p;
    private static volatile long q;
    private static final int[] r;
    private static bg s;
    private static bh t;
    private String d;
    private long e;
    private int f;
    private long g;
    private long h;
    private long i;
    private bg$a j;
    private Map k;
    private String l;
    private long m;
    private ScheduledFuture n;
    
    static {
        bg.b = Executors.newSingleThreadExecutor(new ea());
        bg.c = Executors.newScheduledThreadPool(1, new ea());
        bg.o = new LinkedList();
        bg.p = 0L;
        bg.q = 0L;
        r = new int[] { 32, 544, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 8224 };
        bg.s = null;
        bg.t = new bh();
    }
    
    public bg(final az a, final String d) {
        int optInt = -1;
        this.e = -1L;
        this.f = -1;
        this.n = null;
        if (d.length() > 255) {
            dy.c("Transaction name exceeds 255 characters! Truncating to first 255 characters.");
            this.d = d.substring(0, 255);
        }
        else {
            this.d = d;
        }
        this.j = bg$a.a;
        this.k = new HashMap();
        this.a = a;
        this.l = cg.a.a();
        this.e = -1L;
        final JSONObject optJSONObject = bg.t.d.optJSONObject(d);
        if (optJSONObject != null) {
            optInt = optJSONObject.optInt("value", -1);
        }
        this.f = optInt;
    }
    
    private bg(final bg bg) {
        this.e = -1L;
        this.f = -1;
        this.n = null;
        this.d = bg.d;
        this.e = bg.e;
        this.f = bg.f;
        this.g = bg.g;
        this.h = bg.h;
        this.j = bg.j;
        this.k = bg.k;
        this.l = bg.l;
        this.i = bg.i;
        this.m = bg.m;
    }
    
    public bg(final JSONArray jsonArray) {
        this.e = -1L;
        this.f = -1;
        this.n = null;
        this.d = jsonArray.getString(0);
        this.j = bg$a.values()[jsonArray.getInt(1)];
        this.e = (int)(jsonArray.getDouble(2) * 1000.0);
        this.f = jsonArray.optInt(3, -1);
        this.k = new HashMap();
        final JSONObject jsonObject = jsonArray.getJSONObject(4);
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            this.k.put(s, jsonObject.getString(s));
        }
        this.g = ee.a.a(jsonArray.getString(5));
        this.h = ee.a.a(jsonArray.getString(6));
        this.i = (long)(jsonArray.optDouble(7, 0.0) * Math.pow(10.0, 9.0));
        this.l = cg.a.a();
    }
    
    public static List a(final az p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/LinkedList;
        //     3: dup            
        //     4: invokespecial   java/util/LinkedList.<init>:()V
        //     7: astore          7
        //     9: getstatic       crittercism/android/bg.o:Ljava/util/List;
        //    12: astore          8
        //    14: aload           8
        //    16: monitorenter   
        //    17: aload           7
        //    19: getstatic       crittercism/android/bg.o:Ljava/util/List;
        //    22: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //    27: pop            
        //    28: aload           8
        //    30: monitorexit    
        //    31: invokestatic    java/lang/System.currentTimeMillis:()J
        //    34: lstore_3       
        //    35: invokestatic    java/lang/System.nanoTime:()J
        //    38: lstore          5
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
        //    62: checkcast       Lcrittercism/android/bg;
        //    65: astore          8
        //    67: aload           8
        //    69: monitorenter   
        //    70: aload           8
        //    72: getfield        crittercism/android/bg.j:Lcrittercism/android/bg$a;
        //    75: getstatic       crittercism/android/bg$a.b:Lcrittercism/android/bg$a;
        //    78: if_acmpne       147
        //    81: aload           8
        //    83: lload_3        
        //    84: putfield        crittercism/android/bg.h:J
        //    87: aload           8
        //    89: getstatic       crittercism/android/bg$a.g:Lcrittercism/android/bg$a;
        //    92: putfield        crittercism/android/bg.j:Lcrittercism/android/bg$a;
        //    95: invokestatic    crittercism/android/bg.l:()Z
        //    98: ifeq            126
        //   101: aload           8
        //   103: lload           5
        //   105: getstatic       crittercism/android/bg.p:J
        //   108: aload           8
        //   110: getfield        crittercism/android/bg.m:J
        //   113: invokestatic    java/lang/Math.max:(JJ)J
        //   116: lsub           
        //   117: aload           8
        //   119: getfield        crittercism/android/bg.i:J
        //   122: ladd           
        //   123: putfield        crittercism/android/bg.i:J
        //   126: aload           8
        //   128: invokespecial   crittercism/android/bg.r:()V
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
        //   169: new             Lcrittercism/android/bg$2;
        //   172: dup            
        //   173: aload_0        
        //   174: invokespecial   crittercism/android/bg$2.<init>:(Lcrittercism/android/az;)V
        //   177: aconst_null    
        //   178: invokespecial   java/util/concurrent/FutureTask.<init>:(Ljava/lang/Runnable;Ljava/lang/Object;)V
        //   181: astore          9
        //   183: getstatic       crittercism/android/bg.b:Ljava/util/concurrent/ExecutorService;
        //   186: astore          8
        //   188: aload           8
        //   190: monitorenter   
        //   191: getstatic       crittercism/android/bg.b:Ljava/util/concurrent/ExecutorService;
        //   194: aload           9
        //   196: invokeinterface java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
        //   201: iload_1        
        //   202: ifne            225
        //   205: getstatic       crittercism/android/bg.b:Ljava/util/concurrent/ExecutorService;
        //   208: invokeinterface java/util/concurrent/ExecutorService.shutdown:()V
        //   213: aload           8
        //   215: monitorexit    
        //   216: aload           9
        //   218: invokevirtual   java/util/concurrent/FutureTask.get:()Ljava/lang/Object;
        //   221: pop            
        //   222: aload           7
        //   224: areturn        
        //   225: aload_0        
        //   226: getfield        crittercism/android/az.z:Ljava/util/Map;
        //   229: invokeinterface java/util/Map.clear:()V
        //   234: goto            213
        //   237: astore_0       
        //   238: aload           8
        //   240: monitorexit    
        //   241: aload_0        
        //   242: athrow         
        //   243: astore_0       
        //   244: aload_0        
        //   245: invokestatic    crittercism/android/dy.a:(Ljava/lang/Throwable;)V
        //   248: aload           7
        //   250: areturn        
        //   251: astore_0       
        //   252: aload_0        
        //   253: invokestatic    crittercism/android/dy.a:(Ljava/lang/Throwable;)V
        //   256: aload           7
        //   258: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  17     31     141    147    Any
        //  70     126    159    165    Any
        //  126    134    159    165    Any
        //  147    156    159    165    Any
        //  191    201    237    243    Any
        //  205    213    237    243    Any
        //  213    216    237    243    Any
        //  216    222    243    251    Ljava/lang/InterruptedException;
        //  216    222    251    259    Ljava/util/concurrent/ExecutionException;
        //  225    234    237    243    Any
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
        if (l()) {
            this.n = bg.c.schedule(new bg$5(this), n, TimeUnit.MILLISECONDS);
        }
    }
    
    public static void a(final aw aw) {
        bs w = null;
        try {
            w = aw.w();
            final List c = w.c();
            final long currentTimeMillis = System.currentTimeMillis();
            final Iterator<bq> iterator = c.iterator();
            while (iterator.hasNext()) {
                final JSONArray jsonArray = (JSONArray)((bz)iterator.next()).a();
                if (jsonArray != null) {
                    try {
                        final bg bg = new bg(jsonArray);
                        bg.h = currentTimeMillis;
                        bg.j = bg$a.h;
                        aw.x().a(bg);
                    }
                    catch (JSONException ex) {
                        dy.a((Throwable)ex);
                    }
                    catch (ParseException ex2) {
                        dy.a(ex2);
                    }
                }
            }
        }
        catch (ThreadDeath threadDeath) {}
        catch (Throwable t) {
            dy.a(t);
            return;
        }
        w.a();
    }
    
    public static void a(az b) {
        LinkedList<bg> list;
        while (true) {
            bg.q = System.nanoTime();
            list = new LinkedList<bg>();
            Object o = bg.o;
            while (true) {
                Label_0128: {
                    synchronized (o) {
                        list.addAll((Collection<?>)bg.o);
                        // monitorexit(o)
                        final Iterator<Object> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            o = iterator.next();
                            synchronized (o) {
                                if (((bg)o).j == bg$a.b) {
                                    if (((bg)o).m < bg.p) {
                                        break Label_0128;
                                    }
                                    if (((bg)o).m <= bg.q) {
                                        ((bg)o).i += bg.q - ((bg)o).m;
                                    }
                                }
                                ((bg)o).r();
                            }
                        }
                        break;
                    }
                }
                ((bg)o).i += bg.q - bg.p;
                continue;
            }
        }
        final az az;
        final FutureTask futureTask = new FutureTask(new bg$1(list, az), null);
        b = (az)bg.b;
        synchronized (b) {
            bg.b.execute(futureTask);
            // monitorexit(b)
            final FutureTask futureTask2 = futureTask;
            futureTask2.get();
            return;
        }
        try {
            final FutureTask futureTask2 = futureTask;
            futureTask2.get();
        }
        catch (InterruptedException ex) {
            dy.a(ex);
        }
        catch (ExecutionException ex2) {
            dy.a(ex2);
        }
    }
    
    private void a(final bg$a bg$a) {
        if (bg$a != bg$a.c && bg$a != bg$a.e) {
            final bg$a i = bg$a.i;
        }
        if (this.j == bg$a.b) {
            this.r();
            this.b(bg$a);
        }
        else if (this.j != bg$a.f) {
            dy.b("Transaction " + this.d + " is not running. Either it has not been started or it has been stopped.", new IllegalStateException("Transaction is not running"));
        }
    }
    
    public static void a(final bh t) {
        bg.t = t;
    }
    
    public static void b(final az p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcrittercism/android/bg;
        //     3: dup            
        //     4: aload_0        
        //     5: ldc_w           "App Load"
        //     8: invokespecial   crittercism/android/bg.<init>:(Lcrittercism/android/az;Ljava/lang/String;)V
        //    11: astore          5
        //    13: aload           5
        //    15: putstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    18: aload           5
        //    20: monitorenter   
        //    21: invokestatic    crittercism/android/bg.m:()J
        //    24: lstore_1       
        //    25: lload_1        
        //    26: ldc2_w          -1
        //    29: lcmp           
        //    30: ifeq            195
        //    33: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    36: getstatic       crittercism/android/bg$a.b:Lcrittercism/android/bg$a;
        //    39: putfield        crittercism/android/bg.j:Lcrittercism/android/bg$a;
        //    42: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    45: astore          6
        //    47: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    50: lstore_3       
        //    51: aload           6
        //    53: invokestatic    java/lang/System.currentTimeMillis:()J
        //    56: lload_3        
        //    57: lload_1        
        //    58: lsub           
        //    59: lsub           
        //    60: putfield        crittercism/android/bg.g:J
        //    63: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
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
        //   101: putfield        crittercism/android/bg.m:J
        //   104: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //   107: getstatic       crittercism/android/bg.t:Lcrittercism/android/bh;
        //   110: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //   113: getfield        crittercism/android/bg.d:Ljava/lang/String;
        //   116: invokevirtual   crittercism/android/bh.a:(Ljava/lang/String;)J
        //   119: putfield        crittercism/android/bg.e:J
        //   122: getstatic       crittercism/android/bg.o:Ljava/util/List;
        //   125: astore          6
        //   127: aload           6
        //   129: monitorenter   
        //   130: getstatic       crittercism/android/bg.o:Ljava/util/List;
        //   133: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //   136: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   141: pop            
        //   142: aload           6
        //   144: monitorexit    
        //   145: new             Lcrittercism/android/bg$3;
        //   148: dup            
        //   149: aload_0        
        //   150: new             Lcrittercism/android/bg;
        //   153: dup            
        //   154: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //   157: invokespecial   crittercism/android/bg.<init>:(Lcrittercism/android/bg;)V
        //   160: invokespecial   crittercism/android/bg$3.<init>:(Lcrittercism/android/az;Lcrittercism/android/bg;)V
        //   163: astore          6
        //   165: getstatic       crittercism/android/bg.b:Ljava/util/concurrent/ExecutorService;
        //   168: astore_0       
        //   169: aload_0        
        //   170: monitorenter   
        //   171: getstatic       crittercism/android/bg.b:Ljava/util/concurrent/ExecutorService;
        //   174: aload           6
        //   176: invokeinterface java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
        //   181: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //   184: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //   187: getfield        crittercism/android/bg.e:J
        //   190: invokespecial   crittercism/android/bg.a:(J)V
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
        //   223: invokestatic    crittercism/android/dy.a:(Ljava/lang/Throwable;)V
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
        //  200    205    205    211    Any
        //  206    211    211    214    Ljava/lang/ThreadDeath;
        //  206    211    221    227    Ljava/lang/Throwable;
        //  216    221    205    211    Any
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
    
    private void b(bg$a j) {
        this.j = j;
        this.h = System.currentTimeMillis();
        final long nanoTime = System.nanoTime();
        if (l()) {
            this.i += nanoTime - Math.max(bg.p, this.m);
        }
        j = (bg$a)bg.o;
        final bg$6 bg$6;
        synchronized (j) {
            bg.o.remove(this);
            // monitorexit(j)
            bg$6 = new bg$6(this, new bg(this));
            j = (bg$a)bg.b;
            // monitorenter(j)
            final ExecutorService executorService = bg.b;
            final bg$6 bg$7 = bg$6;
            executorService.execute(bg$7);
            return;
        }
        try {
            final ExecutorService executorService = bg.b;
            final bg$6 bg$7 = bg$6;
            executorService.execute(bg$7);
        }
        finally {
        }
        // monitorexit(j)
    }
    
    public static void f() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.nanoTime:()J
        //     3: putstatic       crittercism/android/bg.p:J
        //     6: new             Ljava/util/LinkedList;
        //     9: dup            
        //    10: invokespecial   java/util/LinkedList.<init>:()V
        //    13: astore_0       
        //    14: getstatic       crittercism/android/bg.o:Ljava/util/List;
        //    17: astore_1       
        //    18: aload_1        
        //    19: monitorenter   
        //    20: aload_0        
        //    21: getstatic       crittercism/android/bg.o:Ljava/util/List;
        //    24: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //    29: pop            
        //    30: aload_1        
        //    31: monitorexit    
        //    32: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    35: ifnull          77
        //    38: getstatic       crittercism/android/bg.q:J
        //    41: lconst_0       
        //    42: lcmp           
        //    43: ifne            77
        //    46: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    49: astore_1       
        //    50: aload_1        
        //    51: monitorenter   
        //    52: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    55: astore_2       
        //    56: aload_2        
        //    57: aload_2        
        //    58: getfield        crittercism/android/bg.i:J
        //    61: getstatic       crittercism/android/bg.p:J
        //    64: getstatic       crittercism/android/bg.s:Lcrittercism/android/bg;
        //    67: getfield        crittercism/android/bg.m:J
        //    70: lsub           
        //    71: ladd           
        //    72: putfield        crittercism/android/bg.i:J
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
        //    99: checkcast       Lcrittercism/android/bg;
        //   102: astore_0       
        //   103: aload_0        
        //   104: monitorenter   
        //   105: aload_0        
        //   106: getfield        crittercism/android/bg.j:Lcrittercism/android/bg$a;
        //   109: getstatic       crittercism/android/bg$a.b:Lcrittercism/android/bg$a;
        //   112: if_acmpne       156
        //   115: aload_0        
        //   116: getfield        crittercism/android/bg.n:Ljava/util/concurrent/ScheduledFuture;
        //   119: ifnull          176
        //   122: aload_0        
        //   123: getfield        crittercism/android/bg.n:Ljava/util/concurrent/ScheduledFuture;
        //   126: invokeinterface java/util/concurrent/ScheduledFuture.isCancelled:()Z
        //   131: ifeq            176
        //   134: aload_0        
        //   135: aload_0        
        //   136: getfield        crittercism/android/bg.e:J
        //   139: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   142: aload_0        
        //   143: getfield        crittercism/android/bg.i:J
        //   146: getstatic       java/util/concurrent/TimeUnit.NANOSECONDS:Ljava/util/concurrent/TimeUnit;
        //   149: invokevirtual   java/util/concurrent/TimeUnit.convert:(JLjava/util/concurrent/TimeUnit;)J
        //   152: lsub           
        //   153: invokespecial   crittercism/android/bg.a:(J)V
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
        //   177: getfield        crittercism/android/bg.n:Ljava/util/concurrent/ScheduledFuture;
        //   180: ifnonnull       156
        //   183: aload_0        
        //   184: aload_0        
        //   185: getfield        crittercism/android/bg.e:J
        //   188: invokespecial   crittercism/android/bg.a:(J)V
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
    
    public static void g() {
        try {
            if (bg.s != null) {
                bg.s.b();
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    public static void i() {
        final LinkedList<bg> list = new LinkedList<bg>();
        Object o = bg.o;
        synchronized (o) {
            list.addAll((Collection<?>)bg.o);
            // monitorexit(o)
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                o = iterator.next();
                synchronized (o) {
                    if (((bg)o).j == bg$a.b) {
                        ((bg)o).e = bg.t.a(((bg)o).d);
                        ((bg)o).r();
                        ((bg)o).a(((bg)o).e);
                    }
                }
            }
        }
    }
    
    private static boolean l() {
        return bg.p > bg.q;
    }
    
    private static long m() {
        final long[] array = { 0L };
        final String string = "/proc/" + Process.myPid() + "/stat";
        try {
            if (!(boolean)Process.class.getDeclaredMethod("readProcFile", String.class, int[].class, String[].class, long[].class, float[].class).invoke(null, string, bg.r, null, array, null)) {
                return -1L;
            }
        }
        catch (NoSuchMethodException ex) {
            dy.a(ex);
            return -1L;
        }
        catch (IllegalArgumentException ex2) {
            dy.a(ex2);
            return -1L;
        }
        catch (IllegalAccessException ex3) {
            dy.a(ex3);
            return -1L;
        }
        catch (InvocationTargetException ex4) {
            dy.a(ex4);
            return -1L;
        }
        return array[0] * 10L;
    }
    
    private void o() {
        synchronized (this) {
            this.a(bg$a.c);
        }
    }
    
    private void r() {
        synchronized (this) {
            if (this.n != null) {
                this.n.cancel(false);
            }
        }
    }
    
    private void s() {
        synchronized (this) {
            if (this.j == bg$a.b) {
                this.b(bg$a.f);
            }
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
    
    public final void b() {
        try {
            this.o();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    @Override
    public final String e() {
        return this.l;
    }
    
    public final JSONArray j() {
        final JSONArray put = new JSONArray().put((Object)this.d).put(this.j.ordinal()).put(this.e / 1000.0);
        Object o;
        if (this.f == -1) {
            o = JSONObject.NULL;
        }
        else {
            o = this.f;
        }
        final JSONArray put2 = put.put(o).put((Object)new JSONObject(this.k)).put((Object)ee.a.a(new Date(this.g))).put((Object)ee.a.a(new Date(this.h)));
        if (Build$VERSION.SDK_INT >= 14) {
            put2.put(Math.round(this.i / Math.pow(10.0, 9.0) * 1000.0) / 1000.0);
            return put2;
        }
        put2.put(JSONObject.NULL);
        return put2;
    }
    
    public final bg$a k() {
        return this.j;
    }
}
