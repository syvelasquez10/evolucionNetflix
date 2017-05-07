// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.widget.ViewSwitcher;
import android.view.Window;
import android.graphics.Rect;
import android.app.Activity;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.HashSet;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;
import android.net.Uri;
import android.view.View$OnClickListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import java.util.List;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.dynamic.d;
import android.view.ViewGroup$LayoutParams;
import android.os.RemoteException;
import android.view.View;
import android.util.DisplayMetrics;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.content.Context;
import android.content.ComponentCallbacks;

@ez
public class u extends bd.a implements aa, bw, bz, cb, cn, dn, dq, fa.a, fd.a, gd, t
{
    private av lp;
    private final ct lq;
    private final b lr;
    private final ab ls;
    private final ae lt;
    private boolean lu;
    private final ComponentCallbacks lv;
    
    public u(final Context context, final ay ay, final String s, final ct ct, final gt gt) {
        this(new b(context, ay, s, gt), ct, null);
    }
    
    u(final b lr, final ct lq, ab ls) {
        this.lv = (ComponentCallbacks)new ComponentCallbacks() {
            public void onConfigurationChanged(final Configuration configuration) {
                if (u.this.lr != null && u.this.lr.lI != null && u.this.lr.lI.rN != null) {
                    u.this.lr.lI.rN.bT();
                }
            }
            
            public void onLowMemory() {
            }
        };
        this.lr = lr;
        this.lq = lq;
        if (ls == null) {
            ls = new ab(this);
        }
        this.ls = ls;
        this.lt = new ae();
        gj.q(this.lr.lB);
        gb.a(this.lr.lB, this.lr.lD);
        this.Z();
    }
    
    private void Z() {
        if (Build$VERSION.SDK_INT >= 14 && this.lr != null && this.lr.lB != null) {
            this.lr.lB.registerComponentCallbacks(this.lv);
        }
    }
    
    private fi.a a(final av av, final Bundle bundle) {
        final ApplicationInfo applicationInfo = this.lr.lB.getApplicationInfo();
        while (true) {
            try {
                final PackageInfo packageInfo = this.lr.lB.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
                Bundle bundle3;
                final Bundle bundle2 = bundle3 = null;
                if (!this.lr.lH.og) {
                    bundle3 = bundle2;
                    if (this.lr.lz.getParent() != null) {
                        final int[] array = new int[2];
                        this.lr.lz.getLocationOnScreen(array);
                        final int n = array[0];
                        final int n2 = array[1];
                        final DisplayMetrics displayMetrics = this.lr.lB.getResources().getDisplayMetrics();
                        final int width = this.lr.lz.getWidth();
                        final int height = this.lr.lz.getHeight();
                        int n4;
                        final int n3 = n4 = 0;
                        if (this.lr.lz.isShown()) {
                            n4 = n3;
                            if (n + width > 0) {
                                n4 = n3;
                                if (n2 + height > 0) {
                                    n4 = n3;
                                    if (n <= displayMetrics.widthPixels) {
                                        n4 = n3;
                                        if (n2 <= displayMetrics.heightPixels) {
                                            n4 = 1;
                                        }
                                    }
                                }
                            }
                        }
                        bundle3 = new Bundle(5);
                        bundle3.putInt("x", n);
                        bundle3.putInt("y", n2);
                        bundle3.putInt("width", width);
                        bundle3.putInt("height", height);
                        bundle3.putInt("visible", n4);
                    }
                }
                final String cx = gb.cX();
                (this.lr.lK = new ga(cx, this.lr.lA)).e(av);
                return new fi.a(bundle3, av, this.lr.lH, this.lr.lA, applicationInfo, packageInfo, cx, gb.vK, this.lr.lD, gb.a(this.lr.lB, this, cx), this.lr.lS, bundle, gb.dd());
            }
            catch (PackageManager$NameNotFoundException ex) {
                final PackageInfo packageInfo = null;
                continue;
            }
            break;
        }
    }
    
    private gv a(final v v) {
        if (this.lr.lH.og) {
            final gv a = gv.a(this.lr.lB, this.lr.lH, false, false, this.lr.lC, this.lr.lD);
            a.dv().a(this, null, this, this, true, this, this, v);
            return a;
        }
        final View nextView = this.lr.lz.getNextView();
        Object a2;
        if (nextView instanceof gv) {
            a2 = nextView;
            ((gv)a2).a(this.lr.lB, this.lr.lH);
        }
        else {
            if (nextView != null) {
                this.lr.lz.removeView(nextView);
            }
            final gv gv = (gv)(a2 = com.google.android.gms.internal.gv.a(this.lr.lB, this.lr.lH, 0 != 0, 0 != 0, this.lr.lC, this.lr.lD));
            if (this.lr.lH.oh == null) {
                this.c((View)gv);
                a2 = gv;
            }
        }
        ((gv)a2).dv().a(this, this, this, this, false, this, v);
        return (gv)a2;
    }
    
    private void a(final int n) {
        gs.W("Failed to load ad: " + n);
        if (this.lr.lE == null) {
            return;
        }
        try {
            this.lr.lE.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            gs.d("Could not call AdListener.onAdFailedToLoad().", (Throwable)ex);
        }
    }
    
    private void aa() {
        if (Build$VERSION.SDK_INT >= 14 && this.lr != null && this.lr.lB != null) {
            this.lr.lB.unregisterComponentCallbacks(this.lv);
        }
    }
    
    private void ak() {
        gs.U("Ad closing.");
        if (this.lr.lE == null) {
            return;
        }
        try {
            this.lr.lE.onAdClosed();
        }
        catch (RemoteException ex) {
            gs.d("Could not call AdListener.onAdClosed().", (Throwable)ex);
        }
    }
    
    private void al() {
        gs.U("Ad leaving application.");
        if (this.lr.lE == null) {
            return;
        }
        try {
            this.lr.lE.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            gs.d("Could not call AdListener.onAdLeftApplication().", (Throwable)ex);
        }
    }
    
    private void am() {
        gs.U("Ad opening.");
        if (this.lr.lE == null) {
            return;
        }
        try {
            this.lr.lE.onAdOpened();
        }
        catch (RemoteException ex) {
            gs.d("Could not call AdListener.onAdOpened().", (Throwable)ex);
        }
    }
    
    private void an() {
        gs.U("Ad finished loading.");
        if (this.lr.lE == null) {
            return;
        }
        try {
            this.lr.lE.onAdLoaded();
        }
        catch (RemoteException ex) {
            gs.d("Could not call AdListener.onAdLoaded().", (Throwable)ex);
        }
    }
    
    private void ao() {
        try {
            if (this.lr.lI.vu instanceof bo && this.lr.lQ != null) {
                this.lr.lQ.a((br)this.lr.lI.vu);
            }
        }
        catch (RemoteException ex) {
            gs.d("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", (Throwable)ex);
        }
    }
    
    private void ap() {
        try {
            if (this.lr.lI.vu instanceof bp && this.lr.lR != null) {
                this.lr.lR.a((bs)this.lr.lI.vu);
            }
        }
        catch (RemoteException ex) {
            gs.d("Could not call OnContentAdLoadedListener.onContentAdLoaded().", (Throwable)ex);
        }
    }
    
    private void at() {
        if (this.lr.lI != null) {
            if (this.lr.lW == 0) {
                this.lr.lI.rN.destroy();
            }
            this.lr.lI = null;
            this.lr.lX = false;
        }
    }
    
    private boolean b(final fz p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        com/google/android/gms/internal/fz.tI:Z
        //     4: ifeq            188
        //     7: aload_1        
        //     8: getfield        com/google/android/gms/internal/fz.qz:Lcom/google/android/gms/internal/cu;
        //    11: invokeinterface com/google/android/gms/internal/cu.getView:()Lcom/google/android/gms/dynamic/d;
        //    16: invokestatic    com/google/android/gms/dynamic/e.f:(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
        //    19: checkcast       Landroid/view/View;
        //    22: astore_1       
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //    27: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //    30: invokevirtual   com/google/android/gms/internal/u$a.getNextView:()Landroid/view/View;
        //    33: astore_2       
        //    34: aload_2        
        //    35: ifnull          49
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //    42: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //    45: aload_2        
        //    46: invokevirtual   com/google/android/gms/internal/u$a.removeView:(Landroid/view/View;)V
        //    49: aload_0        
        //    50: aload_1        
        //    51: invokespecial   com/google/android/gms/internal/u.c:(Landroid/view/View;)V
        //    54: aload_0        
        //    55: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //    58: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //    61: invokevirtual   com/google/android/gms/internal/u$a.getChildCount:()I
        //    64: iconst_1       
        //    65: if_icmple       78
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //    72: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //    75: invokevirtual   com/google/android/gms/internal/u$a.showNext:()V
        //    78: aload_0        
        //    79: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //    82: getfield        com/google/android/gms/internal/u$b.lI:Lcom/google/android/gms/internal/fz;
        //    85: ifnull          155
        //    88: aload_0        
        //    89: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //    92: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //    95: invokevirtual   com/google/android/gms/internal/u$a.getNextView:()Landroid/view/View;
        //    98: astore_1       
        //    99: aload_1        
        //   100: instanceof      Lcom/google/android/gms/internal/gv;
        //   103: ifeq            261
        //   106: aload_1        
        //   107: checkcast       Lcom/google/android/gms/internal/gv;
        //   110: aload_0        
        //   111: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   114: getfield        com/google/android/gms/internal/u$b.lB:Landroid/content/Context;
        //   117: aload_0        
        //   118: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   121: getfield        com/google/android/gms/internal/u$b.lH:Lcom/google/android/gms/internal/ay;
        //   124: invokevirtual   com/google/android/gms/internal/gv.a:(Landroid/content/Context;Lcom/google/android/gms/internal/ay;)V
        //   127: aload_0        
        //   128: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   131: getfield        com/google/android/gms/internal/u$b.lI:Lcom/google/android/gms/internal/fz;
        //   134: getfield        com/google/android/gms/internal/fz.qz:Lcom/google/android/gms/internal/cu;
        //   137: ifnull          155
        //   140: aload_0        
        //   141: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   144: getfield        com/google/android/gms/internal/u$b.lI:Lcom/google/android/gms/internal/fz;
        //   147: getfield        com/google/android/gms/internal/fz.qz:Lcom/google/android/gms/internal/cu;
        //   150: invokeinterface com/google/android/gms/internal/cu.destroy:()V
        //   155: aload_0        
        //   156: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   159: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //   162: iconst_0       
        //   163: invokevirtual   com/google/android/gms/internal/u$a.setVisibility:(I)V
        //   166: iconst_1       
        //   167: ireturn        
        //   168: astore_1       
        //   169: ldc_w           "Could not get View from mediation adapter."
        //   172: aload_1        
        //   173: invokestatic    com/google/android/gms/internal/gs.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   176: iconst_0       
        //   177: ireturn        
        //   178: astore_1       
        //   179: ldc_w           "Could not add mediation view to view hierarchy."
        //   182: aload_1        
        //   183: invokestatic    com/google/android/gms/internal/gs.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   186: iconst_0       
        //   187: ireturn        
        //   188: aload_1        
        //   189: getfield        com/google/android/gms/internal/fz.vr:Lcom/google/android/gms/internal/ay;
        //   192: ifnull          54
        //   195: aload_1        
        //   196: getfield        com/google/android/gms/internal/fz.rN:Lcom/google/android/gms/internal/gv;
        //   199: aload_1        
        //   200: getfield        com/google/android/gms/internal/fz.vr:Lcom/google/android/gms/internal/ay;
        //   203: invokevirtual   com/google/android/gms/internal/gv.a:(Lcom/google/android/gms/internal/ay;)V
        //   206: aload_0        
        //   207: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   210: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //   213: invokevirtual   com/google/android/gms/internal/u$a.removeAllViews:()V
        //   216: aload_0        
        //   217: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   220: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //   223: aload_1        
        //   224: getfield        com/google/android/gms/internal/fz.vr:Lcom/google/android/gms/internal/ay;
        //   227: getfield        com/google/android/gms/internal/ay.widthPixels:I
        //   230: invokevirtual   com/google/android/gms/internal/u$a.setMinimumWidth:(I)V
        //   233: aload_0        
        //   234: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   237: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //   240: aload_1        
        //   241: getfield        com/google/android/gms/internal/fz.vr:Lcom/google/android/gms/internal/ay;
        //   244: getfield        com/google/android/gms/internal/ay.heightPixels:I
        //   247: invokevirtual   com/google/android/gms/internal/u$a.setMinimumHeight:(I)V
        //   250: aload_0        
        //   251: aload_1        
        //   252: getfield        com/google/android/gms/internal/fz.rN:Lcom/google/android/gms/internal/gv;
        //   255: invokespecial   com/google/android/gms/internal/u.c:(Landroid/view/View;)V
        //   258: goto            54
        //   261: aload_1        
        //   262: ifnull          127
        //   265: aload_0        
        //   266: getfield        com/google/android/gms/internal/u.lr:Lcom/google/android/gms/internal/u$b;
        //   269: getfield        com/google/android/gms/internal/u$b.lz:Lcom/google/android/gms/internal/u$a;
        //   272: aload_1        
        //   273: invokevirtual   com/google/android/gms/internal/u$a.removeView:(Landroid/view/View;)V
        //   276: goto            127
        //   279: astore_1       
        //   280: ldc_w           "Could not destroy previous mediation adapter."
        //   283: invokestatic    com/google/android/gms/internal/gs.W:(Ljava/lang/String;)V
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
    
    private void c(final View view) {
        this.lr.lz.addView(view, new ViewGroup$LayoutParams(-2, -2));
    }
    
    private void c(final boolean b) {
        if (this.lr.lI == null) {
            gs.W("Ad state was null when trying to ping impression URLs.");
        }
        else {
            gs.S("Pinging Impression URLs.");
            this.lr.lK.cP();
            if (this.lr.lI.qg != null) {
                gj.a(this.lr.lB, this.lr.lD.wD, this.lr.lI.qg);
            }
            if (this.lr.lI.vq != null && this.lr.lI.vq.qg != null) {
                cr.a(this.lr.lB, this.lr.lD.wD, this.lr.lI, this.lr.lA, b, this.lr.lI.vq.qg);
            }
            if (this.lr.lI.qy != null && this.lr.lI.qy.qb != null) {
                cr.a(this.lr.lB, this.lr.lD.wD, this.lr.lI, this.lr.lA, b, this.lr.lI.qy.qb);
            }
        }
    }
    
    public d X() {
        n.aT("getAdFrame must be called on the main UI thread.");
        return e.k(this.lr.lz);
    }
    
    public ay Y() {
        n.aT("getAdSize must be called on the main UI thread.");
        return this.lr.lH;
    }
    
    Bundle a(final an an) {
        if (an != null) {
            if (an.aZ()) {
                an.wakeup();
            }
            final ak ax = an.aX();
            String ao;
            if (ax != null) {
                ao = ax.aO();
                gs.S("In AdManger: loadAd, " + ax.toString());
            }
            else {
                ao = null;
            }
            if (ao != null) {
                final Bundle bundle = new Bundle(1);
                bundle.putString("fingerprint", ao);
                return bundle;
            }
        }
        return null;
    }
    
    public void a(final ay lh) {
        n.aT("setAdSize must be called on the main UI thread.");
        this.lr.lH = lh;
        if (this.lr.lI != null && this.lr.lW == 0) {
            this.lr.lI.rN.a(lh);
        }
        if (this.lr.lz.getChildCount() > 1) {
            this.lr.lz.removeView(this.lr.lz.getNextView());
        }
        this.lr.lz.setMinimumWidth(lh.widthPixels);
        this.lr.lz.setMinimumHeight(lh.heightPixels);
        this.lr.lz.requestLayout();
    }
    
    public void a(final bc le) {
        n.aT("setAdListener must be called on the main UI thread.");
        this.lr.lE = le;
    }
    
    public void a(final bf ll) {
        n.aT("setAppEventListener must be called on the main UI thread.");
        this.lr.lL = ll;
    }
    
    public void a(final eh ln) {
        n.aT("setInAppPurchaseListener must be called on the main UI thread.");
        this.lr.lN = ln;
    }
    
    public void a(final el lm, final String s) {
        n.aT("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.lr.lT = new ee(s);
        this.lr.lM = lm;
        if (!gb.db() && lm != null) {
            new dx(this.lr.lB, this.lr.lM, this.lr.lT).start();
        }
    }
    
    public void a(final et lo) {
        n.aT("setRawHtmlPublisherAdViewListener must be called on the main UI thread.");
        this.lr.lO = lo;
    }
    
    public void a(final eu lp) {
        n.aT("setRawHtmlPublisherInterstitialAdListener must be called on the main UI thread.");
        this.lr.lP = lp;
    }
    
    @Override
    public void a(final fz.a lj) {
        String string = null;
        this.lr.lF = null;
        this.lr.lJ = lj;
        this.a((List<String>)null);
        gv a;
        if (!lj.vw.tS) {
            final v v = new v();
            a = this.a(v);
            v.a((v.a)new v.b(lj, a));
            a.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    v.ar();
                    return false;
                }
            });
            a.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    v.ar();
                }
            });
        }
        else {
            a = null;
        }
        if (lj.lH != null) {
            this.lr.lH = lj.lH;
        }
        if (lj.errorCode != -2) {
            this.a(new fz(lj, a, null, null, null, null, null));
            return;
        }
        if (!lj.vw.tI && lj.vw.tR) {
            if (lj.vw.rP != null) {
                string = Uri.parse(lj.vw.rP).buildUpon().query((String)null).build().toString();
            }
            final er er = new er(this, string, lj.vw.tG);
            try {
                if (this.lr.lO != null && !this.lr.lH.og && this.lr.lO.e(string, lj.vw.tG)) {
                    this.lr.lW = 1;
                    this.lr.lO.a(er);
                    return;
                }
            }
            catch (RemoteException ex) {
                gs.d("Could not call the rawHtmlPublisherAdViewListener.", (Throwable)ex);
            }
            try {
                if (this.lr.lP != null && this.lr.lH.og && this.lr.lP.e(string, lj.vw.tG)) {
                    this.lr.lW = 1;
                    this.lr.lP.a(er);
                    return;
                }
            }
            catch (RemoteException ex2) {
                gs.d("Could not call the RawHtmlPublisherInterstitialAdListener.", (Throwable)ex2);
            }
        }
        this.lr.lW = 0;
        this.lr.lG = fd.a(this.lr.lB, this, lj, a, this.lq, (fd.a)this);
    }
    
    @Override
    public void a(final fz li) {
        this.lr.lG = null;
        final boolean b = li.vu != null;
        if (li.errorCode != -2 && li.errorCode != 3) {
            gb.b(this.lr.au());
        }
        if (li.errorCode != -1) {
            if (this.a(li, b)) {
                gs.S("Ad refresh scheduled.");
            }
            if (li.errorCode == 3 && li.vq != null && li.vq.qh != null) {
                gs.S("Pinging no fill URLs.");
                cr.a(this.lr.lB, this.lr.lD.wD, li, this.lr.lA, false, li.vq.qh);
            }
            if (li.errorCode != -2) {
                this.a(li.errorCode);
                return;
            }
            if (!this.lr.lH.og && !b && this.lr.lW == 0) {
                if (!this.b(li)) {
                    this.a(0);
                    return;
                }
                if (this.lr.lz != null) {
                    this.lr.lz.ly.Q(li.tN);
                }
            }
            if (this.lr.lI != null && this.lr.lI.qB != null) {
                this.lr.lI.qB.a((cn)null);
            }
            if (li.qB != null) {
                li.qB.a(this);
            }
            this.lt.d(this.lr.lI);
            this.lr.lI = li;
            this.lr.lK.j(li.vs);
            this.lr.lK.k(li.vt);
            this.lr.lK.t(this.lr.lH.og);
            this.lr.lK.u(li.tI);
            if (!this.lr.lH.og && !b && this.lr.lW == 0) {
                this.c(false);
            }
            if (this.lr.lU == null) {
                this.lr.lU = new ge(this.lr.lA);
            }
            int qk;
            int ql;
            if (li.vq != null) {
                qk = li.vq.qk;
                ql = li.vq.ql;
            }
            else {
                ql = 0;
                qk = 0;
            }
            this.lr.lU.d(qk, ql);
            if (this.lr.lW == 0) {
                if (!this.lr.lH.og && li.rN != null && (li.rN.dv().dF() || li.vp != null)) {
                    final af a = this.lt.a(this.lr.lH, this.lr.lI);
                    if (li.rN.dv().dF() && a != null) {
                        a.a(new z(li.rN));
                    }
                }
                if (this.lr.lI.rN != null) {
                    this.lr.lI.rN.bT();
                    this.lr.lI.rN.dv().dG();
                }
                if (b) {
                    final bq.a vu = li.vu;
                    if (vu instanceof bp && this.lr.lR != null) {
                        this.ap();
                    }
                    else {
                        if (!(vu instanceof bo) || this.lr.lQ == null) {
                            gs.W("No matching listener for retrieved native ad template.");
                            this.a(0);
                            return;
                        }
                        this.ao();
                    }
                }
                this.an();
                return;
            }
            if (this.lr.lV != null && li.vp != null) {
                this.lt.a(this.lr.lB, this.lr.lH, this.lr.lI, this.lr.lV, this.lr.lD);
            }
        }
    }
    
    @Override
    public void a(final String s, ArrayList<String> dy) {
        dy = new dy(s, (ArrayList<String>)dy, this.lr.lB, this.lr.lD.wD);
        Block_6: {
            if (this.lr.lN == null) {
                gs.W("InAppPurchaseListener is not set. Try to launch default purchase flow.");
                if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.lr.lB) != 0) {
                    gs.W("Google Play Service unavailable, cannot launch default purchase flow.");
                }
                else {
                    if (this.lr.lM == null) {
                        gs.W("PlayStorePurchaseListener is not set.");
                        return;
                    }
                    if (this.lr.lT == null) {
                        gs.W("PlayStorePurchaseVerifier is not initialized.");
                        return;
                    }
                    while (true) {
                        try {
                            if (this.lr.lM.isValidPurchase(s)) {
                                dz.a(this.lr.lB, this.lr.lD.wG, new dv(dy, this.lr.lM, this.lr.lT, this.lr.lB));
                            }
                            return;
                        }
                        catch (RemoteException ex) {
                            gs.W("Could not start In-App purchase.");
                            continue;
                        }
                        break;
                    }
                    break Block_6;
                }
                return;
            }
        }
        try {
            this.lr.lN.a(dy);
        }
        catch (RemoteException ex2) {
            gs.W("Could not start In-App purchase.");
        }
    }
    
    @Override
    public void a(final HashSet<ga> set) {
        this.lr.a(set);
    }
    
    public void a(final List<String> ls) {
        n.aT("setNativeTemplates must be called on the main UI thread.");
        this.lr.lS = ls;
    }
    
    public boolean a(final av lp) {
        n.aT("loadAd must be called on the main UI thread.");
        if (this.lr.lF != null || this.lr.lG != null) {
            if (this.lp != null) {
                gs.W("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
            }
            this.lp = lp;
        }
        else {
            if (this.lr.lH.og && this.lr.lI != null) {
                gs.W("An interstitial is already loading. Aborting.");
                return false;
            }
            if (this.aq()) {
                gs.U("Starting ad request.");
                if (!lp.nW) {
                    gs.U("Use AdRequest.Builder.addTestDevice(\"" + gr.v(this.lr.lB) + "\") to get test ads on this device.");
                }
                final Bundle a = this.a(gb.cV().l(this.lr.lB));
                this.ls.cancel();
                this.lr.lW = 0;
                this.lr.lF = fa.a(this.lr.lB, this.a(lp, a), this.lr.lC, (fa.a)this);
                return true;
            }
        }
        return false;
    }
    
    boolean a(final fz fz, final boolean b) {
        boolean boolean1 = false;
        av av;
        if (this.lp != null) {
            av = this.lp;
            this.lp = null;
        }
        else {
            final av av2 = av = fz.tx;
            if (av2.extras != null) {
                boolean1 = av2.extras.getBoolean("_noRefresh", false);
                av = av2;
            }
        }
        if (this.lr.lH.og) {
            if (this.lr.lW == 0) {
                gj.a(fz.rN);
            }
        }
        else if (!(boolean1 | b) && this.lr.lW == 0) {
            if (fz.qj > 0L) {
                this.ls.a(av, fz.qj);
            }
            else if (fz.vq != null && fz.vq.qj > 0L) {
                this.ls.a(av, fz.vq.qj);
            }
            else if (!fz.tI && fz.errorCode == 2) {
                this.ls.c(av);
            }
        }
        return this.ls.ay();
    }
    
    @Override
    public void ab() {
        this.al();
    }
    
    @Override
    public void ac() {
        this.lt.d(this.lr.lI);
        if (this.lr.lH.og) {
            this.at();
        }
        this.lu = false;
        this.ak();
        this.lr.lK.cR();
    }
    
    @Override
    public void ad() {
        if (this.lr.lH.og) {
            this.c(false);
        }
        this.lu = true;
        this.am();
    }
    
    @Override
    public void ae() {
        this.onAdClicked();
    }
    
    @Override
    public void af() {
        this.ac();
    }
    
    @Override
    public void ag() {
        this.ab();
    }
    
    @Override
    public void ah() {
        this.ad();
    }
    
    @Override
    public void ai() {
        if (this.lr.lI != null) {
            gs.W("Mediation adapter " + this.lr.lI.qA + " refreshed, but mediation adapters should never refresh.");
        }
        this.c(true);
        this.an();
    }
    
    public void aj() {
        n.aT("recordManualImpression must be called on the main UI thread.");
        if (this.lr.lI == null) {
            gs.W("Ad state was null when trying to ping manual tracking URLs.");
        }
        else {
            gs.S("Pinging manual tracking URLs.");
            if (this.lr.lI.tK != null) {
                gj.a(this.lr.lB, this.lr.lD.wD, this.lr.lI.tK);
            }
        }
    }
    
    public boolean aq() {
        boolean b = true;
        if (!gj.a(this.lr.lB.getPackageManager(), this.lr.lB.getPackageName(), "android.permission.INTERNET")) {
            if (!this.lr.lH.og) {
                gr.a((ViewGroup)this.lr.lz, this.lr.lH, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            b = false;
        }
        if (!gj.p(this.lr.lB)) {
            if (!this.lr.lH.og) {
                gr.a((ViewGroup)this.lr.lz, this.lr.lH, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            b = false;
        }
        if (!b && !this.lr.lH.og) {
            this.lr.lz.setVisibility(0);
        }
        return b;
    }
    
    @Override
    public void ar() {
        if (this.lr.lI == null) {
            gs.W("Ad state was null when trying to ping click URLs.");
        }
        else {
            gs.S("Pinging click URLs.");
            this.lr.lK.cQ();
            if (this.lr.lI.qf != null) {
                gj.a(this.lr.lB, this.lr.lD.wD, this.lr.lI.qf);
            }
            if (this.lr.lI.vq != null && this.lr.lI.vq.qf != null) {
                cr.a(this.lr.lB, this.lr.lD.wD, this.lr.lI, this.lr.lA, false, this.lr.lI.vq.qf);
            }
        }
    }
    
    @Override
    public void as() {
        this.c(false);
    }
    
    @Override
    public void b(final View lv) {
        this.lr.lV = lv;
        this.a(new fz(this.lr.lJ, null, null, null, null, null, null));
    }
    
    public void b(final av av) {
        final ViewParent parent = this.lr.lz.getParent();
        if (parent instanceof View && ((View)parent).isShown() && gj.dl() && !this.lu) {
            this.a(av);
            return;
        }
        gs.U("Ad is not visible. Not refreshing ad.");
        this.ls.c(av);
    }
    
    @Override
    public void b(final boolean lx) {
        this.lr.lX = lx;
    }
    
    public void destroy() {
        n.aT("destroy must be called on the main UI thread.");
        this.aa();
        this.lr.lE = null;
        this.lr.lL = null;
        this.lr.lM = null;
        this.lr.lN = null;
        this.lr.lO = null;
        this.lr.lP = null;
        this.ls.cancel();
        this.lt.stop();
        this.stopLoading();
        if (this.lr.lz != null) {
            this.lr.lz.removeAllViews();
        }
        if (this.lr.lI != null && this.lr.lI.rN != null) {
            this.lr.lI.rN.destroy();
        }
        if (this.lr.lI == null || this.lr.lI.qz == null) {
            return;
        }
        try {
            this.lr.lI.qz.destroy();
        }
        catch (RemoteException ex) {
            gs.W("Could not destroy mediation adapter.");
        }
    }
    
    public String getMediationAdapterClassName() {
        if (this.lr.lI != null) {
            return this.lr.lI.qA;
        }
        return null;
    }
    
    public boolean isReady() {
        n.aT("isLoaded must be called on the main UI thread.");
        return this.lr.lF == null && this.lr.lG == null && this.lr.lI != null;
    }
    
    @Override
    public void onAdClicked() {
        this.ar();
    }
    
    @Override
    public void onAppEvent(final String s, final String s2) {
        if (this.lr.lL == null) {
            return;
        }
        try {
            this.lr.lL.onAppEvent(s, s2);
        }
        catch (RemoteException ex) {
            gs.d("Could not call the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void pause() {
        n.aT("pause must be called on the main UI thread.");
        if (this.lr.lI != null && this.lr.lW == 0) {
            gj.a(this.lr.lI.rN);
        }
        while (true) {
            if (this.lr.lI == null || this.lr.lI.qz == null) {
                break Label_0077;
            }
            try {
                this.lr.lI.qz.pause();
                this.lt.pause();
                this.ls.pause();
            }
            catch (RemoteException ex) {
                gs.W("Could not pause mediation adapter.");
                continue;
            }
            break;
        }
    }
    
    public void resume() {
        n.aT("resume must be called on the main UI thread.");
        if (this.lr.lI != null && this.lr.lW == 0) {
            gj.b(this.lr.lI.rN);
        }
        while (true) {
            if (this.lr.lI == null || this.lr.lI.qz == null) {
                break Label_0077;
            }
            try {
                this.lr.lI.qz.resume();
                this.ls.resume();
                this.lt.resume();
            }
            catch (RemoteException ex) {
                gs.W("Could not resume mediation adapter.");
                continue;
            }
            break;
        }
    }
    
    public void showInterstitial() {
        n.aT("showInterstitial must be called on the main UI thread.");
        if (!this.lr.lH.og) {
            gs.W("Cannot call showInterstitial on a banner ad.");
        }
        else {
            if (this.lr.lI == null) {
                gs.W("The interstitial has not loaded.");
                return;
            }
            if (this.lr.lW != 1) {
                if (this.lr.lI.rN.dz()) {
                    gs.W("The interstitial is already showing.");
                    return;
                }
                this.lr.lI.rN.x(true);
                if (this.lr.lI.rN.dv().dF() || this.lr.lI.vp != null) {
                    final af a = this.lt.a(this.lr.lH, this.lr.lI);
                    if (this.lr.lI.rN.dv().dF() && a != null) {
                        a.a(new z(this.lr.lI.rN));
                    }
                }
                if (this.lr.lI.tI) {
                    try {
                        this.lr.lI.qz.showInterstitial();
                        return;
                    }
                    catch (RemoteException ex) {
                        gs.d("Could not show interstitial.", (Throwable)ex);
                        this.at();
                        return;
                    }
                }
                x x2;
                final x x = x2 = new x(this.lr.lX, 0 != 0);
                if (this.lr.lB instanceof Activity) {
                    final Window window = ((Activity)this.lr.lB).getWindow();
                    final Rect rect = new Rect();
                    final Rect rect2 = new Rect();
                    window.getDecorView().getGlobalVisibleRect(rect);
                    window.getDecorView().getWindowVisibleDisplayFrame(rect2);
                    x2 = x;
                    if (rect.bottom != 0) {
                        x2 = x;
                        if (rect2.bottom != 0) {
                            x2 = new x(this.lr.lX, rect.top == rect2.top);
                        }
                    }
                }
                dk.a(this.lr.lB, new dm(this, this, this, this.lr.lI.rN, this.lr.lI.orientation, this.lr.lD, this.lr.lI.tN, x2));
            }
        }
    }
    
    public void stopLoading() {
        n.aT("stopLoading must be called on the main UI thread.");
        if (this.lr.lI != null && this.lr.lW == 0) {
            this.lr.lI.rN.stopLoading();
            this.lr.lI = null;
        }
        if (this.lr.lF != null) {
            this.lr.lF.cancel();
        }
        if (this.lr.lG != null) {
            this.lr.lG.cancel();
        }
    }
    
    @ez
    private static final class a extends ViewSwitcher
    {
        private final gm ly;
        
        public a(final Context context) {
            super(context);
            this.ly = new gm(context);
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            this.ly.c(motionEvent);
            return false;
        }
    }
    
    @ez
    static class b
    {
        public final String lA;
        public final Context lB;
        public final k lC;
        public final gt lD;
        public bc lE;
        public gg lF;
        public gg lG;
        public ay lH;
        public fz lI;
        public fz.a lJ;
        public ga lK;
        public bf lL;
        public el lM;
        public eh lN;
        public et lO;
        public eu lP;
        public bt lQ;
        public bu lR;
        public List<String> lS;
        public ee lT;
        public ge lU;
        public View lV;
        public int lW;
        public boolean lX;
        private HashSet<ga> lY;
        public final a lz;
        
        public b(final Context lb, final ay lh, final String la, final gt ld) {
            this.lU = null;
            this.lV = null;
            this.lW = 0;
            this.lX = false;
            this.lY = null;
            if (lh.og) {
                this.lz = null;
            }
            else {
                (this.lz = new a(lb)).setMinimumWidth(lh.widthPixels);
                this.lz.setMinimumHeight(lh.heightPixels);
                this.lz.setVisibility(4);
            }
            this.lH = lh;
            this.lA = la;
            this.lB = lb;
            this.lD = ld;
            this.lC = new k(new w(this));
        }
        
        public void a(final HashSet<ga> ly) {
            this.lY = ly;
        }
        
        public HashSet<ga> au() {
            return this.lY;
        }
    }
}
