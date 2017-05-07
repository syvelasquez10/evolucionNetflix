// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import android.text.TextUtils;
import com.google.android.gms.internal.ef;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import android.content.Context;

class t extends Thread implements f
{
    private static t tA;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile String su;
    private volatile ag tB;
    private final LinkedBlockingQueue<Runnable> tw;
    private volatile boolean tx;
    private volatile List<ef> ty;
    private volatile String tz;
    
    private t(final Context mContext) {
        super("GAThread");
        this.tw = new LinkedBlockingQueue<Runnable>();
        this.tx = false;
        this.mClosed = false;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        this.start();
    }
    
    static int H(final String s) {
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
    
    private String a(final Throwable t) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        t.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    static t q(final Context context) {
        if (t.tA == null) {
            t.tA = new t(context);
        }
        return t.tA;
    }
    
    static String r(final Context context) {
        try {
            final FileInputStream openFileInput = context.openFileInput("gaInstallData");
            final int read = openFileInput.read(new byte[8192], 0, 8192);
            if (openFileInput.available() > 0) {
                aa.w("Too much campaign data, ignoring it.");
                openFileInput.close();
                context.deleteFile("gaInstallData");
                return null;
            }
            openFileInput.close();
            context.deleteFile("gaInstallData");
            if (read <= 0) {
                aa.z("Campaign file is empty.");
                return null;
            }
            goto Label_0078;
        }
        catch (FileNotFoundException ex) {
            aa.x("No campaign data found.");
            return null;
        }
        catch (IOException ex2) {
            aa.w("Error reading campaign data.");
            context.deleteFile("gaInstallData");
            return null;
        }
    }
    
    private String r(final Map<String, String> map) {
        if (!map.containsKey("useSecure")) {
            return "https:";
        }
        if (ak.d(map.get("useSecure"), true)) {
            return "https:";
        }
        return "http:";
    }
    
    private boolean s(final Map<String, String> map) {
        if (map.get("&sf") == null) {
            return false;
        }
        final double a = ak.a(map.get("&sf"), 100.0);
        if (a >= 100.0) {
            return false;
        }
        if (H(map.get("&cid")) % 10000 >= a * 100.0) {
            String s;
            if (map.get("&t") == null) {
                s = "unknown";
            }
            else {
                s = map.get("&t");
            }
            aa.y(String.format("%s hit sampled out", s));
            return true;
        }
        return false;
    }
    
    private void t(final Map<String, String> map) {
        final m m = a.m(this.mContext);
        ak.a(map, "&adid", m.getValue("&adid"));
        ak.a(map, "&ate", m.getValue("&ate"));
    }
    
    private void u(final Map<String, String> map) {
        final g ca = g.ca();
        ak.a(map, "&an", ca.getValue("&an"));
        ak.a(map, "&av", ca.getValue("&av"));
        ak.a(map, "&aid", ca.getValue("&aid"));
        ak.a(map, "&aiid", ca.getValue("&aiid"));
        map.put("&v", "1");
    }
    
    void a(final Runnable runnable) {
        this.tw.add(runnable);
    }
    
    @Override
    public void bR() {
        this.a(new Runnable() {
            @Override
            public void run() {
                t.this.tB.bR();
            }
        });
    }
    
    @Override
    public void bW() {
        this.a(new Runnable() {
            @Override
            public void run() {
                t.this.tB.bW();
            }
        });
    }
    
    @Override
    public void bY() {
        this.a(new Runnable() {
            @Override
            public void run() {
                t.this.tB.bY();
            }
        });
    }
    
    @Override
    public LinkedBlockingQueue<Runnable> bZ() {
        return this.tw;
    }
    
    @Override
    public Thread getThread() {
        return this;
    }
    
    protected void init() {
        this.tB.cp();
        (this.ty = new ArrayList<ef>()).add(new ef("appendVersion", "&_v".substring(1), "ma4.0.1"));
        this.ty.add(new ef("appendQueueTime", "&qt".substring(1), null));
        this.ty.add(new ef("appendCacheBuster", "&z".substring(1), null));
    }
    
    @Override
    public void q(final Map<String, String> map) {
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
                this.a(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty((CharSequence)hashMap.get("&cid"))) {
                            hashMap.put("&cid", t.this.su);
                        }
                        if (GoogleAnalytics.getInstance(t.this.mContext).getAppOptOut() || t.this.s(hashMap)) {
                            return;
                        }
                        if (!TextUtils.isEmpty((CharSequence)t.this.tz)) {
                            u.cy().t(true);
                            hashMap.putAll(new HitBuilders.HitBuilder<HitBuilders.HitBuilder>().setCampaignParamsFromUrl(t.this.tz).build());
                            u.cy().t(false);
                            t.this.tz = null;
                        }
                        t.this.u(hashMap);
                        t.this.t(hashMap);
                        t.this.tB.b(y.v(hashMap), Long.valueOf(hashMap.get("&ht")), t.this.r(hashMap), t.this.ty);
                    }
                });
            }
            catch (NumberFormatException ex) {
                s2 = null;
                continue;
            }
            break;
        }
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
        //    12: getfield        com/google/android/gms/analytics/t.tB:Lcom/google/android/gms/analytics/ag;
        //    15: ifnonnull       34
        //    18: aload_0        
        //    19: new             Lcom/google/android/gms/analytics/s;
        //    22: dup            
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/analytics/t.mContext:Landroid/content/Context;
        //    27: aload_0        
        //    28: invokespecial   com/google/android/gms/analytics/s.<init>:(Landroid/content/Context;Lcom/google/android/gms/analytics/f;)V
        //    31: putfield        com/google/android/gms/analytics/t.tB:Lcom/google/android/gms/analytics/ag;
        //    34: aload_0        
        //    35: invokevirtual   com/google/android/gms/analytics/t.init:()V
        //    38: aload_0        
        //    39: invokestatic    com/google/android/gms/analytics/h.cb:()Lcom/google/android/gms/analytics/h;
        //    42: ldc             "&cid"
        //    44: invokevirtual   com/google/android/gms/analytics/h.getValue:(Ljava/lang/String;)Ljava/lang/String;
        //    47: putfield        com/google/android/gms/analytics/t.su:Ljava/lang/String;
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/analytics/t.su:Ljava/lang/String;
        //    54: ifnonnull       62
        //    57: aload_0        
        //    58: iconst_1       
        //    59: putfield        com/google/android/gms/analytics/t.tx:Z
        //    62: aload_0        
        //    63: aload_0        
        //    64: getfield        com/google/android/gms/analytics/t.mContext:Landroid/content/Context;
        //    67: invokestatic    com/google/android/gms/analytics/t.r:(Landroid/content/Context;)Ljava/lang/String;
        //    70: putfield        com/google/android/gms/analytics/t.tz:Ljava/lang/String;
        //    73: ldc_w           "Initialized GA Thread"
        //    76: invokestatic    com/google/android/gms/analytics/aa.y:(Ljava/lang/String;)V
        //    79: aload_0        
        //    80: getfield        com/google/android/gms/analytics/t.mClosed:Z
        //    83: ifne            218
        //    86: aload_0        
        //    87: getfield        com/google/android/gms/analytics/t.tw:Ljava/util/concurrent/LinkedBlockingQueue;
        //    90: invokevirtual   java/util/concurrent/LinkedBlockingQueue.take:()Ljava/lang/Object;
        //    93: checkcast       Ljava/lang/Runnable;
        //    96: astore_1       
        //    97: aload_0        
        //    98: getfield        com/google/android/gms/analytics/t.tx:Z
        //   101: ifne            79
        //   104: aload_1        
        //   105: invokeinterface java/lang/Runnable.run:()V
        //   110: goto            79
        //   113: astore_1       
        //   114: aload_1        
        //   115: invokevirtual   java/lang/InterruptedException.toString:()Ljava/lang/String;
        //   118: invokestatic    com/google/android/gms/analytics/aa.x:(Ljava/lang/String;)V
        //   121: goto            79
        //   124: astore_1       
        //   125: new             Ljava/lang/StringBuilder;
        //   128: dup            
        //   129: invokespecial   java/lang/StringBuilder.<init>:()V
        //   132: ldc_w           "Error on GAThread: "
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: aload_0        
        //   139: aload_1        
        //   140: invokespecial   com/google/android/gms/analytics/t.a:(Ljava/lang/Throwable;)Ljava/lang/String;
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   149: invokestatic    com/google/android/gms/analytics/aa.w:(Ljava/lang/String;)V
        //   152: ldc_w           "Google Analytics is shutting down."
        //   155: invokestatic    com/google/android/gms/analytics/aa.w:(Ljava/lang/String;)V
        //   158: aload_0        
        //   159: iconst_1       
        //   160: putfield        com/google/android/gms/analytics/t.tx:Z
        //   163: goto            79
        //   166: astore_1       
        //   167: ldc_w           "sleep interrupted in GAThread initialize"
        //   170: invokestatic    com/google/android/gms/analytics/aa.z:(Ljava/lang/String;)V
        //   173: goto            11
        //   176: astore_1       
        //   177: new             Ljava/lang/StringBuilder;
        //   180: dup            
        //   181: invokespecial   java/lang/StringBuilder.<init>:()V
        //   184: ldc_w           "Error initializing the GAThread: "
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: aload_0        
        //   191: aload_1        
        //   192: invokespecial   com/google/android/gms/analytics/t.a:(Ljava/lang/Throwable;)Ljava/lang/String;
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   201: invokestatic    com/google/android/gms/analytics/aa.w:(Ljava/lang/String;)V
        //   204: ldc_w           "Google Analytics will not start up."
        //   207: invokestatic    com/google/android/gms/analytics/aa.w:(Ljava/lang/String;)V
        //   210: aload_0        
        //   211: iconst_1       
        //   212: putfield        com/google/android/gms/analytics/t.tx:Z
        //   215: goto            79
        //   218: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  5      11     166    176    Ljava/lang/InterruptedException;
        //  11     34     176    218    Ljava/lang/Throwable;
        //  34     62     176    218    Ljava/lang/Throwable;
        //  62     79     176    218    Ljava/lang/Throwable;
        //  86     110    113    124    Ljava/lang/InterruptedException;
        //  86     110    124    166    Ljava/lang/Throwable;
        //  114    121    124    166    Ljava/lang/Throwable;
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
}
