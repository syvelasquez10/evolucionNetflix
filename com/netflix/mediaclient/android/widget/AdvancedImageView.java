// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.util.AttributeSet;
import android.content.Context;

public class AdvancedImageView extends LoggingImageView
{
    private static final String TAG = "AdvancedImageView";
    protected PressedStateHandler pressedHandler;
    
    public AdvancedImageView(final Context context) {
        super(context);
        this.init();
    }
    
    public AdvancedImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public AdvancedImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        PressedStateHandler pressedHandler;
        if (BrowseExperience.showKidsExperience()) {
            pressedHandler = new ScalePressedStateHandler((View)this);
        }
        else {
            pressedHandler = new AlphaPressedStateHandler((View)this);
        }
        this.pressedHandler = pressedHandler;
    }
    
    protected void dispatchSetPressed(final boolean b) {
        if (this.shouldDispatchToPressHandler()) {
            this.pressedHandler.handleSetPressed(b);
        }
        super.dispatchSetPressed(b);
    }
    
    public AdvancedImageView$ImageLoaderInfo getImageLoaderInfo() {
        return (AdvancedImageView$ImageLoaderInfo)this.getTag(2131623948);
    }
    
    @Override
    protected String getLogTag() {
        return "AdvancedImageView";
    }
    
    public PressedStateHandler getPressedStateHandler() {
        return this.pressedHandler;
    }
    
    public void refreshImageIfNecessary() {
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        if (imageLoader != null) {
            imageLoader.refreshImgIfNecessary(this, null);
        }
    }
    
    public void setImageLoaderInfo(final AdvancedImageView$ImageLoaderInfo advancedImageView$ImageLoaderInfo) {
        this.setTag(2131623948, (Object)advancedImageView$ImageLoaderInfo);
    }
    
    public void setPressedStateHandlerEnabled(final boolean enabled) {
        this.pressedHandler.setEnabled(enabled);
    }
    
    protected boolean shouldDispatchToPressHandler() {
        return true;
    }
}
