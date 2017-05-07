// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import android.text.TextUtils;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import android.content.Context;

class s extends Thread implements f
{
    private static s yX;
    private volatile boolean mClosed;
    private final Context mContext;
    private final LinkedBlockingQueue<Runnable> yT;
    private volatile boolean yU;
    private volatile List<hb> yV;
    private volatile String yW;
    private volatile af yY;
    
    private s(final Context mContext) {
        super("GAThread");
        this.yT = new LinkedBlockingQueue<Runnable>();
        this.yU = false;
        this.mClosed = false;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        this.start();
    }
    
    static s B(final Context context) {
        if (s.yX == null) {
            s.yX = new s(context);
        }
        return s.yX;
    }
    
    static String C(final Context context) {
        try {
            final FileInputStream openFileInput = context.openFileInput("gaInstallData");
            final int read = openFileInput.read(new byte[8192], 0, 8192);
            if (openFileInput.available() > 0) {
                z.T("Too much campaign data, ignoring it.");
                openFileInput.close();
                context.deleteFile("gaInstallData");
                return null;
            }
            openFileInput.close();
            context.deleteFile("gaInstallData");
            if (read <= 0) {
                z.W("Campaign file is empty.");
                return null;
            }
            goto Label_0078;
        }
        catch (FileNotFoundException ex) {
            z.U("No campaign data found.");
            return null;
        }
        catch (IOException ex2) {
            z.T("Error reading campaign data.");
            context.deleteFile("gaInstallData");
            return null;
        }
    }
    
    static int ah(final String s) {
        int n = 1;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final int length = s.length();
            int n2 = 0;
            int n3 = length - 1;
            while (true) {
                n = n2;
                if (n3 < 0) {
                    break;
                }
                final char char1 = s.charAt(n3);
                final int n4 = (n2 << 6 & 0xFFFFFFF) + char1 + (char1 << 14);
                final int n5 = 0xFE00000 & n4;
                n2 = n4;
                if (n5 != 0) {
                    n2 = (n4 ^ n5 >> 21);
                }
                --n3;
            }
        }
        return n;
    }
    
    private String g(final Throwable t) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        t.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    private String v(final Map<String, String> map) {
        if (!map.containsKey("useSecure")) {
            return "https:";
        }
        if (aj.e(map.get("useSecure"), true)) {
            return "https:";
        }
        return "http:";
    }
    
    private boolean w(final Map<String, String> map) {
        if (map.get("&sf") == null) {
            return false;
        }
        final double a = aj.a(map.get("&sf"), 100.0);
        if (a >= 100.0) {
            return false;
        }
        if (ah(map.get("&cid")) % 10000 >= a * 100.0) {
            String s;
            if (map.get("&t") == null) {
                s = "unknown";
            }
            else {
                s = map.get("&t");
            }
            z.V(String.format("%s hit sampled out", s));
            return true;
        }
        return false;
    }
    
    private void x(final Map<String, String> map) {
        final l w = a.w(this.mContext);
        aj.a(map, "&adid", w);
        aj.a(map, "&ate", w);
    }
    
    private void y(final Map<String, String> map) {
        final g dq = g.dQ();
        aj.a(map, "&an", dq);
        aj.a(map, "&av", dq);
        aj.a(map, "&aid", dq);
        aj.a(map, "&aiid", dq);
        map.put("&v", "1");
    }
    
    void b(final Runnable runnable) {
        this.yT.add(runnable);
    }
    
    @Override
    public void dI() {
        this.b(new s$3(this));
    }
    
    @Override
    public void dO() {
        this.b(new s$4(this));
    }
    
    @Override
    public LinkedBlockingQueue<Runnable> dP() {
        return this.yT;
    }
    
    @Override
    public void dispatch() {
        this.b(new s$2(this));
    }
    
    @Override
    public Thread getThread() {
        return this;
    }
    
    protected void init() {
        this.yY.eh();
        (this.yV = new ArrayList<hb>()).add(new hb("appendVersion", "&_v".substring(1), "ma4.0.3"));
        this.yV.add(new hb("appendQueueTime", "&qt".substring(1), null));
        this.yV.add(new hb("appendCacheBuster", "&z".substring(1), null));
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: bipush          10
        //     2: invokestatic    android/os/Process.setThreadPriority:(I)V
        //     5: ldc2_w          5000
        //     8: invokestatic    java/lang/Thread.sleep:(J)V
        //    11: aload_0        
        //    12: getfield        com/google/android/gms/analytics/s.yY:Lcom/google/android/gms/analytics/af;
        //    15: ifnonnull       34
        //    18: aload_0        
        //    19: new             Lcom/google/android/gms/analytics/r;
        //    22: dup            
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/analytics/s.mContext:Landroid/content/Context;
        //    27: aload_0        
        //    28: invokespecial   com/google/android/gms/analytics/r.<init>:(Landroid/content/Context;Lcom/google/android/gms/analytics/f;)V
        //    31: putfield        com/google/android/gms/analytics/s.yY:Lcom/google/android/gms/analytics/af;
        //    34: aload_0        
        //    35: invokevirtual   com/google/android/gms/analytics/s.init:()V
        //    38: aload_0        
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/analytics/s.mContext:Landroid/content/Context;
        //    43: invokestatic    com/google/android/gms/analytics/s.C:(Landroid/content/Context;)Ljava/lang/String;
        //    46: putfield        com/google/android/gms/analytics/s.yW:Ljava/lang/String;
        //    49: ldc_w           "Initialized GA Thread"
        //    52: invokestatic    com/google/android/gms/analytics/z.V:(Ljava/lang/String;)V
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/analytics/s.mClosed:Z
        //    59: ifne            194
        //    62: aload_0        
        //    63: getfield        com/google/android/gms/analytics/s.yT:Ljava/util/concurrent/LinkedBlockingQueue;
        //    66: invokevirtual   java/util/concurrent/LinkedBlockingQueue.take:()Ljava/lang/Object;
        //    69: checkcast       Ljava/lang/Runnable;
        //    72: astore_1       
        //    73: aload_0        
        //    74: getfield        com/google/android/gms/analytics/s.yU:Z
        //    77: ifne            55
        //    80: aload_1        
        //    81: invokeinterface java/lang/Runnable.run:()V
        //    86: goto            55
        //    89: astore_1       
        //    90: aload_1        
        //    91: invokevirtual   java/lang/InterruptedException.toString:()Ljava/lang/String;
        //    94: invokestatic    com/google/android/gms/analytics/z.U:(Ljava/lang/String;)V
        //    97: goto            55
        //   100: astore_1       
        //   101: new             Ljava/lang/StringBuilder;
        //   104: dup            
        //   105: invokespecial   java/lang/StringBuilder.<init>:()V
        //   108: ldc_w           "Error on GAThread: "
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: aload_0        
        //   115: aload_1        
        //   116: invokespecial   com/google/android/gms/analytics/s.g:(Ljava/lang/Throwable;)Ljava/lang/String;
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   125: invokestatic    com/google/android/gms/analytics/z.T:(Ljava/lang/String;)V
        //   128: ldc_w           "Google Analytics is shutting down."
        //   131: invokestatic    com/google/android/gms/analytics/z.T:(Ljava/lang/String;)V
        //   134: aload_0        
        //   135: iconst_1       
        //   136: putfield        com/google/android/gms/analytics/s.yU:Z
        //   139: goto            55
        //   142: astore_1       
        //   143: ldc_w           "sleep interrupted in GAThread initialize"
        //   146: invokestatic    com/google/android/gms/analytics/z.W:(Ljava/lang/String;)V
        //   149: goto            11
        //   152: astore_1       
        //   153: new             Ljava/lang/StringBuilder;
        //   156: dup            
        //   157: invokespecial   java/lang/StringBuilder.<init>:()V
        //   160: ldc_w           "Error initializing the GAThread: "
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: aload_0        
        //   167: aload_1        
        //   168: invokespecial   com/google/android/gms/analytics/s.g:(Ljava/lang/Throwable;)Ljava/lang/String;
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   177: invokestatic    com/google/android/gms/analytics/z.T:(Ljava/lang/String;)V
        //   180: ldc_w           "Google Analytics will not start up."
        //   183: invokestatic    com/google/android/gms/analytics/z.T:(Ljava/lang/String;)V
        //   186: aload_0        
        //   187: iconst_1       
        //   188: putfield        com/google/android/gms/analytics/s.yU:Z
        //   191: goto            55
        //   194: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  5      11     142    152    Ljava/lang/InterruptedException;
        //  11     34     152    194    Ljava/lang/Throwable;
        //  34     55     152    194    Ljava/lang/Throwable;
        //  62     86     89     100    Ljava/lang/InterruptedException;
        //  62     86     100    142    Ljava/lang/Throwable;
        //  90     97     100    142    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0011:
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
    public void u(final Map<String, String> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(map);
        String s2;
        final String s = s2 = map.get("&ht");
        while (true) {
            if (s == null) {
                break Label_0035;
            }
            try {
                Long.valueOf(s);
                s2 = s;
                if (s2 == null) {
                    hashMap.put("&ht", Long.toString(System.currentTimeMillis()));
                }
                this.b(new s$1(this, hashMap));
            }
            catch (NumberFormatException ex) {
                s2 = null;
                continue;
            }
            break;
        }
    }
}
