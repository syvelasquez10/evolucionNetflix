// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import com.facebook.drawee.view.DraweeHolder;

class ReactToolbar$2 extends ReactToolbar$IconControllerListener
{
    final /* synthetic */ ReactToolbar this$0;
    
    ReactToolbar$2(final ReactToolbar this$0, final DraweeHolder draweeHolder) {
        this.this$0 = this$0;
        super(this$0, draweeHolder);
    }
    
    @Override
    protected void setDrawable(final Drawable navigationIcon) {
        this.this$0.setNavigationIcon(navigationIcon);
    }
}
