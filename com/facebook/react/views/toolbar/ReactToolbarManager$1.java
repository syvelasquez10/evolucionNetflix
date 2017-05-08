// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.toolbar.events.ToolbarClickEvent;
import android.view.View;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.view.View$OnClickListener;

class ReactToolbarManager$1 implements View$OnClickListener
{
    final /* synthetic */ ReactToolbarManager this$0;
    final /* synthetic */ EventDispatcher val$mEventDispatcher;
    final /* synthetic */ ReactToolbar val$view;
    
    ReactToolbarManager$1(final ReactToolbarManager this$0, final EventDispatcher val$mEventDispatcher, final ReactToolbar val$view) {
        this.this$0 = this$0;
        this.val$mEventDispatcher = val$mEventDispatcher;
        this.val$view = val$view;
    }
    
    public void onClick(final View view) {
        this.val$mEventDispatcher.dispatchEvent(new ToolbarClickEvent(this.val$view.getId(), -1));
    }
}
