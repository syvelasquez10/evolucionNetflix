// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.graphics.PorterDuff$Mode;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSeekBar;

class MediaRouteVolumeSlider extends AppCompatSeekBar
{
    private int mColor;
    private final float mDisabledAlpha;
    private boolean mHideThumb;
    private Drawable mThumb;
    
    public MediaRouteVolumeSlider(final Context context) {
        this(context, null);
    }
    
    public MediaRouteVolumeSlider(final Context context, final AttributeSet set) {
        this(context, set, R$attr.seekBarStyle);
    }
    
    public MediaRouteVolumeSlider(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
    }
    
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int n;
        if (this.isEnabled()) {
            n = 255;
        }
        else {
            n = (int)(255.0f * this.mDisabledAlpha);
        }
        this.mThumb.setColorFilter(this.mColor, PorterDuff$Mode.SRC_IN);
        this.mThumb.setAlpha(n);
        this.getProgressDrawable().setColorFilter(this.mColor, PorterDuff$Mode.SRC_IN);
        this.getProgressDrawable().setAlpha(n);
    }
    
    public void setThumb(Drawable mThumb) {
        this.mThumb = mThumb;
        if (this.mHideThumb) {
            mThumb = null;
        }
        else {
            mThumb = this.mThumb;
        }
        super.setThumb(mThumb);
    }
}
