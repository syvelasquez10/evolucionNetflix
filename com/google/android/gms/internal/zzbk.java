// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.PowerManager;
import java.util.Iterator;
import java.util.List;
import android.content.Context;
import android.os.Process;
import android.app.KeyguardManager;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.app.Activity;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzgk
public class zzbk extends Thread
{
    private boolean zzam;
    private final Object zzpc;
    private final int zzrC;
    private final int zzrE;
    private boolean zzrO;
    private final zzbj zzrP;
    private final zzbi zzrQ;
    private final zzgj zzrR;
    private final int zzrS;
    private final int zzrT;
    private final int zzrU;
    
    @Override
    public void run() {
    Label_0050_Outer:
        while (!this.zzam) {
            Label_0085: {
                try {
                    if (!this.zzcu()) {
                        break Label_0085;
                    }
                    final Object o = this.zzrP.getActivity();
                    if (o == null) {
                        zzb.zzaC("ContentFetchThread: no activity");
                        continue Label_0050_Outer;
                    }
                    break Label_0085;
                }
                catch (Throwable t) {
                    zzb.zzb("Error in ContentFetchTask", t);
                    this.zzrR.zza(t, true);
                }
            Label_0090_Outer:
                while (true) {
                    final Object o = this.zzpc;
                    synchronized (o) {
                        while (this.zzrO) {
                            try {
                                zzb.zzaC("ContentFetchTask: waiting");
                                this.zzpc.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        continue Label_0050_Outer;
                        while (true) {
                            Thread.sleep(this.zzrS * 1000);
                            continue Label_0090_Outer;
                            this.zza((Activity)o);
                            continue;
                            zzb.zzaC("ContentFetchTask: sleeping");
                            this.zzcw();
                            continue;
                        }
                    }
                    break;
                }
            }
            break;
        }
    }
    
    zzbk$zza zza(final View view, final zzbh zzbh) {
        int i = 0;
        if (view == null) {
            return new zzbk$zza(this, 0, 0);
        }
        if (view instanceof TextView && !(view instanceof EditText)) {
            final CharSequence text = ((TextView)view).getText();
            if (!TextUtils.isEmpty(text)) {
                zzbh.zzw(text.toString());
                return new zzbk$zza(this, 1, 0);
            }
            return new zzbk$zza(this, 0, 0);
        }
        else if (view instanceof WebView && !(view instanceof zzip)) {
            zzbh.zzcp();
            if (this.zza((WebView)view, zzbh)) {
                return new zzbk$zza(this, 0, 1);
            }
            return new zzbk$zza(this, 0, 0);
        }
        else {
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                int n = 0;
                int n2 = 0;
                while (i < viewGroup.getChildCount()) {
                    final zzbk$zza zza = this.zza(viewGroup.getChildAt(i), zzbh);
                    n2 += zza.zzsb;
                    n += zza.zzsc;
                    ++i;
                }
                return new zzbk$zza(this, n2, n);
            }
            return new zzbk$zza(this, 0, 0);
        }
    }
    
    void zza(final Activity activity) {
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
                this.zzf(viewById);
            }
        }
    }
    
    void zza(final zzbh zzbh, final WebView webView, String optString) {
        zzbh.zzco();
        try {
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                optString = new JSONObject(optString).optString("text");
                if (!TextUtils.isEmpty((CharSequence)webView.getTitle())) {
                    zzbh.zzv(webView.getTitle() + "\n" + optString);
                }
                else {
                    zzbh.zzv(optString);
                }
            }
            if (zzbh.zzcl()) {
                this.zzrQ.zzb(zzbh);
            }
        }
        catch (JSONException ex) {
            zzb.zzaC("Json string may be malformed.");
        }
        catch (Throwable t) {
            zzb.zza("Failed to get webview content.", t);
            this.zzrR.zza(t, true);
        }
    }
    
    boolean zza(final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo) {
        return activityManager$RunningAppProcessInfo.importance == 100;
    }
    
    boolean zza(final WebView webView, final zzbh zzbh) {
        if (!zzlv.zzpV()) {
            return false;
        }
        zzbh.zzcp();
        webView.post((Runnable)new zzbk$2(this, zzbh, webView));
        return true;
    }
    
    boolean zzcu() {
        try {
            final Context context = this.zzrP.getContext();
            if (context == null) {
                return false;
            }
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    final List runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                        if (Process.myPid() == activityManager$RunningAppProcessInfo.pid) {
                            if (this.zza(activityManager$RunningAppProcessInfo) && !keyguardManager.inKeyguardRestrictedInputMode() && this.zzr(context)) {
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
    
    public void zzcw() {
        synchronized (this.zzpc) {
            this.zzrO = true;
            zzb.zzaC("ContentFetchThread: paused, mPause = " + this.zzrO);
        }
    }
    
    boolean zzf(final View view) {
        if (view == null) {
            return false;
        }
        view.post((Runnable)new zzbk$1(this, view));
        return true;
    }
    
    void zzg(final View view) {
        try {
            final zzbh zzbh = new zzbh(this.zzrC, this.zzrT, this.zzrE, this.zzrU);
            final zzbk$zza zza = this.zza(view, zzbh);
            zzbh.zzcq();
            if (zza.zzsb == 0 && zza.zzsc == 0) {
                return;
            }
            if ((zza.zzsc != 0 || zzbh.zzcr() != 0) && (zza.zzsc != 0 || !this.zzrQ.zza(zzbh))) {
                this.zzrQ.zzc(zzbh);
            }
        }
        catch (Exception ex) {
            zzb.zzb("Exception in fetchContentOnUIThread", ex);
            this.zzrR.zza(ex, true);
        }
    }
    
    boolean zzr(final Context context) {
        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
        return powerManager != null && powerManager.isScreenOn();
    }
}
