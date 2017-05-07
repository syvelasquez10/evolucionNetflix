// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzp;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Activity;
import android.content.Context;

@zzgk
public class zzhw
{
    private final Context mContext;
    private int mState;
    private String zzHO;
    private float zzHP;
    private float zzHQ;
    private float zzHR;
    private final float zzzP;
    
    public zzhw(final Context mContext) {
        this.mState = 0;
        this.mContext = mContext;
        this.zzzP = mContext.getResources().getDisplayMetrics().density;
    }
    
    public zzhw(final Context context, final String zzHO) {
        this(context);
        this.zzHO = zzHO;
    }
    
    private void showDialog() {
        if (!(this.mContext instanceof Activity)) {
            zzb.zzaD("Can not create dialog without Activity Context");
            return;
        }
        final String zzaA = zzaA(this.zzHO);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setMessage((CharSequence)zzaA);
        alertDialog$Builder.setTitle((CharSequence)"Ad Information");
        alertDialog$Builder.setPositiveButton((CharSequence)"Share", (DialogInterface$OnClickListener)new zzhw$1(this, zzaA));
        alertDialog$Builder.setNegativeButton((CharSequence)"Close", (DialogInterface$OnClickListener)new zzhw$2(this));
        alertDialog$Builder.create().show();
    }
    
    static String zzaA(String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = "No debug information";
        }
        else {
            s = s.replaceAll("\\+", "%20");
            final Uri build = new Uri$Builder().encodedQuery(s).build();
            final StringBuilder sb = new StringBuilder();
            final Map<String, String> zze = zzp.zzbx().zze(build);
            for (final String s2 : zze.keySet()) {
                sb.append(s2).append(" = ").append(zze.get(s2)).append("\n\n");
            }
            if (TextUtils.isEmpty((CharSequence)(s = sb.toString().trim()))) {
                return "No debug information";
            }
        }
        return s;
    }
    
    void zza(final int n, final float zzHP, final float n2) {
        if (n == 0) {
            this.mState = 0;
            this.zzHP = zzHP;
            this.zzHQ = n2;
            this.zzHR = n2;
        }
        else if (this.mState != -1) {
            if (n == 2) {
                if (n2 > this.zzHQ) {
                    this.zzHQ = n2;
                }
                else if (n2 < this.zzHR) {
                    this.zzHR = n2;
                }
                if (this.zzHQ - this.zzHR > 30.0f * this.zzzP) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (zzHP - this.zzHP >= 50.0f * this.zzzP) {
                        this.zzHP = zzHP;
                        ++this.mState;
                    }
                }
                else if ((this.mState == 1 || this.mState == 3) && zzHP - this.zzHP <= -50.0f * this.zzzP) {
                    this.zzHP = zzHP;
                    ++this.mState;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (zzHP > this.zzHP) {
                        this.zzHP = zzHP;
                    }
                }
                else if (this.mState == 2 && zzHP < this.zzHP) {
                    this.zzHP = zzHP;
                }
            }
            else if (n == 1 && this.mState == 4) {
                this.showDialog();
            }
        }
    }
    
    public void zze(final MotionEvent motionEvent) {
        for (int historySize = motionEvent.getHistorySize(), i = 0; i < historySize; ++i) {
            this.zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        this.zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
