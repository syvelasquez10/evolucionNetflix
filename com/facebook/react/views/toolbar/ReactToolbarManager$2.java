// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.toolbar.events.ToolbarClickEvent;
import android.view.MenuItem;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;

class ReactToolbarManager$2 implements Toolbar$OnMenuItemClickListener
{
    final /* synthetic */ ReactToolbarManager this$0;
    final /* synthetic */ EventDispatcher val$mEventDispatcher;
    final /* synthetic */ ReactToolbar val$view;
    
    ReactToolbarManager$2(final ReactToolbarManager this$0, final EventDispatcher val$mEventDispatcher, final ReactToolbar val$view) {
        this.this$0 = this$0;
        this.val$mEventDispatcher = val$mEventDispatcher;
        this.val$view = val$view;
    }
    
    @Override
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.val$mEventDispatcher.dispatchEvent(new ToolbarClickEvent(this.val$view.getId(), menuItem.getOrder()));
        return true;
    }
}
