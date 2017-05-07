// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import android.os.SystemClock;
import org.json.JSONException;
import android.text.TextUtils;
import android.content.Context;

public class cs extends do implements cu.a, ea.a
{
    private final bq ky;
    private final dz lC;
    private final Object li;
    private final Context mContext;
    private bj mR;
    private final cr.a oG;
    private final Object oH;
    private final cx.a oI;
    private final l oJ;
    private do oK;
    private cz oL;
    private boolean oM;
    private bh oN;
    private bn oO;
    
    public cs(final Context mContext, final cx.a oi, final l oj, final dz lc, final bq ky, final cr.a og) {
        this.oH = new Object();
        this.li = new Object();
        this.oM = false;
        this.ky = ky;
        this.oG = og;
        this.lC = lc;
        this.mContext = mContext;
        this.oI = oi;
        this.oJ = oj;
    }
    
    private ak a(final cx cx) throws a {
        if (this.oL.pr == null) {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        final String[] split = this.oL.pr.split("x");
        if (split.length != 2) {
            throw new a("Could not parse the ad size from the ad response: " + this.oL.pr, 0);
        }
        while (true) {
            int int1;
            int int2;
            ak[] lu;
            int length;
            int n = 0;
            ak ak = null;
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
                            lu = cx.kN.lU;
                            length = lu.length;
                            n = 0;
                            if (n >= length) {
                                break;
                            }
                            ak = lu[n];
                            density = this.mContext.getResources().getDisplayMetrics().density;
                            if (ak.width == -1) {
                                width = (int)(ak.widthPixels / density);
                                if (ak.height != -2) {
                                    break Label_0253;
                                }
                                height = (int)(ak.heightPixels / density);
                                if (int1 == width && int2 == height) {
                                    return new ak(ak, cx.kN.lU);
                                }
                                break Label_0263;
                            }
                        }
                        catch (NumberFormatException ex) {
                            throw new a("Could not parse the ad size from the ad response: " + this.oL.pr, 0);
                        }
                        width = ak.width;
                        continue Label_0177_Outer;
                    }
                    height = ak.height;
                    continue;
                }
                ++n;
                continue Label_0156_Outer;
            }
        }
        throw new a("The ad size from the ad response was not one of the requested sizes: " + this.oL.pr, 0);
    }
    
    private void a(final cx cx, final long n) throws a {
        synchronized (this.oH) {
            this.oN = new bh(this.mContext, cx, this.ky, this.mR);
            // monitorexit(this.oH)
            this.oO = this.oN.a(n, 60000L);
            switch (this.oO.nw) {
                default: {
                    throw new a("Unexpected mediation result: " + this.oO.nw, 0);
                }
                case 1: {
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
        throw new a("No fill from any mediation ad networks.", 3);
    }
    
    private void aZ() throws a {
        if (this.oL.errorCode != -3) {
            if (TextUtils.isEmpty((CharSequence)this.oL.pm)) {
                throw new a("No fill from ad server.", 3);
            }
            if (this.oL.po) {
                try {
                    this.mR = new bj(this.oL.pm);
                }
                catch (JSONException ex) {
                    throw new a("Could not parse mediation config: " + this.oL.pm, 0);
                }
            }
        }
    }
    
    private void b(final long n) throws a {
        dv.rp.post((Runnable)new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (cs.this.li) {
                        if (cs.this.oL.errorCode != -2) {
                            return;
                        }
                        cs.this.lC.bI().a((ea.a)cs.this);
                        if (cs.this.oL.errorCode == -3) {
                            dw.y("Loading URL in WebView: " + cs.this.oL.ol);
                            cs.this.lC.loadUrl(cs.this.oL.ol);
                            return;
                        }
                    }
                    dw.y("Loading HTML in WebView.");
                    cs.this.lC.loadDataWithBaseURL(dq.r(cs.this.oL.ol), cs.this.oL.pm, "text/html", "UTF-8", (String)null);
                }
            }
        });
        this.e(n);
    }
    
    private void d(final long n) throws a {
        while (this.f(n)) {
            if (this.oL != null) {
                synchronized (this.oH) {
                    this.oK = null;
                    // monitorexit(this.oH)
                    if (this.oL.errorCode != -2 && this.oL.errorCode != -3) {
                        throw new a("There was a problem getting an ad response. ErrorCode: " + this.oL.errorCode, this.oL.errorCode);
                    }
                }
                return;
            }
        }
        throw new a("Timed out waiting for ad response.", 2);
    }
    
    private void e(final long n) throws a {
        while (this.f(n)) {
            if (this.oM) {
                return;
            }
        }
        throw new a("Timed out waiting for WebView to finish loading.", 2);
    }
    
    private boolean f(long n) throws a {
        n = 60000L - (SystemClock.elapsedRealtime() - n);
        if (n <= 0L) {
            return false;
        }
        try {
            this.li.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            throw new a("Ad request cancelled.", -1);
        }
    }
    
    @Override
    public void a(final cz ol) {
        synchronized (this.li) {
            dw.v("Received ad response.");
            this.oL = ol;
            this.li.notify();
        }
    }
    
    @Override
    public void a(final dz dz) {
        synchronized (this.li) {
            dw.v("WebView finished loading.");
            this.oM = true;
            this.li.notify();
        }
    }
    
    @Override
    public void aY() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/cs.li:Ljava/lang/Object;
        //     4: astore          16
        //     6: aload           16
        //     8: monitorenter   
        //     9: ldc_w           "AdLoaderBackgroundTask started."
        //    12: invokestatic    com/google/android/gms/internal/dw.v:(Ljava/lang/String;)V
        //    15: aload_0        
        //    16: getfield        com/google/android/gms/internal/cs.oJ:Lcom/google/android/gms/internal/l;
        //    19: invokevirtual   com/google/android/gms/internal/l.y:()Lcom/google/android/gms/internal/h;
        //    22: aload_0        
        //    23: getfield        com/google/android/gms/internal/cs.mContext:Landroid/content/Context;
        //    26: invokeinterface com/google/android/gms/internal/h.a:(Landroid/content/Context;)Ljava/lang/String;
        //    31: astore          10
        //    33: new             Lcom/google/android/gms/internal/cx;
        //    36: dup            
        //    37: aload_0        
        //    38: getfield        com/google/android/gms/internal/cs.oI:Lcom/google/android/gms/internal/cx$a;
        //    41: aload           10
        //    43: invokespecial   com/google/android/gms/internal/cx.<init>:(Lcom/google/android/gms/internal/cx$a;Ljava/lang/String;)V
        //    46: astore          13
        //    48: aconst_null    
        //    49: astore          10
        //    51: aconst_null    
        //    52: astore          12
        //    54: bipush          -2
        //    56: istore_1       
        //    57: ldc2_w          -1
        //    60: lstore          5
        //    62: lload           5
        //    64: lstore_3       
        //    65: aload           12
        //    67: astore          11
        //    69: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    72: lstore          7
        //    74: lload           5
        //    76: lstore_3       
        //    77: aload           12
        //    79: astore          11
        //    81: aload_0        
        //    82: getfield        com/google/android/gms/internal/cs.mContext:Landroid/content/Context;
        //    85: aload           13
        //    87: aload_0        
        //    88: invokestatic    com/google/android/gms/internal/cu.a:(Landroid/content/Context;Lcom/google/android/gms/internal/cx;Lcom/google/android/gms/internal/cu$a;)Lcom/google/android/gms/internal/do;
        //    91: astore          15
        //    93: lload           5
        //    95: lstore_3       
        //    96: aload           12
        //    98: astore          11
        //   100: aload_0        
        //   101: getfield        com/google/android/gms/internal/cs.oH:Ljava/lang/Object;
        //   104: astore          14
        //   106: lload           5
        //   108: lstore_3       
        //   109: aload           12
        //   111: astore          11
        //   113: aload           14
        //   115: monitorenter   
        //   116: aload_0        
        //   117: aload           15
        //   119: putfield        com/google/android/gms/internal/cs.oK:Lcom/google/android/gms/internal/do;
        //   122: aload_0        
        //   123: getfield        com/google/android/gms/internal/cs.oK:Lcom/google/android/gms/internal/do;
        //   126: ifnonnull       487
        //   129: new             Lcom/google/android/gms/internal/cs$a;
        //   132: dup            
        //   133: ldc_w           "Could not start the ad request service."
        //   136: iconst_0       
        //   137: invokespecial   com/google/android/gms/internal/cs$a.<init>:(Ljava/lang/String;I)V
        //   140: athrow         
        //   141: astore          10
        //   143: aload           14
        //   145: monitorexit    
        //   146: lload           5
        //   148: lstore_3       
        //   149: aload           12
        //   151: astore          11
        //   153: aload           10
        //   155: athrow         
        //   156: astore          10
        //   158: aload           10
        //   160: invokevirtual   com/google/android/gms/internal/cs$a.getErrorCode:()I
        //   163: istore_1       
        //   164: iload_1        
        //   165: iconst_3       
        //   166: if_icmpeq       174
        //   169: iload_1        
        //   170: iconst_m1      
        //   171: if_icmpne       651
        //   174: aload           10
        //   176: invokevirtual   com/google/android/gms/internal/cs$a.getMessage:()Ljava/lang/String;
        //   179: invokestatic    com/google/android/gms/internal/dw.x:(Ljava/lang/String;)V
        //   182: aload_0        
        //   183: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   186: ifnonnull       662
        //   189: aload_0        
        //   190: new             Lcom/google/android/gms/internal/cz;
        //   193: dup            
        //   194: iload_1        
        //   195: invokespecial   com/google/android/gms/internal/cz.<init>:(I)V
        //   198: putfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   201: getstatic       com/google/android/gms/internal/dv.rp:Landroid/os/Handler;
        //   204: new             Lcom/google/android/gms/internal/cs$1;
        //   207: dup            
        //   208: aload_0        
        //   209: invokespecial   com/google/android/gms/internal/cs$1.<init>:(Lcom/google/android/gms/internal/cs;)V
        //   212: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   215: pop            
        //   216: aload           11
        //   218: astore          10
        //   220: aload_0        
        //   221: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   224: getfield        com/google/android/gms/internal/cz.pw:Ljava/lang/String;
        //   227: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   230: istore          9
        //   232: iload           9
        //   234: ifne            694
        //   237: new             Lorg/json/JSONObject;
        //   240: dup            
        //   241: aload_0        
        //   242: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   245: getfield        com/google/android/gms/internal/cz.pw:Ljava/lang/String;
        //   248: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   251: astore          11
        //   253: aload           13
        //   255: getfield        com/google/android/gms/internal/cx.pg:Lcom/google/android/gms/internal/ah;
        //   258: astore          17
        //   260: aload_0        
        //   261: getfield        com/google/android/gms/internal/cs.lC:Lcom/google/android/gms/internal/dz;
        //   264: astore          18
        //   266: aload_0        
        //   267: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   270: getfield        com/google/android/gms/internal/cz.ne:Ljava/util/List;
        //   273: astore          19
        //   275: aload_0        
        //   276: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   279: getfield        com/google/android/gms/internal/cz.nf:Ljava/util/List;
        //   282: astore          20
        //   284: aload_0        
        //   285: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   288: getfield        com/google/android/gms/internal/cz.pq:Ljava/util/List;
        //   291: astore          21
        //   293: aload_0        
        //   294: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   297: getfield        com/google/android/gms/internal/cz.orientation:I
        //   300: istore_2       
        //   301: aload_0        
        //   302: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   305: getfield        com/google/android/gms/internal/cz.ni:J
        //   308: lstore          5
        //   310: aload           13
        //   312: getfield        com/google/android/gms/internal/cx.pj:Ljava/lang/String;
        //   315: astore          22
        //   317: aload_0        
        //   318: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   321: getfield        com/google/android/gms/internal/cz.po:Z
        //   324: istore          9
        //   326: aload_0        
        //   327: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   330: ifnull          700
        //   333: aload_0        
        //   334: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   337: getfield        com/google/android/gms/internal/bn.nx:Lcom/google/android/gms/internal/bi;
        //   340: astore          12
        //   342: aload_0        
        //   343: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   346: ifnull          706
        //   349: aload_0        
        //   350: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   353: getfield        com/google/android/gms/internal/bn.ny:Lcom/google/android/gms/internal/br;
        //   356: astore          13
        //   358: aload_0        
        //   359: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   362: ifnull          712
        //   365: aload_0        
        //   366: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   369: getfield        com/google/android/gms/internal/bn.nz:Ljava/lang/String;
        //   372: astore          14
        //   374: aload_0        
        //   375: getfield        com/google/android/gms/internal/cs.mR:Lcom/google/android/gms/internal/bj;
        //   378: astore          23
        //   380: aload_0        
        //   381: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   384: ifnull          718
        //   387: aload_0        
        //   388: getfield        com/google/android/gms/internal/cs.oO:Lcom/google/android/gms/internal/bn;
        //   391: getfield        com/google/android/gms/internal/bn.nA:Lcom/google/android/gms/internal/bl;
        //   394: astore          15
        //   396: new             Lcom/google/android/gms/internal/dh;
        //   399: dup            
        //   400: aload           17
        //   402: aload           18
        //   404: aload           19
        //   406: iload_1        
        //   407: aload           20
        //   409: aload           21
        //   411: iload_2        
        //   412: lload           5
        //   414: aload           22
        //   416: iload           9
        //   418: aload           12
        //   420: aload           13
        //   422: aload           14
        //   424: aload           23
        //   426: aload           15
        //   428: aload_0        
        //   429: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   432: getfield        com/google/android/gms/internal/cz.pp:J
        //   435: aload           10
        //   437: aload_0        
        //   438: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   441: getfield        com/google/android/gms/internal/cz.pn:J
        //   444: lload_3        
        //   445: aload_0        
        //   446: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   449: getfield        com/google/android/gms/internal/cz.ps:J
        //   452: aload_0        
        //   453: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   456: getfield        com/google/android/gms/internal/cz.pt:Ljava/lang/String;
        //   459: aload           11
        //   461: invokespecial   com/google/android/gms/internal/dh.<init>:(Lcom/google/android/gms/internal/ah;Lcom/google/android/gms/internal/dz;Ljava/util/List;ILjava/util/List;Ljava/util/List;IJLjava/lang/String;ZLcom/google/android/gms/internal/bi;Lcom/google/android/gms/internal/br;Ljava/lang/String;Lcom/google/android/gms/internal/bj;Lcom/google/android/gms/internal/bl;JLcom/google/android/gms/internal/ak;JJJLjava/lang/String;Lorg/json/JSONObject;)V
        //   464: astore          10
        //   466: getstatic       com/google/android/gms/internal/dv.rp:Landroid/os/Handler;
        //   469: new             Lcom/google/android/gms/internal/cs$2;
        //   472: dup            
        //   473: aload_0        
        //   474: aload           10
        //   476: invokespecial   com/google/android/gms/internal/cs$2.<init>:(Lcom/google/android/gms/internal/cs;Lcom/google/android/gms/internal/dh;)V
        //   479: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   482: pop            
        //   483: aload           16
        //   485: monitorexit    
        //   486: return         
        //   487: aload           14
        //   489: monitorexit    
        //   490: lload           5
        //   492: lstore_3       
        //   493: aload           12
        //   495: astore          11
        //   497: aload_0        
        //   498: lload           7
        //   500: invokespecial   com/google/android/gms/internal/cs.d:(J)V
        //   503: lload           5
        //   505: lstore_3       
        //   506: aload           12
        //   508: astore          11
        //   510: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   513: lstore          5
        //   515: lload           5
        //   517: lstore_3       
        //   518: aload           12
        //   520: astore          11
        //   522: aload_0        
        //   523: invokespecial   com/google/android/gms/internal/cs.aZ:()V
        //   526: lload           5
        //   528: lstore_3       
        //   529: aload           12
        //   531: astore          11
        //   533: aload           13
        //   535: getfield        com/google/android/gms/internal/cx.kN:Lcom/google/android/gms/internal/ak;
        //   538: getfield        com/google/android/gms/internal/ak.lU:[Lcom/google/android/gms/internal/ak;
        //   541: ifnull          559
        //   544: lload           5
        //   546: lstore_3       
        //   547: aload           12
        //   549: astore          11
        //   551: aload_0        
        //   552: aload           13
        //   554: invokespecial   com/google/android/gms/internal/cs.a:(Lcom/google/android/gms/internal/cx;)Lcom/google/android/gms/internal/ak;
        //   557: astore          10
        //   559: lload           5
        //   561: lstore_3       
        //   562: aload           10
        //   564: astore          11
        //   566: aload_0        
        //   567: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   570: getfield        com/google/android/gms/internal/cz.po:Z
        //   573: ifeq            594
        //   576: lload           5
        //   578: lstore_3       
        //   579: aload           10
        //   581: astore          11
        //   583: aload_0        
        //   584: aload           13
        //   586: lload           7
        //   588: invokespecial   com/google/android/gms/internal/cs.a:(Lcom/google/android/gms/internal/cx;J)V
        //   591: goto            724
        //   594: lload           5
        //   596: lstore_3       
        //   597: aload           10
        //   599: astore          11
        //   601: aload_0        
        //   602: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   605: getfield        com/google/android/gms/internal/cz.pu:Z
        //   608: ifeq            635
        //   611: lload           5
        //   613: lstore_3       
        //   614: aload           10
        //   616: astore          11
        //   618: aload_0        
        //   619: lload           7
        //   621: invokevirtual   com/google/android/gms/internal/cs.c:(J)V
        //   624: goto            724
        //   627: astore          10
        //   629: aload           16
        //   631: monitorexit    
        //   632: aload           10
        //   634: athrow         
        //   635: lload           5
        //   637: lstore_3       
        //   638: aload           10
        //   640: astore          11
        //   642: aload_0        
        //   643: lload           7
        //   645: invokespecial   com/google/android/gms/internal/cs.b:(J)V
        //   648: goto            724
        //   651: aload           10
        //   653: invokevirtual   com/google/android/gms/internal/cs$a.getMessage:()Ljava/lang/String;
        //   656: invokestatic    com/google/android/gms/internal/dw.z:(Ljava/lang/String;)V
        //   659: goto            182
        //   662: aload_0        
        //   663: new             Lcom/google/android/gms/internal/cz;
        //   666: dup            
        //   667: iload_1        
        //   668: aload_0        
        //   669: getfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   672: getfield        com/google/android/gms/internal/cz.ni:J
        //   675: invokespecial   com/google/android/gms/internal/cz.<init>:(IJ)V
        //   678: putfield        com/google/android/gms/internal/cs.oL:Lcom/google/android/gms/internal/cz;
        //   681: goto            201
        //   684: astore          11
        //   686: ldc_w           "Error parsing the JSON for Active View."
        //   689: aload           11
        //   691: invokestatic    com/google/android/gms/internal/dw.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   694: aconst_null    
        //   695: astore          11
        //   697: goto            253
        //   700: aconst_null    
        //   701: astore          12
        //   703: goto            342
        //   706: aconst_null    
        //   707: astore          13
        //   709: goto            358
        //   712: aconst_null    
        //   713: astore          14
        //   715: goto            374
        //   718: aconst_null    
        //   719: astore          15
        //   721: goto            396
        //   724: lload           5
        //   726: lstore_3       
        //   727: goto            220
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  9      48     627    635    Any
        //  69     74     156    684    Lcom/google/android/gms/internal/cs$a;
        //  69     74     627    635    Any
        //  81     93     156    684    Lcom/google/android/gms/internal/cs$a;
        //  81     93     627    635    Any
        //  100    106    156    684    Lcom/google/android/gms/internal/cs$a;
        //  100    106    627    635    Any
        //  113    116    156    684    Lcom/google/android/gms/internal/cs$a;
        //  113    116    627    635    Any
        //  116    141    141    156    Any
        //  143    146    141    156    Any
        //  153    156    156    684    Lcom/google/android/gms/internal/cs$a;
        //  153    156    627    635    Any
        //  158    164    627    635    Any
        //  174    182    627    635    Any
        //  182    201    627    635    Any
        //  201    216    627    635    Any
        //  220    232    627    635    Any
        //  237    253    684    694    Ljava/lang/Exception;
        //  237    253    627    635    Any
        //  253    342    627    635    Any
        //  342    358    627    635    Any
        //  358    374    627    635    Any
        //  374    396    627    635    Any
        //  396    486    627    635    Any
        //  487    490    141    156    Any
        //  497    503    156    684    Lcom/google/android/gms/internal/cs$a;
        //  497    503    627    635    Any
        //  510    515    156    684    Lcom/google/android/gms/internal/cs$a;
        //  510    515    627    635    Any
        //  522    526    156    684    Lcom/google/android/gms/internal/cs$a;
        //  522    526    627    635    Any
        //  533    544    156    684    Lcom/google/android/gms/internal/cs$a;
        //  533    544    627    635    Any
        //  551    559    156    684    Lcom/google/android/gms/internal/cs$a;
        //  551    559    627    635    Any
        //  566    576    156    684    Lcom/google/android/gms/internal/cs$a;
        //  566    576    627    635    Any
        //  583    591    156    684    Lcom/google/android/gms/internal/cs$a;
        //  583    591    627    635    Any
        //  601    611    156    684    Lcom/google/android/gms/internal/cs$a;
        //  601    611    627    635    Any
        //  618    624    156    684    Lcom/google/android/gms/internal/cs$a;
        //  618    624    627    635    Any
        //  629    632    627    635    Any
        //  642    648    156    684    Lcom/google/android/gms/internal/cs$a;
        //  642    648    627    635    Any
        //  651    659    627    635    Any
        //  662    681    627    635    Any
        //  686    694    627    635    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0253:
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
    
    protected void c(final long n) throws a {
        final ak r = this.lC.R();
        int n2;
        int n3;
        if (r.lT) {
            n2 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            n3 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        }
        else {
            n2 = r.widthPixels;
            n3 = r.heightPixels;
        }
        final ct ct = new ct(this, this.lC, n2, n3);
        dv.rp.post((Runnable)new Runnable() {
            @Override
            public void run() {
                synchronized (cs.this.li) {
                    if (cs.this.oL.errorCode != -2) {
                        return;
                    }
                    cs.this.lC.bI().a((ea.a)cs.this);
                    ct.b(cs.this.oL);
                }
            }
        });
        this.e(n);
        if (ct.bc()) {
            dw.v("Ad-Network indicated no fill with passback URL.");
            throw new a("AdNetwork sent passback url", 3);
        }
        if (!ct.bd()) {
            throw new a("AdNetwork timed out", 2);
        }
    }
    
    @Override
    public void onStop() {
        synchronized (this.oH) {
            if (this.oK != null) {
                this.oK.cancel();
            }
            this.lC.stopLoading();
            dq.a(this.lC);
            if (this.oN != null) {
                this.oN.cancel();
            }
        }
    }
    
    private static final class a extends Exception
    {
        private final int oS;
        
        public a(final String s, final int os) {
            super(s);
            this.oS = os;
        }
        
        public int getErrorCode() {
            return this.oS;
        }
    }
}
