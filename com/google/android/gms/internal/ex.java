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

public final class ex extends Drawable implements Drawable$Callback
{
    private int CA;
    private int CB;
    private boolean CC;
    private b CD;
    private Drawable CE;
    private Drawable CF;
    private boolean CG;
    private boolean CH;
    private boolean CI;
    private int CJ;
    private boolean Cp;
    private int Cv;
    private long Cw;
    private int Cx;
    private int Cy;
    private int Cz;
    
    public ex(Drawable ea, final Drawable drawable) {
        this(null);
        Drawable ea2 = ea;
        if (ea == null) {
            ea2 = a.CK;
        }
        (this.CE = ea2).setCallback((Drawable$Callback)this);
        final b cd = this.CD;
        cd.CN |= ea2.getChangingConfigurations();
        if ((ea = drawable) == null) {
            ea = a.CK;
        }
        (this.CF = ea).setCallback((Drawable$Callback)this);
        final b cd2 = this.CD;
        cd2.CN |= ea.getChangingConfigurations();
    }
    
    ex(final b b) {
        this.Cv = 0;
        this.Cz = 255;
        this.CB = 0;
        this.Cp = true;
        this.CD = new b(b);
    }
    
    public boolean canConstantState() {
        if (!this.CG) {
            this.CH = (this.CE.getConstantState() != null && this.CF.getConstantState() != null);
            this.CG = true;
        }
        return this.CH;
    }
    
    public void draw(final Canvas canvas) {
        final boolean b = true;
        boolean b2 = true;
        final boolean b3 = false;
        switch (this.Cv) {
            case 1: {
                this.Cw = SystemClock.uptimeMillis();
                this.Cv = 2;
                b2 = b3;
                break;
            }
            case 2: {
                if (this.Cw >= 0L) {
                    final float n = (SystemClock.uptimeMillis() - this.Cw) / this.CA;
                    b2 = (n >= 1.0f && b);
                    if (b2) {
                        this.Cv = 0;
                    }
                    this.CB = (int)(Math.min(n, 1.0f) * (this.Cy - this.Cx) + this.Cx);
                    break;
                }
                break;
            }
        }
        final int cb = this.CB;
        final boolean cp = this.Cp;
        final Drawable ce = this.CE;
        final Drawable cf = this.CF;
        if (b2) {
            if (!cp || cb == 0) {
                ce.draw(canvas);
            }
            if (cb == this.Cz) {
                cf.setAlpha(this.Cz);
                cf.draw(canvas);
            }
            return;
        }
        if (cp) {
            ce.setAlpha(this.Cz - cb);
        }
        ce.draw(canvas);
        if (cp) {
            ce.setAlpha(this.Cz);
        }
        if (cb > 0) {
            cf.setAlpha(cb);
            cf.draw(canvas);
            cf.setAlpha(this.Cz);
        }
        this.invalidateSelf();
    }
    
    public Drawable ez() {
        return this.CF;
    }
    
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.CD.CM | this.CD.CN;
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.canConstantState()) {
            this.CD.CM = this.getChangingConfigurations();
            return this.CD;
        }
        return null;
    }
    
    public int getIntrinsicHeight() {
        return Math.max(this.CE.getIntrinsicHeight(), this.CF.getIntrinsicHeight());
    }
    
    public int getIntrinsicWidth() {
        return Math.max(this.CE.getIntrinsicWidth(), this.CF.getIntrinsicWidth());
    }
    
    public int getOpacity() {
        if (!this.CI) {
            this.CJ = Drawable.resolveOpacity(this.CE.getOpacity(), this.CF.getOpacity());
            this.CI = true;
        }
        return this.CJ;
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        if (gr.fu()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.invalidateDrawable((Drawable)this);
            }
        }
    }
    
    public Drawable mutate() {
        if (!this.CC && super.mutate() == this) {
            if (!this.canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.CE.mutate();
            this.CF.mutate();
            this.CC = true;
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect rect) {
        this.CE.setBounds(rect);
        this.CF.setBounds(rect);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        if (gr.fu()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.scheduleDrawable((Drawable)this, runnable, n);
            }
        }
    }
    
    public void setAlpha(final int n) {
        if (this.CB == this.Cz) {
            this.CB = n;
        }
        this.Cz = n;
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.CE.setColorFilter(colorFilter);
        this.CF.setColorFilter(colorFilter);
    }
    
    public void startTransition(final int ca) {
        this.Cx = 0;
        this.Cy = this.Cz;
        this.CB = 0;
        this.CA = ca;
        this.Cv = 1;
        this.invalidateSelf();
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        if (gr.fu()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.unscheduleDrawable((Drawable)this, runnable);
            }
        }
    }
    
    private static final class a extends Drawable
    {
        private static final ex.a CK;
        private static final ex.a.a CL;
        
        static {
            CK = new ex.a();
            CL = new ex.a.a();
        }
        
        public void draw(final Canvas canvas) {
        }
        
        public Drawable$ConstantState getConstantState() {
            return ex.a.CL;
        }
        
        public int getOpacity() {
            return -2;
        }
        
        public void setAlpha(final int n) {
        }
        
        public void setColorFilter(final ColorFilter colorFilter) {
        }
        
        private static final class a extends Drawable$ConstantState
        {
            public int getChangingConfigurations() {
                return 0;
            }
            
            public Drawable newDrawable() {
                return ex.a.CK;
            }
        }
    }
    
    static final class b extends Drawable$ConstantState
    {
        int CM;
        int CN;
        
        b(final b b) {
            if (b != null) {
                this.CM = b.CM;
                this.CN = b.CN;
            }
        }
        
        public int getChangingConfigurations() {
            return this.CM;
        }
        
        public Drawable newDrawable() {
            return new ex(this);
        }
    }
}
