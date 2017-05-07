// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.widget.ViewSwitcher;
import android.view.ViewParent;
import java.util.HashSet;
import java.util.ArrayList;
import android.webkit.WebView;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.d;
import android.util.DisplayMetrics;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.os.RemoteException;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.content.Context;
import android.content.ComponentCallbacks;

public class v extends ap.a implements az, bc, bk, cf, ci, cr.a, dl, u
{
    private final x kA;
    private final aa kB;
    private boolean kC;
    private final ComponentCallbacks kD;
    private final bq ky;
    private final b kz;
    
    public v(final Context context, final ak ak, final String s, final bq ky, final dx dx) {
        this.kD = (ComponentCallbacks)new ComponentCallbacks() {
            public void onConfigurationChanged(final Configuration configuration) {
                if (v.this.kz != null && v.this.kz.kO != null && v.this.kz.kO.oj != null) {
                    v.this.kz.kO.oj.bE();
                }
            }
            
            public void onLowMemory() {
            }
        };
        this.kz = new b(context, ak, s, dx);
        this.ky = ky;
        this.kA = new x(this);
        this.kB = new aa();
        dw.x("Use AdRequest.Builder.addTestDevice(\"" + dv.l(context) + "\") to get test ads on this device.");
        dq.i(context);
        this.S();
    }
    
    private void S() {
        if (Build$VERSION.SDK_INT >= 14 && this.kz != null && this.kz.kI != null) {
            this.kz.kI.registerComponentCallbacks(this.kD);
        }
    }
    
    private void T() {
        if (Build$VERSION.SDK_INT >= 14 && this.kz != null && this.kz.kI != null) {
            this.kz.kI.unregisterComponentCallbacks(this.kD);
        }
    }
    
    private void a(final int n) {
        dw.z("Failed to load ad: " + n);
        if (this.kz.kL == null) {
            return;
        }
        try {
            this.kz.kL.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            dw.c("Could not call AdListener.onAdFailedToLoad().", (Throwable)ex);
        }
    }
    
    private void ad() {
        dw.x("Ad closing.");
        if (this.kz.kL == null) {
            return;
        }
        try {
            this.kz.kL.onAdClosed();
        }
        catch (RemoteException ex) {
            dw.c("Could not call AdListener.onAdClosed().", (Throwable)ex);
        }
    }
    
    private void ae() {
        dw.x("Ad leaving application.");
        if (this.kz.kL == null) {
            return;
        }
        try {
            this.kz.kL.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            dw.c("Could not call AdListener.onAdLeftApplication().", (Throwable)ex);
        }
    }
    
    private void af() {
        dw.x("Ad opening.");
        if (this.kz.kL == null) {
            return;
        }
        try {
            this.kz.kL.onAdOpened();
        }
        catch (RemoteException ex) {
            dw.c("Could not call AdListener.onAdOpened().", (Throwable)ex);
        }
    }
    
    private void ag() {
        dw.x("Ad finished loading.");
        if (this.kz.kL == null) {
            return;
        }
        try {
            this.kz.kL.onAdLoaded();
        }
        catch (RemoteException ex) {
            dw.c("Could not call AdListener.onAdLoaded().", (Throwable)ex);
        }
    }
    
    private boolean ah() {
        boolean b = true;
        if (!dq.a(this.kz.kI.getPackageManager(), this.kz.kI.getPackageName(), "android.permission.INTERNET")) {
            if (!this.kz.kN.lT) {
                dv.a((ViewGroup)this.kz.kG, this.kz.kN, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            b = false;
        }
        if (!dq.h(this.kz.kI)) {
            if (!this.kz.kN.lT) {
                dv.a((ViewGroup)this.kz.kG, this.kz.kN, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            b = false;
        }
        if (!b && !this.kz.kN.lT) {
            this.kz.kG.setVisibility(0);
        }
        return b;
    }
    
    private void ai() {
        if (this.kz.kO == null) {
            dw.z("Ad state was null when trying to ping click URLs.");
        }
        else {
            dw.v("Pinging click URLs.");
            this.kz.kP.bl();
            if (this.kz.kO.ne != null) {
                dq.a(this.kz.kI, this.kz.kK.rq, this.kz.kO.ne);
            }
            if (this.kz.kO.qt != null && this.kz.kO.qt.ne != null) {
                bo.a(this.kz.kI, this.kz.kK.rq, this.kz.kO, this.kz.kH, false, this.kz.kO.qt.ne);
            }
        }
    }
    
    private void aj() {
        if (this.kz.kO != null) {
            this.kz.kO.oj.destroy();
            this.kz.kO = null;
        }
    }
    
    private void b(final View view) {
        this.kz.kG.addView(view, new ViewGroup$LayoutParams(-2, -2));
    }
    
    private void b(final boolean b) {
        if (this.kz.kO == null) {
            dw.z("Ad state was null when trying to ping impression URLs.");
        }
        else {
            dw.v("Pinging Impression URLs.");
            this.kz.kP.bk();
            if (this.kz.kO.nf != null) {
                dq.a(this.kz.kI, this.kz.kK.rq, this.kz.kO.nf);
            }
            if (this.kz.kO.qt != null && this.kz.kO.qt.nf != null) {
                bo.a(this.kz.kI, this.kz.kK.rq, this.kz.kO, this.kz.kH, b, this.kz.kO.qt.nf);
            }
            if (this.kz.kO.nx != null && this.kz.kO.nx.na != null) {
                bo.a(this.kz.kI, this.kz.kK.rq, this.kz.kO, this.kz.kH, b, this.kz.kO.nx.na);
            }
        }
    }
    
    private boolean b(final dh p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        com/google/android/gms/internal/dh.po:Z
        //     4: ifeq            188
        //     7: aload_1        
        //     8: getfield        com/google/android/gms/internal/dh.ny:Lcom/google/android/gms/internal/br;
        //    11: invokeinterface com/google/android/gms/internal/br.getView:()Lcom/google/android/gms/dynamic/d;
        //    16: invokestatic    com/google/android/gms/dynamic/e.d:(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
        //    19: checkcast       Landroid/view/View;
        //    22: astore_1       
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //    27: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //    30: invokevirtual   com/google/android/gms/internal/v$a.getNextView:()Landroid/view/View;
        //    33: astore_2       
        //    34: aload_2        
        //    35: ifnull          49
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //    42: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //    45: aload_2        
        //    46: invokevirtual   com/google/android/gms/internal/v$a.removeView:(Landroid/view/View;)V
        //    49: aload_0        
        //    50: aload_1        
        //    51: invokespecial   com/google/android/gms/internal/v.b:(Landroid/view/View;)V
        //    54: aload_0        
        //    55: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //    58: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //    61: invokevirtual   com/google/android/gms/internal/v$a.getChildCount:()I
        //    64: iconst_1       
        //    65: if_icmple       78
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //    72: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //    75: invokevirtual   com/google/android/gms/internal/v$a.showNext:()V
        //    78: aload_0        
        //    79: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //    82: getfield        com/google/android/gms/internal/v$b.kO:Lcom/google/android/gms/internal/dh;
        //    85: ifnull          155
        //    88: aload_0        
        //    89: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //    92: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //    95: invokevirtual   com/google/android/gms/internal/v$a.getNextView:()Landroid/view/View;
        //    98: astore_1       
        //    99: aload_1        
        //   100: instanceof      Lcom/google/android/gms/internal/dz;
        //   103: ifeq            261
        //   106: aload_1        
        //   107: checkcast       Lcom/google/android/gms/internal/dz;
        //   110: aload_0        
        //   111: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   114: getfield        com/google/android/gms/internal/v$b.kI:Landroid/content/Context;
        //   117: aload_0        
        //   118: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   121: getfield        com/google/android/gms/internal/v$b.kN:Lcom/google/android/gms/internal/ak;
        //   124: invokevirtual   com/google/android/gms/internal/dz.a:(Landroid/content/Context;Lcom/google/android/gms/internal/ak;)V
        //   127: aload_0        
        //   128: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   131: getfield        com/google/android/gms/internal/v$b.kO:Lcom/google/android/gms/internal/dh;
        //   134: getfield        com/google/android/gms/internal/dh.ny:Lcom/google/android/gms/internal/br;
        //   137: ifnull          155
        //   140: aload_0        
        //   141: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   144: getfield        com/google/android/gms/internal/v$b.kO:Lcom/google/android/gms/internal/dh;
        //   147: getfield        com/google/android/gms/internal/dh.ny:Lcom/google/android/gms/internal/br;
        //   150: invokeinterface com/google/android/gms/internal/br.destroy:()V
        //   155: aload_0        
        //   156: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   159: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //   162: iconst_0       
        //   163: invokevirtual   com/google/android/gms/internal/v$a.setVisibility:(I)V
        //   166: iconst_1       
        //   167: ireturn        
        //   168: astore_1       
        //   169: ldc_w           "Could not get View from mediation adapter."
        //   172: aload_1        
        //   173: invokestatic    com/google/android/gms/internal/dw.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   176: iconst_0       
        //   177: ireturn        
        //   178: astore_1       
        //   179: ldc_w           "Could not add mediation view to view hierarchy."
        //   182: aload_1        
        //   183: invokestatic    com/google/android/gms/internal/dw.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   186: iconst_0       
        //   187: ireturn        
        //   188: aload_1        
        //   189: getfield        com/google/android/gms/internal/dh.qu:Lcom/google/android/gms/internal/ak;
        //   192: ifnull          54
        //   195: aload_1        
        //   196: getfield        com/google/android/gms/internal/dh.oj:Lcom/google/android/gms/internal/dz;
        //   199: aload_1        
        //   200: getfield        com/google/android/gms/internal/dh.qu:Lcom/google/android/gms/internal/ak;
        //   203: invokevirtual   com/google/android/gms/internal/dz.a:(Lcom/google/android/gms/internal/ak;)V
        //   206: aload_0        
        //   207: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   210: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //   213: invokevirtual   com/google/android/gms/internal/v$a.removeAllViews:()V
        //   216: aload_0        
        //   217: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   220: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //   223: aload_1        
        //   224: getfield        com/google/android/gms/internal/dh.qu:Lcom/google/android/gms/internal/ak;
        //   227: getfield        com/google/android/gms/internal/ak.widthPixels:I
        //   230: invokevirtual   com/google/android/gms/internal/v$a.setMinimumWidth:(I)V
        //   233: aload_0        
        //   234: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   237: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //   240: aload_1        
        //   241: getfield        com/google/android/gms/internal/dh.qu:Lcom/google/android/gms/internal/ak;
        //   244: getfield        com/google/android/gms/internal/ak.heightPixels:I
        //   247: invokevirtual   com/google/android/gms/internal/v$a.setMinimumHeight:(I)V
        //   250: aload_0        
        //   251: aload_1        
        //   252: getfield        com/google/android/gms/internal/dh.oj:Lcom/google/android/gms/internal/dz;
        //   255: invokespecial   com/google/android/gms/internal/v.b:(Landroid/view/View;)V
        //   258: goto            54
        //   261: aload_1        
        //   262: ifnull          127
        //   265: aload_0        
        //   266: getfield        com/google/android/gms/internal/v.kz:Lcom/google/android/gms/internal/v$b;
        //   269: getfield        com/google/android/gms/internal/v$b.kG:Lcom/google/android/gms/internal/v$a;
        //   272: aload_1        
        //   273: invokevirtual   com/google/android/gms/internal/v$a.removeView:(Landroid/view/View;)V
        //   276: goto            127
        //   279: astore_1       
        //   280: ldc_w           "Could not destroy previous mediation adapter."
        //   283: invokestatic    com/google/android/gms/internal/dw.z:(Ljava/lang/String;)V
        //   286: goto            155
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      23     168    178    Landroid/os/RemoteException;
        //  49     54     178    188    Ljava/lang/Throwable;
        //  140    155    279    289    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 127, Size: 127
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
    
    private cx.a c(final ah ah) {
        ApplicationInfo applicationInfo;
        PackageInfo packageInfo;
        int[] array;
        int n;
        int n2;
        DisplayMetrics displayMetrics;
        int width;
        int height;
        int n3;
        Bundle bundle;
        String bs;
        Label_0175_Outer:Label_0233_Outer:
        while (true) {
            applicationInfo = this.kz.kI.getApplicationInfo();
            while (true) {
                Label_0336: {
                    while (true) {
                        while (true) {
                            try {
                                packageInfo = this.kz.kI.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
                                if (this.kz.kN.lT || this.kz.kG.getParent() == null) {
                                    break Label_0336;
                                }
                                array = new int[2];
                                this.kz.kG.getLocationOnScreen(array);
                                n = array[0];
                                n2 = array[1];
                                displayMetrics = this.kz.kI.getResources().getDisplayMetrics();
                                width = this.kz.kG.getWidth();
                                height = this.kz.kG.getHeight();
                                if (this.kz.kG.isShown() && n + width > 0 && n2 + height > 0 && n <= displayMetrics.widthPixels && n2 <= displayMetrics.heightPixels) {
                                    n3 = 1;
                                    bundle = new Bundle(5);
                                    bundle.putInt("x", n);
                                    bundle.putInt("y", n2);
                                    bundle.putInt("width", width);
                                    bundle.putInt("height", height);
                                    bundle.putInt("visible", n3);
                                    bs = dj.bs();
                                    (this.kz.kP = new di(bs, this.kz.kH)).f(ah);
                                    return new cx.a(bundle, ah, this.kz.kN, this.kz.kH, applicationInfo, packageInfo, bs, dj.qK, this.kz.kK, dj.a(this, bs));
                                }
                            }
                            catch (PackageManager$NameNotFoundException ex) {
                                packageInfo = null;
                                continue Label_0175_Outer;
                            }
                            break;
                        }
                        n3 = 0;
                        continue Label_0233_Outer;
                    }
                }
                bundle = null;
                continue;
            }
        }
    }
    
    @Override
    public void P() {
        this.ai();
    }
    
    public d Q() {
        fq.aj("getAdFrame must be called on the main UI thread.");
        return e.h(this.kz.kG);
    }
    
    public ak R() {
        fq.aj("getAdSize must be called on the main UI thread.");
        return this.kz.kN;
    }
    
    @Override
    public void U() {
        this.ae();
    }
    
    @Override
    public void V() {
        this.kB.d(this.kz.kO);
        if (this.kz.kN.lT) {
            this.aj();
        }
        this.kC = false;
        this.ad();
        this.kz.kP.bm();
    }
    
    @Override
    public void W() {
        if (this.kz.kN.lT) {
            this.b(false);
        }
        this.kC = true;
        this.af();
    }
    
    @Override
    public void X() {
        this.P();
    }
    
    @Override
    public void Y() {
        this.V();
    }
    
    @Override
    public void Z() {
        this.U();
    }
    
    public void a(final ak kn) {
        fq.aj("setAdSize must be called on the main UI thread.");
        this.kz.kN = kn;
        if (this.kz.kO != null) {
            this.kz.kO.oj.a(kn);
        }
        if (this.kz.kG.getChildCount() > 1) {
            this.kz.kG.removeView(this.kz.kG.getNextView());
        }
        this.kz.kG.setMinimumWidth(kn.widthPixels);
        this.kz.kG.setMinimumHeight(kn.heightPixels);
        this.kz.kG.requestLayout();
    }
    
    public void a(final ao kl) {
        fq.aj("setAdListener must be called on the main UI thread.");
        this.kz.kL = kl;
    }
    
    public void a(final ar kq) {
        fq.aj("setAppEventListener must be called on the main UI thread.");
        this.kz.kQ = kq;
    }
    
    public void a(final co kr) {
        fq.aj("setInAppPurchaseListener must be called on the main UI thread.");
        this.kz.kR = kr;
    }
    
    @Override
    public void a(final dh ko) {
        int nk = 0;
        this.kz.kM = null;
        if (ko.errorCode != -2 && ko.errorCode != 3) {
            dj.b(this.kz.ak());
        }
        if (ko.errorCode == -1) {
            return;
        }
        final boolean b = ko.pg.extras != null && ko.pg.extras.getBoolean("_noRefresh", false);
        if (this.kz.kN.lT) {
            dq.a(ko.oj);
        }
        else if (!b) {
            if (ko.ni > 0L) {
                this.kA.a(ko.pg, ko.ni);
            }
            else if (ko.qt != null && ko.qt.ni > 0L) {
                this.kA.a(ko.pg, ko.qt.ni);
            }
            else if (!ko.po && ko.errorCode == 2) {
                this.kA.d(ko.pg);
            }
        }
        if (ko.errorCode == 3 && ko.qt != null && ko.qt.ng != null) {
            dw.v("Pinging no fill URLs.");
            bo.a(this.kz.kI, this.kz.kK.rq, ko, this.kz.kH, false, ko.qt.ng);
        }
        if (ko.errorCode != -2) {
            this.a(ko.errorCode);
            return;
        }
        if (!this.kz.kN.lT) {
            if (!this.b(ko)) {
                this.a(0);
                return;
            }
            if (this.kz.kG != null) {
                this.kz.kG.kF.t(ko.pt);
            }
        }
        if (this.kz.kO != null && this.kz.kO.nA != null) {
            this.kz.kO.nA.a((bk)null);
        }
        if (ko.nA != null) {
            ko.nA.a(this);
        }
        this.kB.d(this.kz.kO);
        this.kz.kO = ko;
        if (ko.qu != null) {
            this.kz.kN = ko.qu;
        }
        this.kz.kP.h(ko.qv);
        this.kz.kP.i(ko.qw);
        this.kz.kP.m(this.kz.kN.lT);
        this.kz.kP.n(ko.po);
        if (!this.kz.kN.lT) {
            this.b(false);
        }
        if (this.kz.kS == null) {
            this.kz.kS = new dm(this.kz.kH);
        }
        int nj;
        if (ko.qt != null) {
            nj = ko.qt.nj;
            nk = ko.qt.nk;
        }
        else {
            nj = 0;
        }
        this.kz.kS.a(nj, nk);
        if (!this.kz.kN.lT && ko.oj != null && (ko.oj.bI().bP() || ko.qs != null)) {
            final ab a = this.kB.a(this.kz.kN, this.kz.kO);
            if (ko.oj.bI().bP() && a != null) {
                a.a(new w(ko.oj));
            }
        }
        this.kz.kO.oj.bE();
        this.ag();
    }
    
    @Override
    public void a(final String s, final ArrayList<String> list) {
        if (this.kz.kR == null) {
            dw.z("InAppPurchaseListener is not set");
            return;
        }
        try {
            this.kz.kR.a(new cm(s, list, this.kz.kI, this.kz.kK.rq));
        }
        catch (RemoteException ex) {
            dw.z("Could not start In-App purchase.");
        }
    }
    
    @Override
    public void a(final HashSet<di> set) {
        this.kz.a(set);
    }
    
    public boolean a(final ah ah) {
        fq.aj("loadAd must be called on the main UI thread.");
        if (this.kz.kM != null) {
            dw.z("An ad request is already in progress. Aborting.");
        }
        else {
            if (this.kz.kN.lT && this.kz.kO != null) {
                dw.z("An interstitial is already loading. Aborting.");
                return false;
            }
            if (this.ah()) {
                dw.x("Starting ad request.");
                this.kA.cancel();
                final cx.a c = this.c(ah);
                Object o;
                if (this.kz.kN.lT) {
                    o = dz.a(this.kz.kI, this.kz.kN, false, false, this.kz.kJ, this.kz.kK);
                    ((dz)o).bI().a(this, null, this, this, true, this);
                }
                else {
                    final View nextView = this.kz.kG.getNextView();
                    if (nextView instanceof dz) {
                        o = nextView;
                        ((dz)o).a(this.kz.kI, this.kz.kN);
                    }
                    else {
                        if (nextView != null) {
                            this.kz.kG.removeView(nextView);
                        }
                        final dz dz = (dz)(o = com.google.android.gms.internal.dz.a(this.kz.kI, this.kz.kN, 0 != 0, 0 != 0, this.kz.kJ, this.kz.kK));
                        if (this.kz.kN.lU == null) {
                            this.b((View)dz);
                            o = dz;
                        }
                    }
                    ((dz)o).bI().a(this, this, this, this, false, this);
                }
                this.kz.kM = cr.a(this.kz.kI, c, this.kz.kJ, (dz)o, this.ky, (cr.a)this);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void aa() {
        this.W();
    }
    
    @Override
    public void ab() {
        if (this.kz.kO != null) {
            dw.z("Mediation adapter " + this.kz.kO.nz + " refreshed, but mediation adapters should never refresh.");
        }
        this.b(true);
        this.ag();
    }
    
    public void ac() {
        fq.aj("recordManualImpression must be called on the main UI thread.");
        if (this.kz.kO == null) {
            dw.z("Ad state was null when trying to ping manual tracking URLs.");
        }
        else {
            dw.v("Pinging manual tracking URLs.");
            if (this.kz.kO.pq != null) {
                dq.a(this.kz.kI, this.kz.kK.rq, this.kz.kO.pq);
            }
        }
    }
    
    public void b(final ah ah) {
        final ViewParent parent = this.kz.kG.getParent();
        if (parent instanceof View && ((View)parent).isShown() && dq.by() && !this.kC) {
            this.a(ah);
            return;
        }
        dw.x("Ad is not visible. Not refreshing ad.");
        this.kA.d(ah);
    }
    
    public void destroy() {
        fq.aj("destroy must be called on the main UI thread.");
        this.T();
        this.kz.kL = null;
        this.kz.kQ = null;
        this.kA.cancel();
        this.stopLoading();
        if (this.kz.kG != null) {
            this.kz.kG.removeAllViews();
        }
        if (this.kz.kO != null && this.kz.kO.oj != null) {
            this.kz.kO.oj.destroy();
        }
        if (this.kz.kO == null || this.kz.kO.ny == null) {
            return;
        }
        try {
            this.kz.kO.ny.destroy();
        }
        catch (RemoteException ex) {
            dw.z("Could not destroy mediation adapter.");
        }
    }
    
    public boolean isReady() {
        fq.aj("isLoaded must be called on the main UI thread.");
        return this.kz.kM == null && this.kz.kO != null;
    }
    
    @Override
    public void onAppEvent(final String s, final String s2) {
        if (this.kz.kQ == null) {
            return;
        }
        try {
            this.kz.kQ.onAppEvent(s, s2);
        }
        catch (RemoteException ex) {
            dw.c("Could not call the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void pause() {
        fq.aj("pause must be called on the main UI thread.");
        if (this.kz.kO != null) {
            dq.a(this.kz.kO.oj);
        }
        while (true) {
            if (this.kz.kO == null || this.kz.kO.ny == null) {
                break Label_0067;
            }
            try {
                this.kz.kO.ny.pause();
                this.kA.pause();
            }
            catch (RemoteException ex) {
                dw.z("Could not pause mediation adapter.");
                continue;
            }
            break;
        }
    }
    
    public void resume() {
        fq.aj("resume must be called on the main UI thread.");
        if (this.kz.kO != null) {
            dq.b(this.kz.kO.oj);
        }
        while (true) {
            if (this.kz.kO == null || this.kz.kO.ny == null) {
                break Label_0067;
            }
            try {
                this.kz.kO.ny.resume();
                this.kA.resume();
            }
            catch (RemoteException ex) {
                dw.z("Could not resume mediation adapter.");
                continue;
            }
            break;
        }
    }
    
    public void showInterstitial() {
        fq.aj("showInterstitial must be called on the main UI thread.");
        if (!this.kz.kN.lT) {
            dw.z("Cannot call showInterstitial on a banner ad.");
            return;
        }
        if (this.kz.kO == null) {
            dw.z("The interstitial has not loaded.");
            return;
        }
        if (this.kz.kO.oj.bL()) {
            dw.z("The interstitial is already showing.");
            return;
        }
        this.kz.kO.oj.p(true);
        if (this.kz.kO.oj.bI().bP() || this.kz.kO.qs != null) {
            final ab a = this.kB.a(this.kz.kN, this.kz.kO);
            if (this.kz.kO.oj.bI().bP() && a != null) {
                a.a(new w(this.kz.kO.oj));
            }
        }
        if (this.kz.kO.po) {
            try {
                this.kz.kO.ny.showInterstitial();
                return;
            }
            catch (RemoteException ex) {
                dw.c("Could not show interstitial.", (Throwable)ex);
                this.aj();
                return;
            }
        }
        cc.a(this.kz.kI, new ce(this, this, this, this.kz.kO.oj, this.kz.kO.orientation, this.kz.kK, this.kz.kO.pt));
    }
    
    public void stopLoading() {
        fq.aj("stopLoading must be called on the main UI thread.");
        if (this.kz.kO != null) {
            this.kz.kO.oj.stopLoading();
            this.kz.kO = null;
        }
        if (this.kz.kM != null) {
            this.kz.kM.cancel();
        }
    }
    
    private static final class a extends ViewSwitcher
    {
        private final dr kF;
        
        public a(final Context context) {
            super(context);
            this.kF = new dr(context);
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            this.kF.c(motionEvent);
            return false;
        }
    }
    
    private static final class b
    {
        public final a kG;
        public final String kH;
        public final Context kI;
        public final l kJ;
        public final dx kK;
        public ao kL;
        public do kM;
        public ak kN;
        public dh kO;
        public di kP;
        public ar kQ;
        public co kR;
        public dm kS;
        private HashSet<di> kT;
        
        public b(final Context ki, final ak kn, final String kh, final dx kk) {
            this.kS = null;
            this.kT = null;
            if (kn.lT) {
                this.kG = null;
            }
            else {
                (this.kG = new a(ki)).setMinimumWidth(kn.widthPixels);
                this.kG.setMinimumHeight(kn.heightPixels);
                this.kG.setVisibility(4);
            }
            this.kN = kn;
            this.kH = kh;
            this.kI = ki;
            this.kJ = new l(k.a(kk.rq, ki));
            this.kK = kk;
        }
        
        public void a(final HashSet<di> kt) {
            this.kT = kt;
        }
        
        public HashSet<di> ak() {
            return this.kT;
        }
    }
}
