// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import com.facebook.drawee.view.DraweeHolder;

class ReactToolbar$1 extends ReactToolbar$IconControllerListener
{
    final /* synthetic */ ReactToolbar this$0;
    
    ReactToolbar$1(final ReactToolbar this$0, final DraweeHolder draweeHolder) {
        this.this$0 = this$0;
        super(this$0, draweeHolder);
    }
    
    @Override
    protected void setDrawable(final Drawable logo) {
        this.this$0.setLogo(logo);
    }
}
