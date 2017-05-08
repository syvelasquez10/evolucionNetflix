// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.os.SystemClock;
import java.util.Arrays;
import android.graphics.Canvas;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;

public class FadeDrawable extends ArrayDrawable
{
    int mAlpha;
    int[] mAlphas;
    int mDurationMs;
    boolean[] mIsLayerOn;
    private final Drawable[] mLayers;
    int mPreventInvalidateCount;
    int[] mStartAlphas;
    long mStartTimeMs;
    int mTransitionState;
    
    public FadeDrawable(final Drawable[] mLayers) {
        boolean b = true;
        super(mLayers);
        if (mLayers.length < 1) {
            b = false;
        }
        Preconditions.checkState(b, "At least one layer required!");
        this.mLayers = mLayers;
        this.mStartAlphas = new int[mLayers.length];
        this.mAlphas = new int[mLayers.length];
        this.mAlpha = 255;
        this.mIsLayerOn = new boolean[mLayers.length];
        this.mPreventInvalidateCount = 0;
        this.resetInternal();
    }
    
    private void drawDrawableWithAlpha(final Canvas canvas, final Drawable drawable, final int alpha) {
        if (drawable != null && alpha > 0) {
            ++this.mPreventInvalidateCount;
            drawable.mutate().setAlpha(alpha);
            --this.mPreventInvalidateCount;
            drawable.draw(canvas);
        }
    }
    
    private void resetInternal() {
        this.mTransitionState = 2;
        Arrays.fill(this.mStartAlphas, 0);
        this.mStartAlphas[0] = 255;
        Arrays.fill(this.mAlphas, 0);
        this.mAlphas[0] = 255;
        Arrays.fill(this.mIsLayerOn, false);
        this.mIsLayerOn[0] = true;
    }
    
    private boolean updateAlphas(final float n) {
        int i = 0;
        boolean b = true;
        while (i < this.mLayers.length) {
            int n2;
            if (this.mIsLayerOn[i]) {
                n2 = 1;
            }
            else {
                n2 = -1;
            }
            this.mAlphas[i] = (int)(n2 * 255 * n + this.mStartAlphas[i]);
            if (this.mAlphas[i] < 0) {
                this.mAlphas[i] = 0;
            }
            if (this.mAlphas[i] > 255) {
                this.mAlphas[i] = 255;
            }
            boolean b2 = b;
            if (this.mIsLayerOn[i]) {
                b2 = b;
                if (this.mAlphas[i] < 255) {
                    b2 = false;
                }
            }
            b = b2;
            if (!this.mIsLayerOn[i]) {
                b = b2;
                if (this.mAlphas[i] > 0) {
                    b = false;
                }
            }
            ++i;
        }
        return b;
    }
    
    public void beginBatchMode() {
        ++this.mPreventInvalidateCount;
    }
    
    @Override
    public void draw(final Canvas canvas) {
        final int n = 2;
        final int n2 = 0;
        boolean b2;
        final boolean b = b2 = true;
        int i = n2;
        Label_0055: {
            switch (this.mTransitionState) {
                default: {
                    i = n2;
                    b2 = b;
                    break Label_0055;
                }
                case 1: {
                    Preconditions.checkState(this.mDurationMs > 0);
                    b2 = this.updateAlphas((this.getCurrentTimeMs() - this.mStartTimeMs) / this.mDurationMs);
                    int mTransitionState;
                    if (b2) {
                        mTransitionState = n;
                    }
                    else {
                        mTransitionState = 1;
                    }
                    this.mTransitionState = mTransitionState;
                    i = n2;
                    break Label_0055;
                }
                case 0: {
                    System.arraycopy(this.mAlphas, 0, this.mStartAlphas, 0, this.mLayers.length);
                    this.mStartTimeMs = this.getCurrentTimeMs();
                    float n3;
                    if (this.mDurationMs == 0) {
                        n3 = 1.0f;
                    }
                    else {
                        n3 = 0.0f;
                    }
                    b2 = this.updateAlphas(n3);
                    int mTransitionState2;
                    if (b2) {
                        mTransitionState2 = 2;
                    }
                    else {
                        mTransitionState2 = 1;
                    }
                    this.mTransitionState = mTransitionState2;
                    i = n2;
                }
                case 2: {
                    while (i < this.mLayers.length) {
                        this.drawDrawableWithAlpha(canvas, this.mLayers[i], this.mAlphas[i] * this.mAlpha / 255);
                        ++i;
                    }
                    if (!b2) {
                        this.invalidateSelf();
                    }
                }
            }
        }
    }
    
    public void endBatchMode() {
        --this.mPreventInvalidateCount;
        this.invalidateSelf();
    }
    
    public void fadeInAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, true);
        this.invalidateSelf();
    }
    
    public void fadeInLayer(final int n) {
        this.mTransitionState = 0;
        this.mIsLayerOn[n] = true;
        this.invalidateSelf();
    }
    
    public void fadeOutLayer(final int n) {
        this.mTransitionState = 0;
        this.mIsLayerOn[n] = false;
        this.invalidateSelf();
    }
    
    public void finishTransitionImmediately() {
        this.mTransitionState = 2;
        for (int i = 0; i < this.mLayers.length; ++i) {
            final int[] mAlphas = this.mAlphas;
            int n;
            if (this.mIsLayerOn[i]) {
                n = 255;
            }
            else {
                n = 0;
            }
            mAlphas[i] = n;
        }
        this.invalidateSelf();
    }
    
    public int getAlpha() {
        return this.mAlpha;
    }
    
    protected long getCurrentTimeMs() {
        return SystemClock.uptimeMillis();
    }
    
    public void invalidateSelf() {
        if (this.mPreventInvalidateCount == 0) {
            super.invalidateSelf();
        }
    }
    
    @Override
    public void setAlpha(final int mAlpha) {
        if (this.mAlpha != mAlpha) {
            this.mAlpha = mAlpha;
            this.invalidateSelf();
        }
    }
    
    public void setTransitionDuration(final int mDurationMs) {
        this.mDurationMs = mDurationMs;
        if (this.mTransitionState == 1) {
            this.mTransitionState = 0;
        }
    }
}
