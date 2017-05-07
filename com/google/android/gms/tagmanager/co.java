// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;
import com.google.android.gms.internal.c$j;

class co implements Runnable
{
    private final String anR;
    private volatile String aon;
    private final bn aqg;
    private final String aqh;
    private bg<c$j> aqi;
    private volatile r aqj;
    private volatile String aqk;
    private final Context mContext;
    
    co(final Context mContext, final String anR, final bn aqg, final r aqj) {
        this.mContext = mContext;
        this.aqg = aqg;
        this.anR = anR;
        this.aqj = aqj;
        this.aqh = "/r?id=" + anR;
        this.aon = this.aqh;
        this.aqk = null;
    }
    
    public co(final Context context, final String s, final r r) {
        this(context, s, new bn(), r);
    }
    
    private boolean oK() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            bh.V("...no network connectivity");
            return false;
        }
        return true;
    }
    
    private void oL() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/google/android/gms/tagmanager/co.oK:()Z
        //     4: ifne            20
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/tagmanager/co.aqi:Lcom/google/android/gms/tagmanager/bg;
        //    11: getstatic       com/google/android/gms/tagmanager/bg$a.apB:Lcom/google/android/gms/tagmanager/bg$a;
        //    14: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //    19: return         
        //    20: ldc             "Start loading resource from network ..."
        //    22: invokestatic    com/google/android/gms/tagmanager/bh.V:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: invokevirtual   com/google/android/gms/tagmanager/co.oM:()Ljava/lang/String;
        //    29: astore_2       
        //    30: aload_0        
        //    31: getfield        com/google/android/gms/tagmanager/co.aqg:Lcom/google/android/gms/tagmanager/bn;
        //    34: invokevirtual   com/google/android/gms/tagmanager/bn.ov:()Lcom/google/android/gms/tagmanager/bm;
        //    37: astore_1       
        //    38: aload_1        
        //    39: aload_2        
        //    40: invokeinterface com/google/android/gms/tagmanager/bm.cA:(Ljava/lang/String;)Ljava/io/InputStream;
        //    45: astore_3       
        //    46: new             Ljava/io/ByteArrayOutputStream;
        //    49: dup            
        //    50: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    53: astore          4
        //    55: aload_3        
        //    56: aload           4
        //    58: invokestatic    com/google/android/gms/tagmanager/cr.b:(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //    61: aload           4
        //    63: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    66: invokestatic    com/google/android/gms/internal/c$j.b:([B)Lcom/google/android/gms/internal/c$j;
        //    69: astore_3       
        //    70: new             Ljava/lang/StringBuilder;
        //    73: dup            
        //    74: invokespecial   java/lang/StringBuilder.<init>:()V
        //    77: ldc             "Successfully loaded supplemented resource: "
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    82: aload_3        
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    86: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    89: invokestatic    com/google/android/gms/tagmanager/bh.V:(Ljava/lang/String;)V
        //    92: aload_3        
        //    93: getfield        com/google/android/gms/internal/c$j.gs:Lcom/google/android/gms/internal/c$f;
        //    96: ifnonnull       132
        //    99: aload_3        
        //   100: getfield        com/google/android/gms/internal/c$j.gr:[Lcom/google/android/gms/internal/c$i;
        //   103: arraylength    
        //   104: ifne            132
        //   107: new             Ljava/lang/StringBuilder;
        //   110: dup            
        //   111: invokespecial   java/lang/StringBuilder.<init>:()V
        //   114: ldc             "No change for container: "
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: aload_0        
        //   120: getfield        com/google/android/gms/tagmanager/co.anR:Ljava/lang/String;
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   129: invokestatic    com/google/android/gms/tagmanager/bh.V:(Ljava/lang/String;)V
        //   132: aload_0        
        //   133: getfield        com/google/android/gms/tagmanager/co.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   136: aload_3        
        //   137: invokeinterface com/google/android/gms/tagmanager/bg.l:(Ljava/lang/Object;)V
        //   142: aload_1        
        //   143: invokeinterface com/google/android/gms/tagmanager/bm.close:()V
        //   148: ldc             "Load resource from network finished."
        //   150: invokestatic    com/google/android/gms/tagmanager/bh.V:(Ljava/lang/String;)V
        //   153: return         
        //   154: astore_3       
        //   155: new             Ljava/lang/StringBuilder;
        //   158: dup            
        //   159: invokespecial   java/lang/StringBuilder.<init>:()V
        //   162: ldc             "No data is retrieved from the given url: "
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: aload_2        
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: ldc             ". Make sure container_id: "
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: aload_0        
        //   177: getfield        com/google/android/gms/tagmanager/co.anR:Ljava/lang/String;
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: ldc             " is correct."
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   191: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   194: aload_0        
        //   195: getfield        com/google/android/gms/tagmanager/co.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   198: getstatic       com/google/android/gms/tagmanager/bg$a.apD:Lcom/google/android/gms/tagmanager/bg$a;
        //   201: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   206: aload_1        
        //   207: invokeinterface com/google/android/gms/tagmanager/bm.close:()V
        //   212: return         
        //   213: astore_3       
        //   214: new             Ljava/lang/StringBuilder;
        //   217: dup            
        //   218: invokespecial   java/lang/StringBuilder.<init>:()V
        //   221: ldc             "Error when loading resources from url: "
        //   223: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   226: aload_2        
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: ldc             " "
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload_3        
        //   236: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   245: aload_3        
        //   246: invokestatic    com/google/android/gms/tagmanager/bh.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   249: aload_0        
        //   250: getfield        com/google/android/gms/tagmanager/co.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   253: getstatic       com/google/android/gms/tagmanager/bg$a.apC:Lcom/google/android/gms/tagmanager/bg$a;
        //   256: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   261: aload_1        
        //   262: invokeinterface com/google/android/gms/tagmanager/bm.close:()V
        //   267: return         
        //   268: astore_3       
        //   269: new             Ljava/lang/StringBuilder;
        //   272: dup            
        //   273: invokespecial   java/lang/StringBuilder.<init>:()V
        //   276: ldc             "Error when parsing downloaded resources from url: "
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: aload_2        
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: ldc             " "
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   290: aload_3        
        //   291: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   300: aload_3        
        //   301: invokestatic    com/google/android/gms/tagmanager/bh.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   304: aload_0        
        //   305: getfield        com/google/android/gms/tagmanager/co.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   308: getstatic       com/google/android/gms/tagmanager/bg$a.apD:Lcom/google/android/gms/tagmanager/bg$a;
        //   311: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   316: aload_1        
        //   317: invokeinterface com/google/android/gms/tagmanager/bm.close:()V
        //   322: return         
        //   323: astore_2       
        //   324: aload_1        
        //   325: invokeinterface com/google/android/gms/tagmanager/bm.close:()V
        //   330: aload_2        
        //   331: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  38     46     154    213    Ljava/io/FileNotFoundException;
        //  38     46     213    268    Ljava/io/IOException;
        //  38     46     323    332    Any
        //  46     132    268    323    Ljava/io/IOException;
        //  46     132    323    332    Any
        //  132    142    268    323    Ljava/io/IOException;
        //  132    142    323    332    Any
        //  155    206    323    332    Any
        //  214    261    323    332    Any
        //  269    316    323    332    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0132:
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
    
    void a(final bg<c$j> aqi) {
        this.aqi = aqi;
    }
    
    void cG(final String aqk) {
        bh.S("Setting previous container version: " + aqk);
        this.aqk = aqk;
    }
    
    void cr(final String aon) {
        if (aon == null) {
            this.aon = this.aqh;
            return;
        }
        bh.S("Setting CTFE URL path: " + aon);
        this.aon = aon;
    }
    
    String oM() {
        String s2;
        final String s = s2 = this.aqj.ob() + this.aon + "&v=a65833898";
        if (this.aqk != null) {
            s2 = s;
            if (!this.aqk.trim().equals("")) {
                s2 = s + "&pv=" + this.aqk;
            }
        }
        String string = s2;
        if (ce.oH().oI().equals(ce$a.apY)) {
            string = s2 + "&gtm_debug=x";
        }
        return string;
    }
    
    @Override
    public void run() {
        if (this.aqi == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.aqi.nZ();
        this.oL();
    }
}
