// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.internal.eg;
import com.google.android.gms.dynamic.e;
import android.util.Log;
import com.google.android.gms.internal.eh;
import com.google.android.gms.internal.ei;
import android.widget.Button;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public final class SignInButton extends FrameLayout implements View$OnClickListener
{
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mP;
    private View mQ;
    private View$OnClickListener mR;
    private int mSize;
    
    public SignInButton(final Context context) {
        this(context, null);
    }
    
    public SignInButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SignInButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mR = null;
        this.setStyle(0, 0);
    }
    
    private static Button c(final Context context, final int n, final int n2) {
        final ei ei = new ei(context);
        ei.a(context.getResources(), n, n2);
        return ei;
    }
    
    private void p(final Context context) {
        if (this.mQ != null) {
            this.removeView(this.mQ);
        }
        while (true) {
            try {
                this.mQ = eh.d(context, this.mSize, this.mP);
                this.addView(this.mQ);
                this.mQ.setEnabled(this.isEnabled());
                this.mQ.setOnClickListener((View$OnClickListener)this);
            }
            catch (e.a a) {
                Log.w("SignInButton", "Sign in button not found, using placeholder instead");
                this.mQ = (View)c(context, this.mSize, this.mP);
                continue;
            }
            break;
        }
    }
    
    public void onClick(final View view) {
        if (this.mR != null && view == this.mQ) {
            this.mR.onClick((View)this);
        }
    }
    
    public void setColorScheme(final int n) {
        this.setStyle(this.mSize, n);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.mQ.setEnabled(b);
    }
    
    public void setOnClickListener(final View$OnClickListener mr) {
        this.mR = mr;
        if (this.mQ != null) {
            this.mQ.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    public void setSize(final int n) {
        this.setStyle(n, this.mP);
    }
    
    public void setStyle(final int mSize, final int mp) {
        final boolean b = true;
        eg.a(mSize >= 0 && mSize < 3, (Object)("Unknown button size " + mSize));
        eg.a(mp >= 0 && mp < 2 && b, (Object)("Unknown color scheme " + mp));
        this.mSize = mSize;
        this.mP = mp;
        this.p(this.getContext());
    }
}
