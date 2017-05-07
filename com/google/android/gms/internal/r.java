// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.widget.ViewSwitcher;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.b;
import android.view.ViewParent;
import android.webkit.WebView;
import android.util.DisplayMetrics;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.os.RemoteException;
import android.content.Context;

public final class r extends ac.a implements al, av, bn, bq, bu.a, q
{
    private final bb ed;
    private final a ee;
    private final s ef;
    
    public r(final Context context, final x x, final String s, final bb ed, final cu cu) {
        this.ee = new a(context, x, s, cu);
        this.ed = ed;
        this.ef = new s(this);
        ct.t("Use AdRequest.Builder.addTestDevice(\"" + cs.l(context) + "\") to get test ads on this device.");
        co.i(context);
    }
    
    private void I() {
        ct.t("Ad closing.");
        if (this.ee.ek == null) {
            return;
        }
        try {
            this.ee.ek.onAdClosed();
        }
        catch (RemoteException ex) {
            ct.b("Could not call AdListener.onAdClosed().", (Throwable)ex);
        }
    }
    
    private void J() {
        ct.t("Ad leaving application.");
        if (this.ee.ek == null) {
            return;
        }
        try {
            this.ee.ek.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            ct.b("Could not call AdListener.onAdLeftApplication().", (Throwable)ex);
        }
    }
    
    private void K() {
        ct.t("Ad opening.");
        if (this.ee.ek == null) {
            return;
        }
        try {
            this.ee.ek.onAdOpened();
        }
        catch (RemoteException ex) {
            ct.b("Could not call AdListener.onAdOpened().", (Throwable)ex);
        }
    }
    
    private void L() {
        ct.t("Ad finished loading.");
        if (this.ee.ek == null) {
            return;
        }
        try {
            this.ee.ek.onAdLoaded();
        }
        catch (RemoteException ex) {
            ct.b("Could not call AdListener.onAdLoaded().", (Throwable)ex);
        }
    }
    
    private boolean M() {
        boolean b = true;
        if (!co.a(this.ee.eh.getPackageManager(), this.ee.eh.getPackageName(), "android.permission.INTERNET")) {
            if (!this.ee.em.eG) {
                cs.a((ViewGroup)this.ee.eg, this.ee.em, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            b = false;
        }
        if (!co.h(this.ee.eh)) {
            if (!this.ee.em.eG) {
                cs.a((ViewGroup)this.ee.eg, this.ee.em, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            b = false;
        }
        if (!b && !this.ee.em.eG) {
            this.ee.eg.setVisibility(0);
        }
        return b;
    }
    
    private void N() {
        if (this.ee.en == null) {
            ct.v("Ad state was null when trying to ping click URLs.");
        }
        else {
            ct.r("Pinging click URLs.");
            if (this.ee.en.fK != null) {
                co.a(this.ee.eh, this.ee.ej.iJ, this.ee.en.fK);
            }
            if (this.ee.en.is != null && this.ee.en.is.fK != null) {
                az.a(this.ee.eh, this.ee.ej.iJ, this.ee.en, this.ee.adUnitId, false, this.ee.en.is.fK);
            }
        }
    }
    
    private void O() {
        if (this.ee.en != null) {
            this.ee.en.gJ.destroy();
            this.ee.en = null;
        }
    }
    
    private void a(final int n) {
        ct.v("Failed to load ad: " + n);
        if (this.ee.ek == null) {
            return;
        }
        try {
            this.ee.ek.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            ct.b("Could not call AdListener.onAdFailedToLoad().", (Throwable)ex);
        }
    }
    
    private void b(final View view) {
        this.ee.eg.addView(view, new ViewGroup$LayoutParams(-2, -2));
    }
    
    private void b(final boolean b) {
        if (this.ee.en == null) {
            ct.v("Ad state was null when trying to ping impression URLs.");
        }
        else {
            ct.r("Pinging Impression URLs.");
            if (this.ee.en.fL != null) {
                co.a(this.ee.eh, this.ee.ej.iJ, this.ee.en.fL);
            }
            if (this.ee.en.is != null && this.ee.en.is.fL != null) {
                az.a(this.ee.eh, this.ee.ej.iJ, this.ee.en, this.ee.adUnitId, b, this.ee.en.is.fL);
            }
            if (this.ee.en.gb != null && this.ee.en.gb.fG != null) {
                az.a(this.ee.eh, this.ee.ej.iJ, this.ee.en, this.ee.adUnitId, b, this.ee.en.gb.fG);
            }
        }
    }
    
    private boolean b(final cj p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        com/google/android/gms/internal/cj.hy:Z
        //     4: ifeq            188
        //     7: aload_1        
        //     8: getfield        com/google/android/gms/internal/cj.gc:Lcom/google/android/gms/internal/bc;
        //    11: invokeinterface com/google/android/gms/internal/bc.getView:()Lcom/google/android/gms/dynamic/b;
        //    16: invokestatic    com/google/android/gms/dynamic/c.b:(Lcom/google/android/gms/dynamic/b;)Ljava/lang/Object;
        //    19: checkcast       Landroid/view/View;
        //    22: astore_1       
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //    27: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //    30: invokevirtual   android/widget/ViewSwitcher.getNextView:()Landroid/view/View;
        //    33: astore_2       
        //    34: aload_2        
        //    35: ifnull          49
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //    42: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //    45: aload_2        
        //    46: invokevirtual   android/widget/ViewSwitcher.removeView:(Landroid/view/View;)V
        //    49: aload_0        
        //    50: aload_1        
        //    51: invokespecial   com/google/android/gms/internal/r.b:(Landroid/view/View;)V
        //    54: aload_0        
        //    55: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //    58: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //    61: invokevirtual   android/widget/ViewSwitcher.getChildCount:()I
        //    64: iconst_1       
        //    65: if_icmple       78
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //    72: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //    75: invokevirtual   android/widget/ViewSwitcher.showNext:()V
        //    78: aload_0        
        //    79: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //    82: getfield        com/google/android/gms/internal/r$a.en:Lcom/google/android/gms/internal/cj;
        //    85: ifnull          155
        //    88: aload_0        
        //    89: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //    92: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //    95: invokevirtual   android/widget/ViewSwitcher.getNextView:()Landroid/view/View;
        //    98: astore_1       
        //    99: aload_1        
        //   100: instanceof      Lcom/google/android/gms/internal/cw;
        //   103: ifeq            261
        //   106: aload_1        
        //   107: checkcast       Lcom/google/android/gms/internal/cw;
        //   110: aload_0        
        //   111: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   114: getfield        com/google/android/gms/internal/r$a.eh:Landroid/content/Context;
        //   117: aload_0        
        //   118: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   121: getfield        com/google/android/gms/internal/r$a.em:Lcom/google/android/gms/internal/x;
        //   124: invokevirtual   com/google/android/gms/internal/cw.a:(Landroid/content/Context;Lcom/google/android/gms/internal/x;)V
        //   127: aload_0        
        //   128: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   131: getfield        com/google/android/gms/internal/r$a.en:Lcom/google/android/gms/internal/cj;
        //   134: getfield        com/google/android/gms/internal/cj.gc:Lcom/google/android/gms/internal/bc;
        //   137: ifnull          155
        //   140: aload_0        
        //   141: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   144: getfield        com/google/android/gms/internal/r$a.en:Lcom/google/android/gms/internal/cj;
        //   147: getfield        com/google/android/gms/internal/cj.gc:Lcom/google/android/gms/internal/bc;
        //   150: invokeinterface com/google/android/gms/internal/bc.destroy:()V
        //   155: aload_0        
        //   156: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   159: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //   162: iconst_0       
        //   163: invokevirtual   android/widget/ViewSwitcher.setVisibility:(I)V
        //   166: iconst_1       
        //   167: ireturn        
        //   168: astore_1       
        //   169: ldc_w           "Could not get View from mediation adapter."
        //   172: aload_1        
        //   173: invokestatic    com/google/android/gms/internal/ct.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   176: iconst_0       
        //   177: ireturn        
        //   178: astore_1       
        //   179: ldc_w           "Could not add mediation view to view hierarchy."
        //   182: aload_1        
        //   183: invokestatic    com/google/android/gms/internal/ct.b:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   186: iconst_0       
        //   187: ireturn        
        //   188: aload_1        
        //   189: getfield        com/google/android/gms/internal/cj.it:Lcom/google/android/gms/internal/x;
        //   192: ifnull          54
        //   195: aload_1        
        //   196: getfield        com/google/android/gms/internal/cj.gJ:Lcom/google/android/gms/internal/cw;
        //   199: aload_1        
        //   200: getfield        com/google/android/gms/internal/cj.it:Lcom/google/android/gms/internal/x;
        //   203: invokevirtual   com/google/android/gms/internal/cw.a:(Lcom/google/android/gms/internal/x;)V
        //   206: aload_0        
        //   207: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   210: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //   213: invokevirtual   android/widget/ViewSwitcher.removeAllViews:()V
        //   216: aload_0        
        //   217: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   220: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //   223: aload_1        
        //   224: getfield        com/google/android/gms/internal/cj.it:Lcom/google/android/gms/internal/x;
        //   227: getfield        com/google/android/gms/internal/x.widthPixels:I
        //   230: invokevirtual   android/widget/ViewSwitcher.setMinimumWidth:(I)V
        //   233: aload_0        
        //   234: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   237: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //   240: aload_1        
        //   241: getfield        com/google/android/gms/internal/cj.it:Lcom/google/android/gms/internal/x;
        //   244: getfield        com/google/android/gms/internal/x.heightPixels:I
        //   247: invokevirtual   android/widget/ViewSwitcher.setMinimumHeight:(I)V
        //   250: aload_0        
        //   251: aload_1        
        //   252: getfield        com/google/android/gms/internal/cj.gJ:Lcom/google/android/gms/internal/cw;
        //   255: invokespecial   com/google/android/gms/internal/r.b:(Landroid/view/View;)V
        //   258: goto            54
        //   261: aload_1        
        //   262: ifnull          127
        //   265: aload_0        
        //   266: getfield        com/google/android/gms/internal/r.ee:Lcom/google/android/gms/internal/r$a;
        //   269: getfield        com/google/android/gms/internal/r$a.eg:Landroid/widget/ViewSwitcher;
        //   272: aload_1        
        //   273: invokevirtual   android/widget/ViewSwitcher.removeView:(Landroid/view/View;)V
        //   276: goto            127
        //   279: astore_1       
        //   280: ldc_w           "Could not destroy previous mediation adapter."
        //   283: invokestatic    com/google/android/gms/internal/ct.v:(Ljava/lang/String;)V
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
    
    private bz.a c(final v v) {
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
        Label_0175_Outer:Label_0233_Outer:
        while (true) {
            applicationInfo = this.ee.eh.getApplicationInfo();
            while (true) {
                Label_0288: {
                    while (true) {
                        while (true) {
                            try {
                                packageInfo = this.ee.eh.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
                                if (this.ee.em.eG || this.ee.eg.getParent() == null) {
                                    break Label_0288;
                                }
                                array = new int[2];
                                this.ee.eg.getLocationOnScreen(array);
                                n = array[0];
                                n2 = array[1];
                                displayMetrics = this.ee.eh.getResources().getDisplayMetrics();
                                width = this.ee.eg.getWidth();
                                height = this.ee.eg.getHeight();
                                if (this.ee.eg.isShown() && n + width > 0 && n2 + height > 0 && n <= displayMetrics.widthPixels && n2 <= displayMetrics.heightPixels) {
                                    n3 = 1;
                                    bundle = new Bundle(5);
                                    bundle.putInt("x", n);
                                    bundle.putInt("y", n2);
                                    bundle.putInt("width", width);
                                    bundle.putInt("height", height);
                                    bundle.putInt("visible", n3);
                                    return new bz.a(bundle, v, this.ee.em, this.ee.adUnitId, applicationInfo, packageInfo, ck.ar(), ck.iu, this.ee.ej);
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
    public void A() {
        if (this.ee.em.eG) {
            this.O();
        }
        this.I();
    }
    
    @Override
    public void B() {
        if (this.ee.em.eG) {
            this.b(false);
        }
        this.K();
    }
    
    @Override
    public void C() {
        this.w();
    }
    
    @Override
    public void D() {
        this.A();
    }
    
    @Override
    public void E() {
        this.z();
    }
    
    @Override
    public void F() {
        this.B();
    }
    
    @Override
    public void G() {
        if (this.ee.en != null) {
            ct.v("Mediation adapter " + this.ee.en.gd + " refreshed, but mediation adapters should never refresh.");
        }
        this.b(true);
        this.L();
    }
    
    public void H() {
        eg.N("recordManualImpression must be called on the main UI thread.");
        if (this.ee.en == null) {
            ct.v("Ad state was null when trying to ping manual tracking URLs.");
        }
        else {
            ct.r("Pinging manual tracking URLs.");
            if (this.ee.en.hA != null) {
                co.a(this.ee.eh, this.ee.ej.iJ, this.ee.en.hA);
            }
        }
    }
    
    public void a(final ab ek) {
        eg.N("setAdListener must be called on the main UI thread.");
        this.ee.ek = ek;
    }
    
    public void a(final ae eo) {
        eg.N("setAppEventListener must be called on the main UI thread.");
        this.ee.eo = eo;
    }
    
    @Override
    public void a(final cj en) {
        this.ee.el = null;
        if (en.errorCode == -1) {
            return;
        }
        final boolean b = en.hr.extras != null && en.hr.extras.getBoolean("_noRefresh", false);
        if (this.ee.em.eG) {
            co.a(en.gJ);
        }
        else if (!b) {
            if (en.fO > 0L) {
                this.ef.a(en.hr, en.fO);
            }
            else if (en.is != null && en.is.fO > 0L) {
                this.ef.a(en.hr, en.is.fO);
            }
            else if (!en.hy && en.errorCode == 2) {
                this.ef.d(en.hr);
            }
        }
        if (en.errorCode == 3 && en.is != null && en.is.fM != null) {
            ct.r("Pinging no fill URLs.");
            az.a(this.ee.eh, this.ee.ej.iJ, en, this.ee.adUnitId, false, en.is.fM);
        }
        if (en.errorCode != -2) {
            this.a(en.errorCode);
            return;
        }
        if (!this.ee.em.eG && !this.b(en)) {
            this.a(0);
            return;
        }
        if (this.ee.en != null && this.ee.en.ge != null) {
            this.ee.en.ge.a((av)null);
        }
        if (en.ge != null) {
            en.ge.a(this);
        }
        this.ee.en = en;
        if (en.it != null) {
            this.ee.em = en.it;
        }
        if (!this.ee.em.eG) {
            this.b(false);
        }
        this.L();
    }
    
    public void a(final x em) {
        eg.N("setAdSize must be called on the main UI thread.");
        this.ee.em = em;
        if (this.ee.en != null) {
            this.ee.en.gJ.a(em);
        }
        if (this.ee.eg.getChildCount() > 1) {
            this.ee.eg.removeView(this.ee.eg.getNextView());
        }
        this.ee.eg.setMinimumWidth(em.widthPixels);
        this.ee.eg.setMinimumHeight(em.heightPixels);
        this.ee.eg.requestLayout();
    }
    
    public boolean a(final v v) {
        eg.N("loadAd must be called on the main UI thread.");
        if (this.ee.el != null) {
            ct.v("An ad request is already in progress. Aborting.");
        }
        else {
            if (this.ee.em.eG && this.ee.en != null) {
                ct.v("An interstitial is already loading. Aborting.");
                return false;
            }
            if (this.M()) {
                ct.t("Starting ad request.");
                this.ef.cancel();
                final bz.a c = this.c(v);
                Object o;
                if (this.ee.em.eG) {
                    o = cw.a(this.ee.eh, this.ee.em, false, false, this.ee.ei, this.ee.ej);
                    ((cw)o).aC().a(this, null, this, this, true);
                }
                else {
                    final View nextView = this.ee.eg.getNextView();
                    if (nextView instanceof cw) {
                        o = nextView;
                        ((cw)o).a(this.ee.eh, this.ee.em);
                    }
                    else {
                        if (nextView != null) {
                            this.ee.eg.removeView(nextView);
                        }
                        final cw cw = (cw)(o = com.google.android.gms.internal.cw.a(this.ee.eh, this.ee.em, 0 != 0, 0 != 0, this.ee.ei, this.ee.ej));
                        if (this.ee.em.eH == null) {
                            this.b((View)cw);
                            o = cw;
                        }
                    }
                    ((cw)o).aC().a(this, this, this, this, false);
                }
                this.ee.el = bu.a(this.ee.eh, c, this.ee.ei, (cw)o, this.ed, (bu.a)this);
                return true;
            }
        }
        return false;
    }
    
    public void b(final v v) {
        final ViewParent parent = this.ee.eg.getParent();
        if (parent instanceof View && ((View)parent).isShown() && co.at()) {
            this.a(v);
            return;
        }
        ct.t("Ad is not visible. Not refreshing ad.");
        this.ef.d(v);
    }
    
    public void destroy() {
        eg.N("destroy must be called on the main UI thread.");
        this.ee.ek = null;
        this.ee.eo = null;
        this.ef.cancel();
        this.stopLoading();
        if (this.ee.eg != null) {
            this.ee.eg.removeAllViews();
        }
        if (this.ee.en != null && this.ee.en.gJ != null) {
            this.ee.en.gJ.destroy();
        }
    }
    
    public boolean isReady() {
        eg.N("isLoaded must be called on the main UI thread.");
        return this.ee.el == null && this.ee.en != null;
    }
    
    @Override
    public void onAppEvent(final String s, final String s2) {
        if (this.ee.eo == null) {
            return;
        }
        try {
            this.ee.eo.onAppEvent(s, s2);
        }
        catch (RemoteException ex) {
            ct.b("Could not call the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void pause() {
        eg.N("pause must be called on the main UI thread.");
        if (this.ee.en != null) {
            co.a(this.ee.en.gJ);
        }
    }
    
    public void resume() {
        eg.N("resume must be called on the main UI thread.");
        if (this.ee.en != null) {
            co.b(this.ee.en.gJ);
        }
    }
    
    public void showInterstitial() {
        eg.N("showInterstitial must be called on the main UI thread.");
        if (!this.ee.em.eG) {
            ct.v("Cannot call showInterstitial on a banner ad.");
            return;
        }
        if (this.ee.en == null) {
            ct.v("The interstitial has not loaded.");
            return;
        }
        if (this.ee.en.gJ.aF()) {
            ct.v("The interstitial is already showing.");
            return;
        }
        this.ee.en.gJ.l(true);
        if (this.ee.en.hy) {
            try {
                this.ee.en.gc.showInterstitial();
                return;
            }
            catch (RemoteException ex) {
                ct.b("Could not show interstitial.", (Throwable)ex);
                this.O();
                return;
            }
        }
        bk.a(this.ee.eh, new bm(this, this, this, this.ee.en.gJ, this.ee.en.orientation, this.ee.ej));
    }
    
    public void stopLoading() {
        eg.N("stopLoading must be called on the main UI thread.");
        if (this.ee.en != null) {
            this.ee.en.gJ.stopLoading();
            this.ee.en = null;
        }
        if (this.ee.el != null) {
            this.ee.el.cancel();
        }
    }
    
    @Override
    public void w() {
        this.N();
    }
    
    public b x() {
        eg.N("getAdFrame must be called on the main UI thread.");
        return c.h(this.ee.eg);
    }
    
    public x y() {
        eg.N("getAdSize must be called on the main UI thread.");
        return this.ee.em;
    }
    
    @Override
    public void z() {
        this.J();
    }
    
    private static final class a
    {
        public final String adUnitId;
        public final ViewSwitcher eg;
        public final Context eh;
        public final h ei;
        public final cu ej;
        public ab ek;
        public cm el;
        public x em;
        public cj en;
        public ae eo;
        
        public a(final Context eh, final x em, final String adUnitId, final cu ej) {
            if (em.eG) {
                this.eg = null;
            }
            else {
                (this.eg = new ViewSwitcher(eh)).setMinimumWidth(em.widthPixels);
                this.eg.setMinimumHeight(em.heightPixels);
                this.eg.setVisibility(4);
            }
            this.em = em;
            this.adUnitId = adUnitId;
            this.eh = eh;
            this.ei = new h(g.a(ej.iJ, eh));
            this.ej = ej;
        }
    }
}
