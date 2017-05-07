// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

public class TextViewWithPressHandler extends TextView
{
    private PressedStateHandler pressedHandler;
    
    public TextViewWithPressHandler(final Context context) {
        super(context);
        this.init();
    }
    
    public TextViewWithPressHandler(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public TextViewWithPressHandler(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.pressedHandler = new PressedStateHandler((View)this);
    }
    
    protected void dispatchSetPressed(final boolean b) {
        this.pressedHandler.handleSetPressed(b);
        super.dispatchSetPressed(b);
    }
}
