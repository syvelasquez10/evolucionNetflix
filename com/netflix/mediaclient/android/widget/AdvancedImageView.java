// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;

public class AdvancedImageView extends LoggingImageView
{
    private static final String TAG = "AdvancedImageView";
    private PressedStateHandler pressedHandler;
    
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
        this.pressedHandler = new PressedStateHandler((View)this);
    }
    
    protected void dispatchSetPressed(final boolean b) {
        if (this.shouldDispatchToPressHandler()) {
            this.pressedHandler.handleSetPressed(b);
        }
        super.dispatchSetPressed(b);
    }
    
    @Override
    protected String getLogTag() {
        return "AdvancedImageView";
    }
    
    public String getUrlTag() {
        return (String)this.getTag(2131165239);
    }
    
    public void refreshImageIfNecessary() {
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        if (imageLoader != null) {
            imageLoader.refreshImgIfNecessary(this, null);
        }
    }
    
    public void setPressedStateHandlerEnabled(final boolean enabled) {
        this.pressedHandler.setEnabled(enabled);
    }
    
    public void setUrlTag(final String s) {
        this.setTag(2131165239, (Object)s);
    }
    
    protected boolean shouldDispatchToPressHandler() {
        return true;
    }
}
