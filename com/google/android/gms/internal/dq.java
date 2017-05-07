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

public final class dq extends Drawable implements Drawable$Callback
{
    private int oB;
    private long oC;
    private int oD;
    private int oE;
    private int oF;
    private int oG;
    private int oH;
    private boolean oI;
    private b oJ;
    private Drawable oK;
    private Drawable oL;
    private boolean oM;
    private boolean oN;
    private boolean oO;
    private int oP;
    private boolean oy;
    
    public dq(Drawable bd, final Drawable drawable) {
        this(null);
        Drawable bd2 = bd;
        if (bd == null) {
            bd2 = a.oQ;
        }
        (this.oK = bd2).setCallback((Drawable$Callback)this);
        final b oj = this.oJ;
        oj.oT |= bd2.getChangingConfigurations();
        if ((bd = drawable) == null) {
            bd = a.oQ;
        }
        (this.oL = bd).setCallback((Drawable$Callback)this);
        final b oj2 = this.oJ;
        oj2.oT |= bd.getChangingConfigurations();
    }
    
    dq(final b b) {
        this.oB = 0;
        this.oF = 255;
        this.oH = 0;
        this.oy = true;
        this.oJ = new b(b);
    }
    
    public Drawable bC() {
        return this.oL;
    }
    
    public boolean canConstantState() {
        if (!this.oM) {
            this.oN = (this.oK.getConstantState() != null && this.oL.getConstantState() != null);
            this.oM = true;
        }
        return this.oN;
    }
    
    public void draw(final Canvas canvas) {
        final boolean b = true;
        boolean b2 = true;
        final boolean b3 = false;
        switch (this.oB) {
            case 1: {
                this.oC = SystemClock.uptimeMillis();
                this.oB = 2;
                b2 = b3;
                break;
            }
            case 2: {
                if (this.oC >= 0L) {
                    final float n = (SystemClock.uptimeMillis() - this.oC) / this.oG;
                    b2 = (n >= 1.0f && b);
                    if (b2) {
                        this.oB = 0;
                    }
                    this.oH = (int)(Math.min(n, 1.0f) * (this.oE - this.oD) + this.oD);
                    break;
                }
                break;
            }
        }
        final int oh = this.oH;
        final boolean oy = this.oy;
        final Drawable ok = this.oK;
        final Drawable ol = this.oL;
        if (b2) {
            if (!oy || oh == 0) {
                ok.draw(canvas);
            }
            if (oh == this.oF) {
                ol.setAlpha(this.oF);
                ol.draw(canvas);
            }
            return;
        }
        if (oy) {
            ok.setAlpha(this.oF - oh);
        }
        ok.draw(canvas);
        if (oy) {
            ok.setAlpha(this.oF);
        }
        if (oh > 0) {
            ol.setAlpha(oh);
            ol.draw(canvas);
            ol.setAlpha(this.oF);
        }
        this.invalidateSelf();
    }
    
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.oJ.oS | this.oJ.oT;
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.canConstantState()) {
            this.oJ.oS = this.getChangingConfigurations();
            return this.oJ;
        }
        return null;
    }
    
    public int getIntrinsicHeight() {
        return Math.max(this.oK.getIntrinsicHeight(), this.oL.getIntrinsicHeight());
    }
    
    public int getIntrinsicWidth() {
        return Math.max(this.oK.getIntrinsicWidth(), this.oL.getIntrinsicWidth());
    }
    
    public int getOpacity() {
        if (!this.oO) {
            this.oP = Drawable.resolveOpacity(this.oK.getOpacity(), this.oL.getOpacity());
            this.oO = true;
        }
        return this.oP;
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        if (fg.cD()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.invalidateDrawable((Drawable)this);
            }
        }
    }
    
    public Drawable mutate() {
        if (!this.oI && super.mutate() == this) {
            if (!this.canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.oK.mutate();
            this.oL.mutate();
            this.oI = true;
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect rect) {
        this.oK.setBounds(rect);
        this.oL.setBounds(rect);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        if (fg.cD()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.scheduleDrawable((Drawable)this, runnable, n);
            }
        }
    }
    
    public void setAlpha(final int n) {
        if (this.oH == this.oF) {
            this.oH = n;
        }
        this.oF = n;
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.oK.setColorFilter(colorFilter);
        this.oL.setColorFilter(colorFilter);
    }
    
    public void startTransition(final int og) {
        this.oD = 0;
        this.oE = this.oF;
        this.oH = 0;
        this.oG = og;
        this.oB = 1;
        this.invalidateSelf();
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        if (fg.cD()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.unscheduleDrawable((Drawable)this, runnable);
            }
        }
    }
    
    private static final class a extends Drawable
    {
        private static final dq.a oQ;
        private static final dq.a.a oR;
        
        static {
            oQ = new dq.a();
            oR = new dq.a.a();
        }
        
        public void draw(final Canvas canvas) {
        }
        
        public Drawable$ConstantState getConstantState() {
            return dq.a.oR;
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
                return dq.a.oQ;
            }
        }
    }
    
    static final class b extends Drawable$ConstantState
    {
        int oS;
        int oT;
        
        b(final b b) {
            if (b != null) {
                this.oS = b.oS;
                this.oT = b.oT;
            }
        }
        
        public int getChangingConfigurations() {
            return this.oS;
        }
        
        public Drawable newDrawable() {
            return new dq(this);
        }
    }
}
