// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import android.text.TextUtils;
import android.os.SystemClock;
import android.content.Context;

@ez
public class fb extends gg implements ff.a
{
    private final Context mContext;
    private final Object mw;
    private cm pR;
    private final fa.a sU;
    private final Object sV;
    private final fi.a sW;
    private final k sX;
    private gg sY;
    private fk sZ;
    
    public fb(final Context mContext, final fi.a sw, final k sx, final fa.a su) {
        this.sV = new Object();
        this.mw = new Object();
        this.sU = su;
        this.mContext = mContext;
        this.sW = sw;
        this.sX = sx;
    }
    
    private ay a(final fi fi) throws a {
        if (this.sZ.tL == null) {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        final String[] split = this.sZ.tL.split("x");
        if (split.length != 2) {
            throw new a("Could not parse the ad size from the ad response: " + this.sZ.tL, 0);
        }
        while (true) {
            int int1;
            int int2;
            ay[] oh;
            int length;
            int n = 0;
            ay ay = null;
            float density;
            int width;
            int height;
            Label_0156_Outer:Label_0177_Outer:
            while (true) {
            Label_0263:
                while (true) {
                Label_0253:
                    while (true) {
                        try {
                            int1 = Integer.parseInt(split[0]);
                            int2 = Integer.parseInt(split[1]);
                            oh = fi.lH.oh;
                            length = oh.length;
                            n = 0;
                            if (n >= length) {
                                break;
                            }
                            ay = oh[n];
                            density = this.mContext.getResources().getDisplayMetrics().density;
                            if (ay.width == -1) {
                                width = (int)(ay.widthPixels / density);
                                if (ay.height != -2) {
                                    break Label_0253;
                                }
                                height = (int)(ay.heightPixels / density);
                                if (int1 == width && int2 == height) {
                                    return new ay(ay, fi.lH.oh);
                                }
                                break Label_0263;
                            }
                        }
                        catch (NumberFormatException ex) {
                            throw new a("Could not parse the ad size from the ad response: " + this.sZ.tL, 0);
                        }
                        width = ay.width;
                        continue Label_0177_Outer;
                    }
                    height = ay.height;
                    continue;
                }
                ++n;
                continue Label_0156_Outer;
            }
        }
        throw new a("The ad size from the ad response was not one of the requested sizes: " + this.sZ.tL, 0);
    }
    
    private boolean c(long n) throws a {
        n = 60000L - (SystemClock.elapsedRealtime() - n);
        if (n <= 0L) {
            return false;
        }
        try {
            this.mw.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            throw new a("Ad request cancelled.", -1);
        }
    }
    
    private void cy() throws a {
        if (this.sZ.errorCode != -3) {
            if (TextUtils.isEmpty((CharSequence)this.sZ.tG)) {
                throw new a("No fill from ad server.", 3);
            }
            gb.a(this.mContext, this.sZ.tF);
            if (this.sZ.tI) {
                try {
                    this.pR = new cm(this.sZ.tG);
                }
                catch (JSONException ex) {
                    throw new a("Could not parse mediation config: " + this.sZ.tG, 0);
                }
            }
        }
    }
    
    private void e(final long n) throws a {
        while (this.c(n)) {
            if (this.sZ != null) {
                synchronized (this.sV) {
                    this.sY = null;
                    // monitorexit(this.sV)
                    if (this.sZ.errorCode != -2 && this.sZ.errorCode != -3) {
                        throw new a("There was a problem getting an ad response. ErrorCode: " + this.sZ.errorCode, this.sZ.errorCode);
                    }
                }
                return;
            }
        }
        throw new a("Timed out waiting for ad response.", 2);
    }
    
    private void r(final boolean b) {
        gb.cV().v(b);
        final an l = gb.cV().l(this.mContext);
        if (l != null && !l.isAlive()) {
            gs.S("start fetching content...");
            l.aV();
        }
    }
    
    @Override
    public void a(final fk sz) {
        synchronized (this.mw) {
            gs.S("Received ad response.");
            this.sZ = sz;
            this.mw.notify();
        }
    }
    
    @Override
    public void cp() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/fb.mw:Ljava/lang/Object;
        //     4: astore          11
        //     6: aload           11
        //     8: monitorenter   
        //     9: ldc             "AdLoaderBackgroundTask started."
        //    11: invokestatic    com/google/android/gms/internal/gs.S:(Ljava/lang/String;)V
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/fb.sX:Lcom/google/android/gms/internal/k;
        //    18: invokevirtual   com/google/android/gms/internal/k.z:()Lcom/google/android/gms/internal/g;
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/internal/fb.mContext:Landroid/content/Context;
        //    25: invokeinterface com/google/android/gms/internal/g.a:(Landroid/content/Context;)Ljava/lang/String;
        //    30: astore          9
        //    32: new             Lcom/google/android/gms/internal/fi;
        //    35: dup            
        //    36: aload_0        
        //    37: getfield        com/google/android/gms/internal/fb.sW:Lcom/google/android/gms/internal/fi$a;
        //    40: aload           9
        //    42: invokespecial   com/google/android/gms/internal/fi.<init>:(Lcom/google/android/gms/internal/fi$a;Ljava/lang/String;)V
        //    45: astore          12
        //    47: bipush          -2
        //    49: istore_1       
        //    50: ldc2_w          -1
        //    53: lstore          4
        //    55: lload           4
        //    57: lstore_2       
        //    58: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    61: lstore          6
        //    63: lload           4
        //    65: lstore_2       
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/internal/fb.mContext:Landroid/content/Context;
        //    70: aload           12
        //    72: aload_0        
        //    73: invokestatic    com/google/android/gms/internal/ff.a:(Landroid/content/Context;Lcom/google/android/gms/internal/fi;Lcom/google/android/gms/internal/ff$a;)Lcom/google/android/gms/internal/gg;
        //    76: astore          10
        //    78: lload           4
        //    80: lstore_2       
        //    81: aload_0        
        //    82: getfield        com/google/android/gms/internal/fb.sV:Ljava/lang/Object;
        //    85: astore          9
        //    87: lload           4
        //    89: lstore_2       
        //    90: aload           9
        //    92: monitorenter   
        //    93: aload_0        
        //    94: aload           10
        //    96: putfield        com/google/android/gms/internal/fb.sY:Lcom/google/android/gms/internal/gg;
        //    99: aload_0        
        //   100: getfield        com/google/android/gms/internal/fb.sY:Lcom/google/android/gms/internal/gg;
        //   103: ifnonnull       278
        //   106: new             Lcom/google/android/gms/internal/fb$a;
        //   109: dup            
        //   110: ldc_w           "Could not start the ad request service."
        //   113: iconst_0       
        //   114: invokespecial   com/google/android/gms/internal/fb$a.<init>:(Ljava/lang/String;I)V
        //   117: athrow         
        //   118: astore          10
        //   120: aload           9
        //   122: monitorexit    
        //   123: lload           4
        //   125: lstore_2       
        //   126: aload           10
        //   128: athrow         
        //   129: astore          10
        //   131: aconst_null    
        //   132: astore          9
        //   134: aload           10
        //   136: invokevirtual   com/google/android/gms/internal/fb$a.getErrorCode:()I
        //   139: istore_1       
        //   140: iload_1        
        //   141: iconst_3       
        //   142: if_icmpeq       150
        //   145: iload_1        
        //   146: iconst_m1      
        //   147: if_icmpne       347
        //   150: aload           10
        //   152: invokevirtual   com/google/android/gms/internal/fb$a.getMessage:()Ljava/lang/String;
        //   155: invokestatic    com/google/android/gms/internal/gs.U:(Ljava/lang/String;)V
        //   158: aload_0        
        //   159: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   162: ifnonnull       366
        //   165: aload_0        
        //   166: new             Lcom/google/android/gms/internal/fk;
        //   169: dup            
        //   170: iload_1        
        //   171: invokespecial   com/google/android/gms/internal/fk.<init>:(I)V
        //   174: putfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   177: getstatic       com/google/android/gms/internal/gr.wC:Landroid/os/Handler;
        //   180: new             Lcom/google/android/gms/internal/fb$1;
        //   183: dup            
        //   184: aload_0        
        //   185: invokespecial   com/google/android/gms/internal/fb$1.<init>:(Lcom/google/android/gms/internal/fb;)V
        //   188: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   191: pop            
        //   192: aload_0        
        //   193: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   196: getfield        com/google/android/gms/internal/fk.tQ:Ljava/lang/String;
        //   199: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   202: istore          8
        //   204: iload           8
        //   206: ifne            398
        //   209: new             Lorg/json/JSONObject;
        //   212: dup            
        //   213: aload_0        
        //   214: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   217: getfield        com/google/android/gms/internal/fk.tQ:Ljava/lang/String;
        //   220: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   223: astore          10
        //   225: new             Lcom/google/android/gms/internal/fz$a;
        //   228: dup            
        //   229: aload           12
        //   231: aload_0        
        //   232: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   235: aload_0        
        //   236: getfield        com/google/android/gms/internal/fb.pR:Lcom/google/android/gms/internal/cm;
        //   239: aload           9
        //   241: iload_1        
        //   242: lload_2        
        //   243: aload_0        
        //   244: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   247: getfield        com/google/android/gms/internal/fk.tM:J
        //   250: aload           10
        //   252: invokespecial   com/google/android/gms/internal/fz$a.<init>:(Lcom/google/android/gms/internal/fi;Lcom/google/android/gms/internal/fk;Lcom/google/android/gms/internal/cm;Lcom/google/android/gms/internal/ay;IJJLorg/json/JSONObject;)V
        //   255: astore          9
        //   257: getstatic       com/google/android/gms/internal/gr.wC:Landroid/os/Handler;
        //   260: new             Lcom/google/android/gms/internal/fb$2;
        //   263: dup            
        //   264: aload_0        
        //   265: aload           9
        //   267: invokespecial   com/google/android/gms/internal/fb$2.<init>:(Lcom/google/android/gms/internal/fb;Lcom/google/android/gms/internal/fz$a;)V
        //   270: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   273: pop            
        //   274: aload           11
        //   276: monitorexit    
        //   277: return         
        //   278: aload           9
        //   280: monitorexit    
        //   281: lload           4
        //   283: lstore_2       
        //   284: aload_0        
        //   285: lload           6
        //   287: invokespecial   com/google/android/gms/internal/fb.e:(J)V
        //   290: lload           4
        //   292: lstore_2       
        //   293: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   296: lstore          4
        //   298: lload           4
        //   300: lstore_2       
        //   301: aload_0        
        //   302: invokespecial   com/google/android/gms/internal/fb.cy:()V
        //   305: lload           4
        //   307: lstore_2       
        //   308: aload           12
        //   310: getfield        com/google/android/gms/internal/fi.lH:Lcom/google/android/gms/internal/ay;
        //   313: getfield        com/google/android/gms/internal/ay.oh:[Lcom/google/android/gms/internal/ay;
        //   316: ifnull          412
        //   319: lload           4
        //   321: lstore_2       
        //   322: aload_0        
        //   323: aload           12
        //   325: invokespecial   com/google/android/gms/internal/fb.a:(Lcom/google/android/gms/internal/fi;)Lcom/google/android/gms/internal/ay;
        //   328: astore          9
        //   330: aload_0        
        //   331: aload_0        
        //   332: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   335: getfield        com/google/android/gms/internal/fk.tT:Z
        //   338: invokespecial   com/google/android/gms/internal/fb.r:(Z)V
        //   341: lload           4
        //   343: lstore_2       
        //   344: goto            192
        //   347: aload           10
        //   349: invokevirtual   com/google/android/gms/internal/fb$a.getMessage:()Ljava/lang/String;
        //   352: invokestatic    com/google/android/gms/internal/gs.W:(Ljava/lang/String;)V
        //   355: goto            158
        //   358: astore          9
        //   360: aload           11
        //   362: monitorexit    
        //   363: aload           9
        //   365: athrow         
        //   366: aload_0        
        //   367: new             Lcom/google/android/gms/internal/fk;
        //   370: dup            
        //   371: iload_1        
        //   372: aload_0        
        //   373: getfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   376: getfield        com/google/android/gms/internal/fk.qj:J
        //   379: invokespecial   com/google/android/gms/internal/fk.<init>:(IJ)V
        //   382: putfield        com/google/android/gms/internal/fb.sZ:Lcom/google/android/gms/internal/fk;
        //   385: goto            177
        //   388: astore          10
        //   390: ldc_w           "Error parsing the JSON for Active View."
        //   393: aload           10
        //   395: invokestatic    com/google/android/gms/internal/gs.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   398: aconst_null    
        //   399: astore          10
        //   401: goto            225
        //   404: astore          10
        //   406: lload           4
        //   408: lstore_2       
        //   409: goto            134
        //   412: aconst_null    
        //   413: astore          9
        //   415: goto            330
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  9      47     358    366    Any
        //  58     63     129    134    Lcom/google/android/gms/internal/fb$a;
        //  58     63     358    366    Any
        //  66     78     129    134    Lcom/google/android/gms/internal/fb$a;
        //  66     78     358    366    Any
        //  81     87     129    134    Lcom/google/android/gms/internal/fb$a;
        //  81     87     358    366    Any
        //  90     93     129    134    Lcom/google/android/gms/internal/fb$a;
        //  90     93     358    366    Any
        //  93     118    118    129    Any
        //  120    123    118    129    Any
        //  126    129    129    134    Lcom/google/android/gms/internal/fb$a;
        //  126    129    358    366    Any
        //  134    140    358    366    Any
        //  150    158    358    366    Any
        //  158    177    358    366    Any
        //  177    192    358    366    Any
        //  192    204    358    366    Any
        //  209    225    388    398    Ljava/lang/Exception;
        //  209    225    358    366    Any
        //  225    277    358    366    Any
        //  278    281    118    129    Any
        //  284    290    129    134    Lcom/google/android/gms/internal/fb$a;
        //  284    290    358    366    Any
        //  293    298    129    134    Lcom/google/android/gms/internal/fb$a;
        //  293    298    358    366    Any
        //  301    305    129    134    Lcom/google/android/gms/internal/fb$a;
        //  301    305    358    366    Any
        //  308    319    129    134    Lcom/google/android/gms/internal/fb$a;
        //  308    319    358    366    Any
        //  322    330    129    134    Lcom/google/android/gms/internal/fb$a;
        //  322    330    358    366    Any
        //  330    341    404    412    Lcom/google/android/gms/internal/fb$a;
        //  330    341    358    366    Any
        //  347    355    358    366    Any
        //  360    363    358    366    Any
        //  366    385    358    366    Any
        //  390    398    358    366    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 205, Size: 205
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
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
    
    @Override
    public void onStop() {
        synchronized (this.sV) {
            if (this.sY != null) {
                this.sY.cancel();
            }
        }
    }
    
    @ez
    private static final class a extends Exception
    {
        private final int tc;
        
        public a(final String s, final int tc) {
            super(s);
            this.tc = tc;
        }
        
        public int getErrorCode() {
            return this.tc;
        }
    }
}
