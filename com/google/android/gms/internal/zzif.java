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

@zzgr
public class zzif
{
    private final Context mContext;
    private int mState;
    private final float zzAC;
    private String zzII;
    private float zzIJ;
    private float zzIK;
    private float zzIL;
    
    public zzif(final Context mContext) {
        this.mState = 0;
        this.mContext = mContext;
        this.zzAC = mContext.getResources().getDisplayMetrics().density;
    }
    
    public zzif(final Context context, final String zzII) {
        this(context);
        this.zzII = zzII;
    }
    
    private void showDialog() {
        if (!(this.mContext instanceof Activity)) {
            zzb.zzaG("Can not create dialog without Activity Context");
            return;
        }
        final String zzaD = zzaD(this.zzII);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setMessage((CharSequence)zzaD);
        alertDialog$Builder.setTitle((CharSequence)"Ad Information");
        alertDialog$Builder.setPositiveButton((CharSequence)"Share", (DialogInterface$OnClickListener)new zzif$1(this, zzaD));
        alertDialog$Builder.setNegativeButton((CharSequence)"Close", (DialogInterface$OnClickListener)new zzif$2(this));
        alertDialog$Builder.create().show();
    }
    
    static String zzaD(String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = "No debug information";
        }
        else {
            s = s.replaceAll("\\+", "%20");
            final Uri build = new Uri$Builder().encodedQuery(s).build();
            final StringBuilder sb = new StringBuilder();
            final Map<String, String> zze = zzp.zzbv().zze(build);
            for (final String s2 : zze.keySet()) {
                sb.append(s2).append(" = ").append(zze.get(s2)).append("\n\n");
            }
            if (TextUtils.isEmpty((CharSequence)(s = sb.toString().trim()))) {
                return "No debug information";
            }
        }
        return s;
    }
    
    void zza(final int n, final float zzIJ, final float n2) {
        if (n == 0) {
            this.mState = 0;
            this.zzIJ = zzIJ;
            this.zzIK = n2;
            this.zzIL = n2;
        }
        else if (this.mState != -1) {
            if (n == 2) {
                if (n2 > this.zzIK) {
                    this.zzIK = n2;
                }
                else if (n2 < this.zzIL) {
                    this.zzIL = n2;
                }
                if (this.zzIK - this.zzIL > 30.0f * this.zzAC) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (zzIJ - this.zzIJ >= 50.0f * this.zzAC) {
                        this.zzIJ = zzIJ;
                        ++this.mState;
                    }
                }
                else if ((this.mState == 1 || this.mState == 3) && zzIJ - this.zzIJ <= -50.0f * this.zzAC) {
                    this.zzIJ = zzIJ;
                    ++this.mState;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (zzIJ > this.zzIJ) {
                        this.zzIJ = zzIJ;
                    }
                }
                else if (this.mState == 2 && zzIJ < this.zzIJ) {
                    this.zzIJ = zzIJ;
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
