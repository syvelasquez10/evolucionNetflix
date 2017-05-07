// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.os.SystemClock;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public final class iw extends Drawable implements Drawable$Callback
{
    private boolean KE;
    private int KK;
    private long KL;
    private int KM;
    private int KN;
    private int KO;
    private int KP;
    private boolean KQ;
    private iw$b KR;
    private Drawable KS;
    private Drawable KT;
    private boolean KU;
    private boolean KV;
    private boolean KW;
    private int KX;
    private int mFrom;
    
    public iw(Drawable gm, final Drawable drawable) {
        this(null);
        Drawable gm2 = gm;
        if (gm == null) {
            gm2 = iw$a.KY;
        }
        (this.KS = gm2).setCallback((Drawable$Callback)this);
        final iw$b kr = this.KR;
        kr.Lb |= gm2.getChangingConfigurations();
        if ((gm = drawable) == null) {
            gm = iw$a.KY;
        }
        (this.KT = gm).setCallback((Drawable$Callback)this);
        final iw$b kr2 = this.KR;
        kr2.Lb |= gm.getChangingConfigurations();
    }
    
    iw(final iw$b iw$b) {
        this.KK = 0;
        this.KN = 255;
        this.KP = 0;
        this.KE = true;
        this.KR = new iw$b(iw$b);
    }
    
    public boolean canConstantState() {
        if (!this.KU) {
            this.KV = (this.KS.getConstantState() != null && this.KT.getConstantState() != null);
            this.KU = true;
        }
        return this.KV;
    }
    
    public void draw(final Canvas canvas) {
        final boolean b = true;
        boolean b2 = true;
        final boolean b3 = false;
        switch (this.KK) {
            case 1: {
                this.KL = SystemClock.uptimeMillis();
                this.KK = 2;
                b2 = b3;
                break;
            }
            case 2: {
                if (this.KL >= 0L) {
                    final float n = (SystemClock.uptimeMillis() - this.KL) / this.KO;
                    b2 = (n >= 1.0f && b);
                    if (b2) {
                        this.KK = 0;
                    }
                    this.KP = (int)(Math.min(n, 1.0f) * (this.KM - this.mFrom) + this.mFrom);
                    break;
                }
                break;
            }
        }
        final int kp = this.KP;
        final boolean ke = this.KE;
        final Drawable ks = this.KS;
        final Drawable kt = this.KT;
        if (b2) {
            if (!ke || kp == 0) {
                ks.draw(canvas);
            }
            if (kp == this.KN) {
                kt.setAlpha(this.KN);
                kt.draw(canvas);
            }
            return;
        }
        if (ke) {
            ks.setAlpha(this.KN - kp);
        }
        ks.draw(canvas);
        if (ke) {
            ks.setAlpha(this.KN);
        }
        if (kp > 0) {
            kt.setAlpha(kp);
            kt.draw(canvas);
            kt.setAlpha(this.KN);
        }
        this.invalidateSelf();
    }
    
    public Drawable gL() {
        return this.KT;
    }
    
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.KR.La | this.KR.Lb;
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.canConstantState()) {
            this.KR.La = this.getChangingConfigurations();
            return this.KR;
        }
        return null;
    }
    
    public int getIntrinsicHeight() {
        return Math.max(this.KS.getIntrinsicHeight(), this.KT.getIntrinsicHeight());
    }
    
    public int getIntrinsicWidth() {
        return Math.max(this.KS.getIntrinsicWidth(), this.KT.getIntrinsicWidth());
    }
    
    public int getOpacity() {
        if (!this.KW) {
            this.KX = Drawable.resolveOpacity(this.KS.getOpacity(), this.KT.getOpacity());
            this.KW = true;
        }
        return this.KX;
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        if (kc.hB()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.invalidateDrawable((Drawable)this);
            }
        }
    }
    
    public Drawable mutate() {
        if (!this.KQ && super.mutate() == this) {
            if (!this.canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.KS.mutate();
            this.KT.mutate();
            this.KQ = true;
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect rect) {
        this.KS.setBounds(rect);
        this.KT.setBounds(rect);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        if (kc.hB()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.scheduleDrawable((Drawable)this, runnable, n);
            }
        }
    }
    
    public void setAlpha(final int n) {
        if (this.KP == this.KN) {
            this.KP = n;
        }
        this.KN = n;
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.KS.setColorFilter(colorFilter);
        this.KT.setColorFilter(colorFilter);
    }
    
    public void startTransition(final int ko) {
        this.mFrom = 0;
        this.KM = this.KN;
        this.KP = 0;
        this.KO = ko;
        this.KK = 1;
        this.invalidateSelf();
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        if (kc.hB()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.unscheduleDrawable((Drawable)this, runnable);
            }
        }
    }
}
