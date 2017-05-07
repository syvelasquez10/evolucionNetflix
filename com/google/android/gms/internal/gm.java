// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.content.Context;

@ez
public final class gm
{
    private final Context mContext;
    private int mState;
    private final float ri;
    private String ws;
    private float wt;
    private float wu;
    private float wv;
    
    public gm(final Context mContext) {
        this.mState = 0;
        this.mContext = mContext;
        this.ri = mContext.getResources().getDisplayMetrics().density;
    }
    
    public gm(final Context context, final String ws) {
        this(context);
        this.ws = ws;
    }
    
    private void a(final int n, final float wt, final float n2) {
        if (n == 0) {
            this.mState = 0;
            this.wt = wt;
            this.wu = n2;
            this.wv = n2;
        }
        else if (this.mState != -1) {
            if (n == 2) {
                if (n2 > this.wu) {
                    this.wu = n2;
                }
                else if (n2 < this.wv) {
                    this.wv = n2;
                }
                if (this.wu - this.wv > 30.0f * this.ri) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (wt - this.wt >= 50.0f * this.ri) {
                        this.wt = wt;
                        ++this.mState;
                    }
                }
                else if ((this.mState == 1 || this.mState == 3) && wt - this.wt <= -50.0f * this.ri) {
                    this.wt = wt;
                    ++this.mState;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (wt > this.wt) {
                        this.wt = wt;
                    }
                }
                else if (this.mState == 2 && wt < this.wt) {
                    this.wt = wt;
                }
            }
            else if (n == 1 && this.mState == 4) {
                this.showDialog();
            }
        }
    }
    
    private void showDialog() {
        String trim;
        if (!TextUtils.isEmpty((CharSequence)this.ws)) {
            final Uri build = new Uri$Builder().encodedQuery(this.ws).build();
            final StringBuilder sb = new StringBuilder();
            final Map<String, String> c = gj.c(build);
            for (final String s : c.keySet()) {
                sb.append(s).append(" = ").append(c.get(s)).append("\n\n");
            }
            trim = sb.toString().trim();
            if (TextUtils.isEmpty((CharSequence)trim)) {
                trim = "No debug information";
            }
        }
        else {
            trim = "No debug information";
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setMessage((CharSequence)trim);
        alertDialog$Builder.setTitle((CharSequence)"Ad Information");
        alertDialog$Builder.setPositiveButton((CharSequence)"Share", (DialogInterface$OnClickListener)new gm$1(this, trim));
        alertDialog$Builder.setNegativeButton((CharSequence)"Close", (DialogInterface$OnClickListener)new gm$2(this));
        alertDialog$Builder.create().show();
    }
    
    public void Q(final String ws) {
        this.ws = ws;
    }
    
    public void c(final MotionEvent motionEvent) {
        for (int historySize = motionEvent.getHistorySize(), i = 0; i < historySize; ++i) {
            this.a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        this.a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
