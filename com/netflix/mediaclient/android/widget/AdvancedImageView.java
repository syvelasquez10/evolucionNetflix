// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.drawable.Drawable$Callback;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.TargetApi;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.MotionEvent;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.makeramen.RoundedImageView;

public class AdvancedImageView extends RoundedImageView
{
    private static final String TAG = "AdvancedImageView";
    private int defaultForegroundResId;
    private Drawable foreground;
    protected PressedStateHandler pressedHandler;
    
    public AdvancedImageView(final Context context) {
        super(context);
        this.defaultForegroundResId = 2130838138;
        this.pressedHandler = null;
        this.init(null);
    }
    
    public AdvancedImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.defaultForegroundResId = 2130838138;
        this.pressedHandler = null;
        this.init(set);
    }
    
    public AdvancedImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.defaultForegroundResId = 2130838138;
        this.pressedHandler = null;
        this.init(set);
    }
    
    private void init(final AttributeSet set) {
        PressedStateHandler pressedHandler;
        if (BrowseExperience.showKidsExperience()) {
            pressedHandler = new ScalePressedStateHandler((View)this);
        }
        else {
            pressedHandler = new RipplePressedStateHandler(this);
        }
        this.pressedHandler = pressedHandler;
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.AdvancedImageView);
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            this.setForeground(drawable);
        }
        else {
            this.setForeground(this.getResources().getDrawable(this.defaultForegroundResId));
        }
        obtainStyledAttributes.recycle();
    }
    
    protected void dispatchSetPressed(final boolean b) {
        this.pressedHandler.handleSetPressed(b);
        super.dispatchSetPressed(b);
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.foreground != null) {
            this.foreground.draw(canvas);
        }
    }
    
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.foreground != null && this.foreground.isStateful()) {
            this.foreground.setState(this.getDrawableState());
        }
    }
    
    public Drawable getForegroundDrawable() {
        return this.foreground;
    }
    
    public AdvancedImageView$ImageLoaderInfo getImageLoaderInfo() {
        return (AdvancedImageView$ImageLoaderInfo)this.getTag(2131820561);
    }
    
    @Override
    protected String getLogTag() {
        return "AdvancedImageView";
    }
    
    public PressedStateHandler getPressedStateHandler() {
        return this.pressedHandler;
    }
    
    public boolean isImageLoaded() {
        return this.getImageLoaderInfo() != null && this.getImageLoaderInfo().loaded;
    }
    
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.foreground != null) {
            this.foreground.jumpToCurrentState();
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.foreground != null) {
            this.foreground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            this.invalidate();
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.foreground != null) {
            this.foreground.setBounds(0, 0, n, n2);
            this.invalidate();
        }
    }
    
    @TargetApi(21)
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        boolean b = true;
        boolean b2;
        if (AndroidUtils.getAndroidVersion() >= 21) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.foreground == null) {
            b = false;
        }
        if (b2 & b) {
            this.foreground.setHotspot(motionEvent.getX(), motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void refreshImageIfNecessary() {
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        if (imageLoader != null) {
            imageLoader.refreshImgIfNecessary(this, (IClientLogging$AssetType)null);
        }
    }
    
    public void setForeground(final Drawable foreground) {
        if (this.foreground == foreground) {
            return;
        }
        if (this.foreground != null) {
            this.foreground.setCallback((Drawable$Callback)null);
            this.unscheduleDrawable(this.foreground);
        }
        if ((this.foreground = foreground) != null) {
            foreground.setCallback((Drawable$Callback)this);
            if (foreground.isStateful()) {
                foreground.setState(this.getDrawableState());
            }
        }
        this.requestLayout();
        this.invalidate();
    }
    
    public void setForegroundResource(final int n) {
        this.setForeground(this.getContext().getResources().getDrawable(n));
    }
    
    public void setImageLoaderInfo(final AdvancedImageView$ImageLoaderInfo advancedImageView$ImageLoaderInfo) {
        this.setTag(2131820561, (Object)advancedImageView$ImageLoaderInfo);
    }
    
    public void setPressedStateHandlerEnabled(final boolean enabled) {
        this.pressedHandler.setEnabled(enabled);
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.foreground;
    }
}
