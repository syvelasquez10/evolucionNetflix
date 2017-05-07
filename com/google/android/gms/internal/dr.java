// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.content.Context;

public final class dr
{
    private final Context mContext;
    private int mState;
    private String rh;
    private final float ri;
    private float rj;
    private float rk;
    private float rl;
    
    public dr(final Context mContext) {
        this.mState = 0;
        this.mContext = mContext;
        this.ri = mContext.getResources().getDisplayMetrics().density;
    }
    
    public dr(final Context context, final String rh) {
        this(context);
        this.rh = rh;
    }
    
    private void a(final int n, final float rj, final float n2) {
        if (n == 0) {
            this.mState = 0;
            this.rj = rj;
            this.rk = n2;
            this.rl = n2;
        }
        else if (this.mState != -1) {
            if (n == 2) {
                if (n2 > this.rk) {
                    this.rk = n2;
                }
                else if (n2 < this.rl) {
                    this.rl = n2;
                }
                if (this.rk - this.rl > 30.0f * this.ri) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (rj - this.rj >= 50.0f * this.ri) {
                        this.rj = rj;
                        ++this.mState;
                    }
                }
                else if ((this.mState == 1 || this.mState == 3) && rj - this.rj <= -50.0f * this.ri) {
                    this.rj = rj;
                    ++this.mState;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (rj > this.rj) {
                        this.rj = rj;
                    }
                }
                else if (this.mState == 2 && rj < this.rj) {
                    this.rj = rj;
                }
            }
            else if (n == 1 && this.mState == 4) {
                this.showDialog();
            }
        }
    }
    
    private void showDialog() {
        String trim;
        if (!TextUtils.isEmpty((CharSequence)this.rh)) {
            final Uri build = new Uri$Builder().encodedQuery(this.rh).build();
            final StringBuilder sb = new StringBuilder();
            final Map<String, String> b = dq.b(build);
            for (final String s : b.keySet()) {
                sb.append(s).append(" = ").append(b.get(s)).append("\n\n");
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
        alertDialog$Builder.setPositiveButton((CharSequence)"Share", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dr.this.mContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", trim), (CharSequence)"Share via"));
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Close", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    public void c(final MotionEvent motionEvent) {
        for (int historySize = motionEvent.getHistorySize(), i = 0; i < historySize; ++i) {
            this.a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        this.a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
    
    public void t(final String rh) {
        this.rh = rh;
    }
}
