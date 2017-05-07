// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.internal.fq;
import com.google.android.gms.dynamic.g;
import android.util.Log;
import com.google.android.gms.internal.fr;
import com.google.android.gms.internal.fs;
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
    private int Av;
    private View Aw;
    private View$OnClickListener Ax;
    private int mSize;
    
    public SignInButton(final Context context) {
        this(context, null);
    }
    
    public SignInButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SignInButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.Ax = null;
        this.setStyle(0, 0);
    }
    
    private static Button a(final Context context, final int n, final int n2) {
        final fs fs = new fs(context);
        fs.a(context.getResources(), n, n2);
        return fs;
    }
    
    private void v(final Context context) {
        if (this.Aw != null) {
            this.removeView(this.Aw);
        }
        while (true) {
            try {
                this.Aw = fr.b(context, this.mSize, this.Av);
                this.addView(this.Aw);
                this.Aw.setEnabled(this.isEnabled());
                this.Aw.setOnClickListener((View$OnClickListener)this);
            }
            catch (g.a a) {
                Log.w("SignInButton", "Sign in button not found, using placeholder instead");
                this.Aw = (View)a(context, this.mSize, this.Av);
                continue;
            }
            break;
        }
    }
    
    public void onClick(final View view) {
        if (this.Ax != null && view == this.Aw) {
            this.Ax.onClick((View)this);
        }
    }
    
    public void setColorScheme(final int n) {
        this.setStyle(this.mSize, n);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.Aw.setEnabled(b);
    }
    
    public void setOnClickListener(final View$OnClickListener ax) {
        this.Ax = ax;
        if (this.Aw != null) {
            this.Aw.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    public void setSize(final int n) {
        this.setStyle(n, this.Av);
    }
    
    public void setStyle(final int mSize, final int av) {
        final boolean b = true;
        fq.a(mSize >= 0 && mSize < 3, (Object)("Unknown button size " + mSize));
        fq.a(av >= 0 && av < 2 && b, (Object)("Unknown color scheme " + av));
        this.mSize = mSize;
        this.Av = av;
        this.v(this.getContext());
    }
}
