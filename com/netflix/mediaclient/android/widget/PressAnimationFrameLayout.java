// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

public class PressAnimationFrameLayout extends FrameLayout
{
    private static final String TAG = "FrameLayoutPressedHandler";
    protected PressedStateHandler pressedHandler;
    
    public PressAnimationFrameLayout(final Context context) {
        super(context);
        this.init();
    }
    
    public PressAnimationFrameLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public PressAnimationFrameLayout(final Context context, final AttributeSet set, final int n) {
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
    
    public PressedStateHandler getPressedStateHandler() {
        return this.pressedHandler;
    }
    
    public void setPressedStateHandlerEnabled(final boolean enabled) {
        this.pressedHandler.setEnabled(enabled);
    }
    
    protected boolean shouldDispatchToPressHandler() {
        return true;
    }
}
