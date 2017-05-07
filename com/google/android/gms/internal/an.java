// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import android.content.Context;
import android.os.Process;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.os.PowerManager;
import android.app.KeyguardManager;
import android.app.ActivityManager;
import android.webkit.WebView;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;

@ez
public class an extends Thread
{
    private boolean mStarted;
    private final Object mw;
    private final int nf;
    private final int nh;
    private boolean ns;
    private boolean nt;
    private final am nu;
    private final al nv;
    private final ey nw;
    private final int nx;
    private final int ny;
    private final int nz;
    
    public an(final am nu, final al nv, final Bundle bundle, final ey nw) {
        this.mStarted = false;
        this.ns = false;
        this.nt = false;
        this.nu = nu;
        this.nv = nv;
        this.nw = nw;
        this.mw = new Object();
        this.nf = bundle.getInt(bn.pe.getKey());
        this.ny = bundle.getInt(bn.pf.getKey());
        this.nh = bundle.getInt(bn.pg.getKey());
        this.nz = bundle.getInt(bn.ph.getKey());
        this.nx = bundle.getInt(bn.pi.getKey(), 10);
        this.setName("ContentFetchTask");
    }
    
    private void a(final Activity activity) {
        if (activity != null) {
            View viewById;
            final View view = viewById = null;
            if (activity.getWindow() != null) {
                viewById = view;
                if (activity.getWindow().getDecorView() != null) {
                    viewById = activity.getWindow().getDecorView().findViewById(16908290);
                }
            }
            if (viewById != null) {
                this.g(viewById);
            }
        }
    }
    
    private boolean a(final WebView webView, final ak ak) {
        if (!kc.hI()) {
            return false;
        }
        ak.aR();
        webView.post((Runnable)new an$2(this, ak, webView));
        return true;
    }
    
    private boolean aW() {
        try {
            final Context context = this.nu.getContext();
            if (context == null) {
                return false;
            }
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
            final PowerManager powerManager = (PowerManager)context.getSystemService("power");
            if (activityManager != null && keyguardManager != null) {
                if (powerManager != null) {
                    final List runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                        if (Process.myPid() == activityManager$RunningAppProcessInfo.pid) {
                            if (activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                                return true;
                            }
                            break;
                        }
                    }
                    return false;
                }
            }
        }
        catch (Throwable t) {
            return false;
        }
        return false;
    }
    
    an$a a(final View view, final ak ak) {
        int i = 0;
        if (view == null) {
            return new an$a(this, 0, 0);
        }
        if (view instanceof TextView && !(view instanceof EditText)) {
            ak.i(((TextView)view).getText().toString());
            return new an$a(this, 1, 0);
        }
        if (view instanceof WebView && !(view instanceof gv)) {
            ak.aR();
            if (this.a((WebView)view, ak)) {
                return new an$a(this, 0, 1);
            }
            return new an$a(this, 0, 0);
        }
        else {
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                int n = 0;
                int n2 = 0;
                while (i < viewGroup.getChildCount()) {
                    final an$a a = this.a(viewGroup.getChildAt(i), ak);
                    n2 += a.nG;
                    n += a.nH;
                    ++i;
                }
                return new an$a(this, n2, n);
            }
            return new an$a(this, 0, 0);
        }
    }
    
    void a(final ak ak, final WebView webView, String optString) {
        ak.aQ();
        try {
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                optString = new JSONObject(optString).optString("text");
                if (!TextUtils.isEmpty((CharSequence)webView.getTitle())) {
                    ak.h(webView.getTitle() + "\n" + optString);
                }
                else {
                    ak.h(optString);
                }
            }
            if (ak.aN()) {
                this.nv.b(ak);
            }
        }
        catch (JSONException ex) {
            gs.S("Json string may be malformed.");
        }
        catch (Throwable t) {
            gs.a("Failed to get webview content.", t);
            this.nw.b(t);
        }
    }
    
    public void aV() {
        synchronized (this.mw) {
            if (this.mStarted) {
                gs.S("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            // monitorexit(this.mw)
            this.start();
        }
    }
    
    public ak aX() {
        return this.nv.aU();
    }
    
    public void aY() {
        synchronized (this.mw) {
            this.ns = true;
            gs.S("ContentFetchThread: paused, mPause = " + this.ns);
        }
    }
    
    public boolean aZ() {
        return this.ns;
    }
    
    boolean g(final View view) {
        if (view == null) {
            return false;
        }
        view.post((Runnable)new an$1(this, view));
        return true;
    }
    
    void h(final View view) {
        try {
            final ak ak = new ak(this.nf, this.ny, this.nh, this.nz);
            final an$a a = this.a(view, ak);
            ak.aS();
            if (a.nG == 0 && a.nH == 0) {
                return;
            }
            if ((a.nH != 0 || ak.aT() != 0) && (a.nH != 0 || !this.nv.a(ak))) {
                this.nv.c(ak);
            }
        }
        catch (Exception ex) {
            gs.b("Exception in fetchContentOnUIThread", ex);
            this.nw.b(ex);
        }
    }
    
    @Override
    public void run() {
    Label_0051_Outer:
        while (!this.nt) {
            Label_0087: {
                try {
                    if (!this.aW()) {
                        break Label_0087;
                    }
                    final Object o = this.nu.getActivity();
                    if (o == null) {
                        gs.S("ContentFetchThread: no activity");
                        continue Label_0051_Outer;
                    }
                    break Label_0087;
                }
                catch (Throwable t) {
                    gs.b("Error in ContentFetchTask", t);
                    this.nw.b(t);
                }
                while (true) {
                    final Object o = this.mw;
                    synchronized (o) {
                        while (this.ns) {
                            try {
                                gs.S("ContentFetchTask: waiting");
                                this.mw.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        continue Label_0051_Outer;
                        gs.S("ContentFetchTask: sleeping");
                        this.aY();
                        Label_0092: {
                            break Label_0092;
                            this.a((Activity)o);
                        }
                        Thread.sleep(this.nx * 1000);
                        continue;
                    }
                    break;
                }
            }
            break;
        }
    }
    
    public void wakeup() {
        synchronized (this.mw) {
            this.ns = false;
            this.mw.notifyAll();
            gs.S("ContentFetchThread: wakeup");
        }
    }
}
