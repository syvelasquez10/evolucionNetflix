// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Canvas;
import com.facebook.common.internal.Preconditions;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public class ArrayDrawable extends Drawable implements Drawable$Callback, TransformAwareDrawable, TransformCallback
{
    private final DrawableParent[] mDrawableParents;
    private final DrawableProperties mDrawableProperties;
    private boolean mIsMutated;
    private boolean mIsStateful;
    private boolean mIsStatefulCalculated;
    private final Drawable[] mLayers;
    private final Rect mTmpRect;
    private TransformCallback mTransformCallback;
    
    public ArrayDrawable(final Drawable[] mLayers) {
        int i = 0;
        this.mDrawableProperties = new DrawableProperties();
        this.mTmpRect = new Rect();
        this.mIsStateful = false;
        this.mIsStatefulCalculated = false;
        this.mIsMutated = false;
        Preconditions.checkNotNull(mLayers);
        this.mLayers = mLayers;
        while (i < this.mLayers.length) {
            DrawableUtils.setCallbacks(this.mLayers[i], (Drawable$Callback)this, this);
            ++i;
        }
        this.mDrawableParents = new DrawableParent[this.mLayers.length];
    }
    
    private DrawableParent createDrawableParentForIndex(final int n) {
        return new ArrayDrawable$1(this, n);
    }
    
    public void draw(final Canvas canvas) {
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }
    
    public Drawable getDrawable(final int n) {
        final boolean b = true;
        Preconditions.checkArgument(n >= 0);
        Preconditions.checkArgument(n < this.mLayers.length && b);
        return this.mLayers[n];
    }
    
    public DrawableParent getDrawableParentForIndex(final int n) {
        final boolean b = true;
        Preconditions.checkArgument(n >= 0);
        Preconditions.checkArgument(n < this.mDrawableParents.length && b);
        if (this.mDrawableParents[n] == null) {
            this.mDrawableParents[n] = this.createDrawableParentForIndex(n);
        }
        return this.mDrawableParents[n];
    }
    
    public int getIntrinsicHeight() {
        int i = 0;
        int n = -1;
        while (i < this.mLayers.length) {
            final Drawable drawable = this.mLayers[i];
            int max = n;
            if (drawable != null) {
                max = Math.max(n, drawable.getIntrinsicHeight());
            }
            ++i;
            n = max;
        }
        if (n > 0) {
            return n;
        }
        return -1;
    }
    
    public int getIntrinsicWidth() {
        int i = 0;
        int n = -1;
        while (i < this.mLayers.length) {
            final Drawable drawable = this.mLayers[i];
            int max = n;
            if (drawable != null) {
                max = Math.max(n, drawable.getIntrinsicWidth());
            }
            ++i;
            n = max;
        }
        if (n > 0) {
            return n;
        }
        return -1;
    }
    
    public int getNumberOfLayers() {
        return this.mLayers.length;
    }
    
    public int getOpacity() {
        int n;
        if (this.mLayers.length == 0) {
            n = -2;
        }
        else {
            int n2 = -1;
            int n3 = 1;
            while (true) {
                n = n2;
                if (n3 >= this.mLayers.length) {
                    break;
                }
                final Drawable drawable = this.mLayers[n3];
                int resolveOpacity = n2;
                if (drawable != null) {
                    resolveOpacity = Drawable.resolveOpacity(n2, drawable.getOpacity());
                }
                ++n3;
                n2 = resolveOpacity;
            }
        }
        return n;
    }
    
    public boolean getPadding(final Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        final Rect mTmpRect = this.mTmpRect;
        while (i < this.mLayers.length) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.getPadding(mTmpRect);
                rect.left = Math.max(rect.left, mTmpRect.left);
                rect.top = Math.max(rect.top, mTmpRect.top);
                rect.right = Math.max(rect.right, mTmpRect.right);
                rect.bottom = Math.max(rect.bottom, mTmpRect.bottom);
            }
            ++i;
        }
        return true;
    }
    
    public void getRootBounds(final RectF rectF) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(rectF);
            return;
        }
        rectF.set(this.getBounds());
    }
    
    public void getTransform(final Matrix matrix) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(matrix);
            return;
        }
        matrix.reset();
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        this.invalidateSelf();
    }
    
    public boolean isStateful() {
        if (!this.mIsStatefulCalculated) {
            this.mIsStateful = false;
            for (int i = 0; i < this.mLayers.length; ++i) {
                final Drawable drawable = this.mLayers[i];
                this.mIsStateful |= (drawable != null && drawable.isStateful());
            }
            this.mIsStatefulCalculated = true;
        }
        return this.mIsStateful;
    }
    
    public Drawable mutate() {
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.mutate();
            }
        }
        this.mIsMutated = true;
        return this;
    }
    
    protected void onBoundsChange(final Rect bounds) {
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setBounds(bounds);
            }
        }
    }
    
    protected boolean onLevelChange(final int level) {
        int i = 0;
        boolean b = false;
        while (i < this.mLayers.length) {
            final Drawable drawable = this.mLayers[i];
            boolean b2 = b;
            if (drawable != null) {
                b2 = b;
                if (drawable.setLevel(level)) {
                    b2 = true;
                }
            }
            ++i;
            b = b2;
        }
        return b;
    }
    
    protected boolean onStateChange(final int[] state) {
        int i = 0;
        boolean b = false;
        while (i < this.mLayers.length) {
            final Drawable drawable = this.mLayers[i];
            boolean b2 = b;
            if (drawable != null) {
                b2 = b;
                if (drawable.setState(state)) {
                    b2 = true;
                }
            }
            ++i;
            b = b2;
        }
        return b;
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        this.scheduleSelf(runnable, n);
    }
    
    public void setAlpha(final int n) {
        this.mDrawableProperties.setAlpha(n);
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setAlpha(n);
            }
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }
    
    public void setDither(final boolean b) {
        this.mDrawableProperties.setDither(b);
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setDither(b);
            }
        }
    }
    
    public Drawable setDrawable(final int n, final Drawable drawable) {
        final boolean b = true;
        Preconditions.checkArgument(n >= 0);
        Preconditions.checkArgument(n < this.mLayers.length && b);
        final Drawable drawable2 = this.mLayers[n];
        if (drawable != drawable2) {
            if (drawable != null && this.mIsMutated) {
                drawable.mutate();
            }
            DrawableUtils.setCallbacks(this.mLayers[n], null, null);
            DrawableUtils.setCallbacks(drawable, null, null);
            DrawableUtils.setDrawableProperties(drawable, this.mDrawableProperties);
            DrawableUtils.copyProperties(drawable, this);
            DrawableUtils.setCallbacks(drawable, (Drawable$Callback)this, this);
            this.mIsStatefulCalculated = false;
            this.mLayers[n] = drawable;
            this.invalidateSelf();
        }
        return drawable2;
    }
    
    public void setFilterBitmap(final boolean b) {
        this.mDrawableProperties.setFilterBitmap(b);
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setFilterBitmap(b);
            }
        }
    }
    
    @TargetApi(21)
    public void setHotspot(final float n, final float n2) {
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setHotspot(n, n2);
            }
        }
    }
    
    public void setTransformCallback(final TransformCallback mTransformCallback) {
        this.mTransformCallback = mTransformCallback;
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        final boolean setVisible = super.setVisible(b, b2);
        for (int i = 0; i < this.mLayers.length; ++i) {
            final Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.setVisible(b, b2);
            }
        }
        return setVisible;
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
}
