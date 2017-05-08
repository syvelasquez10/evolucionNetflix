// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import com.facebook.drawee.view.DraweeHolder;
import android.view.MenuItem;

class ReactToolbar$ActionIconControllerListener extends ReactToolbar$IconControllerListener
{
    private final MenuItem mItem;
    final /* synthetic */ ReactToolbar this$0;
    
    ReactToolbar$ActionIconControllerListener(final ReactToolbar this$0, final MenuItem mItem, final DraweeHolder draweeHolder) {
        this.this$0 = this$0;
        super(this$0, draweeHolder);
        this.mItem = mItem;
    }
    
    @Override
    protected void setDrawable(final Drawable icon) {
        this.mItem.setIcon(icon);
    }
}
