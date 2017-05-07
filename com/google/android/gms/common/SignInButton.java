// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.p;
import android.widget.Button;
import com.google.android.gms.dynamic.g$a;
import android.util.Log;
import com.google.android.gms.common.internal.o;
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
    private View Ih;
    private View$OnClickListener Ii;
    private int mColor;
    private int mSize;
    
    public SignInButton(final Context context) {
        this(context, null);
    }
    
    public SignInButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SignInButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.Ii = null;
        this.setStyle(0, 0);
    }
    
    private void G(final Context context) {
        if (this.Ih != null) {
            this.removeView(this.Ih);
        }
        while (true) {
            try {
                this.Ih = o.b(context, this.mSize, this.mColor);
                this.addView(this.Ih);
                this.Ih.setEnabled(this.isEnabled());
                this.Ih.setOnClickListener((View$OnClickListener)this);
            }
            catch (g$a g$a) {
                Log.w("SignInButton", "Sign in button not found, using placeholder instead");
                this.Ih = (View)a(context, this.mSize, this.mColor);
                continue;
            }
            break;
        }
    }
    
    private static Button a(final Context context, final int n, final int n2) {
        final p p3 = new p(context);
        p3.a(context.getResources(), n, n2);
        return p3;
    }
    
    public void onClick(final View view) {
        if (this.Ii != null && view == this.Ih) {
            this.Ii.onClick((View)this);
        }
    }
    
    public void setColorScheme(final int n) {
        this.setStyle(this.mSize, n);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.Ih.setEnabled(b);
    }
    
    public void setOnClickListener(final View$OnClickListener ii) {
        this.Ii = ii;
        if (this.Ih != null) {
            this.Ih.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    public void setSize(final int n) {
        this.setStyle(n, this.mColor);
    }
    
    public void setStyle(final int mSize, final int mColor) {
        n.a(mSize >= 0 && mSize < 3, "Unknown button size %d", mSize);
        n.a(mColor >= 0 && mColor < 2, "Unknown color scheme %s", mColor);
        this.mSize = mSize;
        this.mColor = mColor;
        this.G(this.getContext());
    }
}
